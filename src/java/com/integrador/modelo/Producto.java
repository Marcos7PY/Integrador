
package com.integrador.modelo;

import java.io.InputStream;


public class Producto {
    int idProductos;
    int tipoProducto;
    String Nombre;
    InputStream Foto;   
    String Descripcion;
    double Precio;
    String Link;
    

    public Producto() {
    }

    public Producto(int idProductos, int tipoProducto, String Nombre, InputStream Foto, String Descripcion, double Precio, String Link) {
        this.idProductos = idProductos;
        this.tipoProducto = tipoProducto;
        this.Nombre = Nombre;
        this.Foto = Foto;
        this.Descripcion = Descripcion;
        this.Precio = Precio;
        this.Link = Link;
    }
    
    public int getIdProductos() {
        return idProductos;
    }

    public void setIdProductos(int idProductos) {
        this.idProductos = idProductos;
    }

    public int getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(int tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public InputStream getFoto() {
        return Foto;
    }

    public void setFoto(InputStream Foto) {
        this.Foto = Foto;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double Precio) {
        this.Precio = Precio;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String Link) {
        this.Link = Link;
    }

    

    

   
    }
