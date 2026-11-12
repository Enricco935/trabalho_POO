package com.andre_enricco.visao.gui.servicoPrestado;

import com.andre_enricco.visao.gui.MenuVertical;
import java.awt.event.ActionListener;

public class MenuServicoPrestado extends MenuVertical {

    public MenuServicoPrestado(ActionListener[] acoes) {
        super(
            new String[] {
                "Listar serviços prestados",
                "Buscar serviço prestado",
                "Adicionar serviço prestado",
                "Remover serviço prestado",
                "Animais do dono",
                "Serviços por período",
                "Maior valor por cliente",
                "Serviços do cliente",
                "Voltar ao menu principal"
            },
            acoes
        );
    }
}
