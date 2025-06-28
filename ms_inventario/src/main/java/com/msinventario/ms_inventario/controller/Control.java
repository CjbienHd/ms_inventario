package com.msinventario.ms_inventario.controller;

import lombok.RequiredArgsConstructor;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.List;


import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
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
import com.msinventario.ms_inventario.assembler.ProductoAssembler;
import com.msinventario.ms_inventario.dto.ActualizadVidaDTO;
import com.msinventario.ms_inventario.dto.ActualizarCantidadDTO;
import com.msinventario.ms_inventario.dto.ActualizarCategoriaDTO;
import com.msinventario.ms_inventario.dto.ActualizarMaterialDTO;
import com.msinventario.ms_inventario.dto.ActualizarNombreDTO;
import com.msinventario.ms_inventario.dto.ActualizarOrigenDTO;
import com.msinventario.ms_inventario.dto.ActualizarPrecioDTO;
import com.msinventario.ms_inventario.dto.ActualizarReutilizacionDTO;
import com.msinventario.ms_inventario.dto.DescontarStockRequest;
import com.msinventario.ms_inventario.dto.VerificarStockRequest;
import com.msinventario.ms_inventario.model.Producto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestControllerAdvice
@RestController
@RequiredArgsConstructor
@RequestMapping("/inventario")
@Tag(name = "Búsqueda de productos", description = "Operaciones para consultar productos")
public class Control {

    private final InventarioService inventarioService;

    private final ProductoAssembler assembler;


    @Operation(
            summary = "Consulta estado del servidor",
            description = "Obtiene el estado del servicio si esta activo"
    )
    @ApiResponse(responseCode = "200", description = "Servicio activo")
    @GetMapping
    public String status() {
        return "microservicio busqueda de productos funcionando correctamente";
    }


    @Operation(
        summary = "Obtener todos los productos",
        description = "Devuelve una lista con todos los productos registrados"
    )
    @ApiResponse(responseCode = "200", description = "Listado completo de productos")
    @GetMapping("/obtener")
    public CollectionModel<EntityModel<Producto>> obtenerProductos() {
                List<EntityModel<Producto>> productos = inventarioService.obtenerTodos().stream()
                .map(assembler::toModel)
                .toList();

        return CollectionModel.of(productos,
                linkTo(methodOn(Control.class).obtenerProductos()).withSelfRel());
    }

    @GetMapping("/producto/{id}")
    public ResponseEntity<EntityModel<Producto>> obtenerPorId(@PathVariable Long id) {
        Producto producto = inventarioService.obtenerPorId(id);
        return ResponseEntity.ok(assembler.toModel(producto));
    }


    // Actualizar cantidad de un producto
    @PutMapping("cantidad/{id}")
    @Operation(summary = "Actualizar cantidad", description = "Actualiza la cantidad disponible del producto.")
    @ApiResponse(responseCode = "200", description = "Cantidad actualizada correctamente")

    public ResponseEntity<EntityModel<Producto>> actualizarCantidad(@Parameter(description = "ID del producto")@PathVariable Long id, @RequestBody ActualizarCantidadDTO dto) {
        Producto actualizado = inventarioService.actualizarCantidad(id, dto);
        return ResponseEntity.ok(assembler.toModel(actualizado));
    }

    // Actualizar precio de un producto
    @PutMapping("precio/{id}")
    @Operation(summary = "Actualizar precio", description = "Actualiza el precio del producto")
    @ApiResponse(responseCode = "200", description = "Precio actualizado correctamente")

    public ResponseEntity<EntityModel<Producto>> actualizarPrecio(@Parameter(description = "ID del producto")@PathVariable Long id, @RequestBody ActualizarPrecioDTO dto) {
        Producto actualizado = inventarioService.actualizarPrecio(id, dto);
        return ResponseEntity.ok(assembler.toModel(actualizado));
    }

        
    // Actualizar nombre de un producto
    @PutMapping("nombre/{id}")
    @Operation(summary = "Actualizar nombre", description = "Actualiza el nombre del producto según su ID")
    @ApiResponse(responseCode = "200", description = "Nombre actualizado correctamente")
    
    public ResponseEntity<EntityModel<Producto>> actualizarNombre(@Parameter(description = "ID del producto") @PathVariable Long id,@RequestBody ActualizarNombreDTO nombredto) {
        Producto actualizado = inventarioService.actualizarNombre(id, nombredto);
        return ResponseEntity.ok(assembler.toModel(actualizado));
    }


        
    // Actualizar origen de un producto
    @PutMapping("origen/{id}")
    @Operation(summary = "Actualizar origen", description = "Actualiza el origen del producto según su ID")
    @ApiResponse(responseCode = "200", description = "Origen actualizado correctamente")

