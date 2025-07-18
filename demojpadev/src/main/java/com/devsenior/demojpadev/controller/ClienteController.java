package com.devsenior.demojpadev.controller;

import com.devsenior.demojpadev.data.ClienteRepository;
import com.devsenior.demojpadev.data.model.Cliente;
import com.devsenior.demojpadev.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> getAll(){
        return clienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public Cliente getById(@PathVariable Long id){
        return clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + id));
    }

    @GetMapping("/busqueda")
    public List<Cliente> getAllWithCriteria(@RequestParam(required = false) String nombre, @RequestParam(required = false) String email){
       return clienteService.buscarClientesPorCriterios(nombre, email);
    }

    @PostMapping
    public Cliente create(@RequestBody Cliente cliente){
        return clienteRepository.save(cliente);
    }

    @PutMapping("/{id}")
    public Cliente update(@PathVariable Long id, @RequestBody Cliente detalleCliente){
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + id));
        cliente.setNombre(detalleCliente.getNombre());
        cliente.setEmail(detalleCliente.getEmail());
        return clienteRepository.save(cliente);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        clienteRepository.deleteById(id);
    }


}
