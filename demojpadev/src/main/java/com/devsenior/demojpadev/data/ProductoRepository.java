package com.devsenior.demojpadev.data;

import com.devsenior.demojpadev.data.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Query("SELECT p, COUNT(pedido) as vecesVendido FROM Producto p " +
           "JOIN p.pedidos pedido" +
           " GROUP BY p.id " +
           "ORDER BY vecesVendido DESC")
    List<Object[]> findProductosMasVendidos();
}
