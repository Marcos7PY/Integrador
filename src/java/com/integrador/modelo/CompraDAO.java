
package com.integrador.modelo;

import com.integrador.config.Conexion;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; // Asegúrate de tener esta importación
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

public class CompraDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    public int agregar(Compra compra) {
    String sql = "INSERT INTO compra (User, Total, Receptor, Direccion, MetodoPago, Comprobante, codCompra, Estado, Departamento, Provincia, Distrito, tipoDocumento, nDocumento, fechaCompra) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    int idGenerado = -1; // Variable para almacenar el ID generado

    try {
        con = cn.getConnection(); // Obtener la conexión
        ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // Preparar la consulta con clave generada
        ps.setString(1, compra.getUser());
        ps.setString(2, compra.getTotal());
        ps.setString(3, compra.getReceptor());
        ps.setString(4, compra.getDireccion());
        ps.setString(5, compra.getMetodoPago());
        ps.setBlob(6, compra.getComprobante());
        ps.setString(7, compra.getCodCompra());
        ps.setInt(8, 0); // Estado: puedes ajustar este valor según tu lógica
        ps.setString(9, compra.getDepartamento());
        ps.setString(10, compra.getProvincia());
        ps.setString(11, compra.getDistrito());
        ps.setInt(12, compra.getTipoDocumento());
        ps.setInt(13, compra.getnDocumento());
        ps.setDate(14, new java.sql.Date(System.currentTimeMillis())); // Establecer fecha actual

        ps.executeUpdate(); // Ejecutar la consulta

        // Obtener el ID generado
        rs = ps.getGeneratedKeys();
        if (rs.next()) {
            idGenerado = rs.getInt(1); // Obtener el ID de la primera columna
        }
    } catch (Exception e) {
        e.printStackTrace(); // Manejar excepciones
    } finally {
        try {
            if (rs != null) rs.close(); // Cerrar ResultSet
            if (ps != null) ps.close(); // Cerrar PreparedStatement
            if (con != null) con.close(); // Cerrar conexión
        } catch (Exception e) {
            e.printStackTrace(); // Manejar excepciones
        }
    }
    return idGenerado; // Devolver el ID generado
}

    
    public Compra obtenerCompraPorCodigo(String codCompra) {
    Compra compra = null;
    String sql = "SELECT * FROM compra WHERE codCompra = ?";
    
    try {
        con = cn.getConnection(); // Obtener la conexión
        ps = con.prepareStatement(sql); // Preparar la consulta SQL
        ps.setString(1, codCompra); // Establecer el parámetro codCompra
        ResultSet rs = ps.executeQuery(); // Ejecutar la consulta
        
        if (rs.next()) {
            compra = new Compra();
            
            // Asignar valores a la instancia de Compra
            compra.setUser(rs.getString("User"));
            compra.setTotal(rs.getString("Total"));
            compra.setReceptor(rs.getString("Receptor"));
            compra.setDireccion(rs.getString("Direccion"));
            compra.setMetodoPago(rs.getString("MetodoPago"));
            compra.setCodCompra(rs.getString("codCompra"));
            compra.setEstado(rs.getInt("Estado"));
            compra.setDepartamento(rs.getString("Departamento"));
            compra.setProvincia(rs.getString("Provincia"));
            compra.setDistrito(rs.getString("Distrito"));
            compra.setTipoDocumento(rs.getInt("TipoDocumento"));
            compra.setnDocumento(rs.getInt("nDocumento"));
            compra.setFechaCompra(rs.getDate("fechaCompra")); // Extraer la fecha de compra
        }
    } catch (Exception e) {
        e.printStackTrace(); // Manejar excepciones
    } finally {
        try {
            if (ps != null) ps.close(); // Cerrar el PreparedStatement
            if (con != null) con.close(); // Cerrar la conexión
        } catch (Exception e) {
            e.printStackTrace(); // Manejar excepciones
        }
    }
    
    return compra; // Retornar el objeto Compra
}

       public List<Compra> obtenerCompraPorUser(String user) {
    List<Compra> compras = new ArrayList<>();
    String sql = "SELECT * FROM compra WHERE User = ?";
    
    try {
        con = cn.getConnection(); // Obtener la conexión
        ps = con.prepareStatement(sql); // Preparar la consulta SQL
        ps.setString(1, user); // Establecer el parámetro codCompra
        ResultSet rs = ps.executeQuery(); // Ejecutar la consulta
        
        while (rs.next()) {
            Compra compra = new Compra();
            // Asignar valores a la instancia de Compra
            compra.setUser(rs.getString("User"));
            compra.setTotal(rs.getString("Total"));
            compra.setReceptor(rs.getString("Receptor"));
            compra.setDireccion(rs.getString("Direccion"));
            compra.setMetodoPago(rs.getString("MetodoPago"));
            compra.setCodCompra(rs.getString("codCompra"));
            compra.setEstado(rs.getInt("Estado"));
            compra.setDepartamento(rs.getString("Departamento"));
            compra.setProvincia(rs.getString("Provincia"));
            compra.setDistrito(rs.getString("Distrito"));
            compra.setTipoDocumento(rs.getInt("TipoDocumento"));
            compra.setnDocumento(rs.getInt("nDocumento"));
            compra.setFechaCompra(rs.getDate("fechaCompra")); // Extraer la fecha de compra
            
            compras.add(compra); // Agregar a la lista
        }
    } catch (Exception e) {
        e.printStackTrace(); // Manejar excepciones
    } finally {
        try {
            if (ps != null) ps.close(); // Cerrar el PreparedStatement
            if (con != null) con.close(); // Cerrar la conexión
        } catch (Exception e) {
            e.printStackTrace(); // Manejar excepciones
        }
    }
    
    return compras; // Retornar la lista de objetos Compra
}
 public List<Compra> obtenerCompras() {
    List<Compra> compras = new ArrayList<>();
    String sql = "SELECT * FROM compra";
    
    try {
        con = cn.getConnection(); // Obtener la conexión
        ps = con.prepareStatement(sql); // Preparar la consulta SQL
        ResultSet rs = ps.executeQuery(); // Ejecutar la consulta
        
        while (rs.next()) {
            Compra compra = new Compra();
            // Asignar valores a la instancia de Compra
            compra.setUser(rs.getString("User"));
            compra.setTotal(rs.getString("Total"));
            compra.setReceptor(rs.getString("Receptor"));
            compra.setDireccion(rs.getString("Direccion"));
            compra.setMetodoPago(rs.getString("MetodoPago"));
            compra.setCodCompra(rs.getString("codCompra"));
            compra.setEstado(rs.getInt("Estado"));
            compra.setDepartamento(rs.getString("Departamento"));
            compra.setProvincia(rs.getString("Provincia"));
            compra.setDistrito(rs.getString("Distrito"));
            compra.setTipoDocumento(rs.getInt("TipoDocumento"));
            compra.setnDocumento(rs.getInt("nDocumento"));
            compra.setFechaCompra(rs.getDate("fechaCompra")); // Extraer la fecha de compra
            
            compras.add(compra); // Agregar a la lista
        }
    } catch (Exception e) {
        e.printStackTrace(); // Manejar excepciones
    } finally {
        try {
            if (ps != null) ps.close(); // Cerrar el PreparedStatement
            if (con != null) con.close(); // Cerrar la conexión
        } catch (Exception e) {
            e.printStackTrace(); // Manejar excepciones
        }
    }
    
    return compras; // Retornar la lista de objetos Compra
}

    public void listarImagen(String CodCompra, HttpServletResponse response) {
    String sql = "SELECT Comprobante FROM compra WHERE CodCompra = ?";
    InputStream inputStream = null;
    OutputStream outputStream = null;
    BufferedInputStream bufferedInputStream = null;
    BufferedOutputStream bufferedOutputStream = null;

    try {
        outputStream = response.getOutputStream();
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, CodCompra);  // Uso de PreparedStatement para evitar SQL injection
        rs = ps.executeQuery();

        if (rs.next()) {
            inputStream = rs.getBinaryStream("Comprobante");
            if (inputStream != null) {
                bufferedInputStream = new BufferedInputStream(inputStream);
                bufferedOutputStream = new BufferedOutputStream(outputStream);

                int data = 0;
                while ((data = bufferedInputStream.read()) != -1) {
                    bufferedOutputStream.write(data);
                }
            } else {
                System.out.println("No se encontró la imagen para el CodCompra: " + CodCompra);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (bufferedOutputStream != null) bufferedOutputStream.close();
            if (bufferedInputStream != null) bufferedInputStream.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


}
