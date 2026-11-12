package com.andre_enricco.visao.gui.cliente;

import com.andre_enricco.visao.gui.MenuVertical;
import java.awt.event.ActionListener;

public class MenuCliente extends MenuVertical {

    public MenuCliente(ActionListener[] acoes) {

        super(
            new String[] {
                "Listar clientes",
                "Buscar cliente",
                "Alterar cliente",
                "Remover cliente",
                "Voltar ao menu principal"
            },
            acoes
        );
    }
}