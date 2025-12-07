package com.ingsoftw.EmpresaPedidos.infrastructure.adapters.output.persistence.adapter;

import com.ingsoftw.EmpresaPedidos.application.ports.output.PedidoRepositoryPort;
import com.ingsoftw.EmpresaPedidos.domain.model.Pedido;
import com.ingsoftw.EmpresaPedidos.infrastructure.adapters.output.persistence.mapper.PedidoPersistenceMapper;
import com.ingsoftw.EmpresaPedidos.infrastructure.adapters.output.persistence.repository.JpaPedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PedidoRepositoryAdapter implements PedidoRepositoryPort {

    private final JpaPedidoRepository jpaPedidoRepository;
    private final PedidoPersistenceMapper mapper;

    @Override
    public Pedido save(Pedido pedido) {
        var entity = mapper.toEntity(pedido);
        var savedEntity = jpaPedidoRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Pedido> findById(Long id) {
        return jpaPedidoRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Pedido> findAll() {
        return jpaPedidoRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        jpaPedidoRepository.deleteById(id);
    }
}