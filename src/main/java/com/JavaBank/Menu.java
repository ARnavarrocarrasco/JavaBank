package com.JavaBank;

import java.util.Scanner;

public class Menu {
    private Scanner sc = new Scanner(System.in);
    private AuthService authService;
    private TransferenciaService transferenciaService;

    public Menu(AuthService authService, TransferenciaService transferenciaService) {
        this.authService = authService;
        this.transferenciaService = transferenciaService;
    }


    public void showMenu(Cuenta cuenta) {
        int option;
        do {
            System.out.println("Opciones del ATM:");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Depositar dinero");
            System.out.println("3. Retirar dinero");
            System.out.println("4. Realizar transferencia internacional");
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
                case 4:
                    System.out.println("=== Realizar transferencia ===");
                    String cuentaOrigen = cuenta.getNumeroCuenta();

                    sc.nextLine(); // limpiar buffer ANTES de leer con nextLine()
                    System.out.print("Número de cuenta destino: ");
                    String cuentaDestino = sc.nextLine();

                    System.out.print("Monto a transferir: ");
                    double montoTranferencia = sc.nextDouble();

                    System.out.print("Moneda (PEN / USD / EUR): ");
                    sc.nextLine(); // limpiar buffer otra vez antes de leer texto
                    String monedaInput = sc.nextLine().toUpperCase();
                    Moneda moneda = Moneda.valueOf(monedaInput);

                    // Crear la transferencia
                    Transferencia transferencia = new Transferencia();
                    transferencia.setCuentaOrigen(cuentaOrigen);
                    transferencia.setCuentaDestino(cuentaDestino);
                    transferencia.setMonto(montoTranferencia);
                    transferencia.setMoneda(moneda);
                    transferencia.setComision(5.0); // ejemplo de comisión fija

                    // Realizar la transferencia a través del servicio

                    Transferencia resultado = transferenciaService.realizarTransferencia(transferencia);

                    if (resultado != null) {
                        System.out.println("Transferencia exitosa:");
                        System.out.println(resultado); // usa tu toString() de Transferencia
                    } else {
                        System.out.println("La transferencia no pudo realizarse.");
                    }
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
