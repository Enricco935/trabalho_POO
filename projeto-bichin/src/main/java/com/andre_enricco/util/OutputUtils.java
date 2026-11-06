package com.andre_enricco.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OutputUtils {


    public static String formatarData(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter);
    }

}
