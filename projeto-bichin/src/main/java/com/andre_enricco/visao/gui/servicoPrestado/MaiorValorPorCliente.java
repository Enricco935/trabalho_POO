package com.andre_enricco.visao.gui.servicoPrestado;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.andre_enricco.controller.ClienteController;
import com.andre_enricco.controller.ServicoPrestadoController;
import com.andre_enricco.model.Cliente;
import com.andre_enricco.visao.gui.modelos.ModeloFormulario;
import com.andre_enricco.visao.gui.modelos.ModeloTabelaGenerica;

public class MaiorValorPorCliente extends JPanel {

    private final ClienteController clienteController;
    private final ServicoPrestadoController servicoPrestadoController;
    private final ModeloFormulario formulario;
    private final ModeloTabelaGenerica tabela;

    public MaiorValorPorCliente(
            ClienteController clienteController,
            ServicoPrestadoController servicoPrestadoController) {
        this.clienteController = clienteController;
        this.servicoPrestadoController = servicoPrestadoController;
        setLayout(new BorderLayout(10, 10));

        formulario = new ModeloFormulario(
                new String[] { "ID do cliente:" },
                "Buscar maior valor");
        tabela = new ModeloTabelaGenerica(new String[] {
            "ID do cliente", "Nome", "Maior valor de serviço"
        });

        formulario.getBotao().addActionListener(e -> buscar());
        add(formulario, BorderLayout.NORTH);
        add(tabela, BorderLayout.CENTER);
    }

    private void buscar() {
        tabela.limparLinhas();

        try {
            int id = Integer.parseInt(formulario.getValor(0).trim());
            Cliente cliente = clienteController.buscar(id);

            if (cliente == null) {
                JOptionPane.showMessageDialog(this, "Cliente não encontrado.");
                return;
            }

            double maiorValor = servicoPrestadoController.maiorValorPorCliente(cliente);
            tabela.adicionarLinha(new Object[] {
                cliente.getId(),
                cliente.getNome(),
                maiorValor
            });
        } catch (NumberFormatException erro) {
            JOptionPane.showMessageDialog(this, "Digite um ID válido.");
        }
    }
}
