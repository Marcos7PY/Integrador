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
        <title>Detalle de compra</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="styles.css">
        <style>
            /* Estilo general */
            body {
                font-family: Arial, sans-serif;
            }

            /* Contenedor principal para la tabla y los detalles */
            .detalle-compra-container {
                display: flex;
                justify-content: space-between;
            }

            /* Estilo para la sección de detalles */
            .detalle-compra-info {
                margin-top: 50px;
                margin-left: 250px; /* Ajusta el margen para centrar */
                width: 25%; /* Reduce el ancho */
                border: 1px solid #dcdcdc;
                padding: 15px; /* Reduce el padding */
                border-radius: 5px;
                background-color: #f9f9f9;
            }

            .detalle-compra-info h1 {
                font-size: 1.4em;
                margin-bottom: 10px;
                border-bottom: 2px solid #e0e0e0;
                padding-bottom: 10px;
            }

            .detalle-compra-info p {
                margin: 5px 0; /* Reduce el margen */
            }

            /* Tabla de productos */
            .detalle-compra-productos {
                width: 40%; /* Aumenta el ancho */
                margin-top: 50px;
                margin-right: 250px; /* Ajusta el margen para centrar */
            }

            .detalle-compra-productos table {
                width: 100%;
                border-collapse: collapse;
            }

            .detalle-compra-productos th,
            .detalle-compra-productos td {
                text-align: center;
                padding: 4px; /* Reduce el padding en celdas */
                border-bottom: 1px solid #e0e0e0;
            }

            .detalle-compra-productos th {
                background-color: #f2f2f2;
                font-weight: bold;
            }

            /* Precios alineados a la derecha */
            .detalle-compra-productos td:nth-child(2),
            .detalle-compra-productos td:nth-child(4) {
                text-align: center;
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
                                    <li><a class="dropdown-item" href="#">Mi perfil</a></li>
                                    <li><a class="dropdown-item" href="CerrarSesion">Cerrar sesión</a></li>
                                </ul>
                            </li>
                            <% }%>
                        </div>
                    </div>
            </nav>  
        </header>
        <div class="detalle-compra-container">
            <!-- Detalles de la compra -->
            <div class="detalle-compra-info">
                <h1>Detalle de compra</h1>
                <p><strong>ID Transacción:</strong> ${id_transaccion}</p> <!-- CodCompra -->
                <p><strong>Fecha de compra:</strong> ${fecha}</p>
                <p><strong>Total:</strong> S/ ${total}</p> <!-- Total de la compra -->
                <p><strong>Receptor: </strong> ${receptor}</p>
                <p><strong>Tipo de documento:</strong><c:choose>
                        <c:when test="${tipo == 1}">
                            DNI
                        </c:when>
                        <c:when test="${tipo == 2}">
                            RUC
                        </c:when>
                        <c:when test="${tipo == 3}">
                            Otro
                        </c:when>
                        <c:otherwise>
                            Desconocido
                        </c:otherwise>
                    </c:choose></p>
                <p><strong>N° de documento:</strong> ${ndocumento}</p>
                <p><strong>Departamento:</strong> ${depa}</p>
                <p><strong>Provincia:</strong> ${provincia}</p>
                <p><strong>Distrito:</strong> ${distrito}</p>
                <p><strong>Dirección:</strong> ${direccion}</p>
                <p><strong>Método de pago:</strong> ${metodo}</p>
                <p><strong>Estado del pedido:</strong>
                    <c:choose>
                        <c:when test="${estado == 0}">
                            Pago en revisión
                        </c:when>
                        <c:when test="${estado == 1}">
                            Pago verificado
                        </c:when>
                        <c:when test="${estado == 2}">
                            Preparando pedido
                        </c:when>
                        <c:when test="${estado == 3}">
                            Pedido enviado
                        </c:when>
                        <c:when test="${estado == 4}">
                            Pedido recibido
                        </c:when>
                        <c:when test="${estado == 5}">
                            Pago rechazado
                        </c:when>
                        <c:otherwise>
                            Desconocido
                        </c:otherwise>
                    </c:choose>
                </p>
            </div>

            <!-- Tabla de productos -->
            <div class="detalle-compra-productos">
                <table>
                    <tr>
                        <th>Producto</th>
                        <th>Precio</th>
                        <th>Cantidad</th>
                        <th>Subtotal</th>
                    </tr>
                    <c:forEach var="detalle" items="${detalles}">
                        <tr>
                            <td>
                                <div style="display: flex; align-items: center;">
                                    <img src="ControladorIMG?idProductos=${detalle.idProductos}" alt="" class="product-image" style="width: 80px; height: 80px; margin-right: 10px;"> <!-- Aumentar el tamaño de la imagen -->
                                    <span>${detalle.nombre}</span> <!-- Mostrar el nombre del producto -->
                                </div>
                            </td>
                            <td>S/ ${detalle.precio}</td>
                            <td>${detalle.cantidad}</td>
                            <td>S/ ${detalle.cantidad * detalle.precio}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>