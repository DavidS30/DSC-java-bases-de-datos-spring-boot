package com.devsenior.demojpadev.controller.dto;

import com.devsenior.demojpadev.data.model.Pedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ResponsePedido {
    private String message;
    private List<PedidoDTO> pedidos;
}
