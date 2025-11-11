package com.desafio.convertidormonedas;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Clase principal que contiene el menú y la lógica del conversor de monedas.
 * @author adrian
 */
public class ConvertidorMonedas {

    private static final ApiHandler apiHandler = new ApiHandler();
    private static final Scanner scanner = new Scanner(System.in);
    private static ConversionRate currentRates = null;

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("  CONVERSOR DE MONEDAS - Basado en ExchangeRate API");
        System.out.println("==================================================");

        int option = -1;
        while (option != 0) {
            displayMenu();
            try {
                if (scanner.hasNextInt()) {
                    option = scanner.nextInt();
                    scanner.nextLine(); // Consumir nueva línea
                } else {
                    System.out.println("\nERROR: Ingrese solo números para la opción del menú.");
                    scanner.nextLine();
                    continue;
                }

                switch (option) {
                    case 1 -> selectBaseCurrency();
                    case 2 -> performConversion();
                    case 0 -> System.out.println("\nGracias por usar el conversor de monedas. ¡Adiós!");
                    default -> System.out.println("\nOpción no válida. Intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nERROR: Entrada no válida. Intente de nuevo.");
                scanner.nextLine();
            } catch (IOException e) {
                System.out.println("\nERROR de API: No se pudo conectar con el servicio o la solicitud falló. Detalle: " + e.getMessage());
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n--- MENÚ PRINCIPAL ---");
        System.out.println("1. Seleccionar la moneda base (Actual: " + (currentRates == null ? "Ninguna" : currentRates.getBaseCode()) + ")");
        System.out.println("2. Realizar Conversión (Requiere moneda base)");
        System.out.println("0. Salir");
        System.out.print("Elija una opción: ");
    }

    private static void selectBaseCurrency() throws IOException {
        System.out.print("\nIngrese el código de la moneda base (Ej: USD, EUR, COP): ");
        String baseCode = scanner.nextLine().toUpperCase();

        System.out.println("Cargando tasas para " + baseCode + "...");
        currentRates = apiHandler.getRates(baseCode);

        if ("success".equalsIgnoreCase(currentRates.getResult())) {
            System.out.println("\n✅ Moneda base (" + baseCode + ") cargada exitosamente.");
        } else {
            System.out.println("\n❌ ERROR: No se pudieron cargar las tasas. Verifique el código de moneda.");
            currentRates = null;
        }
    }

    private static void performConversion() {
        if (currentRates == null) {
            System.out.println("\n⚠️ Primero debe seleccionar la moneda base (Opción 1).");
            return;
        }

        Map<String, Double> rates = currentRates.getConversionRates();
        Set<String> codes = rates.keySet();
        String baseCode = currentRates.getBaseCode();

        System.out.println("\n--- CONVERSIÓN ---");
        System.out.println("Moneda Base Actual: " + baseCode);

        System.out.print("Ingrese el código de la moneda de destino (Ej: MXN, BRL, ARS): ");
        String targetCode = scanner.nextLine().toUpperCase();

        if (!codes.contains(targetCode)) {
            System.out.println("\n❌ ERROR: Código de moneda destino '" + targetCode + "' no soportado o inválido.");
            return;
        }

        System.out.print("Ingrese la cantidad de " + baseCode + " a convertir: ");
        double amount;
        try {
            amount = scanner.nextDouble();
        } catch (InputMismatchException e) {
            System.out.println("\nERROR: Ingrese una cantidad válida (número).");
            scanner.nextLine();
            return;
        } finally {
            scanner.nextLine(); // Consumir nueva línea
        }

        double rate = rates.get(targetCode);
        double convertedAmount = amount * rate;

        System.out.println("\n=============================================");
        System.out.printf("  %.2f %s = %.2f %s%n", amount, baseCode, convertedAmount, targetCode);
        System.out.println("  (Tasa actual: 1 " + baseCode + " = " + rate + " " + targetCode + ")");
        System.out.println("=============================================");
    }
}
