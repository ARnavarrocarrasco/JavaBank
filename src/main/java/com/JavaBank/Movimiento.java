package com.JavaBank;

import java.time.LocalDateTime;

public class Movimiento {
    private static  int contador = 0;
    private int id;
    private String numeroCuenta;
    private String numeroCuentaDestino;
    private Tipo tipo;
    private double monto;
    private LocalDateTime fecha;
    private String descripcion;

    public Movimiento(Tipo tipo, String numeroCuenta, double monto, String descripcion) {
        this.id = ++contador; // incrementa y asigna automáticamente
        this.numeroCuenta = numeroCuenta;
        this.tipo = tipo;
        this.monto = monto;
        this.fecha = LocalDateTime.now();
        this.descripcion = descripcion;
    }

    public Movimiento(String numeroCuenta, String numeroCuentaDestino, Tipo tipo, double monto, String descripcion) {
        this.numeroCuenta = numeroCuenta;
        this.numeroCuentaDestino = numeroCuentaDestino;
        this.tipo = tipo;
        this.monto = monto;
        this.fecha = LocalDateTime.now();;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Movimiento{" +
                "id='" + id + '\'' +
                ", tipo=" + tipo +
                ", monto=" + monto +
                ", fecha=" + fecha +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
