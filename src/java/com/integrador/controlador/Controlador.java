package com.integrador.controlador;

import com.integrador.modelo.Carrito;
import com.integrador.modelo.Compra;
import com.integrador.modelo.CompraDAO;
import com.integrador.modelo.DetalleCompra;
import com.integrador.modelo.Producto;
import com.integrador.modelo.ProductoDAO;
import com.integrador.modelo.detalleCompraDAO;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig
public class Controlador extends HttpServlet {

    ProductoDAO pdao = new ProductoDAO();
    Producto p = new Producto();
    detalleCompraDAO detalleDAO = new detalleCompraDAO();
    CompraDAO compraDAO = new CompraDAO();
    List<Producto> productos = new ArrayList<>();
    List<Carrito> listaCarrito = new ArrayList<>();
    List<Compra> compras = new ArrayList<>();
   
    int item;
    double totalPagar = 0.0;
    int cantidad = 1;
    int idp;
    Carrito car = new Carrito();
    String idProductosStr;
    String tipoProductoStr;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        productos = pdao.listar();

        // Obtener la sesión y el contador del carrito
        HttpSession session = request.getSession();
        Integer contador = (Integer) session.getAttribute("contador");
        if (contador == null) {
            contador = 0; // Inicializar el contador si es null
        }

