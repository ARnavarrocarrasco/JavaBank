package com.JavaBank;

import java.util.Date;
import java.util.UUID;

public class Transferencia {
    private String cuentaOrigen;
    private String nombreOrigen;
    private String cuentaDestino;
    private String nombreDestino;
    private double monto;
    private String bancoDestino;
    private Moneda moneda;
    private double comision;
    private double montoFinal;
    private Date fecha;
    private Estado estado;

    public Transferencia() {
    }

    public Transferencia(String cuentaOrigen, String nombreOrigen, String cuentaDestino, String nombreDestino, double monto, String bancoDestino, Moneda moneda, double comision, double montoFinal, Date fecha, Estado estado) {
        this.cuentaOrigen = cuentaOrigen;
        this.nombreOrigen = nombreOrigen;
        this.cuentaDestino = cuentaDestino;
        this.nombreDestino = nombreDestino;
        this.monto = monto;
        this.bancoDestino = bancoDestino;
        this.moneda = moneda;
        this.comision = comision;
        this.montoFinal = montoFinal;
        this.fecha = fecha;
        this.estado = estado;
    }

    public String getNombreOrigen() {
        return nombreOrigen;
    }

    public String getNombreDestino() {
        return nombreDestino;
    }

    public String getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(String cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public String getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(String cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getMontoFinal() {
        return montoFinal;
    }

    public void setMontoFinal(double montoFinal) {
        this.montoFinal = montoFinal;
    }

    public double getComision() {
        return comision;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public String getBancoDestino() {
        return bancoDestino;
    }

    public void setBancoDestino(String bancoDestino) {
        this.bancoDestino = bancoDestino;
    }

    @Override
    public String toString() {
        return "ID: " + UUID.randomUUID() + "\n" +
                "Cuenta Origen: " + cuentaOrigen + "\n" +
                "Cuenta Destino: " + cuentaDestino + "\n" +
                "Nombre origen : " + nombreOrigen + "\n" +
                "Nombre beneficiario: " + nombreDestino + "\n" +
                "monto: " + monto + "\n" +
                "moneda: " + moneda + "\n" +
                "comision: " + comision + "\n" +
                "montoFinal: " + montoFinal + "\n" +
                "Fecha: " + fecha + "\n" +
                "Estado: " + estado;
    }
}

