package com.andre_enricco.visao.gui.servico;

import javax.swing.*;

import com.andre_enricco.controller.ServicoController;
import com.andre_enricco.model.Servico;
import com.andre_enricco.visao.gui.modelos.ModeloFormulario;

public class AlterarServico extends JPanel {

    private final ServicoController controller;
    private final ModeloFormulario formularioBusca;
    private final ModeloFormulario formularioEdicao;

    private int idServicoSelecionado;

    public AlterarServico(ServicoController controller) {
        this.controller = controller;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        formularioBusca = new ModeloFormulario(
                new String[] { "ID do serviço:" },
                "Buscar");

        formularioEdicao = new ModeloFormulario(
                new String[] { "Descrição:", "Valor (use ponto para decimais):" },
                "Salvar alterações");

        formularioBusca.getBotao().addActionListener(e -> buscarServico());
        formularioEdicao.getBotao().addActionListener(e -> salvarAlteracoes());

        add(formularioBusca);
        add(Box.createVerticalGlue());
        add(formularioEdicao);
    }

    private void buscarServico() {
        try {
            int id = Integer.parseInt(formularioBusca.getValor(0).trim());
            Servico servico = controller.buscar(id);

            if (servico == null) {
                JOptionPane.showMessageDialog(this, "Serviço não encontrado.");
                return;
            }

            idServicoSelecionado = servico.getId();
            formularioEdicao.setValor(0, servico.getDescricao());
            formularioEdicao.setValor(1, String.valueOf(servico.getValor()));

        } catch (NumberFormatException erro) {
            JOptionPane.showMessageDialog(this, "Digite um ID válido.");
        }
    }

    private void salvarAlteracoes() {
        String descricao = formularioEdicao.getValor(0).trim();
        String textoValor = formularioEdicao.getValor(1).trim();

        if (descricao.isEmpty() || textoValor.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            return;
        }

        try {
            double valor = Double.parseDouble(textoValor);
            controller.alterar(idServicoSelecionado, descricao, valor);
            JOptionPane.showMessageDialog(this, "Serviço alterado com sucesso.");
            formularioBusca.limparCampos();
            formularioEdicao.limparCampos();
        } catch (NumberFormatException erro) {
            JOptionPane.showMessageDialog(this, "Digite um valor válido, usando ponto para decimais.");
        }
    }
}
