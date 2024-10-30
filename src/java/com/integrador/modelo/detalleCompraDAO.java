/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.integrador.modelo;

import com.integrador.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MARCOS
 */
public class detalleCompraDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    public void agregar(DetalleCompra detalle) {
    String sql = "INSERT INTO detalle_compra (idCompra, idProductos, cantidad, Precio) VALUES (?, ?, ?, ?)";
    Connection con = null;
    PreparedStatement ps = null;

    try {
        con = cn.getConnection(); // Obtener la conexión
        ps = con.prepareStatement(sql); // Preparar la consulta
        ps.setInt(1, detalle.getIdCompra()); // Establecer idCompra
        ps.setInt(2, detalle.getIdProductos());
        ps.setInt(3, detalle.getCantidad());// Establecer idProductos
        ps.setString(4, detalle.getPrecio()); // Establecer Precio

        ps.executeUpdate(); // Ejecutar la consulta
    } catch (Exception e) {
        e.printStackTrace(); // Manejar excepciones
    } finally {
        try {
            if (ps != null) ps.close(); // Cerrar PreparedStatement
            if (con != null) con.close(); // Cerrar conexión
        } catch (Exception e) {
            e.printStackTrace(); // Manejar excepciones
        }
    }
}
    
    public List<DetalleCompra> obtenerDetallesPorCompra(int idCompra) {
    List<DetalleCompra> detalles = new ArrayList<>();
    String sql = "SELECT * FROM detalle_compra WHERE idCompra = ?";
    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, idCompra);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            DetalleCompra detalle = new DetalleCompra();
            // Asigna valores a detalle desde el ResultSet
            detalles.add(detalle);
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    return detalles;
}
   public List<DetalleCompra> obtenerDetallesPorCompraId(int idCompra) {
    List<DetalleCompra> detalles = new ArrayList<>();
    String sql = "SELECT dc.*, p.nombre AS nombreProducto " +
                 "FROM detalle_compra dc " +
                 "JOIN productos p ON dc.idProductos = p.idProductos " +
                 "WHERE dc.idCompra = ?";

    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, idCompra);
        rs = ps.executeQuery();

        while (rs.next()) {
            DetalleCompra detalle = new DetalleCompra();
            detalle.setIdProductos(rs.getInt("idProductos"));
            detalle.setPrecio(rs.getString("precio")); 
            detalle.setCantidad(rs.getInt("cantidad"));
            detalle.setNombre(rs.getString("nombreProducto")); // Asignar el nombre del producto
            detalles.add(detalle);
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    return detalles;
}

   public List<DetalleCompra> obtenerDetallesPorCodCompra(String CodCompra) {
    List<DetalleCompra> detalles = new ArrayList<>();
    String sql = "SELECT dc.*, p.nombre AS nombreProducto, c.codCompra " +
             "FROM detalle_compra dc " +
             "JOIN productos p ON dc.idProductos = p.idProductos " +
             "JOIN compra c ON dc.idCompra = c.idCompra " + 
             "WHERE c.codCompra = ?";



    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, CodCompra);
        rs = ps.executeQuery();

        while (rs.next()) {
            DetalleCompra detalle = new DetalleCompra();
            detalle.setIdProductos(rs.getInt("idProductos"));
            detalle.setPrecio(rs.getString("precio")); 
            detalle.setCantidad(rs.getInt("cantidad"));
            detalle.setNombre(rs.getString("nombreProducto")); // Asignar el nombre del producto
            detalles.add(detalle);
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    return detalles;
}
   




}
