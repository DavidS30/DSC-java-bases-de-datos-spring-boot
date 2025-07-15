package com.devsenior.demojpadev.service;

import com.devsenior.demojpadev.controller.request.PedidoRequest;
import com.devsenior.demojpadev.data.ClienteRepository;
import com.devsenior.demojpadev.data.PedidosRepository;
import com.devsenior.demojpadev.data.ProductoRepository;
import com.devsenior.demojpadev.data.model.Cliente;
import com.devsenior.demojpadev.data.model.Pedido;
import com.devsenior.demojpadev.data.model.Producto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidosRepository pedidosRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EntityManager entityManager;

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

    public Page<Pedido> buscarPedidosAvanzado(
            String clienteNombre,
            LocalDate fechaInicio,
            LocalDate fechaFin,
            Double totalMinimo,
            Double totalMaximo,
            int page,
            int size
    ){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pedido> cq = cb.createQuery(Pedido.class);
        Root<Pedido> pedido = cq.from(Pedido.class);
        Join<Pedido, Cliente> cliente = pedido.join("cliente");

        List<Predicate> filtros = construirFiltros(cb, pedido, cliente, clienteNombre, fechaInicio, fechaFin, totalMinimo, totalMaximo);

        cq.select(pedido).where(filtros.toArray(new Predicate[0]));
        TypedQuery<Pedido> query = entityManager.createQuery(cq);
        query.setFirstResult(page * size);
        query.setMaxResults(size);
        List<Pedido> pedidos = query.getResultList();

        // Conteo de resultados
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Pedido> countRoot = countQuery.from(Pedido.class);
        Join<Pedido, Cliente> countCliente = countRoot.join("cliente");
        List<Predicate> countFiltros = construirFiltros(cb, countRoot, countCliente, clienteNombre, fechaInicio, fechaFin, totalMinimo, totalMaximo);
        countQuery.select(cb.count(countRoot)).where(countFiltros.toArray(new Predicate[0]));

        Long total = entityManager.createQuery(countQuery).getSingleResult();
        Pageable pageable = PageRequest.of(page, size, Sort.by("fechaPedido").descending());
        return new PageImpl<>(pedidos, pageable, total);
    }

    private List<Predicate> construirFiltros(
            CriteriaBuilder cb,
            Root<Pedido> root,
            Join<Pedido, Cliente> cliente,
            String clienteNombre,
            LocalDate fechaInicio,
            LocalDate fechaFin,
            Double totalMinimo,
            Double totalMaximo
    ) {
        List<Predicate> filtros = new ArrayList<>();
        if(clienteNombre != null && !clienteNombre.isEmpty()){
            filtros.add(cb.like(cb.lower(cliente.get("nombre")), "%" + clienteNombre.toLowerCase() + "%"));
        }
        if (fechaInicio != null){
            filtros.add(cb.greaterThanOrEqualTo(root.get("fechaPedido"), fechaInicio));
        }
        if (fechaFin != null){
            filtros.add(cb.lessThanOrEqualTo(root.get("fechaPedido"), fechaFin));
        }
        if(totalMinimo != null){
            filtros.add(cb.greaterThanOrEqualTo(root.get("total"), totalMinimo));
        }
        if (totalMaximo != null){
            filtros.add(cb.lessThanOrEqualTo(root.get("total"), totalMaximo));
        }
        return filtros;
    }
}
