package com.devsenior.demojpadev.controller;

import com.devsenior.demojpadev.controller.dto.PedidoDTO;
import com.devsenior.demojpadev.controller.dto.ResponsePedido;
import com.devsenior.demojpadev.controller.request.PedidoRequest;
import com.devsenior.demojpadev.data.PedidosRepository;
import com.devsenior.demojpadev.data.model.Pedido;
import com.devsenior.demojpadev.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    @Autowired
    private PedidosRepository pedidosRepository;

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponsePedido getAll() {
        return new ResponsePedido("Pedidos encontrados existosamente",pedidosRepository.findAll().stream().map(pedido -> new PedidoDTO(pedido)).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public PedidoDTO getById(@PathVariable Long id) {
        Pedido pedido = pedidosRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + id));
        return new PedidoDTO(pedido);
    }

    @GetMapping("/ultimo-mes")
    public List<PedidoDTO> getUltimoMes() {
       return pedidoService.obtenerPedidosUltimoMes().stream().map(PedidoDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/paginado")
    public Page<PedidoDTO> getPedidosPaginados(){
        return pedidoService.obtenerPedidosPaginados().map(PedidoDTO::new);
    }

    @GetMapping("paginados")
    public Page<PedidoDTO> getPedidosPaginadosAttr(@PageableDefault( size = 2, sort="fechaPedido", direction = Sort.Direction.DESC) Pageable pageable){
        return pedidoService.obtenerPedidosPaginadosAttr(pageable).map(PedidoDTO::new);
    }

    @PostMapping
    public ResponsePedido create(@RequestBody PedidoRequest pedidoRequest) {
        try{
            Pedido pedido = pedidoService.crearPedido(pedidoRequest);
            List<PedidoDTO> pedidos = new ArrayList<>();
            pedidos.add(new PedidoDTO(pedido));
            return new ResponsePedido("Pedido creado exitosamente", pedidos);
        }catch(RuntimeException e){
            return new ResponsePedido("Error al crear el pedido: " + e.getMessage(), new ArrayList<>());
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        pedidosRepository.deleteById(id);
    }

}
