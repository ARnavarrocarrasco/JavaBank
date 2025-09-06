package com.JavaBank;

import java.util.HashMap;
import java.util.Map;

public class AuthService {

    private Map<String, Cuenta>  cuentas = new HashMap<String, Cuenta>();

    public AuthService() {
        cuentas.put("12345", new Cuenta("1111", 1500.0 ,"12345", "Anthony"));
        cuentas.put("67890", new Cuenta("2222", 1500.0 ,"67890", "Diana"));
        cuentas.put("54321", new Cuenta("3333", 1500.0 ,"54321", "Maria"));
    }

    public Cuenta login(String numeroDeCuenta, String pin) {
        Cuenta cuenta = cuentas.get(numeroDeCuenta);
        if (cuenta != null && cuenta.validarPin(pin)) {
            return cuenta;
        }
        return null;
    }

    public void registrarCuenta(Cuenta cuenta) {
        if (!cuentas.containsKey(cuenta.getNumeroCuenta())) {
            cuentas.put(cuenta.getNumeroCuenta(), cuenta);
        } else {
            System.out.println("El número de cuenta ya existe");
        }
    }
}
