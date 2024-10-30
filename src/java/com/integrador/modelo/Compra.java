
package com.integrador.modelo;

import java.io.InputStream;
import java.util.Date;


public class Compra {
    String user;
    String total;
    String receptor;
    String direccion;
    String metodoPago;
    InputStream comprobante;
    String codCompra;
    String departamento;
    String provincia;
    String distrito;
    int estado;
    int tipoDocumento;
    int nDocumento;
    Date fechaCompra;

    public Compra() {
    }

    public Compra(String user, String total, String receptor, String direccion, String metodoPago, InputStream comprobante, String codCompra, String departamento, String provincia, String distrito, int estado, int tipoDocumento, int nDocumento, Date fechaCompra) {
        this.user = user;
        this.total = total;
        this.receptor = receptor;
        this.direccion = direccion;
        this.metodoPago = metodoPago;
        this.comprobante = comprobante;
        this.codCompra = codCompra;
        this.departamento = departamento;
        this.provincia = provincia;
        this.distrito = distrito;
        this.estado = estado;
        this.tipoDocumento = tipoDocumento;
        this.nDocumento = nDocumento;
        this.fechaCompra = fechaCompra;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getReceptor() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public InputStream getComprobante() {
        return comprobante;
    }

    public void setComprobante(InputStream comprobante) {
        this.comprobante = comprobante;
    }

    public String getCodCompra() {
        return codCompra;
    }

    public void setCodCompra(String codCompra) {
        this.codCompra = codCompra;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(int tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public int getnDocumento() {
        return nDocumento;
    }

    public void setnDocumento(int nDocumento) {
        this.nDocumento = nDocumento;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
    
    
    
    

    
    
}
