package com.msinventario.ms_inventario.controller;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.msinventario.ms_inventario.service.InventarioService;
import com.msinventario.ms_inventario.dto.ActualizadVidaDTO;
import com.msinventario.ms_inventario.dto.ActualizarCantidadDTO;
import com.msinventario.ms_inventario.dto.ActualizarCategoriaDTO;
import com.msinventario.ms_inventario.dto.ActualizarMaterialDTO;
import com.msinventario.ms_inventario.dto.ActualizarNombreDTO;
import com.msinventario.ms_inventario.dto.ActualizarOrigenDTO;
import com.msinventario.ms_inventario.dto.ActualizarPrecioDTO;
import com.msinventario.ms_inventario.dto.ActualizarReutilizacionDTO;
import com.msinventario.ms_inventario.model.Producto;




@RestController
@RequiredArgsConstructor
@RequestMapping("/inventario")
public class Control {

    private final InventarioService inventarioService;


    @GetMapping
    public String status() {
        return "microservicio inventario funcionando correctamente";
    }

    @GetMapping("/obtener")
    public List<Producto> obtenerProductos() {
        return inventarioService.obtenerTodos();
    }
    


    // Actualizar cantidad de un producto
    @PutMapping("cantidad/{id}")
    public ResponseEntity<Producto> actualizarCantidad(@PathVariable Long id, @RequestBody ActualizarCantidadDTO dto) {
        return inventarioService.actualizarCantidad(id, dto);
    }

    // Actualizar precio de un producto
    @PutMapping("precio/{id}")
    public ResponseEntity<Producto> actualizarPrecio(@PathVariable Long id, @RequestBody ActualizarPrecioDTO preciodto) {
        return inventarioService.actualizarPrecio(id, preciodto);
    }

        
    // Actualizar nombre de un producto
    @PutMapping("nombre/{id}")
    public ResponseEntity<Producto> actualizarNombre(@PathVariable Long id, @RequestBody ActualizarNombreDTO nombredto) {
        return inventarioService.actualizarNombre(id, nombredto);
    }


        
    // Actualizar origen de un producto
    @PutMapping("origen/{id}")
    public ResponseEntity<Producto> actualizarOrigen(@PathVariable Long id, @RequestBody ActualizarOrigenDTO origendto) {
        return inventarioService.actualizarOrigen(id, origendto);
    }

        
    // Actualizar material principal de un producto
    @PutMapping("material/{id}")
    public ResponseEntity<Producto> actualizarMaterial(@PathVariable Long id, @RequestBody ActualizarMaterialDTO materialdto) {
        return inventarioService.actualizarMaterial(id, materialdto);
    }


        
    // Actualizar reutilizacion de un producto
    @PutMapping("reutilizacion/{id}")
    public ResponseEntity<Producto> actualizarReutilizacion(@PathVariable Long id, @RequestBody ActualizarReutilizacionDTO reutidto) {
        return inventarioService.actualizarReutilizabilidad(id, reutidto);
    }


        
    // Actualizar vida util de un producto
    @PutMapping("vidautil/{id}")
    public ResponseEntity<Producto> actualizarVidaUtil(@PathVariable Long id, @RequestBody ActualizadVidaDTO vidadto) {
        return inventarioService.actualizarVidaUtill(id, vidadto);
    }


    // Actualizar categoria de un producto
    @PutMapping("categoria/{id}")
    public ResponseEntity<Producto> actualizarCategoria(@PathVariable Long id, @RequestBody ActualizarCategoriaDTO categoriadto) {
        return inventarioService.actualizarCategoria(id, categoriadto);
    }


    // Crear un nuevo producto
    @PostMapping("/crearProducto")
    public Producto crearProducto(@RequestBody Producto prod) {
        return inventarioService.creaProducto(prod);
    }

    //Eliminar producto
    @DeleteMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id){
        return inventarioService.eliminarProducto(id);
    }
    
    
}

    


