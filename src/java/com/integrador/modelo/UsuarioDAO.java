package com.integrador.modelo;

import com.integrador.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public Usuario validar(String User, String Password) {
        Usuario usuario = new Usuario();
        String sql = "SELECT * FROM usuarios WHERE User = ? AND Password = ?";
        try {
            con = cn.getConnection(); // Asume que tienes un método para obtener la conexión
            ps = con.prepareStatement(sql);
            ps.setString(1, User);
            ps.setString(2, Password);
            rs = ps.executeQuery();
            while (rs.next()) {
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setUser(rs.getString("User"));
                usuario.setPassword(rs.getString("Password"));
                usuario.setNombres(rs.getString("Nombres"));
                usuario.setApellidos(rs.getString("Apellidos"));
                usuario.setCorreo(rs.getString("Correo"));
                usuario.setCelular(rs.getInt("Celular"));
            }

        } catch (Exception e) {

        }
        return usuario;
    }
}
