package com.ingsoftw.EmpresaPedidos.infrastructure.adapters.output.persistence.mapper;

import com.ingsoftw.EmpresaPedidos.domain.model.Usuario;
import com.ingsoftw.EmpresaPedidos.infrastructure.adapters.output.persistence.entity.UsuarioEntity;
import org.springframework.stereotype.Component;

@Component
public class UsuarioPersistenceMapper {

    public UsuarioEntity toEntity(Usuario usuario) {
        return UsuarioEntity.builder()
                .id(usuario.getId())
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .rol(usuario.getRol())
                .build();
    }

    public Usuario toDomain(UsuarioEntity entity) {
        return Usuario.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .rol(entity.getRol())
                .build();
    }
}