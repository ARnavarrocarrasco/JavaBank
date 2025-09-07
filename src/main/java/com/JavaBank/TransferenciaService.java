package com.JavaBank;


public class TransferenciaService {

    private AuthService authService;

    public TransferenciaService(AuthService authService) {
        this.authService = authService;
    }

    public Transferencia realizarTransferencia(Transferencia transferencia) {

        if (transferencia.getMonto() <= 0) {
            System.out.println("Monto incorrecto");
            return null;
        }

        // Conversión a soles y cálculo del monto final
        double montoConvertido = convertirAMonedaLocal(transferencia.getMonto(), transferencia.getMoneda());
        double montoFinal = montoConvertido + transferencia.getComision();
        transferencia.setMontoFinal(montoFinal);

        // Buscar cuentas
        Cuenta cuentaOrigen = authService.getCuentaPorNumero(transferencia.getCuentaOrigen());
        Cuenta cuentaDestino = authService.getCuentaPorNumero(transferencia.getCuentaDestino());

        if (cuentaOrigen == null || cuentaDestino == null) {
            System.out.println("Cuenta origen o destino no encontrada");
            return null;
        }

        // Validar saldo
        if (cuentaOrigen.getBalance() <= 0 || cuentaOrigen.getBalance() < transferencia.getMontoFinal()) {
            System.out.println("No dispone de saldo para realizar la transferencia");
            return null;
        }

        // Actualizar balances
        cuentaOrigen.setBalance(cuentaOrigen.getBalance() - transferencia.getMontoFinal());
        cuentaDestino.setBalance(cuentaDestino.getBalance() + transferencia.getMonto());

        System.out.println("Transferencia realizada con éxito");

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