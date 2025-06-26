package com.msinventario.ms_inventario.service;

import com.msinventario.ms_inventario.dto.*;
import com.msinventario.ms_inventario.model.Producto;
import com.msinventario.ms_inventario.repository.RepositoryInventario;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class InventarioServiceTest {

    @Mock
    private RepositoryInventario repositoryInventario;

    @InjectMocks
    private InventarioService inventarioService;

    @Test
    void actualizarNombre_productoExiste_devuelveActualizado() {
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombreProd("Antiguo");

        when(repositoryInventario.findById(1L)).thenReturn(Optional.of(producto));
        when(repositoryInventario.save(any())).thenReturn(producto);

        ActualizarNombreDTO dto = new ActualizarNombreDTO();
        dto.setNombre("Nuevo");

        Producto result = inventarioService.actualizarNombre(1L, dto);
        assertEquals("Nuevo", result.getNombreProd());
    }

    @Test
    void actualizarNombre_productoNoExiste_devuelveVacio() {
        when(repositoryInventario.findById(1L)).thenReturn(Optional.empty());

        ActualizarNombreDTO dto = new ActualizarNombreDTO();
        dto.setNombre("Nuevo");

        Producto result = inventarioService.actualizarNombre(1L, dto);
        assertNull(result.getId());
        assertNull(result.getNombreProd());
    }

    @Test
    void actualizarCategoria_productoExiste() {
        Producto producto = new Producto();
        producto.setId(1L);

        when(repositoryInventario.findById(1L)).thenReturn(Optional.of(producto));
        when(repositoryInventario.save(any())).thenReturn(producto);

        ActualizarCategoriaDTO dto = new ActualizarCategoriaDTO();
        dto.setCategoria("nueva");

        Producto result = inventarioService.actualizarCategoria(1L, dto);
        assertEquals("nueva", result.getCategoria());
    }

    @Test
    void actualizarCategoria_productoNoExiste_devuelveVacio() {
        when(repositoryInventario.findById(1L)).thenReturn(Optional.empty());
        ActualizarCategoriaDTO dto = new ActualizarCategoriaDTO();
        dto.setCategoria("categoria");
        Producto result = inventarioService.actualizarCategoria(1L, dto);
        assertNull(result.getId());
    }

    @Test
    void actualizarCantidad_productoExiste() {
        Producto producto = new Producto();
        producto.setId(1L);

        when(repositoryInventario.findById(1L)).thenReturn(Optional.of(producto));
        when(repositoryInventario.save(any())).thenReturn(producto);

        ActualizarCantidadDTO dto = new ActualizarCantidadDTO();
        dto.setCantidad(10);

        Producto result = inventarioService.actualizarCantidad(1L, dto);
        assertEquals(10, result.getCantidad());
    }

        @Test
    void actualizarCantidad_productoNoExiste_devuelveVacio() {
        when(repositoryInventario.findById(1L)).thenReturn(Optional.empty());
        ActualizarCantidadDTO dto = new ActualizarCantidadDTO();
        dto.setCantidad(10);
        Producto result = inventarioService.actualizarCantidad(1L, dto);
        assertNull(result.getId());
    }


    @Test
    void actualizarOrigen_productoExiste() {
        Producto producto = new Producto();
        producto.setId(1L);

        when(repositoryInventario.findById(1L)).thenReturn(Optional.of(producto));
        when(repositoryInventario.save(any())).thenReturn(producto);

        ActualizarOrigenDTO dto = new ActualizarOrigenDTO();
        dto.setOrigen("Chile");

        Producto result = inventarioService.actualizarOrigen(1L, dto);
        assertEquals("Chile", result.getOrigenProd());
    }

    @Test
    void actualizarOrigen_productoNoExiste_devuelveVacio() {
        when(repositoryInventario.findById(1L)).thenReturn(Optional.empty());
        ActualizarOrigenDTO dto = new ActualizarOrigenDTO();
        dto.setOrigen("Chile");
        Producto result = inventarioService.actualizarOrigen(1L, dto);
        assertNull(result.getId());
    }

    @Test
    void actualizarPrecio_productoExiste() {
        Producto producto = new Producto();
        producto.setId(1L);

        when(repositoryInventario.findById(1L)).thenReturn(Optional.of(producto));
        when(repositoryInventario.save(any())).thenReturn(producto);

        ActualizarPrecioDTO dto = new ActualizarPrecioDTO();
        dto.setPrecio(2000);

        Producto result = inventarioService.actualizarPrecio(1L, dto);
        assertEquals(2000, result.getPrecio());
    }

        @Test
    void actualizarPrecio_productoNoExiste_devuelveVacio() {
        when(repositoryInventario.findById(1L)).thenReturn(Optional.empty());
        ActualizarPrecioDTO dto = new ActualizarPrecioDTO();
        dto.setPrecio(2000);
        Producto result = inventarioService.actualizarPrecio(1L, dto);
        assertNull(result.getId());
    }

    @Test
    void actualizarReutilizacion_productoExiste() {
        Producto producto = new Producto();
        producto.setId(1L);

        when(repositoryInventario.findById(1L)).thenReturn(Optional.of(producto));
        when(repositoryInventario.save(any())).thenReturn(producto);

        ActualizarReutilizacionDTO dto = new ActualizarReutilizacionDTO();
        dto.setReutilizacion(true);

        Producto result = inventarioService.actualizarReutilizabilidad(1L, dto);
        assertTrue(result.isReutilizable());
    }

    @Test
    void actualizarReutilizacion_productoNoExiste_devuelveVacio() {
        when(repositoryInventario.findById(1L)).thenReturn(Optional.empty());
        ActualizarReutilizacionDTO dto = new ActualizarReutilizacionDTO();
        dto.setReutilizacion(true);
        Producto result = inventarioService.actualizarReutilizabilidad(1L, dto);
        assertNull(result.getId());
    }

    @Test
    void actualizarVidaUtil_productoExiste() {
        Producto producto = new Producto();
        producto.setId(1L);

        when(repositoryInventario.findById(1L)).thenReturn(Optional.of(producto));
        when(repositoryInventario.save(any())).thenReturn(producto);

        ActualizadVidaDTO dto = new ActualizadVidaDTO();
        dto.setVidaUtil(12);

        Producto result = inventarioService.actualizarVidaUtill(1L, dto);
        assertEquals(12, result.getVidaUtilMeses());
    }

    @Test
    void actualizarVidaUtil_productoNoExiste_devuelveVacio() {
        when(repositoryInventario.findById(1L)).thenReturn(Optional.empty());
        ActualizadVidaDTO dto = new ActualizadVidaDTO();
        dto.setVidaUtil(12);
        Producto result = inventarioService.actualizarVidaUtill(1L, dto);
        assertNull(result.getId());
    }

    @Test
    void actualizarMaterial_productoExiste() {
        Producto producto = new Producto();
        producto.setId(1L);

        when(repositoryInventario.findById(1L)).thenReturn(Optional.of(producto));
        when(repositoryInventario.save(any())).thenReturn(producto);

        ActualizarMaterialDTO dto = new ActualizarMaterialDTO();
        dto.setMaterialPrincipal("nuevo material");

        Producto result = inventarioService.actualizarMaterial(1L, dto);
        assertEquals("nuevo material", result.getMaterialPrincipal());
    }

    @Test
    void actualizarMaterial_productoNoExiste_devuelveVacio() {
        when(repositoryInventario.findById(1L)).thenReturn(Optional.empty());
        ActualizarMaterialDTO dto = new ActualizarMaterialDTO();
        dto.setMaterialPrincipal("nuevo");
        Producto result = inventarioService.actualizarMaterial(1L, dto);
        assertNull(result.getId());
    }

    @Test
    void creaProducto_deberiaGuardarYRetornarProducto() {
        Producto prod = new Producto();
        prod.setNombreProd("Nuevo");

        when(repositoryInventario.save(any())).thenReturn(prod);

        Producto result = inventarioService.creaProducto(prod);

        assertEquals("Nuevo", result.getNombreProd());
        verify(repositoryInventario, times(1)).save(prod);
    }

    @Test
    void eliminarProducto_productoExiste_deberiaEliminar() {
        Producto producto = new Producto();
        producto.setId(1L);

        when(repositoryInventario.findById(1L)).thenReturn(Optional.of(producto));
        doNothing().when(repositoryInventario).deleteById(1L);

        inventarioService.eliminarProducto(1L);

        verify(repositoryInventario, times(1)).deleteById(1L);
    }

    @Test
    void eliminarProducto_productoNoExiste_noDebeEliminar() {
        when(repositoryInventario.findById(1L)).thenReturn(Optional.empty());

        inventarioService.eliminarProducto(1L);

        verify(repositoryInventario, never()).deleteById(anyLong());
    }
}
