package com.andre_enricco.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputUtils {

    private static final Scanner scanner = new Scanner(System.in);

    public static LocalDate lerData(String mensagem) {

        while (true) {
            System.out.print(mensagem);

            String entrada = scanner.nextLine().trim();

            try {
                return FormatoUtils.converterData(entrada);

            } catch (DateTimeParseException e) {
                System.out.println("Data inválida. Use o formato dd/MM/aaaa e tente novamente.");
            }
        }
    }

    public static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine().trim();
    }

    public static String lerTelefone(String mensagem) {
        while (true) {
            String telefone = lerTexto(mensagem);
            if (FormatoUtils.telefoneValido(telefone)) {
                return telefone;
            }
            System.out.println("Telefone inválido. Use o formato (dd) ddddd-dddd e tente novamente.");
        }
    }

    public static int lerInteiro(String mensagem) {
        while (true) {
            String entrada = lerTexto(mensagem);
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Valor invalido. Digite um numero inteiro.");
            }
        }
    }

    public static double lerDouble(String mensagem) {
        while (true) {
            String entrada = lerTexto(mensagem).replace(',', '.');
            try {
                return Double.parseDouble(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Valor invalido. Digite um numero decimal.");
            }
        }
    }
}
