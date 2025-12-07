package com.ingsoftw.EmpresaPedidos.infrastructure.adapters.output.persistence.adapter;

import com.ingsoftw.EmpresaPedidos.application.ports.output.UserRepositoryPort;
import com.ingsoftw.EmpresaPedidos.domain.model.Usuario;
import com.ingsoftw.EmpresaPedidos.infrastructure.adapters.output.persistence.mapper.UsuarioPersistenceMapper;
import com.ingsoftw.EmpresaPedidos.infrastructure.adapters.output.persistence.repository.JpaUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UsuarioRepositoryAdapter implements UserRepositoryPort {

    private final JpaUsuarioRepository jpaUsuarioRepository;
    private final UsuarioPersistenceMapper mapper;

    @Override
    public Usuario save(Usuario usuario) {
        var entity = mapper.toEntity(usuario);
        var saved = jpaUsuarioRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Usuario> findByUsername(String username) {
        return jpaUsuarioRepository.findByUsername(username).map(mapper::toDomain);
    }
}