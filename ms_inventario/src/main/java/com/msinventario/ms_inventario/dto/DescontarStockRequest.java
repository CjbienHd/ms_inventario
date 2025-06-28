package com.msinventario.ms_inventario.dto;

import lombok.Data;

@Data
public class DescontarStockRequest {
    private Long productoId;
    private int cantidad;
}