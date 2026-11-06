package com.enricco;

import com.enricco.application.Application;

public class Main {
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        Application app = new Application();
        app.iniciar();

    }
}