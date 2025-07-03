package com.devsenior.demojpadev.data;

import com.devsenior.demojpadev.data.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
