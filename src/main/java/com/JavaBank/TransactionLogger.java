package com.JavaBank;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class TransactionLogger {
    private String logFile;

    public TransactionLogger(String logFile) {
        this.logFile = logFile;
    }

    public void logTransaction(Movimiento movimiento) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(logFile, true))) {
            bw.write(movimiento.getId() + "," +
                    movimiento.getTipo() + "," +
                    movimiento.getNumeroCuenta() + "," +
                    movimiento.getMonto() + "," +
                    movimiento.getFecha() + "," +
                    movimiento.getDescripcion());

            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error logging transaction: " + e.getMessage());
        }
    }

    public void readTransactions(String logFile) {

        try (BufferedReader br = new BufferedReader(new FileReader(logFile))) {
            List<Movimiento> movimientos = br.lines()
                    .map(line -> line.split(","))
                    .map(data -> new Movimiento(Tipo.valueOf(data[1]), data[2], Double.parseDouble(data[3]), data[5]))
                    .toList();

            movimientos.forEach(movimiento -> System.out.println("Cuenta : " + movimiento.getNumeroCuenta() + "Monto: " + movimiento.getMonto()));

        } catch (IOException e) {
            System.err.println("Error reading transactions file: " + e.getMessage());
        }
    }

    public void obtenerTransaccionPorCuenta(String logFile, Cuenta cuenta) {
        try (BufferedReader br = new BufferedReader(new FileReader(logFile))) {
            Optional<Movimiento> movimiento = br.lines()
                    .map(line -> line.split(","))
                    .map(data -> new Movimiento(Tipo.valueOf(data[1]), data[2], Double.parseDouble(data[3]), data[5]))
                    .filter(m -> cuenta.getNumeroCuenta().equals(m.getNumeroCuenta()))
                    .findFirst();
            movimiento.ifPresent(m -> System.out.println("Cuenta: " + m.getNumeroCuenta()));
        }
        catch (IOException e) {
            System.err.println("Error reading transactions file: " + e.getMessage());
        }
    }
}
