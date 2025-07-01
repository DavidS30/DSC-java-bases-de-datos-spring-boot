package com.devsenior.conexionpostgres.controller;

import com.devsenior.conexionpostgres.components.VerifyConnection;
import com.devsenior.conexionpostgres.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private VerifyConnection verifyConnection;

    @GetMapping("/users")
    public List<Usuario> getUsers() {
        return verifyConnection.getUsers();
    }

}
