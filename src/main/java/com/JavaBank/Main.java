package com.JavaBank;

import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        AuthService auth = new AuthService();
        Menu menu = new Menu();

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