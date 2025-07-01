package com.devsenior.conexionpostgres.components;

import com.devsenior.conexionpostgres.model.Usuario;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VerifyConnection {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void verifyConnection() {
        try {
            List<String> nombres = jdbcTemplate.query(
                    "SELECT nombre from usuarios",
                    (rs, rowNum) -> rs.getString("nombre")
            );
            System.out.println("Conexión Exitosa, se encontraron los usuarios:");
            nombres.forEach(x -> System.out.println(x));
        } catch (Exception e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    public List<Usuario> getUsers(){
        try{
            return jdbcTemplate.query(
                    "SELECT * from usuarios",
                    (rs,rowNum) -> new Usuario(
                            rs.getLong("id"),
                            rs.getString("nombre"),
                            rs.getString("correo"),
                            rs.getDate("fecha_registro").toLocalDate()
                    )
            );
        }catch (Exception e){
            System.out.println("Error al obtener los usuarios: " + e.getMessage());
            e.printStackTrace();
            return List.of();
        }
    }


}
