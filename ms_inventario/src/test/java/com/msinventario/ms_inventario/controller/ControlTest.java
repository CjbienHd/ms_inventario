package com.msinventario.ms_inventario.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msinventario.ms_inventario.assembler.ProductoAssembler;
import com.msinventario.ms_inventario.dto.*;
import com.msinventario.ms_inventario.model.Producto;
import com.msinventario.ms_inventario.service.InventarioService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(Control.class)
public class ControlTest{
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private InventarioService inventarioService;
      
    @MockitoBean
    private ProductoAssembler productoAssembler;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void obtenerProductos_deberiaRetornar200() throws Exception {
        Producto p = new Producto();
        p.setId(1L);
        EntityModel<Producto> model = EntityModel.of(p);

        when(inventarioService.obtenerTodos()).thenReturn(Collections.singletonList(p));
        when(productoAssembler.toModel(p)).thenReturn(model);

        mockMvc.perform(get("/inventario/obtener"))
                .andExpect(status().isOk());
    }

        @Test
        void obtenerPorId_deberiaRetornar200() throws Exception {
        Producto p = new Producto();
        p.setId(1L);
        when(inventarioService.obtenerPorId(1L)).thenReturn(p);
        when(productoAssembler.toModel(p)).thenReturn(EntityModel.of(p));

        mockMvc.perform(get("/inventario/producto/1"))
                .andExpect(status().isOk());
    }


