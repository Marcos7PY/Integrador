
package com.integrador.controlador;


import com.integrador.modelo.Usuario;
import com.integrador.modelo.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Validar")
public class Validar extends HttpServlet {

    UsuarioDAO udao = new UsuarioDAO();
    Usuario usuario = new Usuario();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if(accion.equalsIgnoreCase("Ingresar")){
            String user = request.getParameter("txtUser");
            String password = request.getParameter("txtPassword");

            // Verificar si es el usuario administrador
            if("admin".equals(user) && "admin".equals(password)){
                // Crear la sesión y almacenar el nombre de usuario administrador
                request.getSession().setAttribute("nombreUsuario", "Administrador");
                request.getRequestDispatcher("Controlador?accion=ListarComprasAdmin").forward(request, response); // Cambia a la vista de administrador
                return;
            }

            // Validar el usuario con la base de datos para otros usuarios
            usuario = udao.validar(user, password);
            
            if(usuario.getUser() != null){
                // Crear la sesión y almacenar el nombre de usuario
                request.getSession().setAttribute("nombreUsuario", usuario.getUser());
                request.getRequestDispatcher("home.jsp").forward(request, response);  
            } else {
                // Si no es exitoso, redirigir al login con un mensaje de error
                request.setAttribute("error", "Usuario o contraseña incorrectos");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        }
    }
}

