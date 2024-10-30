
package com.integrador.modelo;

public class Usuario {
    int idUsuario;
    String User;
    String Password;
    String Nombres;
    String Apellidos;
    String Correo;
    int Celular;

    public Usuario() {
    }

    public Usuario(int idUsuario, String User, String Password, String Nombres, String Apellidos, String Correo, int Celular) {
        this.idUsuario = idUsuario;
        this.User = User;
        this.Password = Password;
        this.Nombres = Nombres;
        this.Apellidos = Apellidos;
        this.Correo = Correo;
        this.Celular = Celular;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String User) {
        this.User = User;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String Nombres) {
        this.Nombres = Nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public int getCelular() {
        return Celular;
    }

    public void setCelular(int Celular) {
        this.Celular = Celular;
    }

    
    
    
    
    
}
