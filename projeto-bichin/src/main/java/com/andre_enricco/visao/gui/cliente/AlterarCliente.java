package com.andre_enricco.visao.gui.cliente;

import javax.swing.*;

import com.andre_enricco.controller.ClienteController;
import com.andre_enricco.model.Cliente;
import com.andre_enricco.util.FormatoUtils;
import com.andre_enricco.visao.gui.modelos.ModeloFormulario;

public class AlterarCliente extends JPanel {

    private final ClienteController controller;
    private final ModeloFormulario formularioBusca;
    private final ModeloFormulario formularioEdicao;

    private int idClienteSelecionado;

    public AlterarCliente(ClienteController controller) {
        this.controller = controller;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        formularioBusca = new ModeloFormulario(
                new String[] { "ID do cliente:" },
                "Buscar");

        formularioEdicao = new ModeloFormulario(
                new String[] { "Nome:", "Telefone:" },
                "Salvar alterações");

        formularioEdicao.setVisible(true);

        formularioBusca.getBotao().addActionListener(e -> buscarCliente());
        formularioEdicao.getBotao().addActionListener(e -> salvarAlteracoes());

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

    private void salvarAlteracoes() {
        String nome = formularioEdicao.getValor(0).trim();
        String telefone = formularioEdicao.getValor(1).trim();
        

        if (nome.isEmpty() || telefone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha nome e telefone.");
            return;
        }

        if (!FormatoUtils.telefoneValido(telefone)) {
            JOptionPane.showMessageDialog(this, "Use o telefone no formato (dd) ddddd-dddd.");
            return;
        }

        controller.alterar(idClienteSelecionado, nome, telefone);
        JOptionPane.showMessageDialog(this, "Cliente alterado com sucesso.");
        formularioEdicao.limparCampos();
        formularioBusca.limparCampos();
    }
}
