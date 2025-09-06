package com.JavaBank;

public class Cuenta {
    private String pin;
    private double balance;
    private String numeroCuenta;
    private String titular;

    public Cuenta() {
    }

    public Cuenta(String pin, double balance, String numeroCuenta, String titular) {
        this.pin = pin;
        this.balance = balance;
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public boolean validarPin(String pinIngresado) {
        return this.pin.equals(pinIngresado);
    }

    public void checkBalance() {
        System.out.println("Saldo actual de: " + titular + ": S/. " + balance);
    }

    public void depositMoney(double monto) {
        if (monto > 0 ) {
            balance += monto;
            System.out.println("Depósito éxitoso: S/." + monto);
        } else {
            System.out.println("Monto insuficiente");
        }
    }

    public void withDrawMoney(double monto) {
        if (monto > 0  && balance >= monto) {
            balance -= monto;
        } else if (monto > balance) {
            System.out.println("Saldo insuficiente.");
        } else {
            System.out.println("Monto inválido.");
        }
    }
}
