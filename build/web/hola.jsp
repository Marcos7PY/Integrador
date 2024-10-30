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
        <title>Realizar Compra</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="styles.css">
        <style>
            body {
                font-family: 'Roboto', sans-serif;
                background-color: #f4f7fa;
            }

            .container-compra {
                margin-left: 250px;
                margin-top: 50px;
                margin-bottom: 100px;
                max-height: 800px !important;
                max-width: 600px;
                padding: 20px;
                background-color: #ffffff;
                border-radius: 8px;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            }

            h2 {
                text-align: left;
                color: #333;
                margin-bottom: 20px;
            }

            .form-group {
                margin-bottom: 20px;
            }

            label {
                display: block;
                margin-bottom: 8px;
                color: #555;
                font-weight: 500;
            }

            input[type="text"], select, input[type="file"] {
                border: 1px solid #ccc;
                border-radius: 4px;
                transition: border-color 0.3s;
                width: 100%; /* Ajuste del ancho por defecto */
            }

            input[type="text"]:focus, select:focus, input[type="file"]:focus {
                border-color: #007bff;
                outline: none;
            }

            /* Clases para el tamaño individual de los inputs */
            .input-dos-columnas {
                width: calc(50% - 10px); /* Ajusta el ancho para dejar espacio entre columnas */
            }

            .input-completo {
                width: 100%;
            }

            /* Espacio entre columnas */
            .form-row {
                display: flex;
                flex-wrap: wrap;
                gap: 10px; /* Espacio entre columnas */
            }

            button {
                width: 100%;
                background-color: #007bff;
                color: white;
                padding: 12px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                font-size: 16px;
                transition: background-color 0.3s;
            }

            button:hover {
                background-color: #0056b3;
            }

            footer {
                text-align: center;
                margin-top: 20px;
                font-size: 14px;
                color: #777;
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
                            <li class="nav-item">
                                <a class="btn btn-primary" href="login.jsp">Iniciar sesión</a>
                            </li>
                            <% } else {%>
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

        <main class="container-compra">
            <h2>Ingrese datos para finalizar la compra</h2>
            <form action="Controlador" method="POST" enctype="multipart/form-data">
                <input type="hidden" name="accion" value="FinalizarCompra">

                <div class="form-group">
                    <label for="receptor">Receptor:</label>
                    <input type="text" id="receptor" name="receptor" class="form-control" required>
                </div>

                <div class="form-group">
                    <label for="dni">DNI:</label>
                    <input type="text" id="dni" name="dni" class="form-control" required>
                </div>

                <!-- Fila 1: Departamento y Provincia -->
                <div class="form-row">
                    <div class="form-group input-dos-columnas">
                        <label for="departamento">Departamento:</label>
                        <input type="text" id="departamento" name="departamento" class="form-control" required>
                    </div>
                    <div class="form-group input-dos-columnas">
                        <label for="provincia">Provincia:</label>
                        <input type="text" id="provincia" name="provincia" class="form-control" required>
                    </div>
                </div>

                <!-- Fila 2: Distrito y Dirección -->
                <div class="form-row">
                    <div class="form-group input-dos-columnas">
                        <label for="distrito">Distrito:</label>
                        <input type="text" id="distrito" name="distrito" class="form-control" required>
                    </div>
                    <div class="form-group input-dos-columnas">
                        <label for="direccion">Dirección:</label>
                        <input type="text" id="direccion" name="direccion" class="form-control" required>
                    </div>
                </div>

                <div class="form-group">
                    <label for="metodoPago">Método de Pago:</label>
                    <select id="metodoPago" name="metodoPago" class="form-control" required>
                        <option value="">Selecciona un método</option>
                        <option value="Yape">Yape</option>
                        <option value="Plin">Plin</option>
                        <option value="Transferencia Bancaria">Transferencia bancaria</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="comprobante">Comprobante de Pago:</label>
                    <input type="file" id="comprobante" name="comprobante" accept="application/pdf,image/*" class="form-control" required>
                </div>

                <div class="form-group">
                    <button type="submit" class="btn btn-success">Enviar Compra</button>
                </div>
            </form>
        </main>



        <footer>
            <p>&copy; 2024 AgroPiura. Todos los derechos reservados.</p>
        </footer>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>   
    </body>
</html>
