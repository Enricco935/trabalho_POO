package com.andre_enricco.visao.gui.telaPrincipal;

import java.awt.event.ActionListener;

import com.andre_enricco.visao.gui.modelos.ModeloMenuVertical;

public class MenuPrincipal extends ModeloMenuVertical {

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