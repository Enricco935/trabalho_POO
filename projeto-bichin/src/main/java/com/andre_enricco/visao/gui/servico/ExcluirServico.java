package com.andre_enricco.visao.gui.servico;

import javax.swing.*;

import com.andre_enricco.controller.ServicoController;
import com.andre_enricco.model.Servico;
import com.andre_enricco.visao.gui.modelos.ModeloFormulario;

public class ExcluirServico extends JPanel {

    private final ServicoController controller;
    private final ModeloFormulario formularioBusca;
    private final ModeloFormulario formularioDados;

    private int idServicoSelecionado;

    public ExcluirServico(ServicoController controller) {
        this.controller = controller;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        formularioBusca = new ModeloFormulario(
                new String[] { "ID do serviço:" },
                "Visualizar dados");

        formularioDados = new ModeloFormulario(
                new String[] { "Descrição:", "Valor:" },
                "Excluir");
        formularioDados.setCamposEditaveis(false);

        formularioBusca.getBotao().addActionListener(e -> buscarServico());
        formularioDados.getBotao().addActionListener(e -> excluirServico());

        add(formularioBusca);
        add(Box.createVerticalGlue());
        add(formularioDados);
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
            formularioDados.setValor(0, servico.getDescricao());
            formularioDados.setValor(1, String.valueOf(servico.getValor()));

        } catch (NumberFormatException erro) {
            JOptionPane.showMessageDialog(this, "Digite um ID válido.");
        }
    }

    private void excluirServico() {
        int confirmacao = JOptionPane.showConfirmDialog(
                this,
                "Deseja excluir este serviço?",
                "Confirmar exclusão",
                JOptionPane.YES_NO_OPTION);

        if (confirmacao != JOptionPane.YES_OPTION) {
            return;
        }

        if (controller.remover(idServicoSelecionado)) {
            JOptionPane.showMessageDialog(this, "Serviço excluído com sucesso.");
            formularioBusca.limparCampos();
            formularioDados.limparCampos();
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Não foi possível excluir. O serviço pode estar associado a um serviço prestado.");
        }
    }

}
