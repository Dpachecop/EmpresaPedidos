package com.ingsoftw.EmpresaPedidos.application.usecases;

import com.ingsoftw.EmpresaPedidos.application.ports.output.PedidoRepositoryPort;
import com.ingsoftw.EmpresaPedidos.domain.model.EstadoPedido;
import com.ingsoftw.EmpresaPedidos.domain.model.Pedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

// @ExtendWith le dice a JUnit que use Mockito para manejar las simulaciones
@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    // 1. @Mock: Crea un repositorio falso. No conecta a MySQL real.
    @Mock
    private PedidoRepositoryPort pedidoRepositoryPort;

    // 2. @InjectMocks: Crea una instancia real de PedidoService e inyecta el mock anterior dentro de ella.
    @InjectMocks
    private PedidoService pedidoService;

    private Pedido pedidoTest;

    @BeforeEach
    void setUp() {
        // Preparamos un pedido de prueba antes de cada test
        pedidoTest = Pedido.builder()
                .id(1L)
                .descripcion("Carga de Celulares")
                .usuarioId(10L)
                .build();
    }

    @Test
    void crearPedido_DeberiaAsignarEstadoPendienteYGuardar() {
        // ARRANGE (Preparar): Le decimos al mock: "Cuando te llamen a guardar cualquier cosa, devuelve el pedidoTest"
        when(pedidoRepositoryPort.save(any(Pedido.class))).thenReturn(pedidoTest);

        // ACT (Actuar): Llamamos al método real que queremos probar
        Pedido resultado = pedidoService.crearPedido(pedidoTest);

        // ASSERT (Verificar): Comprobamos que la lógica funcionó
        assertNotNull(resultado);
        assertEquals(EstadoPedido.PENDIENTE, resultado.getEstado()); // ¿El servicio asignó PENDIENTE automáticamente?
        verify(pedidoRepositoryPort, times(1)).save(any(Pedido.class)); // ¿Se llamó al repositorio 1 vez?
    }

    @Test
    void obtenerPedido_SiExiste_DeberiaRetornarlo() {
        // Simulamos que el repositorio encuentra el pedido con ID 1
        when(pedidoRepositoryPort.findById(1L)).thenReturn(Optional.of(pedidoTest));

        Pedido resultado = pedidoService.obtenerPedido(1L);

        assertEquals("Carga de Celulares", resultado.getDescripcion());
    }
}