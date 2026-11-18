package com.andre_enricco.visao.gui.cliente;

import com.andre_enricco.controller.ClienteController;
import com.andre_enricco.model.Cliente;
import com.andre_enricco.visao.gui.modelos.ModeloTabelaGenerica;
import com.andre_enricco.visao.gui.modelos.TelaAtualizavel;

public class ListaClientes extends ModeloTabelaGenerica implements TelaAtualizavel {

    private final ClienteController controller;

    public ListaClientes(ClienteController controller) {

        super(new String[] {
            "ID",
            "Nome",
            "Telefone"
        });

        this.controller = controller;
        atualizar();
    }

    @Override
    public void atualizar() {
        limparLinhas();

        for (Cliente cliente : controller.listar()) {
            adicionarLinha(new Object[] {
                cliente.getId(),
                cliente.getNome(),
                cliente.getTelefone()
            });
        }
    }
}
