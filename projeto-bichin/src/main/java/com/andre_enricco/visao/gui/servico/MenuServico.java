package com.andre_enricco.visao.gui.servico;

import java.awt.event.ActionListener;

import com.andre_enricco.visao.gui.modelos.ModeloMenuVertical;

public class MenuServico extends ModeloMenuVertical {

    public MenuServico(ActionListener[] acoes) {
        super(
            new String[] {
                "Listar serviços",
                "Buscar serviço",
                "Adicionar serviço",
                "Alterar serviço",
                "Remover serviço"
            },
            acoes
        );
    }
}
