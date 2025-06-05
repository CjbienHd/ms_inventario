package com.msinventario.ms_inventario.service;
import com.msinventario.ms_inventario.dto.ActualizadVidaDTO;
import com.msinventario.ms_inventario.dto.ActualizarCantidadDTO;
import com.msinventario.ms_inventario.dto.ActualizarCategoriaDTO;
import com.msinventario.ms_inventario.dto.ActualizarMaterialDTO;
import com.msinventario.ms_inventario.dto.ActualizarNombreDTO;
import com.msinventario.ms_inventario.dto.ActualizarOrigenDTO;
import com.msinventario.ms_inventario.dto.ActualizarPrecioDTO;
import com.msinventario.ms_inventario.dto.ActualizarReutilizacionDTO;
import com.msinventario.ms_inventario.model.Producto;
import com.msinventario.ms_inventario.repository.RepositoryInventario;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



@Service
public class InventarioService {

    private final RepositoryInventario inventarioRepo;
    
    /** Constructor del servicio de inventario */
    public InventarioService(RepositoryInventario inventarioRepo) {
        this.inventarioRepo = inventarioRepo;
    }

    //obtiene todos los productos
    public List<Producto> obtenerTodos(){
        return inventarioRepo.findAll();
    }

    /*Crea un Producto */
    public Producto creaProducto(Producto prod){
        return inventarioRepo.save(prod);
    }


    /** Actualiza la cantidad de un producto */
    public ResponseEntity<Producto> actualizarCantidad(Long id, ActualizarCantidadDTO dto) {
        Optional<Producto> productoOpt = inventarioRepo.findById(id);
        if (productoOpt.isPresent()){
            Producto producto = productoOpt.get();
            producto.setCantidad(dto.getCantidad());
            inventarioRepo.save(producto);
            return ResponseEntity.ok(producto);

        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    /** Permite actualizar el precio de un producto */
    public ResponseEntity<Producto> actualizarPrecio(Long id, ActualizarPrecioDTO preciodto) {

        Optional<Producto> productoOpt = inventarioRepo.findById(id);
        if (productoOpt.isPresent()){
            Producto producto = productoOpt.get();
            producto.setPrecio(preciodto.getPrecio());
            inventarioRepo.save(producto);
            return ResponseEntity.ok(producto);

        }
        else{
            return ResponseEntity.notFound().build();
        }
    }


    /** Permite actualizar el nombre de un producto */
    public ResponseEntity<Producto> actualizarNombre(Long id, ActualizarNombreDTO nombredto) {
        Optional<Producto> productoOpt = inventarioRepo.findById(id);
        if (productoOpt.isPresent()){
            Producto producto = productoOpt.get();
            producto.setNombreProd(nombredto.getNombre());
            inventarioRepo.save(producto);
            return ResponseEntity.ok(producto);

        }
        else{
            return ResponseEntity.notFound().build();
        }

    }

    /** Actualiza el origen de un producto */
    public ResponseEntity<Producto> actualizarOrigen(Long id, ActualizarOrigenDTO origendto) {
        Optional<Producto> productoOpt = inventarioRepo.findById(id);
        if (productoOpt.isPresent()){
            Producto producto = productoOpt.get();
            producto.setOrigenProd(origendto.getOrigen());
            inventarioRepo.save(producto);
            return ResponseEntity.ok(producto);

        }
        else{
            return ResponseEntity.notFound().build();
        }
    
    }



    /** Actualiza material principal de un producto */
    public ResponseEntity<Producto> actualizarMaterial(Long id, ActualizarMaterialDTO materialdto) {
        Optional<Producto> productoOpt = inventarioRepo.findById(id);
        if (productoOpt.isPresent()){
            Producto producto = productoOpt.get();
            producto.setMaterialPrincipal(materialdto.getMaterialPrincipal());
            inventarioRepo.save(producto);
            return ResponseEntity.ok(producto);

        }
        else{
            return ResponseEntity.notFound().build();
        }
    }


    /** Actualiza la reutilizabilidad de un producto */
    public ResponseEntity<Producto> actualizarReutilizabilidad(Long id, ActualizarReutilizacionDTO reutidto) {
        Optional<Producto> productoOpt = inventarioRepo.findById(id);
        if (productoOpt.isPresent()){
            Producto producto = productoOpt.get();
            producto.setReutilizable(reutidto.isReutilizacion());
            inventarioRepo.save(producto);
            return ResponseEntity.ok(producto);

        }
        else{
            return ResponseEntity.notFound().build();
        }
    }



    /** Actualiza la vida util de un producto */
    public ResponseEntity<Producto> actualizarVidaUtill(Long id, ActualizadVidaDTO vidadto) {
        Optional<Producto> productoOpt = inventarioRepo.findById(id);
        if (productoOpt.isPresent()){
            Producto producto = productoOpt.get();
            producto.setVidaUtilMeses(vidadto.getVidaUtil());
            inventarioRepo.save(producto);
            return ResponseEntity.ok(producto);

        }
        else{
            return ResponseEntity.notFound().build();
        }
    }


    /** Actualiza la cantidad de un producto */
    public ResponseEntity<Producto> actualizarCategoria(Long id, ActualizarCategoriaDTO categoriadto) {
        Optional<Producto> productoOpt = inventarioRepo.findById(id);
        if (productoOpt.isPresent()){
            Producto producto = productoOpt.get();
            producto.setCategoria(categoriadto.getCategoria());
            inventarioRepo.save(producto);
            return ResponseEntity.ok(producto);

        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    

    public String eliminarProducto(Long id){
        inventarioRepo.deleteById(id);
        return "El producto con el id: " + id + "a sido eliminado";
    }

}