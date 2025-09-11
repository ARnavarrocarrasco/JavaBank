package com.JavaBank;

public class OperacionBancaria {

    private String cuentaOrigen;
    private String cuentaDestino; // opcional (solo en transferencias)
    private Tipo tipo;
    private double monto;
    private Moneda moneda;

    public OperacionBancaria() {
    }

    // Constructor completo (transferencia)
    public OperacionBancaria(String cuentaOrigen, String cuentaDestino,
                             Tipo tipo, double monto, Moneda moneda) {
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.tipo = tipo;
        this.monto = monto;
        this.moneda = moneda;
    }

    // Constructor sin cuentaDestino (retiro o depósito)
    public OperacionBancaria(String cuentaOrigen,
                             Tipo tipo, double monto, Moneda moneda) {
        this(cuentaOrigen, null, tipo, monto, moneda);
    }

    // Getters y setters
    public String getCuentaOrigen() {
        return cuentaOrigen;
    }

    public String getCuentaDestino() {
        return cuentaDestino;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public double getMonto() {
        return monto;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    @Override
    public String toString() {
        return "OperacionBancaria{" +
                "cuentaOrigen='" + cuentaOrigen + '\'' +
                ", cuentaDestino='" + cuentaDestino + '\'' +
                ", tipo=" + tipo +
                ", monto=" + monto +
                ", moneda=" + moneda +
                '}';
    }
}

