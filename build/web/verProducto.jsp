<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="javax.servlet.http.HttpSession" %>

<%
    String nombreUsuario = (session != null && session.getAttribute("nombreUsuario") != null)
            ? (String) session.getAttribute("nombreUsuario")
            : null;
%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>AgroPiura</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="css/verProducto.css" rel="stylesheet">

    </head>
    <body>
        <header>
            <div class="header-container">
                <div class="logo">
                    <img src="assets/logo.png" alt="AgroPiura">
                </div>
                <div class="contact-info">
                    <p>📞 Número fijo</p>
                    <p>📱 Celular</p>
                    <p>📍 Dirección</p>
                    <p>R.U.C</p>
                </div>
            </div>
            <nav class="navbar navbar-expand-lg" style="background-color: #004b23;">
                <div class="container-fluid">
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse d-flex justify-content-between" id="navbarNavAltMarkup">
                        <div class="navbar-nav mx-auto">
                            <a class="nav-link active" aria-current="page" href="http://localhost:8080/Integrador/home.jsp">INICIO</a>
                            <a class="nav-link" href="http://localhost:8080/Integrador/catProductos.jsp">PRODUCTOS</a>
                            <a class="nav-link" href="#">NOSOTROS</a>
                            <a class="nav-link" href="#">GALERÍA</a>
                            <a class="nav-link" href="#">CONTÁCTANOS</a>
                        </div>

                        <div class="d-flex">
                            <% if (nombreUsuario == null) { %>
                            <!-- Mostrar solo el botón de iniciar sesión si no está iniciada la sesión -->
                            <li class="nav-item">
                                <a class="btn btn-primary" href="login.jsp">Iniciar sesión</a>
                            </li>
                            <% } else {%>
                            <!-- Mostrar el dropdown cuando la sesión esté iniciada -->
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    <%= nombreUsuario%>
                                </a>
                                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <li><a class="dropdown-item" href="#">Mi perfil</a></li>
                                    <li><a class="dropdown-item" href="CerrarSesion">Cerrar sesión</a></li>
                                </ul>
                            </li>
                            <% }%>




                            <!-- Recuperar el contador del carrito -->
                            <c:set var="contador" value="${sessionScope.contador != null ? sessionScope.contador : 0}" />
                            <div class="carrito">
                                <a href="Controlador?accion=VerCarrito">(<label style="color: white">${contador}</label>)
                                    <img src="carrito.png" alt="carrito" width="40px">
                                </a>
                            </div>
                        </div>
                    </div>
            </nav>  
        </header>


        <div class="container product-container">
            <div class="row">
                <!-- Imagen del producto -->
                <div class="col-md-6 product-image">
                    <img src="ControladorIMG?idProductos=${producto.idProductos}" alt="${producto.nombre}">
                </div>
                <!-- Detalles del producto -->
                <div class="col-md-6 d-flex align-items-center">
                    <div class="product-details">
                        <h1 class="product-title">${producto.nombre}</h1>
                        <p class="product-description">${producto.descripcion}</p>
                        <h4 class="product-price">S/. ${producto.precio}</h4>
                        <button class="btn btn-custom mt-3">
                            <a href="Controlador?accion=AgregarCarrito&idProductos=${producto.idProductos}&volver=verProducto&id=${producto.idProductos}" style="color: white; text-decoration: none;">
                                Agregar a Carrito
                            </a>
                        </button>
                        <button class="btn btn-custom mt-3">
                            <a href="${producto.link}" target="_blank" style="color: white; text-decoration: none;">
                                Ficha técnica
                            </a>
                        </button>



                    </div>
                </div>
            </div>
        </div>

        <footer class="bg-dark text-white text-center py-3">
            <p>&copy; 2024 AgroPiura. Todos los derechos reservados.</p>
        </footer>

        <!-- Bootstrap JS y dependencias -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>

