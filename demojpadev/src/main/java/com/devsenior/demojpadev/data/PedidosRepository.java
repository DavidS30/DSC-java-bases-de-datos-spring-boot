package com.devsenior.demojpadev.data;

import com.devsenior.demojpadev.data.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PedidosRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByFechaPedido(LocalDate fechaPedido);

    List<Pedido> findByTotalGreaterThanAndFechaPedidoGreaterThan(Double total, LocalDate fechaPedido);

    @Query("SELECT p FROM Pedido p WHERE p.fechaPedido >= :fechaInicio")
    List<Pedido> findPedidosUiltimoMes(@Param("fechaInicio") LocalDate fechaInicio);
}
