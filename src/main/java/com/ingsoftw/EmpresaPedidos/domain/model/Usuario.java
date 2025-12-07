package com.ingsoftw.EmpresaPedidos.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    private Long id;
    private String username;
    private String password;
    private String rol; // EJ: "ADMIN", "USER"
}