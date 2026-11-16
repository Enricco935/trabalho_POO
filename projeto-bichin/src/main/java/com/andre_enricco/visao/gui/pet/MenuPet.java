package com.andre_enricco.visao.gui.pet;

import java.awt.event.ActionListener;

import com.andre_enricco.visao.gui.modelos.ModeloMenuVertical;

public class MenuPet extends ModeloMenuVertical {

    public MenuPet(ActionListener[] acoes) {
        super(
            new String[] {
                "Listar pets",
                "Buscar pet",
                "Adicionar pet",
                "Alterar pet",
                "Remover pet"
            },
            acoes
        );
    }
}
