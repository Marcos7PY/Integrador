<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container mt-5">
            <h1 class="text-center mb-4">Lista de compras</h1>

            <c:if test="${not empty compras}">
                <c:forEach var="compra" items="${compras}">
                    <div class="card mb-3">
                        <div class="card-header">
                            ${compra.fechaCompra}
                        </div>
                        <div class="card-body">
                            <h5 class="card-title">Código de compra: ${compra.codCompra}</h5>
                            <p class="card-text">Total: S/. ${compra.total}</p>
                            <p class="card-text">Estado: 
                             <c:choose>
                        <c:when test="${compra.estado == 0}">
                            Pago en revisión
                        </c:when>
                        <c:when test="${compra.estado == 1}">
                            Pago verificado
                        </c:when>
                        <c:when test="${compra.estado == 2}">
                            Preparando pedido
                        </c:when>
                        <c:when test="${compra.estado == 3}">
                            Pedido enviado
                        </c:when>
                        <c:when test="${compra.estado == 4}">
                            Pedido recibido
                        </c:when>
                        <c:when test="${compra.estado == 5}">
                            Pago rechazado
                        </c:when>
                        <c:otherwise>
                            Desconocido
                        </c:otherwise>
                    </c:choose>
                            </p>
                            <a href="Controlador?accion=VerDetalleCompraAdmin&CodCompra=${compra.codCompra}" class="btn btn-primary">Ver Compra</a>
                        </div>
                    </div>
                </c:forEach>
            </c:if>

            
        </div>
    </body>
</html>
