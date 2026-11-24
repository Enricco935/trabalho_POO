package com.andre_enricco.visao.gui.servico;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.andre_enricco.controller.ServicoController;
import com.andre_enricco.visao.gui.modelos.ModeloFormulario;

public class AdicionarServicoInterno extends JPanel {

    private final ServicoController controller;
    private final ModeloFormulario formulario;

    public AdicionarServicoInterno(ServicoController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        formulario = new ModeloFormulario(new String[] {
                "ID do serviço:",
                "Descrição:",
                "Valor (use ponto para decimais):",
                "CRMV do responsável:"
        }, "Cadastrar serviço interno");

        formulario.getBotao().addActionListener(e -> cadastrar());
        add(formulario, BorderLayout.NORTH);
    }

    private void cadastrar() {
        String idTexto = formulario.getValor(0).trim();
        String descricao = formulario.getValor(1).trim();
        String valorTexto = formulario.getValor(2).trim();
        String crmv = formulario.getValor(3).trim();

        if (idTexto.isEmpty() || descricao.isEmpty() || valorTexto.isEmpty() || crmv.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);
            double valor = Double.parseDouble(valorTexto);

            if (controller.buscar(id) != null) {
                JOptionPane.showMessageDialog(this, "Já existe um serviço com esse ID.");
                return;
            }

            controller.adicionarInterno(id, descricao, valor, crmv);
            JOptionPane.showMessageDialog(this, "Serviço interno cadastrado com sucesso.");
            formulario.limparCampos();
        } catch (NumberFormatException erro) {
            JOptionPane.showMessageDialog(
                    this,
                    "Digite um ID inteiro e um valor válido usando ponto para decimais.");
        }
    }
}
