CREATE TABLE IF NOT EXISTS usuarios (
                                        id SERIAL PRIMARY KEY,
                                        nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100) UNIQUE,
    fecha_registro DATE DEFAULT CURRENT_DATE
    );

INSERT INTO usuarios (nombre, correo) VALUES ('Juan Pérez', 'juan.perez@email.com');
INSERT INTO usuarios (nombre, correo) VALUES ('María López', 'maria.lopez@email.com');
INSERT INTO usuarios (nombre, correo) VALUES ('Carlos García', 'carlos.garcia@email.com');
INSERT INTO usuarios (nombre, correo) VALUES ('Ana Torres', 'ana.torres@email.com');
INSERT INTO usuarios (nombre, correo) VALUES ('Luis Fernández', 'luis.fernandez@email.com');
INSERT INTO usuarios (nombre, correo) VALUES ('Sofía Ramírez', 'sofia.ramirez@email.com');
INSERT INTO usuarios (nombre, correo) VALUES ('Miguel Sánchez', 'miguel.sanchez@email.com');
INSERT INTO usuarios (nombre, correo) VALUES ('Laura Gómez', 'laura.gomez@email.com');
INSERT INTO usuarios (nombre, correo) VALUES ('Pedro Díaz', 'pedro.diaz@email.com');
INSERT INTO usuarios (nombre, correo) VALUES ('Lucía Herrera', 'lucia.herrera@email.com');
INSERT INTO usuarios (nombre, correo) VALUES ('Adrian Gonzales', 'adrian.gonzales@email.com');


select * from usuarios;
delete from usuarios where id = 15;

SELECT setval('usuarios_id_seq', 10);
SELECT currval('usuarios_id_seq');


--  RELACIONES ENTRE TABLAS
-- 1:1 one-to-one
-- 1:N one-to-many
-- N:M many-to-many

-- CREACIÓN DE TABLAS DE RELACIONES
CREATE TABLE IF NOT EXISTS direcciones (
                                           id SERIAL PRIMARY KEY,
                                           usuario_id INTEGER REFERENCES usuarios(id),
    direccion VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS perfil (
                                      id SERIAL PRIMARY KEY,
                                      usuario_id INTEGER UNIQUE REFERENCES usuarios(id),
    fecha_nacimiento DATE,
    genero VARCHAR(10),
    telefono VARCHAR(15),
    edad INTEGER
    );

CREATE TABLE IF NOT EXISTS roles (
                                     id SERIAL PRIMARY KEY,
                                     nombre VARCHAR(50) UNIQUE NOT NULL
    );

CREATE TABLE IF NOT EXISTS usuarios_roles (
                                              usuario_id INTEGER REFERENCES usuarios(id),
    rol_id INTEGER REFERENCES roles(id),
    PRIMARY KEY (usuario_id, rol_id)
    );

-- EJERCICIO: INSERTAR DATOS EN TABLAS DE RELACIONES

-- Relación 1:N entre usuarios y direcciones
INSERT INTO direcciones (usuario_id, direccion) VALUES (1, 'Calle Falsa 123');
INSERT INTO direcciones (usuario_id, direccion) VALUES (1, 'Calle Falsa 456');

INSERT INTO direcciones (usuario_id, direccion) VALUES (11, 'Avenida Siempre Viva 742');

SELECT * FROM direcciones;

-- Relación 1:1 entre usuarios y perfil
INSERT INTO perfil (usuario_id, fecha_nacimiento, genero, telefono, edad) VALUES (1, '1990-01-01', 'Masculino', '123456789', 33);
INSERT INTO perfil (usuario_id, fecha_nacimiento, genero, telefono, edad) VALUES (11, '2010-01-01', 'Masculino', '894849212', 20);

select * from perfil;

-- Relación N:M entre usuarios y roles
INSERT INTO roles (nombre) VALUES ('Administrador');
INSERT INTO roles (nombre) VALUES ('Cliente');
INSERT INTO roles (nombre) VALUES ('Vendedor');
INSERT INTO roles (nombre) VALUES ('Desarrollador');
INSERT INTO roles (nombre) VALUES ('Comercial');

select * from roles;

INSERT INTO usuarios_roles (usuario_id, rol_id) VALUES (1,1);
INSERT INTO usuarios_roles (usuario_id, rol_id) VALUES (1,4);
INSERT INTO usuarios_roles (usuario_id, rol_id) VALUES (11,4);
INSERT INTO usuarios_roles (usuario_id, rol_id) VALUES (11,2);
INSERT INTO usuarios_roles (usuario_id, rol_id) VALUES (10,5);
INSERT INTO usuarios_roles (usuario_id, rol_id) VALUES (10,3);

SELECT * FROM usuarios_roles;

-- consultas de ejemplo

SELECT u.nombre, d.direccion
FROM usuarios u
         JOIN direcciones d ON u.id = d.usuario_id
where u.id = 1;