        switch (accion) {
            case "ComprarProducto":
                totalPagar = 0.0;  // Reiniciar el total
                idProductosStr = request.getParameter("idProductos");
                tipoProductoStr = request.getParameter("tipoProducto"); // Obtener el tipo de producto

                if (idProductosStr != null && !idProductosStr.isEmpty()) {
                    idp = Integer.parseInt(idProductosStr);
                    p = pdao.listarId(idp);

                    boolean productoExistente = false; // Variable para verificar si el producto ya está en el carrito

                    // Recorremos el carrito para verificar si ya existe el producto
                    for (Carrito c : listaCarrito) {
                        if (c.getIdProductos() == p.getIdProductos()) {
                            // Si el producto ya está, aumentamos la cantidad
                            c.setCantidad(c.getCantidad() + 1);
                            c.setSubTotal(c.getCantidad() * c.getPrecioCompra());
                            productoExistente = true;  // Marcamos que el producto ya está en el carrito
                        }
                    }
                    // Si el producto no existe en el carrito, lo agregamos como nuevo
                    if (!productoExistente) {
                        item++;
                        car = new Carrito();
                        car.setItem(item);
                        car.setIdProductos(p.getIdProductos());
                        car.setNombre(p.getNombre());
                        car.setPrecioCompra(p.getPrecio());
                        car.setCantidad(cantidad);
                        car.setSubTotal(cantidad * p.getPrecio());
                        listaCarrito.add(car);
                        contador++; // Incrementar el contador al agregar un producto nuevo
                    }

                    // Calcular el total a pagar sumando los subtotales
                    for (Carrito c : listaCarrito) {
                        totalPagar += c.getSubTotal();
                    }

                    // Actualizar el contador en la sesión
                    session.setAttribute("contador", contador);
                }

                // Volver a aplicar el filtro después de comprar
                if (tipoProductoStr != null && !tipoProductoStr.isEmpty()) {
                    request.setAttribute("productos", pdao.listarPorTipoProducto(Integer.parseInt(tipoProductoStr)));
                } else {
                    request.setAttribute("productos", productos);
                }

                request.setAttribute("carrito", listaCarrito);
                request.setAttribute("totalPagar", totalPagar);
                request.getRequestDispatcher("verCarrito.jsp").forward(request, response);
                break;

            case "AgregarCarrito":
                totalPagar = 0.0;  // Reiniciar el total
                idProductosStr = request.getParameter("idProductos");
                tipoProductoStr = request.getParameter("tipoProducto"); // Obtener el tipo de producto

                // Si tipoProducto es nulo o está vacío, asignamos un valor por defecto
                if (tipoProductoStr == null || tipoProductoStr.isEmpty()) {
                    tipoProductoStr = "1"; // Por defecto, tipoProducto = 1 (ajusta según tu necesidad)
                }

                if (idProductosStr != null && !idProductosStr.isEmpty()) {
                    idp = Integer.parseInt(idProductosStr);
                    p = pdao.listarId(idp);

                    boolean productoExistente = false;  // Variable para verificar si el producto ya está en el carrito

                    // Recorremos el carrito para verificar si ya existe el producto
                    for (Carrito c : listaCarrito) {
                        if (c.getIdProductos() == p.getIdProductos()) {
                            // Si el producto ya está, aumentamos la cantidad
                            c.setCantidad(c.getCantidad() + 1);
                            c.setSubTotal(c.getCantidad() * c.getPrecioCompra());
                            productoExistente = true;  // Marcamos que el producto ya está en el carrito
                            break;
                        }
                    }

                    // Si el producto no existe en el carrito, lo agregamos como nuevo
                    if (!productoExistente) {
                        item++;
                        car = new Carrito();
                        car.setItem(item);
                        car.setIdProductos(p.getIdProductos());
                        car.setNombre(p.getNombre());
                        car.setPrecioCompra(p.getPrecio());
                        car.setCantidad(cantidad);
                        car.setSubTotal(cantidad * p.getPrecio());
                        listaCarrito.add(car);
                        contador++; // Incrementar el contador al agregar un producto nuevo
                    }

                    // Calcular el total a pagar sumando los subtotales
                    for (Carrito c : listaCarrito) {
                        totalPagar += c.getSubTotal();
                    }

                    // Actualizar el contador en la sesión
                    session.setAttribute("contador", contador);
                }

                // Aplicar el filtro de productos por tipo
                request.setAttribute("productos", pdao.listarPorTipoProducto(Integer.parseInt(tipoProductoStr)));

                // Mantener el filtro en la redirección
                request.getRequestDispatcher("index.jsp?tipoProducto=" + tipoProductoStr).forward(request, response);
                break;

            case "Delete":
                int idproducto = Integer.parseInt(request.getParameter("idp"));
                for (int i = 0; i < listaCarrito.size(); i++) {
                    if (listaCarrito.get(i).getIdProductos() == idproducto) {
                        listaCarrito.remove(i); // Elimina el producto del carrito
                        contador--; // Decrementar el contador al eliminar un producto
                        break; // Termina el ciclo una vez que lo elimina
                    }
                }

                // Recalcula el total después de eliminar el producto
                totalPagar = 0.0;
                for (Carrito c : listaCarrito) {
                    totalPagar += c.getSubTotal();
                }

                // Actualiza el contador en la sesión
                session.setAttribute("contador", contador);

                // Actualiza los atributos para enviarlos de vuelta a la vista
                request.setAttribute("carrito", listaCarrito);
                request.setAttribute("totalPagar", totalPagar);

                // Redirige a la vista actualizada del carrito
                request.getRequestDispatcher("verCarrito.jsp").forward(request, response);
                break;

            case "VerProducto":
                try {
                int idProducto = Integer.parseInt(request.getParameter("idProductos"));
                Producto producto = pdao.listarId(idProducto);

                if (producto.getIdProductos() == 0) {
                    throw new IllegalArgumentException("Producto no encontrado");
                }

                request.setAttribute("producto", producto);
                request.setAttribute("contador", contador); // Mostrar el contador en la vista
                request.getRequestDispatcher("verProducto.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            }
            break;

            case "VerCarrito":
                request.setAttribute("carrito", listaCarrito);
                request.setAttribute("totalPagar", totalPagar);
                request.getRequestDispatcher("verCarrito.jsp").forward(request, response);
                break;

            case "ActualizarCantidad":
                int idpro = Integer.parseInt(request.getParameter("idp"));
                int cant = Integer.parseInt(request.getParameter("Cantidad"));

                double subtotal = 0.0;
                // Actualizar la cantidad y subtotal del producto
                for (int i = 0; i < listaCarrito.size(); i++) {
                    if (listaCarrito.get(i).getIdProductos() == idpro) {
                        listaCarrito.get(i).setCantidad(cant);
                        subtotal = listaCarrito.get(i).getPrecioCompra() * cant;
                        listaCarrito.get(i).setSubTotal(subtotal);
                    }
                }

                // Recalcular el total general del carrito
                totalPagar = 0.0;
                for (Carrito c : listaCarrito) {
                    totalPagar += c.getSubTotal();
                }

                // Enviar subtotal y total como respuesta
                response.setContentType("application/json");
                response.getWriter().write("{\"subtotal\": " + subtotal + ", \"totalPagar\": " + totalPagar + "}");
                break;

            case "admin":
                productos = pdao.listar();  // Obtener la lista de productos
                request.setAttribute("productos", productos);  // Pasar la lista de productos a la vista
                request.getRequestDispatcher("admin.jsp").forward(request, response);
                break;

            case "Guardar":
                InputStream inputStream = null;
                try {
                    // Obtener parámetros del formulario
                    String nom = request.getParameter("txtNom");
                    Part part = request.getPart("fileFoto"); // Obtener archivo
                    inputStream = null;

                    if (part != null) {
                        inputStream = part.getInputStream(); // Obtener el InputStream del archivo subido
                    }

                    String desc = request.getParameter("txtDesc");
                    Double precio = Double.parseDouble(request.getParameter("txtPrecio"));

                    Producto p = new Producto();
                    p.setNombre(nom);
                    p.setFoto(inputStream);
                    p.setDescripcion(desc);
                    p.setPrecio(precio);

                    pdao.agregar(p);

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                request.setAttribute("productos", productos);
                response.sendRedirect("Controlador?accion=admin");
                break;

            case "Borrar":
                String idProductoStr = request.getParameter("idProducto");

                if (idProductoStr != null && !idProductoStr.isEmpty()) {
                    int idProductoEliminar = Integer.parseInt(idProductoStr);
                    pdao.eliminar(idProductoEliminar);
                } else {
                    System.out.println("El valor de idProducto es nulo o vacío.");
                }

                response.sendRedirect("Controlador?accion=admin");
                break;
               
            case "FiltrarPorTipoProducto":
                tipoProductoStr = request.getParameter("tipoProducto");
                if (tipoProductoStr != null && !tipoProductoStr.isEmpty()) {
                    int tipoProducto = Integer.parseInt(tipoProductoStr);
                    productos = pdao.listarPorTipoProducto(tipoProducto); // Método que crearemos en el DAO
                }
                request.setAttribute("productos", productos);
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;

            case "FinalizarCompra":
                inputStream = null;
                int idCompraGenerada = 0;
                try {
                    // Obtener parámetros del formulario
                    String receptor = request.getParameter("receptor");
                    String direccion = request.getParameter("direccion");
                    String metodoPago = request.getParameter("metodoPago");
                    int numDocumento = Integer.parseInt(request.getParameter("dni"));
                    int tipoDocumento = Integer.parseInt(request.getParameter("tipoDocumento"));
                    String departamento = request.getParameter("departamento");
                    String provincia = request.getParameter("provincia");
                    String distrito = request.getParameter("distrito");

                    // Calcular el total del carrito
                    double totalPagar = 0.0;
                    for (Carrito c : listaCarrito) {
                        totalPagar += c.getSubTotal();
                    }

                    // Obtener el usuario actual de la sesión
                    session = request.getSession();
                    String user = (String) session.getAttribute("nombreUsuario");

                    // Generar un código único para la compra (CodCompra)
                    String codCompra = generarCodigoCompra();

                    // Obtener archivo del comprobante
                    Part part = request.getPart("comprobante");
                    if (part != null && part.getSize() > 0) {
                        inputStream = part.getInputStream();
                    }

                    // Crear objeto Compra
                    Compra compra = new Compra();
                    compra.setUser(user);
                    compra.setTotal(String.valueOf(totalPagar));
                    compra.setReceptor(receptor);
                    compra.setDireccion(direccion);
                    compra.setMetodoPago(metodoPago);
                    compra.setComprobante(inputStream);
                    compra.setCodCompra(codCompra);
                    compra.setDepartamento(departamento);
                    compra.setProvincia(provincia);
                    compra.setDistrito(distrito);
                    compra.setTipoDocumento(tipoDocumento);
                    compra.setnDocumento(numDocumento);

                    // Guardar la compra y obtener el ID generado
                    idCompraGenerada = compraDAO.agregar(compra);
                    detalleCompraDAO detalleCompraDAO = new detalleCompraDAO();

                    // Guardar detalles de la compra
                    for (Carrito c : listaCarrito) {
                        DetalleCompra detalle = new DetalleCompra();
                        detalle.setIdCompra(idCompraGenerada);
                        detalle.setIdProductos(c.getIdProductos());
                        detalle.setPrecio(String.valueOf(c.getPrecioCompra()));
                        detalle.setCantidad(c.getCantidad());
                        detalle.setNombre(c.getNombre());
                        detalleCompraDAO.agregar(detalle);
                    }

                    // Obtener la fecha de compra después de la inserción
                    Compra compraGuardada = compraDAO.obtenerCompraPorCodigo(codCompra); // Obtener la compra basada en el código de compra

                    // Pasar la fecha de compra al JSP
                    request.setAttribute("fecha", compraGuardada.getFechaCompra());

                    // Limpiar carrito
                    listaCarrito.clear();
                    session.setAttribute("contador", 0);

                    // Obtener detalles de la compra
                    List<DetalleCompra> detalles = detalleCompraDAO.obtenerDetallesPorCompraId(idCompraGenerada);
                    request.setAttribute("detalles", detalles);

                    request.setAttribute("id_transaccion", compraGuardada.getCodCompra());
                    request.setAttribute("estado", compraGuardada.getEstado());
                    request.setAttribute("total", compraGuardada.getTotal());
                    request.setAttribute("fecha", compraGuardada.getFechaCompra());
                    request.setAttribute("receptor", compraGuardada.getReceptor());
                    request.setAttribute("tipo", compraGuardada.getTipoDocumento());
                    request.setAttribute("ndocumento", compraGuardada.getnDocumento());
                    request.setAttribute("metodo", compraGuardada.getMetodoPago());
                    request.setAttribute("depa", compraGuardada.getDepartamento());
                    request.setAttribute("provincia", compraGuardada.getProvincia());
                    request.setAttribute("distrito", compraGuardada.getDistrito());
                    request.setAttribute("direccion", compraGuardada.getDireccion());
                request.getRequestDispatcher("DetalleCompra.jsp").forward(request, response);

                    request.getRequestDispatcher("DetalleCompra.jsp").forward(request, response);

                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("error", "Error al finalizar la compra: " + e.getMessage());
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                } finally {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;

            case "ListarCompras":
                session = request.getSession();
                String user = (String) session.getAttribute("nombreUsuario");       
                 compras = compraDAO.obtenerCompraPorUser(user);
                 request.setAttribute("compras", compras);
                request.getRequestDispatcher("MisCompras.jsp").forward(request, response);
                break;
                
                case "VerDetalleCompra":
                try {
                String CodCompra = request.getParameter("CodCompra");
                Compra compraGuardada = compraDAO.obtenerCompraPorCodigo(CodCompra); 
                List<DetalleCompra> detalles = detalleDAO.obtenerDetallesPorCodCompra(CodCompra);
                request.setAttribute("detalles", detalles);
                request.setAttribute("id_transaccion", compraGuardada.getCodCompra());
                    request.setAttribute("estado", compraGuardada.getEstado());
                    request.setAttribute("total", compraGuardada.getTotal());
                    request.setAttribute("fecha", compraGuardada.getFechaCompra());
                    request.setAttribute("receptor", compraGuardada.getReceptor());
                    request.setAttribute("tipo", compraGuardada.getTipoDocumento());
                    request.setAttribute("ndocumento", compraGuardada.getnDocumento());
                    request.setAttribute("metodo", compraGuardada.getMetodoPago());
                    request.setAttribute("depa", compraGuardada.getDepartamento());
                    request.setAttribute("provincia", compraGuardada.getProvincia());
                    request.setAttribute("distrito", compraGuardada.getDistrito());
                    request.setAttribute("direccion", compraGuardada.getDireccion());
                request.getRequestDispatcher("DetalleCompra.jsp").forward(request, response);
                    
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            }
            break;
            
            case "ListarComprasAdmin":
                 compras = compraDAO.obtenerCompras();
                 request.setAttribute("compras", compras);
                request.getRequestDispatcher("admin/listarCompras.jsp").forward(request, response);
                break;
            
            case "VerDetalleCompraAdmin":
                try {
                String CodCompra = request.getParameter("CodCompra");
                Compra compraGuardada = compraDAO.obtenerCompraPorCodigo(CodCompra); 
                List<DetalleCompra> detalles = detalleDAO.obtenerDetallesPorCodCompra(CodCompra);
                request.setAttribute("detalles", detalles);
                request.setAttribute("id_transaccion", compraGuardada.getCodCompra());
                    request.setAttribute("estado", compraGuardada.getEstado());
                    request.setAttribute("total", compraGuardada.getTotal());
                    request.setAttribute("fecha", compraGuardada.getFechaCompra());
                    request.setAttribute("receptor", compraGuardada.getReceptor());
                    request.setAttribute("tipo", compraGuardada.getTipoDocumento());
                    request.setAttribute("ndocumento", compraGuardada.getnDocumento());
                    request.setAttribute("metodo", compraGuardada.getMetodoPago());
                    request.setAttribute("depa", compraGuardada.getDepartamento());
                    request.setAttribute("provincia", compraGuardada.getProvincia());
                    request.setAttribute("distrito", compraGuardada.getDistrito());
                    request.setAttribute("direccion", compraGuardada.getDireccion());
                request.getRequestDispatcher("admin/DetalleCompraAdmin.jsp").forward(request, response);
                    
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            }
            break;
                
                
            default:               
                request.setAttribute("productos", productos);
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
        }
    }

    private String generarCodigoCompra() {
        return "COMP" + System.currentTimeMillis(); // Ejemplo simple, genera un código basado en el tiempo actual
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
