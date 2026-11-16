package com.andre_enricco.visao.gui.servico;

import com.andre_enricco.controller.ServicoController;
import com.andre_enricco.model.Servico;
import com.andre_enricco.visao.gui.modelos.ModeloTabelaGenerica;

public class ListaServicos extends ModeloTabelaGenerica {

    public ListaServicos(ServicoController controller) {
        super(new String[] {
            "ID",
            "Descrição",
            "Valor"
        });

        for (Servico servico : controller.listar()) {
            adicionarLinha(new Object[] {
                servico.getId(),
                servico.getDescricao(),
                servico.getValor()
            });
        }
    }
}
