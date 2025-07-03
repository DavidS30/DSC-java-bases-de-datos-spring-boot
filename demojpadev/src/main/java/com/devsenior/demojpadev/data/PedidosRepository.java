package com.devsenior.demojpadev.data;

import com.devsenior.demojpadev.data.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidosRepository extends JpaRepository<Pedido, Long> {
}
