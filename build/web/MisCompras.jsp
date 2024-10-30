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
        <title>Mis Compras</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="css/header.css" rel="stylesheet">
        <style>
            .container {
                display: flex !important;
                flex-direction: column !important;
                align-items: center !important; /* Centra el contenido horizontalmente */
            }
            .card-header {
                background-color: #004b23;
                color: white;
            }
            .card {
                width: 1500px !important; /* Mantén el ancho deseado */
                border: 2px solid #004b23 !important; /* Cambia el color del borde de la tarjeta */
            }
            .btn-primary {
                background-color: #004b23; /* Cambia el color de fondo del botón */
                border: 2px solid #002d18; /* Cambia el color del borde del botón */
            }
            .btn-primary:hover {
                background-color: #003d1f; /* Cambia el color al pasar el mouse */
                border: 2px solid #002d18; /* Mantiene el color del borde al pasar el mouse */
            }
        </style>
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
                                    <li><a class="dropdown-item" href="http://localhost:8080/Integrador/Controlador?accion=ListarCompras">Mis compras</a></li>

                                    <li><a class="dropdown-item" href="CerrarSesion">Cerrar sesión</a></li>
                                </ul>
                            </li>
                            <% }%>




                        </div>
                    </div>
            </nav>  
        </header>
        <div class="container mt-5">
            <h1 class="text-center mb-4">Mis Compras</h1>

            <c:if test="${not empty compras}">
                <c:forEach var="compra" items="${compras}">
                    <div class="card mb-3">
                        <div class="card-header">
                            ${compra.fechaCompra}
                        </div>
                        <div class="card-body">
                            <h5 class="card-title">Código de compra: ${compra.codCompra}</h5>
                            <p class="card-text">Total: S/. ${compra.total}</p>
                            <a href="Controlador?accion=VerDetalleCompra&CodCompra=${compra.codCompra}" class="btn btn-primary">Ver Compra</a>
                        </div>
                    </div>
                </c:forEach>
            </c:if>

            <c:if test="${empty compras}">
                <div class="alert alert-warning text-center" role="alert">
                    No hay compras registradas para este usuario.
                </div>
            </c:if>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
