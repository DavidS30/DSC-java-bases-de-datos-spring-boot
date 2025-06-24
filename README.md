# Módulo 6 - Bases de Datos Relacionales y NoSQL con Java y Spring Boot

Contenido para el programa de Java Senior AI de [www.devseniorcode.com](https://www.devseniorcode.com).

---

## Objetivo del Módulo

Desarrollar competencias sólidas en el diseño, conexión y manipulación de bases de datos relacionales y NoSQL desde aplicaciones Java. Se trabajará con **PostgreSQL** y **MongoDB**, aprendiendo a realizar operaciones CRUD, modelado de entidades, relaciones y optimización de consultas utilizando **Spring Data JPA**. Además, se incorporará el uso de IA para sugerencias de mapeo, creación de consultas y diseño de esquemas eficientes.

---

## Contenido por Clases

### CLASE 1: Introducción a PostgreSQL y conexión desde Java

- ¿Qué es una base de datos relacional? Características de PostgreSQL.
- Instalación, configuración y uso de herramientas como **pgAdmin** o **DBeaver**.
- Creación de esquemas, tablas, tipos de datos y restricciones (**PRIMARY KEY, FOREIGN KEY, NOT NULL**, etc.).
- Conceptos de normalización y modelado lógico de datos.
- Conexión desde Java usando **Spring Boot + Spring Data JPA**.
- Configuración de `application.properties` y dependencias necesarias.
- Prueba de conexión con herramientas de IA: sugerencias para verificación automática.

**Ejercicio práctico:**
Crear la base de datos `devsenior_db`, una tabla `usuarios`, y conectarla desde un proyecto Spring Boot básico.

---

### CLASE 2: Modelado, conexión y validación de entidades

**Objetivos:**

- Asegurar el correcto diseño y conexión de las tablas desde el backend.
- Reforzar el uso de modelos de datos relacionales reales.

**Actividades:**

- Ejercicios de modelado: de requerimientos a esquemas con entidades y relaciones.
- Análisis de errores comunes: claves mal definidas, datos duplicados, tipos incorrectos.
- Revisión de conexión y mapeo con asistencia de IA.
- Refuerzo de relaciones 1 a 1, 1 a N y N a N en casos prácticos.

---

### CLASE 3: Spring Data JPA – Entidades, Repositorios y Relaciones

**Objetivos:**

- Mapear objetos Java a tablas de base de datos usando JPA.
- Implementar relaciones entre entidades de forma correcta.
- Realizar operaciones CRUD profesionalmente con Spring Data JPA.

**Contenidos:**

- Uso de anotaciones: `@Entity`, `@Id`, `@GeneratedValue`, `@Column`, `@Table`.
- Creación de clases Repository y uso de interfaces `JpaRepository` y `CrudRepository`.
- Relaciones entre entidades: `@OneToOne`, `@OneToMany`, `@ManyToOne`, `@ManyToMany`.
- Atributos de relación: `cascade`, `fetch`, `mappedBy`.
- Validación de relaciones complejas con ayuda de IA.

---

### CLASE 4: Refuerzo en entidades, relaciones y pruebas de integridad

**Objetivos:**

- Verificar la correcta implementación de relaciones y operaciones básicas en Spring Data.
- Afianzar el concepto de modelo de dominio coherente y escalable.

**Actividades:**

- Taller de creación y ajuste de relaciones entre entidades usando diagrama de clases.
- Verificación de consistencia entre clases Java y tablas reales con herramientas externas.
- Pruebas cruzadas con IA: detectar relaciones mal planteadas, nombres de métodos inadecuados y clases mal configuradas.
- Validación de datos: campos obligatorios, formatos, referencias.

---

### CLASE 5: Consultas Avanzadas con JPQL, Criteria API y Spring Queries

**Objetivos:**

- Crear consultas avanzadas según necesidades del negocio.
- Optimizar consultas y estructuras para eficiencia en tiempo real.

**Ejercicio práctico:**

- Implementar las entidades `Cliente`, `Pedido`, `Producto` y sus relaciones.
- Crear controladores básicos para operaciones CRUD.

**Contenidos:**

- Introducción a **JPQL** (Java Persistence Query Language).
- Uso de anotaciones `@Query` para consultas personalizadas.
- Consultas dinámicas con **Criteria API**.
- Métodos derivados (`findByNombre`, `findByPrecioGreaterThan`).
- Paginación y ordenamiento con `Pageable`, `Sort`.
- IA como generador automático de métodos de consulta y optimizador de filtros.

**Ejercicios:**

- Consultas personalizadas para obtener:
  - Pedidos realizados en el último mes.
  - Productos más vendidos.
  - Clientes con más de X compras.

---

### CLASE 6: Diagnóstico, rendimiento y consultas con IA

**Objetivos:**

- Asegurar la comprensión y correcta aplicación de consultas avanzadas.
- Analizar el rendimiento de las consultas y aplicar optimizaciones.

**Actividades:**

- Simulación de escenarios reales con múltiples filtros y relaciones.
- Taller: consultas complejas usando Criteria API y paginación.
- Análisis de rendimiento con **EXPLAIN ANALYZE** desde pgAdmin y revisión con IA.
- Refactorización y limpieza de consultas usando recomendaciones automáticas.

---

### CLASE 7: MongoDB – Introducción a NoSQL y conexión con Spring Boot

**Objetivos:**

- Comprender los fundamentos de las bases de datos NoSQL.
- Implementar una aplicación con MongoDB y Spring Boot.

**Contenidos:**

- ¿Qué es MongoDB? Estructura flexible, documentos y colecciones.
- Diferencias entre SQL y NoSQL (modelo, consultas, escalabilidad).
- Instalación de MongoDB local o en Atlas (cloud).
- Uso de `spring-boot-starter-data-mongodb`.
- Anotaciones: `@Document`, `@Id`, `@Field`, repositorios `MongoRepository`.
- Operaciones CRUD básicas y consultas por campos.
- Uso de IA para transformar consultas relacionales a NoSQL.

**Proyecto en clase:**

- Crear una colección `usuarios` en MongoDB con documentos dinámicos y conectarla a una app Java.
- Implementar endpoints REST para registrar, consultar y eliminar usuarios.

---

### CLASE 8: Práctica NoSQL y comparativa relacional vs. documental

**Objetivos:**

- Consolidar el conocimiento en bases de datos NoSQL.
- Evaluar en qué escenarios usar MongoDB vs. PostgreSQL.

**Actividades:**

- Taller práctico: construir una app de mensajería usando MongoDB.
- Comparación de rendimiento en lectura/escritura con colecciones grandes.
- Creación de consultas con filtros anidados y expresiones regulares (regex).
- Asistencia de IA para transformar estructuras rígidas en documentos flexibles.
- Discusión grupal: ¿cuándo preferir SQL? ¿cuándo es mejor usar NoSQL?

---
