package com.ingsoftw.EmpresaPedidos.application.ports.input;

import com.ingsoftw.EmpresaPedidos.domain.model.Pedido;
import java.util.List;

public interface PedidoServicePort {
    Pedido crearPedido(Pedido pedido);
    Pedido actualizarPedido(Long id, Pedido pedido);
    Pedido obtenerPedido(Long id);
    List<Pedido> listarPedidos();
    void eliminarPedido(Long id);
}