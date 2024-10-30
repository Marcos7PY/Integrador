<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="javax.servlet.http.HttpSession" %>

<%
    String nombreUsuario = (session != null && session.getAttribute("nombreUsuario") != null) 
                           ? (String) session.getAttribute("nombreUsuario") 
                           : null;
%>
<c:set var="tipoProducto" value="${param.tipoProducto}" />
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>AgroPiura</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="styles.css">
        <link href="css/carousel.rtl.css" rel="stylesheet">
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
                                <li><a class="dropdown-item" href="http://localhost:8080/Integrador/Controlador?accion=ListarCompras">Mis compras</a></li>
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

        <section>
            <section class="py-5">
                <div class="container px-4 px-lg-5 mt-5">
                    <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-3 justify-content-center">
                        <c:forEach var="producto" items="${productos}">
                            <div class="col mb-5">
                                <div class="card h-100">
                                    <!-- Imagen del producto -->
                                    <img class="card-img-top" src="ControladorIMG?idProductos=${producto.idProductos}" alt="${producto.nombre}" />

                                    <!-- Detalles del producto -->
                                    <div class="card-body p-4">
                                        <div class="text-center">
                                            <!-- Nombre del producto (con enlace a los detalles) -->
                                            <h5 class="fw-bolder">
                                                <a href="Controlador?accion=VerProducto&idProductos=${producto.idProductos}" style="text-decoration: none; color: inherit;">
                                                    ${producto.nombre}
                                                </a>
                                            </h5>
                                            <!-- Precio del producto -->
                                            <i>S/. ${producto.precio}</i>
                                        </div>
                                    </div>

                                    <!-- Acciones del producto (Botones) -->
                                    <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                        <div class="d-flex justify-content-between">
                                            <!-- Botón Agregar a Carrito (izquierda) -->
                                            <a href="Controlador?accion=AgregarCarrito&idProductos=${producto.idProductos}&tipoProducto=${tipoProducto}" class="btn btn-custom mt-auto">Agregar a Carrito</a>

                                            <!-- Botón Comprar (derecha) -->
                                            <a href="Controlador?accion=ComprarProducto&idProductos=${producto.idProductos}&tipoProducto=${tipoProducto}" class="btn btn-custom mt-auto">Comprar</a>


                                        </div>
                                    </div>

                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </section>

        </section>

        <footer class="bg-dark text-white text-center py-3">
            <p>&copy; 2024 AgroPiura. Todos los derechos reservados.</p>
        </footer>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>     
    </body>
</html>

