package com.msinventario.ms_inventario.repository;

import com.msinventario.ms_inventario.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
/**Repositorio que implementa la interfaz JpaRepository para la clase Producto */
public interface RepositoryInventario extends JpaRepository<Producto, Long> {

}
        


