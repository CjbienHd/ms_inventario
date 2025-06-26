package com.msinventario.ms_inventario.controller;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
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



@RestControllerAdvice
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
    public ResponseEntity<List<Producto>> obtenerProductos() {
        return ResponseEntity.ok().body(inventarioService.obtenerTodos());
    }
    


    // Actualizar cantidad de un producto
    @PutMapping("cantidad/{id}")
    public ResponseEntity<Producto> actualizarCantidad(@PathVariable Long id, @RequestBody ActualizarCantidadDTO dto) {
        try{
            if (dto.getCantidad() < 0) throw new IllegalArgumentException("Cantidad negativa");
            return ResponseEntity.ok().body(inventarioService.actualizarCantidad(id, dto));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(new Producto());
        }
    }

    // Actualizar precio de un producto
    @PutMapping("precio/{id}")
    public ResponseEntity<Producto> actualizarPrecio(@PathVariable Long id, @RequestBody ActualizarPrecioDTO preciodto) {
        try{
            if (preciodto.getPrecio() < 0) throw new IllegalArgumentException("Cantidad negativa");
            return ResponseEntity.ok().body(inventarioService.actualizarPrecio(id, preciodto));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(new Producto());
        }
    
    
    }

        
    // Actualizar nombre de un producto
    @PutMapping("nombre/{id}")
    public ResponseEntity<Producto> actualizarNombre(@PathVariable Long id, @RequestBody ActualizarNombreDTO nombredto) {
        try{
            return ResponseEntity.ok().body(inventarioService.actualizarNombre(id, nombredto));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(new Producto());
        }
    }


        
    // Actualizar origen de un producto
    @PutMapping("origen/{id}")
    public ResponseEntity<Producto> actualizarOrigen(@PathVariable Long id, @RequestBody ActualizarOrigenDTO origendto) {
        try{
            return ResponseEntity.ok().body(inventarioService.actualizarOrigen(id, origendto));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(new Producto());
        }
    }

        
    // Actualizar material principal de un producto
    @PutMapping("material/{id}")
    public ResponseEntity<Producto> actualizarMaterial(@PathVariable Long id, @RequestBody ActualizarMaterialDTO materialdto) {
        try{
            return ResponseEntity.ok().body(inventarioService.actualizarMaterial(id, materialdto));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(new Producto());
        }
    }


        
    // Actualizar reutilizacion de un producto
    @PutMapping("reutilizacion/{id}")
    public ResponseEntity<Producto> actualizarReutilizacion(@PathVariable Long id, @RequestBody ActualizarReutilizacionDTO reutidto) {
        try{
            return ResponseEntity.ok().body(inventarioService.actualizarReutilizabilidad(id, reutidto));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(new Producto());
        }
    }


        
    // Actualizar vida util de un producto
    @PutMapping("vidautil/{id}")
    public ResponseEntity<Producto> actualizarVidaUtil(@PathVariable Long id, @RequestBody ActualizadVidaDTO vidadto) {
        try{
        return ResponseEntity.ok().body(inventarioService.actualizarVidaUtill(id, vidadto));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(new Producto());
        }
    }


    // Actualizar categoria de un producto
    @PutMapping("categoria/{id}")
    public ResponseEntity<Producto> actualizarCategoria(@PathVariable Long id, @RequestBody ActualizarCategoriaDTO categoriadto) {
        try{
        return ResponseEntity.ok().body(inventarioService.actualizarCategoria(id, categoriadto));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(new Producto());
        }
    }


    // Crear un nuevo producto
    @PostMapping("/crearProducto")
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto prod) {
        try{
        return ResponseEntity.ok().body(inventarioService.creaProducto(prod));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(new Producto());
        }
    }

    //Eliminar producto
    @DeleteMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id){
        return inventarioService.eliminarProducto(id);
    }
    
    
}

    


