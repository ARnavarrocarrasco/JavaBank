package com.JavaBank;

import java.time.LocalDateTime;
import java.util.*;

import static java.lang.System.out;

public class Cuenta {
    private String pin;
    private double balance;
    private String numeroCuenta;
    private String titular;

    private List<Movimiento> movimientos = new ArrayList<>();

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

    public void agregarMovimientos(Movimiento movimiento) {
        movimientos.add(movimiento);
    }

    public void mostrarMovimientos() {
        for (Movimiento mov: movimientos) {
            out.println(mov);
        }
    }

    public void montoTotalDepositos() {
        double sumAmount  = movimientos.stream()
                .filter(m -> m.getTipo() == Tipo.DEPOSITO)
                .mapToDouble(Movimiento::getMonto).
                sum();
        System.out.println("Total Deposits: " + sumAmount);
    }

    public void transaccionMontoMayor() {
        Movimiento highestTransaction = movimientos.stream()
                .max(Comparator.comparing(Movimiento::getMonto))
                .orElseThrow(NoSuchElementException::new);

        System.out.println("Transaccion mayor: " + highestTransaction.getTipo() + "monto: " + highestTransaction.getMonto());
    }

}
