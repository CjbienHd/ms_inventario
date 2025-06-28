package com.msinventario.ms_inventario.dto;

import lombok.Data;

@Data
public class VerificarStockRequest {
    private Long productoId;
    private int cantidad;
}