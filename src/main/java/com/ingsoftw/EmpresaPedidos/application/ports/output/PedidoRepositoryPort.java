package com.ingsoftw.EmpresaPedidos.application.ports.output;

import com.ingsoftw.EmpresaPedidos.domain.model.Pedido;
import java.util.List;
import java.util.Optional;

public interface PedidoRepositoryPort {
    Pedido save(Pedido pedido);
    Optional<Pedido> findById(Long id);
    List<Pedido> findAll();
    void deleteById(Long id);
}