package com.ingsoftw.EmpresaPedidos.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    private Long id;
    private String descripcion;
    private EstadoPedido estado;
    private LocalDateTime fechaCreacion;
    private Long usuarioId; // Referencia al usuario que creó el pedido
}