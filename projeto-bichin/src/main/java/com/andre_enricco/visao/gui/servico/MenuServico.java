package com.andre_enricco.visao.gui.servico;

import com.andre_enricco.visao.gui.MenuVertical;
import java.awt.event.ActionListener;

public class MenuServico extends MenuVertical {

    public MenuServico(ActionListener[] acoes) {
        super(
            new String[] {
                "Listar serviços",
                "Buscar serviço",
                "Adicionar serviço",
                "Alterar serviço",
                "Remover serviço",
                "Voltar ao menu principal"
            },
            acoes
        );
    }
}
