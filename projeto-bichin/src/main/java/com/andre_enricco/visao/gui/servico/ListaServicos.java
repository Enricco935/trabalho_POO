package com.andre_enricco.visao.gui.servico;

import com.andre_enricco.controller.ServicoController;
import com.andre_enricco.model.Servico;
import com.andre_enricco.visao.gui.modelos.ModeloTabelaGenerica;
import com.andre_enricco.visao.gui.modelos.TelaAtualizavel;

public class ListaServicos extends ModeloTabelaGenerica implements TelaAtualizavel {

    private final ServicoController controller;

    public ListaServicos(ServicoController controller) {
        super(new String[] {
            "ID",
            "Descrição",
            "Valor"
        });

        this.controller = controller;
        atualizar();
    }

    @Override
    public void atualizar() {
        limparLinhas();
        for (Servico servico : controller.listar()) {
            adicionarLinha(new Object[] {
                servico.getId(),
                servico.getDescricao(),
                servico.getValor()
            });
        }
    }
}
