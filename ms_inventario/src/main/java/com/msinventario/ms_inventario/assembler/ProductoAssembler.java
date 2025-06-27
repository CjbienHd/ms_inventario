package com.msinventario.ms_inventario.assembler;

import com.msinventario.ms_inventario.controller.Control;
import com.msinventario.ms_inventario.model.Producto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ProductoAssembler implements RepresentationModelAssembler<Producto, EntityModel<Producto>> {

    @Override
    public EntityModel<Producto> toModel(Producto producto) {
        return EntityModel.of(producto,
                linkTo(methodOn(Control.class).obtenerProductos()).withRel("todos"),
                linkTo(methodOn(Control.class).actualizarCantidad(producto.getId(), null)).withRel("actualizar-cantidad"),
                linkTo(methodOn(Control.class).actualizarPrecio(producto.getId(), null)).withRel("actualizar-precio"),
                linkTo(methodOn(Control.class).actualizarNombre(producto.getId(), null)).withRel("actualizar-nombre"),
                linkTo(methodOn(Control.class).actualizarOrigen(producto.getId(), null)).withRel("actualizar-origen"),
                linkTo(methodOn(Control.class).actualizarMaterial(producto.getId(), null)).withRel("actualizar-material"),
                linkTo(methodOn(Control.class).actualizarReutilizable(producto.getId(), null)).withRel("actualizar-reutilizable"),
                linkTo(methodOn(Control.class).actualizarVida(producto.getId(), null)).withRel("actualizar-vida-util"),
                linkTo(methodOn(Control.class).actualizarCategoria(producto.getId(), null)).withRel("actualizar-categoria")
        );
    }
}