package com.ingsoftw.EmpresaPedidos.infrastructure.adapters.output.persistence.repository;

import com.ingsoftw.EmpresaPedidos.infrastructure.adapters.output.persistence.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPedidoRepository extends JpaRepository<PedidoEntity, Long> {
}