package com.ingsoftw.EmpresaPedidos.infrastructure.adapters.output.persistence.mapper;

import com.ingsoftw.EmpresaPedidos.domain.model.EstadoPedido;
import com.ingsoftw.EmpresaPedidos.domain.model.Pedido;
import com.ingsoftw.EmpresaPedidos.infrastructure.adapters.output.persistence.entity.PedidoEntity;
import org.springframework.stereotype.Component;

@Component
public class PedidoPersistenceMapper {

    public PedidoEntity toEntity(Pedido pedido) {
        return PedidoEntity.builder()
                .id(pedido.getId())
                .descripcion(pedido.getDescripcion())
                .estado(pedido.getEstado().name()) // Enum a String
                .fechaCreacion(pedido.getFechaCreacion())
                .usuarioId(pedido.getUsuarioId())
                .build();
    }

    public Pedido toDomain(PedidoEntity entity) {
        return Pedido.builder()
                .id(entity.getId())
                .descripcion(entity.getDescripcion())
                .estado(EstadoPedido.valueOf(entity.getEstado())) // String a Enum
                .fechaCreacion(entity.getFechaCreacion())
                .usuarioId(entity.getUsuarioId())
                .build();
    }
}