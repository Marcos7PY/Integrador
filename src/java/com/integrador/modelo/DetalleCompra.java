
package com.integrador.modelo;

public class DetalleCompra {
    int idDetalleCompra;
    int idCompra;
    int idProductos;
    int cantidad;
    String precio;
    String nombre;

    public DetalleCompra() {
    }

    public DetalleCompra(int idDetalleCompra, int idCompra, int idProductos, int cantidad, String precio, String nombre) {
        this.idDetalleCompra = idDetalleCompra;
        this.idCompra = idCompra;
        this.idProductos = idProductos;
        this.cantidad = cantidad;
        this.precio = precio;
        this.nombre = nombre;
    }

    public int getIdDetalleCompra() {
        return idDetalleCompra;
    }

    public void setIdDetalleCompra(int idDetalleCompra) {
        this.idDetalleCompra = idDetalleCompra;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public int getIdProductos() {
        return idProductos;
    }

    public void setIdProductos(int idProductos) {
        this.idProductos = idProductos;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    
    
}