    @Test
    void crearProducto_deberiaRetornar201() throws Exception {
        Producto producto = new Producto();
        producto.setId(1L);

        when(inventarioService.creaProducto(any())).thenReturn(producto);
        when(productoAssembler.toModel(any())).thenReturn(EntityModel.of(producto));

        mockMvc.perform(post("/inventario/crearProducto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isCreated());
    }

    @Test
    void crearProducto_deberiaRetornar400() throws Exception {
        Producto producto = new Producto();
        when(inventarioService.creaProducto(any())).thenThrow(new RuntimeException("Error"));

        mockMvc.perform(post("/inventario/crearProducto")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(producto)))
            .andExpect(status().isBadRequest());
    }

    @Test
    void actualizarNombre_deberiaRetornar200() throws Exception {
        Producto producto = new Producto();
        producto.setId(1L);
        ActualizarNombreDTO dto = new ActualizarNombreDTO();
        dto.setNombre("Nuevo");

        when(inventarioService.actualizarNombre(eq(1L), any())).thenReturn(producto);
        when(productoAssembler.toModel(any())).thenReturn(EntityModel.of(producto));

        mockMvc.perform(put("/inventario/nombre/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    void actualizarNombre_conError_deberiaRetornar400() throws Exception {
        ActualizarNombreDTO dto = new ActualizarNombreDTO();
        dto.setNombre("ErrorNombre");

        when(inventarioService.actualizarNombre(eq(1L), any())).thenThrow(new RuntimeException("Error"));

        mockMvc.perform(put("/inventario/nombre/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void actualizarCategoria_deberiaRetornar200() throws Exception {
        ActualizarCategoriaDTO dto = new ActualizarCategoriaDTO();
        dto.setCategoria("Nueva categoría");

        Producto mockProducto = new Producto();
        when(inventarioService.actualizarCategoria(eq(1L), any())).thenReturn(mockProducto);

        mockMvc.perform(put("/inventario/categoria/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    void actualizarCategoria_conError_deberiaRetornar400() throws Exception {
        ActualizarCategoriaDTO dto = new ActualizarCategoriaDTO();
        dto.setCategoria("x");

        when(inventarioService.actualizarCategoria(eq(1L), any())).thenThrow(new RuntimeException("Error"));

        mockMvc.perform(put("/inventario/categoria/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void eliminarProducto_deberiaRetornar204() throws Exception {
        doNothing().when(inventarioService).eliminarProducto(1L);

        mockMvc.perform(delete("/inventario/eliminar/1"))
                .andExpect(status().isOk());
    }


    @Test
    void actualizarCantidad_deberiaRetornar200() throws Exception {
        Producto producto = new Producto();
        producto.setId(1L);
        ActualizarCantidadDTO dto = new ActualizarCantidadDTO();
        dto.setCantidad(99);

        when(inventarioService.actualizarCantidad(eq(1L), any())).thenReturn(producto);
        when(productoAssembler.toModel(any())).thenReturn(EntityModel.of(producto));

        mockMvc.perform(put("/inventario/cantidad/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    void actualizarCantidad_conError_deberiaRetornar400() throws Exception {
        ActualizarCantidadDTO dto = new ActualizarCantidadDTO();
        dto.setCantidad(99);

        when(inventarioService.actualizarCantidad(eq(1L), any())).thenThrow(new RuntimeException("Error"));

        mockMvc.perform(put("/inventario/cantidad/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void actualizarOrigen_deberiaRetornar200() throws Exception {
        ActualizarOrigenDTO dto = new ActualizarOrigenDTO();
        dto.setOrigen("Chile");

        Producto mockProducto = new Producto();
        when(inventarioService.actualizarOrigen(eq(1L), any())).thenReturn(mockProducto);

        mockMvc.perform(put("/inventario/origen/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

     @Test
    void actualizarOrigen_conError_deberiaRetornar400() throws Exception {
        ActualizarOrigenDTO dto = new ActualizarOrigenDTO();
        dto.setOrigen("ErrorOrigen");

        when(inventarioService.actualizarOrigen(eq(1L), any())).thenThrow(new RuntimeException("Error"));

        mockMvc.perform(put("/inventario/origen/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void actualizarMaterial_deberiaRetornar200() throws Exception {
        ActualizarMaterialDTO dto = new ActualizarMaterialDTO();
        dto.setMaterialPrincipal("Metal");

        Producto mockProducto = new Producto();
        when(inventarioService.actualizarMaterial(eq(1L), any())).thenReturn(mockProducto);

        mockMvc.perform(put("/inventario/material/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    void actualizarMaterial_conError_deberiaRetornar400() throws Exception {
        ActualizarMaterialDTO dto = new ActualizarMaterialDTO();
        dto.setMaterialPrincipal("ErrorMaterial");

        when(inventarioService.actualizarMaterial(eq(1L), any())).thenThrow(new RuntimeException("Error"));

        mockMvc.perform(put("/inventario/material/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void actualizarPrecio_deberiaRetornar200() throws Exception {
        ActualizarPrecioDTO dto = new ActualizarPrecioDTO();
        dto.setPrecio(1500);

        Producto mockProducto = new Producto();
        when(inventarioService.actualizarPrecio(eq(1L), any())).thenReturn(mockProducto);

        mockMvc.perform(put("/inventario/precio/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    void actualizarPrecio_conError_deberiaRetornar400() throws Exception {
        ActualizarPrecioDTO dto = new ActualizarPrecioDTO();
        dto.setPrecio(1234);

        when(inventarioService.actualizarPrecio(eq(1L), any())).thenThrow(new RuntimeException("Error"));

        mockMvc.perform(put("/inventario/precio/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void actualizarReutilizacion_deberiaRetornar200() throws Exception {
        ActualizarReutilizacionDTO dto = new ActualizarReutilizacionDTO();
        dto.setReutilizacion(true);

        Producto mockProducto = new Producto();
        when(inventarioService.actualizarReutilizabilidad(eq(1L), any())).thenReturn(mockProducto);

        mockMvc.perform(put("/inventario/reutilizacion/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    void actualizarReutilizacion_conError_deberiaRetornar400() throws Exception {
        ActualizarReutilizacionDTO dto = new ActualizarReutilizacionDTO();
        dto.setReutilizacion(false);

        when(inventarioService.actualizarReutilizabilidad(eq(1L), any())).thenThrow(new RuntimeException("Error"));

        mockMvc.perform(put("/inventario/reutilizacion/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }


    @Test
    void actualizarVidaUtil_deberiaRetornar200() throws Exception {
        ActualizadVidaDTO dto = new ActualizadVidaDTO();
        dto.setVidaUtil(24);

        Producto mockProducto = new Producto();
        when(inventarioService.actualizarVidaUtill(eq(1L), any())).thenReturn(mockProducto);

        mockMvc.perform(put("/inventario/vidautil/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    void actualizarVidaUtil_conError_deberiaRetornar400() throws Exception {
        ActualizadVidaDTO dto = new ActualizadVidaDTO();
        dto.setVidaUtil(0);

        when(inventarioService.actualizarVidaUtill(eq(1L), any())).thenThrow(new RuntimeException("Error"));

        mockMvc.perform(put("/inventario/vidautil/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }


}
