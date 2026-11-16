package com.andre_enricco;

import com.andre_enricco.application.Application;

public class Main {


    public static void main(String[] args) {
         System.out.println("Diretório de execução:");
        System.out.println(System.getProperty("user.dir"));
        Application app = new Application();
        app.iniciar();
    }
}