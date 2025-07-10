package com.devsenior.demojpadev.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PedidoRequest {

    private Long clienteId;
    private List<Long> productosIds;

}
