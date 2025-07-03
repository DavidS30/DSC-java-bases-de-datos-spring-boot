package com.devsenior.demojpadev.controller.dto;

import com.devsenior.demojpadev.data.model.Pedido;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoDTO {
    private Long id;
    private Double total;
    private String fecha;
    private String nombreCliente;

    public PedidoDTO(Pedido pedido){
        this.id = pedido.getId();
        this.total = pedido.getTotal();
        this.fecha = pedido.getFechaPedido() != null ? pedido.getFechaPedido().toString() : null;
        this.nombreCliente = pedido.getCliente().getNombre();
    }
}
