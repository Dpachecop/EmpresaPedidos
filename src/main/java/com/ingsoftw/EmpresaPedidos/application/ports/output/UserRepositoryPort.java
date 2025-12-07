package com.ingsoftw.EmpresaPedidos.application.ports.output;

import com.ingsoftw.EmpresaPedidos.domain.model.Usuario;
import java.util.Optional;

public interface UserRepositoryPort {
    Usuario save(Usuario usuario);
    Optional<Usuario> findByUsername(String username);
}