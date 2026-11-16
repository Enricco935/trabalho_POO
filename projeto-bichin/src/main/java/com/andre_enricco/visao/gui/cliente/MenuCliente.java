package com.andre_enricco.visao.gui.cliente;

import java.awt.event.ActionListener;

import com.andre_enricco.visao.gui.modelos.ModeloMenuVertical;

public class MenuCliente extends ModeloMenuVertical {

    public MenuCliente(ActionListener[] acoes) {

        super(
            new String[] {
                "Listar clientes",
                "Buscar cliente",
                "Alterar cliente",
                "Remover cliente"
            },
            acoes
        );
    }
}