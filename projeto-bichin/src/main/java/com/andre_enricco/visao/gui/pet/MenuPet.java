package com.andre_enricco.visao.gui.pet;

import com.andre_enricco.visao.gui.MenuVertical;
import java.awt.event.ActionListener;

public class MenuPet extends MenuVertical {

    public MenuPet(ActionListener[] acoes) {
        super(
            new String[] {
                "Listar pets",
                "Buscar pet",
                "Adicionar pet",
                "Alterar pet",
                "Remover pet",
                "Voltar ao menu principal"
            },
            acoes
        );
    }
}
