package com.devsenior.demojpadev.service;

import com.devsenior.demojpadev.controller.request.PedidoRequest;
import com.devsenior.demojpadev.data.ClienteRepository;
import com.devsenior.demojpadev.data.PedidosRepository;
import com.devsenior.demojpadev.data.ProductoRepository;
import com.devsenior.demojpadev.data.model.Cliente;
import com.devsenior.demojpadev.data.model.Pedido;
import com.devsenior.demojpadev.data.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidosRepository pedidosRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public Pedido crearPedido(PedidoRequest pedidoRequest){
        Cliente cliente = clienteRepository.findById(pedidoRequest.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        List<Producto> productos = productoRepository.findAllById(pedidoRequest.getProductosIds());

        if (productos.size() != pedidoRequest.getProductosIds().size()) {
            throw new RuntimeException("Algunos productos no fueron encontrados");
        }

        Double total = productos.stream().mapToDouble(Producto::getPrecio).sum();

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setProductos(productos);
        pedido.setTotal(total);
        pedido.setFechaPedido(LocalDate.now());

        return pedidosRepository.save(pedido);
    }

    public List<Pedido> obtenerPedidosUltimoMes(){
        LocalDate fechaInicio = LocalDate.now().minusDays(30);
        return pedidosRepository.findPedidosUiltimoMes(fechaInicio);
    }

    public Page<Pedido> obtenerPedidosPaginados(){
        Pageable pageable = PageRequest.of(0, 2, Sort.by("fechaPedido").descending());
        return pedidosRepository.findAll(pageable);
    }

    public Page<Pedido> obtenerPedidosPaginadosAttr(Pageable pageable){
        return pedidosRepository.findAll(pageable);
    }
}
