package com.integrador.modelo;

import com.integrador.config.Conexion;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

public class ProductoDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public Producto listarId(int idProductos) {
        Producto p = new Producto();
        String sql = "SELECT * FROM productos WHERE idProductos=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idProductos); // Evitar inyección SQL
            rs = ps.executeQuery();
            if (rs.next()) {
                p.setIdProductos(rs.getInt(1));
                p.setTipoProducto(rs.getInt(2));
                p.setNombre(rs.getString(3));
                p.setFoto(rs.getBinaryStream(4));
                p.setDescripcion(rs.getString(5));
                p.setPrecio(rs.getDouble(6));
                p.setLink(rs.getString(7));
                System.out.println("Producto encontrado: " + p.getNombre()); // Confirmar que el producto fue encontrado
            } else {
                System.out.println("No se encontró ningún producto con ID: " + idProductos); // Producto no encontrado
            }
        } catch (Exception e) {
            e.printStackTrace(); // Imprimir excepciones en consola
        }
        finally {
        try {
            if (ps != null) ps.close(); // Cerrar PreparedStatement
            if (con != null) con.close(); // Cerrar conexión
        } catch (Exception e) {
            e.printStackTrace(); // Manejar excepciones
        }
    }
        return p;
    }

    public List listar() {
        List<Producto> productos = new ArrayList();
        String sql = "select * from productos ";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto p = new Producto();
                p.setIdProductos(rs.getInt(1));
                p.setTipoProducto(rs.getInt(2));
                p.setNombre(rs.getString(3));
                p.setFoto(rs.getBinaryStream(4));
                p.setDescripcion(rs.getString(5));
                p.setPrecio(rs.getDouble(6));
                p.setLink(rs.getString(7));
                productos.add(p);
            }
        } catch (Exception e) {
        }
        finally {
        try {
            if (ps != null) ps.close(); // Cerrar PreparedStatement
            if (con != null) con.close(); // Cerrar conexión
        } catch (Exception e) {
            e.printStackTrace(); // Manejar excepciones
        }
    }
        return productos;
    }

    public void listarImagen(int idProductos, HttpServletResponse response) {
        String sql = "select * from productos where idProductos=" + idProductos;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            outputStream = response.getOutputStream();
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                inputStream = rs.getBinaryStream("Foto");
            }
            bufferedInputStream = new BufferedInputStream(inputStream);
            bufferedOutputStream = new BufferedOutputStream(outputStream);
            int i = 0;
            while ((i = bufferedInputStream.read()) != -1) {
                bufferedOutputStream.write(i);
            }
        } catch (Exception e) {
        }
        finally {
        try {
            if (ps != null) ps.close(); // Cerrar PreparedStatement
            if (con != null) con.close(); // Cerrar conexión
        } catch (Exception e) {
            e.printStackTrace(); // Manejar excepciones
        }
    }
    }

    public List<Producto> listarPorTipoProducto(int tipoProducto) {
    List<Producto> productos = new ArrayList<>();
    String sql = "SELECT * FROM productos WHERE tipoProducto = ?";
    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, tipoProducto);
        rs = ps.executeQuery();
        while (rs.next()) {
            Producto p = new Producto();
            p.setIdProductos(rs.getInt(1));
            p.setTipoProducto(rs.getInt(2));
            p.setNombre(rs.getString(3));
            p.setFoto(rs.getBinaryStream(4));
            p.setDescripcion(rs.getString(5));
            p.setPrecio(rs.getDouble(6));
            p.setLink(rs.getString(7));
            productos.add(p);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    finally {
        try {
            if (ps != null) ps.close(); // Cerrar PreparedStatement
            if (con != null) con.close(); // Cerrar conexión
        } catch (Exception e) {
            e.printStackTrace(); // Manejar excepciones
        }
    }
    return productos;
}

    
    
    
 
    public void agregar(Producto p) {
    String sql = "insert into productos(Nombre,Foto,Descripcion,Precio)values(?,?,?,?)";
    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, p.getNombre()); 
        ps.setBlob(2, p.getFoto());     
        ps.setString(3, p.getDescripcion()); 
        ps.setDouble(4, p.getPrecio());
        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace(); 
    }
}

   public void eliminar(int idProductos) {
    String sql = "DELETE FROM productos WHERE idProductos=?";
    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, idProductos);
        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
   
 public Producto obtenerProductoPorId(int idProducto) {
        Producto producto = null;
        String sql = "SELECT * FROM productos WHERE idproductos = ?"; // Ajusta según tu tabla

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idProducto);
            rs = ps.executeQuery();

            if (rs.next()) {
                producto = new Producto();
                producto.setIdProductos(rs.getInt("idproductos"));
                producto.setNombre(rs.getString("Nombre")); // Asegúrate de que este campo exista
                // Establece otros campos según sea necesario
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return producto;
    }


}
