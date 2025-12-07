package com.ingsoftw.EmpresaPedidos.application.usecases;

import com.ingsoftw.EmpresaPedidos.application.ports.input.PedidoServicePort;
import com.ingsoftw.EmpresaPedidos.application.ports.output.PedidoRepositoryPort;
import com.ingsoftw.EmpresaPedidos.domain.model.EstadoPedido;
import com.ingsoftw.EmpresaPedidos.domain.model.Pedido;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService implements PedidoServicePort {

    private final PedidoRepositoryPort pedidoRepositoryPort;

    @Override
    public Pedido crearPedido(Pedido pedido) {
        pedido.setFechaCreacion(LocalDateTime.now());
        if (pedido.getEstado() == null) {
            pedido.setEstado(EstadoPedido.PENDIENTE);
        }
        return pedidoRepositoryPort.save(pedido);
    }

    @Override
    public Pedido actualizarPedido(Long id, Pedido pedidoActualizado) {
        Pedido pedidoExistente = obtenerPedido(id);

        pedidoExistente.setDescripcion(pedidoActualizado.getDescripcion());
        pedidoExistente.setEstado(pedidoActualizado.getEstado());

        return pedidoRepositoryPort.save(pedidoExistente);
    }

    @Override
    public Pedido obtenerPedido(Long id) {
        return pedidoRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + id));
    }

    @Override
    public List<Pedido> listarPedidos() {
        return pedidoRepositoryPort.findAll();
    }

    @Override
    public void eliminarPedido(Long id) {
        pedidoRepositoryPort.deleteById(id);
    }
}