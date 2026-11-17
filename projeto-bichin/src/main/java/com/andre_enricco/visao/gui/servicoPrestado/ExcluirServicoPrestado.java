package com.andre_enricco.visao.gui.servicoPrestado;

import javax.swing.*;

import com.andre_enricco.controller.ServicoPrestadoController;
import com.andre_enricco.model.ServicoPrestado;
import com.andre_enricco.visao.gui.modelos.ModeloFormulario;

public class ExcluirServicoPrestado extends JPanel {

    private final ServicoPrestadoController controller;
    private final ModeloFormulario formularioBusca;
    private final ModeloFormulario formularioDados;

    private int idServicoPrestadoSelecionado;

    public ExcluirServicoPrestado(ServicoPrestadoController controller) {
        this.controller = controller;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        formularioBusca = new ModeloFormulario(
                new String[] { "ID do serviço prestado:" },
                "Visualizar dados");

        formularioDados = new ModeloFormulario(
                new String[] { "Serviço:", "Data:", "Valor cobrado:", "Pet:", "Dono:" },
                "Excluir");
        formularioDados.setCamposEditaveis(false);

        formularioBusca.getBotao().addActionListener(e -> buscarServicoPrestado());
        formularioDados.getBotao().addActionListener(e -> excluirServicoPrestado());

        add(formularioBusca);
        add(Box.createVerticalGlue());
        add(formularioDados);
    }

    private void buscarServicoPrestado() {
        try {
            int id = Integer.parseInt(formularioBusca.getValor(0).trim());
            ServicoPrestado servicoPrestado = controller.buscar(id);

            if (servicoPrestado == null) {
                JOptionPane.showMessageDialog(this, "Serviço prestado não encontrado.");
                return;
            }

            idServicoPrestadoSelecionado = servicoPrestado.getId();
            formularioDados.setValor(0,
                    servicoPrestado.getServico() == null ? "" : servicoPrestado.getServico().getDescricao());
            formularioDados.setValor(1, String.valueOf(servicoPrestado.getData()));
            formularioDados.setValor(2, String.valueOf(servicoPrestado.getValorCobrado()));
            formularioDados.setValor(3,
                    servicoPrestado.getPet() == null ? "" : servicoPrestado.getPet().getNome());
            formularioDados.setValor(4,
                    servicoPrestado.getPet() == null || servicoPrestado.getPet().getCliente() == null
                            ? ""
                            : servicoPrestado.getPet().getCliente().getNome());

        } catch (NumberFormatException erro) {
            JOptionPane.showMessageDialog(this, "Digite um ID válido.");
        }
    }

    private void excluirServicoPrestado() {
        int confirmacao = JOptionPane.showConfirmDialog(
                this,
                "Deseja excluir este serviço prestado?",
                "Confirmar exclusão",
                JOptionPane.YES_NO_OPTION);

        if (confirmacao != JOptionPane.YES_OPTION) {
            return;
        }

        if (controller.removerServicoPrestado(idServicoPrestadoSelecionado)) {
            JOptionPane.showMessageDialog(this, "Serviço prestado excluído com sucesso.");
            formularioBusca.limparCampos();
            formularioDados.limparCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Não foi possível excluir o serviço prestado.");
        }
    }

}
