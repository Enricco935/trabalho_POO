package com.andre_enricco.visao.gui.cliente;

import javax.swing.*;

import com.andre_enricco.controller.ClienteController;
import com.andre_enricco.model.Cliente;
import com.andre_enricco.visao.gui.modelos.ModeloFormulario;

public class ExcluirCliente extends JPanel {

    private final ClienteController controller;
    private final ModeloFormulario formularioBusca;
    private final ModeloFormulario formularioEdicao;

    private int idClienteSelecionado;

    public ExcluirCliente(ClienteController controller) {
        this.controller = controller;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        formularioBusca = new ModeloFormulario(
                new String[] { "ID do cliente:" },
                "Visualizar dados");

        formularioEdicao = new ModeloFormulario(
                new String[] { "Nome:", "Telefone:" },
                "Excluir");

        formularioBusca.getBotao().addActionListener(e -> buscarCliente());
        formularioEdicao.getBotao().addActionListener(e -> excluirCliente());
        formularioEdicao.setCamposEditaveis(false);

        add(formularioBusca);
        add(Box.createVerticalGlue());
        add(formularioEdicao);
    }

    private void buscarCliente() {

        try {
            int id = Integer.parseInt(formularioBusca.getValor(0).trim());
            Cliente cliente = controller.buscar(id);

            if (cliente == null) {
                JOptionPane.showMessageDialog(this, "Cliente não encontrado.");
                return;
            }

            idClienteSelecionado = cliente.getId();
            formularioEdicao.setValor(0, cliente.getNome());
            formularioEdicao.setValor(1, cliente.getTelefone());


        } catch (NumberFormatException erro) {
            JOptionPane.showMessageDialog(this, "Digite um ID válido.");
        }
    }

    private void excluirCliente() {
        controller.remover(idClienteSelecionado);
        JOptionPane.showMessageDialog(this, "Cliente excluído com sucesso.");
        formularioEdicao.limparCampos();
        formularioBusca.limparCampos();
    }
}


