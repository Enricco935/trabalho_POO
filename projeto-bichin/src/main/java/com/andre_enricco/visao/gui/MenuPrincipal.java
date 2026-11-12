package com.andre_enricco.visao.gui;

import java.awt.event.ActionListener;

public class MenuPrincipal extends MenuVertical {

    public MenuPrincipal(ActionListener[] acoes) {

        super(
            new String[] {
                "Clientes",
                "Pets",
                "Serviços",
                "Serviços Prestados",
                "Sair"
            },

            acoes
        );
    }
}