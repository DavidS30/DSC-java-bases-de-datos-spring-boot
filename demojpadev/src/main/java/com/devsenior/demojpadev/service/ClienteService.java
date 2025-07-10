package com.devsenior.demojpadev.service;

import com.devsenior.demojpadev.data.model.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private EntityManager entityManager;

    public List<Cliente> buscarClientesPorCriterios(String nombre, String email){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Cliente> cq  = cb.createQuery(Cliente.class);
        Root<Cliente> root = cq.from(Cliente.class);

        List<Predicate> predicates = new ArrayList<>();
        if (nombre != null && !nombre.isEmpty()){
           predicates.add(cb.like(cb.lower(root.get("nombre")), "%" + nombre.toLowerCase() + "%"));
        }
        if (email != null && !email.isEmpty()){
            predicates.add(cb.like(cb.lower(root.get("email")), "%" + email.toLowerCase() + "%"));
        }
        cq.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(cq).getResultList();
    }
}
