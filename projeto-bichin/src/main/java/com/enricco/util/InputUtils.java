package com.enricco.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Scanner;

public class InputUtils {

    private static final Scanner scanner = new Scanner(System.in);

    public static LocalDate lerData(String mensagem) {

        DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);

        while (true) {
            System.out.print(mensagem);

            String entrada = scanner.nextLine().trim();

            try {
                return LocalDate.parse(entrada, formatter);

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
            if (telefone.matches("\\(\\d{2}\\) \\d{5}-\\d{4}")) {
                return telefone;
            }
            System.out.println("Telefone invalido. Use o formato (dd) ddddd-ddddd e tente novamente.");
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
