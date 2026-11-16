package com.andre_enricco.visao.gui.cliente;

import java.util.ArrayList;

import com.andre_enricco.controller.ClienteController;
import com.andre_enricco.model.Cliente;
import com.andre_enricco.visao.gui.modelos.ModeloTabelaGenerica;

public class ListaClientes extends ModeloTabelaGenerica {

    public ListaClientes(ClienteController controller) {

        super(new String[] {
            "ID",
            "Nome",
            "Telefone"
        });

        ArrayList<Cliente> clientes = controller.listar();

        for (Cliente cliente : clientes) {
            adicionarLinha(new Object[] {
                cliente.getId(),
                cliente.getNome(),
                cliente.getTelefone()
            });
        }
    }
}