    public ResponseEntity<EntityModel<Producto>> actualizarOrigen(@Parameter(description = "ID del producto")@PathVariable Long id, @RequestBody ActualizarOrigenDTO dto) {
        Producto actualizado = inventarioService.actualizarOrigen(id, dto);
        return ResponseEntity.ok(assembler.toModel(actualizado));
    }

        
    // Actualizar material principal de un producto
    @PutMapping("material/{id}")
    @Operation(summary = "Actualizar material", description = "Actualiza el material del producto")
    @ApiResponse(responseCode = "200", description = "Material actualizado correctamente")

    public ResponseEntity<EntityModel<Producto>> actualizarMaterial(@Parameter(description = "ID del producto")@PathVariable Long id, @RequestBody ActualizarMaterialDTO dto) {
        Producto actualizado = inventarioService.actualizarMaterial(id, dto);
        return ResponseEntity.ok(assembler.toModel(actualizado));
    }


        
    // Actualizar reutilizacion de un producto
    @PutMapping("reutilizacion/{id}")
    @Operation(summary = "Actualizar reutilización", description = "Actualiza si el producto es reutilizable.")
    @ApiResponse(responseCode = "200", description = "Atributo reutilizable actualizado correctamente")

    public ResponseEntity<EntityModel<Producto>> actualizarReutilizable(@Parameter(description = "ID del producto")@PathVariable Long id, @RequestBody ActualizarReutilizacionDTO dto) {
        Producto actualizado = inventarioService.actualizarReutilizabilidad(id, dto);
        return ResponseEntity.ok(assembler.toModel(actualizado));
    }


        
    // Actualizar vida util de un producto
    @PutMapping("vidautil/{id}")
    @Operation(summary = "Actualizar vida útil", description = "Actualiza la vida útil del producto")
    @ApiResponse(responseCode = "200", description = "Vida útil actualizada correctamente")

    public ResponseEntity<EntityModel<Producto>> actualizarVida(@Parameter(description = "ID del producto")@PathVariable Long id, @RequestBody ActualizadVidaDTO dto) {
        Producto actualizado = inventarioService.actualizarVidaUtill(id, dto);
        return ResponseEntity.ok(assembler.toModel(actualizado));
    }


    // Actualizar categoria de un producto
    @PutMapping("categoria/{id}")
    @Operation(summary = "Actualizar categoría", description = "Actualiza la categoría del producto.")
    @ApiResponse(responseCode = "200", description = "Categoría actualizada correctamente")

    public ResponseEntity<EntityModel<Producto>> actualizarCategoria(@Parameter(description = "ID del producto")@PathVariable Long id, @RequestBody ActualizarCategoriaDTO dto) {
        Producto actualizado = inventarioService.actualizarCategoria(id, dto);
        return ResponseEntity.ok(assembler.toModel(actualizado));
    }


    // Crear un nuevo producto

    @PostMapping("/crearProducto")
    @Operation(summary = "Crea nuevo producto", description = "Permite registrar un nuevo producto en el sistema")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Producto creado correctamente"),
        @ApiResponse(responseCode = "400", description = "Error al crear producto")

    })
    public ResponseEntity<EntityModel<Producto>> crearProducto(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Producto a crear",
                required = true,
                content = @Content(schema = @Schema(implementation = Producto.class))
            )
            @RequestBody Producto prod) {
                Producto creado = inventarioService.creaProducto(prod);
            return ResponseEntity
                .created(linkTo(methodOn(Control.class).obtenerPorId(creado.getId())).toUri())
                .body(assembler.toModel(creado));
            }

    //Eliminar producto
    @DeleteMapping("/eliminar/{id}")
    @Operation(summary = "Elimina producto", description = "Elimina un producto segun su id del sistema")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Producto eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })

    public ResponseEntity<Void> eliminarProducto(
            @Parameter(description = "ID del producto a eliminar", example = "1") @PathVariable Long id) {
        inventarioService.eliminarProducto(id);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/verificar-stock")
    public ResponseEntity<Double> verificarStock(@RequestBody VerificarStockRequest request) {
        double precio = inventarioService.verificarStockYObtenerPrecio(request.getProductoId(), request.getCantidad());
        return ResponseEntity.ok(precio);
    }

    @PutMapping("/descontar")
    public ResponseEntity<Void> descontarStock(@RequestBody DescontarStockRequest request) {
        inventarioService.descontarStock(request.getProductoId(), request.getCantidad());
        return ResponseEntity.ok().build();
    }
    

    
}

    


