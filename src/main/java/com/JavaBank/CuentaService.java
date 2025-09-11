package com.JavaBank;

import com.JavaBank.Exceptions.IncorrectAmount;
import com.JavaBank.Exceptions.InsufficientFundException;
import com.JavaBank.Exceptions.NotFoundAccountException;

import java.time.LocalDate;
import java.util.Date;

public class CuentaService {

    private AuthService authService;
    private TransactionLogger transactionLogger;
    private static final double COMISION_FIJA = 5.0;

    public CuentaService(AuthService authService, TransactionLogger transactionLogger) {
        this.authService = authService;
        this.transactionLogger = transactionLogger;
    }

    public void checkBalance(Cuenta cuenta) {
        System.out.println("Saldo actual de: " + cuenta.getTitular() + ": S/. " + cuenta.getBalance());
    }

    private void depositMoney(Cuenta cuenta, double monto) throws IncorrectAmount{
        if (monto > 0 ) {
            cuenta.setBalance(cuenta.getBalance() + monto); ;
        } else {
            throw new IncorrectAmount("Incorrect Amount");
        }
    }

    private void withDrawMoney(Cuenta cuenta, double monto) throws InsufficientFundException, IncorrectAmount {
        if (monto > 0  && cuenta.getBalance() >= monto) {
            cuenta.setBalance(cuenta.getBalance() - monto);
        } else if (monto > cuenta.getBalance()) {
            throw new InsufficientFundException("Insufficient Fund");
        } else {
            throw new IncorrectAmount("Incorrect Amount");
        }
    }

    public Transferencia realizarTransferencia(OperacionBancaria operacionBancaria) throws IncorrectAmount, NotFoundAccountException, InsufficientFundException {

        Transferencia transferencia = new Transferencia();

        if (operacionBancaria.getMonto() <= 0) {
            throw new IncorrectAmount("Incorrect Amount");
        }

        // Conversión a soles y cálculo del monto final
        double montoConvertido = convertirAMonedaLocal(operacionBancaria.getMonto(), operacionBancaria.getMoneda());
        double montoFinal = montoConvertido + COMISION_FIJA;


        // Buscar cuentas
        Cuenta cuentaOrigen = authService.getCuentaPorNumero(operacionBancaria.getCuentaOrigen());
        Cuenta cuentaDestino = authService.getCuentaPorNumero(operacionBancaria.getCuentaDestino());

        if (cuentaDestino == null) {
            throw new NotFoundAccountException("Not found account");
        }

        // Validar saldo
        if (cuentaOrigen.getBalance() <= 0 || cuentaOrigen.getBalance() < montoFinal) {
            throw new InsufficientFundException("Insufficient Fund");
        }

        // Actualizar balances
        withDrawMoney(cuentaOrigen, montoFinal);
        depositMoney(cuentaOrigen, montoConvertido);
        cuentaOrigen.agregarMovimientos(new Movimiento(cuentaOrigen.getNumeroCuenta(), cuentaDestino.getNumeroCuenta(), Tipo.TRANSFERENCIA,  montoFinal, "Usted acaba de realizar una transferencia a la cuenta " + operacionBancaria.getCuentaDestino() + "de un monto final de: " + montoFinal));
        cuentaDestino.agregarMovimientos(new Movimiento(cuentaOrigen.getNumeroCuenta(), cuentaDestino.getNumeroCuenta(), Tipo.TRANSFERENCIA, montoConvertido, "Usted acaba de recibir una transacción de la cuenta " + operacionBancaria.getCuentaOrigen() + "de un monto : S/. " + montoConvertido));


        transferencia.setCuentaOrigen(operacionBancaria.getCuentaOrigen());
        transferencia.setNombreOrigen(cuentaOrigen.getTitular());
        transferencia.setNombreDestino(cuentaDestino.getTitular());
        transferencia.setCuentaDestino(operacionBancaria.getCuentaDestino());
        transferencia.setMonto(montoConvertido);
        transferencia.setMoneda(operacionBancaria.getMoneda());
        transferencia.setComision(COMISION_FIJA);
        transferencia.setMontoFinal(montoFinal);
        transferencia.setFecha(new Date());
        transferencia.setEstado(Estado.APROBADO);
        return transferencia;
    }

    public void performBasicTransaction(OperacionBancaria operacionBancaria) {
        Cuenta cuenta = authService.getCuentaPorNumero(operacionBancaria.getCuentaOrigen());
        try {
            switch (operacionBancaria.getTipo()) {
                case DEPOSITO -> depositMoney(cuenta, operacionBancaria.getMonto());
                case RETIRO   -> withDrawMoney(cuenta, operacionBancaria.getMonto());
                default -> throw new UnsupportedOperationException("Tipo no soportado en transacción básica");
            }
        } catch (IncorrectAmount | InsufficientFundException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if(operacionBancaria.getTipo() == Tipo.DEPOSITO) {
                String descripcion = "Se deposito a cuenta : " + cuenta.getNumeroCuenta() + "la cantidad de: S/. " + operacionBancaria.getMonto();
                Movimiento movimientoDeposito = new Movimiento(Tipo.DEPOSITO, cuenta.getNumeroCuenta(), operacionBancaria.getMonto(), descripcion);
                cuenta.agregarMovimientos(movimientoDeposito);
                transactionLogger.logTransaction(movimientoDeposito);
                System.out.println(descripcion);
            } else if (operacionBancaria.getTipo() == Tipo.RETIRO) {
                String descripcion = "Se retiro de cuenta : " + cuenta.getNumeroCuenta() + "la cantidad de: S/. " + operacionBancaria.getMonto();
                Movimiento movimientoRetiro = new Movimiento(Tipo.RETIRO, cuenta.getNumeroCuenta(), operacionBancaria.getMonto(), descripcion);
                cuenta.agregarMovimientos(movimientoRetiro);
                transactionLogger.logTransaction(movimientoRetiro);
                System.out.println(descripcion);
            }
        }
    }

    public Transferencia performTransferTransaction(OperacionBancaria operacionBancaria) {
        Transferencia transferencia = null;
        try {
            transferencia = realizarTransferencia(operacionBancaria);
        } catch (IncorrectAmount | InsufficientFundException | NotFoundAccountException e) {
            System.out.println("Error en transferencia: " + e.getMessage());
        } finally {
            if (transferencia != null) {

                System.out.println("Transferencia realizada con éxito");
            }
        }
        return transferencia;
    }



    private double convertirAMonedaLocal(double monto, Moneda moneda) {
        return switch (moneda) {
            case USD -> convertirDolaresaSoles(monto);
            case EUR -> convertirEurosASoles(monto);
            default -> monto;
        };
    }


    private double convertirDolaresaSoles(double monto) {
        double valorDolar = 3.52;
        return monto*valorDolar;
    }

    private double convertirEurosASoles(double monto) {
        double valorEuros = 4.12;
        return monto*valorEuros;
    }



}
