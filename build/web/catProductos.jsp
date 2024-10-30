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
        <link rel="stylesheet" href="styles.css">
        <link rel="stylesheet" href="css/catProductos.css">
        
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
                    <% } else { %>
                        <!-- Mostrar el dropdown cuando la sesión esté iniciada -->
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                <%= nombreUsuario %>
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="#">Mi perfil</a></li>
                                <li><a class="dropdown-item" href="CerrarSesion">Cerrar sesión</a></li>
                            </ul>
                        </li>
                    <% } %>




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
<section class="hero-section">
    <div class="hero-content">
        <h1>PRODUCTOS</h1>
    </div>
</section>

        <main>
    <div class="container catProductos">
        <div class="row text-center">
            <div class="col">
                <div class="image-container">
                    <img src="assets/cat/agro.png" alt="Agroquimicos" width="250" height="250">
                    <div class="item-text mt-2">
                        <a href="Controlador?accion=FiltrarPorTipoProducto&tipoProducto=5" style="text-decoration: none; color: inherit;">AGROQUÍMICOS</a>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="image-container">
                    <img src="assets/cat/arroz.png" alt="Fertilizante" width="250" height="250">
                    <div class="item-text mt-2">
                        <a href="Controlador?accion=FiltrarPorTipoProducto&tipoProducto=1" style="text-decoration: none; color: inherit;">FERTILIZANTES</a>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="image-container">
                    <img src="assets/cat/tierra.png" alt="Tierra" width="250" height="250">
                    <div class="item-text mt-2">
                        <a href="Controlador?accion=FiltrarPorTipoProducto&tipoProducto=4" style="text-decoration: none; color: inherit;">TIERRA</a>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="image-container">
                    <img src="assets/cat/papa.png" alt="Sistema de Riego" width="250" height="250">
                    <div class="item-text mt-2">
                        <a href="http://localhost:8080/Integrador/catRiego.jsp" style="text-decoration: none; color: inherit;">SISTEMA DE RIEGO</a>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="image-container">
                    <img src="assets/cat/tomate.png" alt="Herramientas" width="250" height="250">
                    <div class="item-text mt-2">
                        <a href="http://localhost:8080/Integrador/catHerramientas.jsp" style="text-decoration: none; color: inherit;">HERRAMIENTAS</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>




        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>   
    </body>
</html>
