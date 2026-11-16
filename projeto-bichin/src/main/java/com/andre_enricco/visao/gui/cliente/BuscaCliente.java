package com.andre_enricco.visao.gui.cliente;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.andre_enricco.controller.ClienteController;
import com.andre_enricco.model.Cliente;
import com.andre_enricco.visao.gui.modelos.ModeloFormulario;
import com.andre_enricco.visao.gui.modelos.ModeloTabelaGenerica;

public class BuscaCliente extends JPanel {

    private final ModeloTabelaGenerica tabela;

    public BuscaCliente(ClienteController controller) {
        setLayout(new BorderLayout(10, 10));

        tabela = new ModeloTabelaGenerica(
                new String[] { "ID", "Nome", "Telefone" });

        ModeloFormulario formulario = new ModeloFormulario(
                new String[] { "ID do cliente:" },
                "Buscar");

        formulario.getBotao().addActionListener(e -> {
            tabela.limparLinhas();

            try {
                int id = Integer.parseInt(formulario.getValor(0).trim());
                Cliente cliente = controller.buscar(id);

                if (cliente == null) {
                    JOptionPane.showMessageDialog(this, "Cliente não encontrado.");
                    return;
                }

                tabela.adicionarLinha(new Object[] {
                    cliente.getId(),
                    cliente.getNome(),
                    cliente.getTelefone()
                });

            } catch (NumberFormatException erro) {
                JOptionPane.showMessageDialog(this, "Digite um ID válido.");
            }
        });

        add(formulario, BorderLayout.NORTH);
        add(tabela, BorderLayout.CENTER);
    }
}