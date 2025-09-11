package com.JavaBank;

import java.util.Scanner;

public class Calculador {

    private Scanner sc = new Scanner(System.in);

    public void start() {
        System.out.println("Introduce el primer número: ");
        double num1 = sc.nextDouble();
        System.out.println("Introduce el segundo numero: ");
        double num2 = sc.nextDouble();

        System.out.println("¿Qué operación desea realizar ? : ( + , - , * , / ");
        String operacion = sc.next();

        double resultado;
        switch (operacion) {
            case "+":
                resultado = sumar(num1, num2);
                System.out.println(resultado);
                break;
            case  "-":
                resultado = restar(num1, num2);
                System.out.println(resultado);
                break;
            case "*":
                resultado = multiplicar(num1, num2);
                System.out.println(resultado);
                break;
            case "/":
                resultado = dividir(num1, num2);
                System.out.println(resultado);
                break;
            default:
                System.out.println("Opción incorrecta");
                break;
        }
    }

    public double sumar(double num1, double num2){
        return num1 + num2;
    }

    public double restar(double num1, double num2){
        return num1 - num2;
    }

    public double multiplicar(double num1, double num2){
        return num1 * num2;
    }

    public double dividir(double num1, double num2){
        if (num2 != 0){
            return num1 / num2;
        } else {
            throw  new ArithmeticException("Operador 2 no válido");
        }
    }

}
