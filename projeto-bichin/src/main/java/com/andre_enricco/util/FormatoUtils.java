package com.andre_enricco.util;

import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import com.andre_enricco.model.Especie;

/** Regras de conversão e validação independentes da interface de entrada. */
public final class FormatoUtils {
    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/uuuu")
            .withResolverStyle(ResolverStyle.STRICT);
    private static final DateTimeFormatter FORMATO_DATA_EXIBICAO = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final String REGEX_TELEFONE = "\\(\\d{2}\\) \\d{5}-\\d{4}";

    private FormatoUtils() {
    }

    public static LocalDate converterData(String texto) throws DateTimeParseException {
        return LocalDate.parse(texto.trim(), FORMATO_DATA);
    }

    public static boolean telefoneValido(String telefone) {
        return telefone != null && telefone.trim().matches(REGEX_TELEFONE);
    }

    public static String formatarData(LocalDate data) {
        return data.format(FORMATO_DATA_EXIBICAO);
    }

    public static Especie parseEspecie(String especie) throws IllegalArgumentException{
        return Especie.valueOf(removerAcentos(especie.trim().toUpperCase()));
    }

    public static String removerAcentos(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
    }
}
