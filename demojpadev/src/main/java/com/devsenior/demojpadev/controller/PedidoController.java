package com.devsenior.demojpadev.controller;

import com.devsenior.demojpadev.controller.dto.PedidoDTO;
import com.devsenior.demojpadev.data.ClienteRepository;
import com.devsenior.demojpadev.data.PedidosRepository;
import com.devsenior.demojpadev.data.model.Cliente;
import com.devsenior.demojpadev.data.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    @Autowired
    private PedidosRepository pedidosRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<PedidoDTO> getAll() {
        return pedidosRepository.findAll().stream().map(pedido -> new PedidoDTO(pedido)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PedidoDTO getById(@PathVariable Long id) {
        Pedido pedido = pedidosRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + id));
        return new PedidoDTO(pedido);
    }

    @PostMapping("/{clienteId}")
    public Pedido create(@PathVariable Long clienteId, @RequestBody Pedido pedido) {
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + clienteId));
        pedido.setCliente(cliente);
        return pedidosRepository.save(pedido);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        pedidosRepository.deleteById(id);
    }

}
