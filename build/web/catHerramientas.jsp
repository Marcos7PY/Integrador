
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>AgroPiura</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="css/catProductos.css" rel="stylesheet">
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
                    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                        <div class="navbar-nav mx-auto">
                            <a class="nav-link active" aria-current="page" href="http://localhost:8080/Integrador/home.jsp">INICIO</a>
                            <a class="nav-link" href="http://localhost:8080/Integrador/catProductos.jsp">PRODUCTOS</a>
                            <a class="nav-link" href="#">NOSOTROS</a>
                            <a class="nav-link" href="#">GALERÍA</a>
                            <a class="nav-link" href="#">CONTÁCTANOS</a>
                        </div>
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
    <div class="container catProductos">
        <div class="row justify-content-center">
            <div class="col-md-3 custom-col">
                <div class="image-container">
                    <img src="assets/cat/arroz.png" alt="Fertilizante" class="img-fluid">
                    <div class="item-text"><a href="Controlador?accion=FiltrarPorTipoProducto&tipoProducto=21" style="text-decoration: none; color: inherit;">AZADÓN</a></div>
                </div>
            </div>
            <div class="col-md-3 custom-col">
                <div class="image-container">
                    <img src="assets/cat/tierra.png" alt="Tierra" class="img-fluid">
                    <div class="item-text"><a href="Controlador?accion=FiltrarPorTipoProducto&tipoProducto=22" style="text-decoration: none; color: inherit;">GUANTES</a></div>
                </div>
            </div>
            <div class="col-md-3 custom-col">
                <div class="image-container">
                    <img src="assets/cat/papa.png" alt="Tierra" class="img-fluid">
                    <div class="item-text"><a href="Controlador?accion=FiltrarPorTipoProducto&tipoProducto=23" style="text-decoration: none; color: inherit;">PALA</a></div>
                </div>
            </div>
            <div class="col-md-3 custom-col">
                <div class="image-container">
                    <img src="assets/cat/tomate.png" alt="Tierra" class="img-fluid">
                    <div class="item-text"><a href="Controlador?accion=FiltrarPorTipoProducto&tipoProducto=24" style="text-decoration: none; color: inherit;">RASTRILLO</a></div>
                </div>
            </div>
            <div class="col-md-3 custom-col">
                <div class="image-container">
                    <img src="assets/cat/palta.png" alt="Tierra" class="img-fluid">
                    <div class="item-text"><a href="Controlador?accion=FiltrarPorTipoProducto&tipoProducto=25" style="text-decoration: none; color: inherit;">TIJERA DE PODAR</a></div>
                </div>
            </div>
        </div>
    </div>
</main>




        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>   
    </body>
</html>
