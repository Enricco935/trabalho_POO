package com.andre_enricco.visao.gui.servicoPrestado;

import java.awt.event.ActionListener;

import com.andre_enricco.visao.gui.modelos.ModeloMenuVertical;

public class MenuServicoPrestado extends ModeloMenuVertical {

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
                "Serviços do cliente"
            },
            acoes
        );
    }
}
