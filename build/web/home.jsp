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

        <main>

            <div id="myCarousel" class="carousel slide mb-6" data-bs-ride="carousel">
                <div class="carousel-indicators">
                    <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                    <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="1" aria-label="Slide 2"></button>
                    <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="2" aria-label="Slide 3"></button>
                </div>
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <svg class="bd-placeholder-img" width="100%" height="100%" xmlns="http://www.w3.org/2000/svg" aria-hidden="true" preserveAspectRatio="xMidYMid slice" focusable="false"><rect width="100%" height="100%" fill="var(--bs-secondary-color)"/></svg>
                        <div class="container">
                            <div class="carousel-caption text-start">
                                <h1>Desc1.</h1>
                                <p class="opacity-75">Texto1</p>
                                <p><a class="btn btn-lg btn-primary" href="#">Botón1</a></p>
                            </div>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <svg class="bd-placeholder-img" width="100%" height="100%" xmlns="http://www.w3.org/2000/svg" aria-hidden="true" preserveAspectRatio="xMidYMid slice" focusable="false"><rect width="100%" height="100%" fill="var(--bs-secondary-color)"/></svg>
                        <div class="container">
                            <div class="carousel-caption">
                                <h1>Desc2.</h1>
                                <p class="opacity-75">Texto2</p>
                                <p><a class="btn btn-lg btn-primary" href="#">Botón2</a></p>
                            </div>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <svg class="bd-placeholder-img" width="100%" height="100%" xmlns="http://www.w3.org/2000/svg" aria-hidden="true" preserveAspectRatio="xMidYMid slice" focusable="false"><rect width="100%" height="100%" fill="var(--bs-secondary-color)"/></svg>
                        <div class="container">
                            <div class="carousel-caption text-end">
                                <h1>Desc3.</h1>
                                <p class="opacity-75">Texto3</p>
                                <p><a class="btn btn-lg btn-primary" href="#">Botón3</a></p>
                            </div>
                        </div>
                    </div>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#myCarousel" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">السابق</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#myCarousel" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">التالي</span>
                </button>
            </div>


            <!-- Marketing messaging and featurettes
            ================================================== -->
            <!-- Wrap the rest of the page in another container to center all the content. -->

            <div class="container marketing">

                <!-- Three columns of text below the carousel -->
                <div class="row">
                    <div class="col-lg-4">
                        <img src="assets/hombre.png" alt="Hombre" class="bd-placeholder-img rounded-circle" width="200" height="200">
                        <h2 class="fw-normal">Usuario</h2>
                        <p>Buenazo todo lo que venden acá. Compré fertilizantes para mis paltos y ya se nota la diferencia. Además, me explicaron cómo usar todo bien. Súper recomendado, volveré sin duda.</p>
                    </div><!-- /.col-lg-4 -->
                    <div class="col-lg-4">
                        <img src="assets/mujer.png" alt="Mujer" class="bd-placeholder-img rounded-circle" width="200" height="200">
                        <h2 class="fw-normal">Usuario</h2>
                        <p>Es la mejor tienda de productos agrícolas en Piura. Tienen una gran variedad de insumos y me encanta la rapidez con la que gestionan los pedidos. Mis cultivos han mejorado desde que empecé a comprar aquí. ¡Totalmente recomendable!</p>
                    </div><!-- /.col-lg-4 -->
                    <div class="col-lg-4">
                        <img src="assets/hombre.png" alt="Hombre" class="bd-placeholder-img rounded-circle" width="200" height="200">
                        <h2 class="fw-normal">Usuario</h2>
                        <p>Todo perfecto. Tienen de todo y los precios están bien para la calidad. Me ayudaron a elegir lo que necesitaba para mis tomates. Gente chévere, muy atentos</p>
                    </div><!-- /.col-lg-4 -->
                </div><!-- /.row -->
            </div>
        </main>


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>   
    </body>
</html>
