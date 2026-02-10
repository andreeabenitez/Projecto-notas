# Proyecto: App de Notas con Spring Boot y Thymeleaf

## Descripción

Esta aplicación web permite a los usuarios **crear, leer, actualizar y eliminar notas** (CRUD) de manera sencilla.  
Está desarrollada con **Java**, **Spring Boot**, **Thymeleaf** y utiliza **H2 Database** en memoria para persistencia.  

El proyecto sigue el patrón **MVC (Modelo-Vista-Controlador)** para separar responsabilidades, facilitando el mantenimiento y escalabilidad.

---

## Tecnologías utilizadas

- **Java 17**
- **Spring Boot 3.x**
  - Spring Web
  - Spring Data JPA
  - Spring Boot DevTools
  - Spring Boot Security
- **Thymeleaf** (plantillas HTML)
- **H2 Database** (base de datos en memoria)
- **Maven** (gestión de dependencias)
- **Bootstrap 5** (estilos de interfaz)

---

## Estructura del proyecto
src/main/java/notas/

├── controller/ → Controladores (manejan las URLs y peticiones)

├── model/ → Entidades de la base de datos (@Entity)

├── repository/ → Repositorios JPA (acceso a datos)

├── service/ → Lógica de negocio

└── NotasApplication.java → Clase principal que arranca la aplicación


src/main/resources/

├── templates/ → Archivos HTML (index.html, formulario.html)

└── application.properties → Configuración de Spring Boot y base de datos



---

## Funcionalidades

- Listar todas las notas
- Crear una nueva nota
- Editar una nota existente
- Eliminar una nota
- Visualización amigable con Bootstrap
- Base de datos H2 para pruebas rápidas
- MVC separado (modelo, vista y controlador)

---

## Requisitos

- JDK 17+
- Maven 3+
- Navegador web moderno (Chrome, Edge, Firefox)

---

## Configuración y ejecución

Clona el repositorio:

git clone <URL_DEL_REPOSITORIO>
cd nombre-del-proyecto

Compila el proyecto:

mvn clean package


Ejecuta la aplicación:

mvn spring-boot:run


Abre tu navegador y accede a:

http://localhost:8080/notas

---

## Archivos principales

- Archivo / Carpeta	Descripción
- NotasApplication.java	Clase principal que inicia Spring Boot
- model/Nota.java	Representa la entidad Nota en la base de datos
- repository/NotaRepository.java	Interfaz para operaciones CRUD sobre Nota
- service/NotaService.java	Contiene la lógica de negocio para notas
- controller/NotaController.java	Controla las rutas y comunicación con las vistas
- templates/index.html	Lista todas las notas
- templates/formulario.html	Formulario para crear/editar notas
- application.properties	Configuración de H2 y Spring Boot

---

## Patrones y buenas prácticas

- Patrón MVC (Separación de Modelo, Vista y Controlador)
- Uso de Servicios para lógica de negocio
- Repositorio JPA para abstraer acceso a la base de datos
- DTOs opcionales para futuras implementaciones de API REST
- Thymeleaf para vistas dinámicas seguras
- Autenticación y autorización (Spring Security)

---

## Mejoras futuras

- Implementar MySQL o PostgreSQL en lugar de H2
- Agregar paginación y búsqueda de notas
- Soporte para adjuntar imágenes o archivos a las notas
- Implementar API REST con DTOs para un frontend independiente

---

## Autor

Andrea Benitez Brenes
