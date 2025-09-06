package com.JavaBank;

import java.util.Scanner;

public class Menu {
    private Scanner sc = new Scanner(System.in);



    public void showMenu(Cuenta cuenta) {
        int option;
        do {
            System.out.println("Opciones del ATM:");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Depositar dinero");
            System.out.println("3. Retirar dinero");
            System.out.println("0. Salir");
            System.out.println("Selecciona una opción:");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    cuenta.checkBalance();
                    break;
                case 2:
                    System.out.println("Ingrese el monto a depositar:");
                    double monto = sc.nextDouble();
                    cuenta.depositMoney(monto);
                    break;
                case 3:
                    System.out.println("Ingrese el monto a retirar:");
                    double retiro = sc.nextDouble();
                    cuenta.withDrawMoney(retiro);
                    break;
                case 0:
                    System.out.println("Gracias por usar el cajero. ¡Adiós!");
                    break;
                default: 
                    System.out.println("Opción inválida");
                    break;
            }

        } while (option != 0);
    }
}
