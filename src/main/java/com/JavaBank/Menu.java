package com.JavaBank;

import java.util.Scanner;

public class Menu {
    private Scanner sc = new Scanner(System.in);
    private AuthService authService;
    private Calculador calculador;
    private CuentaService cuentaService;
    private Moneda moneda;
    private TransactionLogger transactionLogger;
    private static final String LOG_FILE = "transactions.log";

    public Menu(AuthService authService, CuentaService cuentaService, TransactionLogger transactionLogger,  Calculador calculador) {
        this.authService = authService;
        this.cuentaService = cuentaService;
        this.transactionLogger = transactionLogger;
        this.calculador = calculador;
    }


    public void showMenu(Cuenta cuenta) {
        int option;
        do {
            System.out.println("Opciones del ATM:");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Depositar dinero");
            System.out.println("3. Retirar dinero");
            System.out.println("4. Realizar transferencia internacional");
            System.out.println("5. Ver mis movimientos");
            System.out.println("6. Calculadora");
            System.out.println("0. Salir");
            System.out.println("Selecciona una opción:");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    cuentaService.checkBalance(cuenta);
                    break;
                case 2:
                    moneda = seleccionarMoneda();
                    if (moneda == null) {
                        // Usuario decidió regresar al menú principal
                        break;
                    }
                    System.out.println("Ingrese el monto a depositar:");
                    double monto = sc.nextDouble();
                    cuentaService.performBasicTransaction(new OperacionBancaria(cuenta.getNumeroCuenta(), Tipo.DEPOSITO, monto, moneda));
                    break;
                case 3:
                    moneda = seleccionarMoneda();
                    if (moneda == null) {
                        // Usuario decidió regresar al menú principal
                        break;
                    }
                    System.out.println("Ingrese el monto a retirar:");
                    double retiro = sc.nextDouble();
                    cuentaService.performBasicTransaction(new OperacionBancaria(cuenta.getNumeroCuenta(), Tipo.RETIRO, retiro, moneda));
                    break;
                case 4:

                    System.out.println("=== Realizar transferencia ===");
                    String cuentaOrigen = cuenta.getNumeroCuenta();

                    sc.nextLine(); // limpiar buffer ANTES de leer con nextLine()
                    System.out.print("Número de cuenta destino: ");
                    String cuentaDestino = sc.nextLine();

                    System.out.print("Monto a transferir: ");
                    double montoTranferencia = sc.nextDouble();

                    moneda = seleccionarMoneda();

                    if (moneda == null) {
                        // Usuario decidió regresar al menú principal
                        break;
                    }
                    // Realizar la transferencia a través del servicio

                    Transferencia resultado = cuentaService.performTransferTransaction(new OperacionBancaria(cuentaOrigen, cuentaDestino, Tipo.TRANSFERENCIA, montoTranferencia, moneda));

                    if (resultado != null) {
                        System.out.println("Transferencia exitosa:");
                        System.out.println(resultado); // usa tu toString() de Transferencia
                    } else {
                        System.out.println("La transferencia no pudo realizarse.");
                    }
                    break;
                case 5:
                    System.out.println("=== Lista de movimientos ===");
                    transactionLogger.obtenerTransaccionPorCuenta(LOG_FILE, cuenta);
                    break;
                case 6:
                    System.out.println("=== Bienvenido a la calculadora BANCAL ===");
                    calculador.start();
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

    private Moneda seleccionarMoneda() {
        Moneda monedaSeleccionada = null;

        while (monedaSeleccionada == null) {
            System.out.println("Seleccione la moneda:");
            System.out.println("1. Soles (PEN)");
            System.out.println("2. Dólares (USD)");
            System.out.println("3. Euros (EUR)");
            System.out.println("0. Regresar");

            int opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> monedaSeleccionada = Moneda.PEN;
                case 2 -> monedaSeleccionada = Moneda.USD;
                case 3 -> monedaSeleccionada = Moneda.EUR;
                case 0 -> {
                    System.out.println("Retornando a las opciones del menú principal.");
                    return null; // null para indicar que el usuario no eligió nada
                }
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }
        }

        return monedaSeleccionada;
    }

}
