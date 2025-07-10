package com.devsenior.demojpadev.controller;

import com.devsenior.demojpadev.data.ProductoRepository;
import com.devsenior.demojpadev.data.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Producto> getAll() {
        return productoRepository.findAll();
    }


    @GetMapping("/producto-mas-vendidos")
    public List<Object[]> getProductoMasVendidos() {
        return productoRepository.findProductosMasVendidos();
    }

    @PostMapping
    public Producto createProduct(@RequestBody Producto producto){
        return productoRepository.save(producto);
    }

}
