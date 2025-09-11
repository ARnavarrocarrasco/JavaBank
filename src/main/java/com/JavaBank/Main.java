package com.JavaBank;

import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static final String LOG_FILE = "transactions.log";

    public static void main(String[] args) {
        AuthService auth = new AuthService();
        TransactionLogger transactionLogger = new TransactionLogger(LOG_FILE);
        CuentaService cuentaService = new CuentaService(auth,  transactionLogger);
        Calculador calculador = new Calculador();

        Menu menu = new Menu(auth, cuentaService, transactionLogger, calculador);

        System.out.println("=== BIENVENIDO AL CAJERO AUTOMÁTICO ===");
        System.out.println("Ingrese su número de cuenta");

        String numeroCuenta = sc.nextLine();

        System.out.println("Ingrese su pin");

        String pin = sc.nextLine();

        Cuenta cuenta = auth.login(numeroCuenta, pin);

        if (cuenta == null) {
            System.out.println("No existe cuenta con el numero cuenta " + numeroCuenta);
            return;
        }

        System.out.println("Bienvenido " +  cuenta.getTitular() + "!");
        menu.showMenu(cuenta);

    }
}