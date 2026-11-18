package com.andre_enricco.visao.gui.servicoPrestado;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.andre_enricco.controller.ClienteController;
import com.andre_enricco.controller.ServicoPrestadoController;
import com.andre_enricco.model.Cliente;
import com.andre_enricco.model.ServicoPrestado;
import com.andre_enricco.util.FormatoUtils;
import com.andre_enricco.visao.gui.modelos.ModeloFormulario;
import com.andre_enricco.visao.gui.modelos.ModeloTabelaGenerica;

public class ServicosDoCliente extends JPanel {

    private final ClienteController clienteController;
    private final ServicoPrestadoController servicoPrestadoController;
    private final ModeloFormulario formulario;
    private final ModeloTabelaGenerica tabela;

    public ServicosDoCliente(
            ClienteController clienteController,
            ServicoPrestadoController servicoPrestadoController) {
        this.clienteController = clienteController;
        this.servicoPrestadoController = servicoPrestadoController;
        setLayout(new BorderLayout(10, 10));

        formulario = new ModeloFormulario(
                new String[] { "ID do cliente:" },
                "Buscar serviços");
        tabela = new ModeloTabelaGenerica(new String[] {
            "ID", "Serviço", "Data", "Valor cobrado", "Pet", "Dono"
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

            ArrayList<ServicoPrestado> servicos =
                    servicoPrestadoController.buscarPorCliente(cliente);
            if (servicos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Este cliente não possui serviços prestados.");
                return;
            }

            for (ServicoPrestado servico : servicos) {
                adicionarLinha(servico);
            }
        } catch (NumberFormatException erro) {
            JOptionPane.showMessageDialog(this, "Digite um ID válido.");
        }
    }

    private void adicionarLinha(ServicoPrestado servico) {
        tabela.adicionarLinha(new Object[] {
            servico.getId(),
            servico.getServico() == null ? "" : servico.getServico().getDescricao(),
            FormatoUtils.formatarData(servico.getData()),
            servico.getValorCobrado(),
            servico.getPet() == null ? "" : servico.getPet().getNome(),
            servico.getPet() == null || servico.getPet().getCliente() == null
                    ? ""
                    : servico.getPet().getCliente().getNome()
        });
    }
}
