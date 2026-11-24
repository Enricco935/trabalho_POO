package com.andre_enricco.visao.gui.servico;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.andre_enricco.controller.ServicoController;
import com.andre_enricco.visao.gui.modelos.ModeloFormulario;

public class AdicionarServicoExterno extends JPanel {

    private final ServicoController controller;
    private final ModeloFormulario formulario;

    public AdicionarServicoExterno(ServicoController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        formulario = new ModeloFormulario(new String[] {
                "ID do serviço:",
                "Descrição:",
                "Valor (use ponto para decimais):",
                "Valor terceirizado (use ponto para decimais):"
        }, "Cadastrar serviço externo");

        formulario.getBotao().addActionListener(e -> cadastrar());
        add(formulario, BorderLayout.NORTH);
    }

    private void cadastrar() {
        String idTexto = formulario.getValor(0).trim();
        String descricao = formulario.getValor(1).trim();
        String valorTexto = formulario.getValor(2).trim();
        String valorTerceirizadoTexto = formulario.getValor(3).trim();

        if (idTexto.isEmpty() || descricao.isEmpty() || valorTexto.isEmpty() || valorTerceirizadoTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);
            double valor = Double.parseDouble(valorTexto);
            double valorTerceirizado = Double.parseDouble(valorTerceirizadoTexto);

            if (controller.buscar(id) != null) {
                JOptionPane.showMessageDialog(this, "Já existe um serviço com esse ID.");
                return;
            }

            if (valorTerceirizado < 0) {
                JOptionPane.showMessageDialog(this, "O valor terceirizado não pode ser negativo.");
                return;
            }

            controller.adicionarExterno(id, descricao, valor, valorTerceirizado);
            JOptionPane.showMessageDialog(this, "Serviço externo cadastrado com sucesso.");
            formulario.limparCampos();
        } catch (NumberFormatException erro) {
            JOptionPane.showMessageDialog(
                    this,
                    "Digite um ID inteiro e valores válidos usando ponto para decimais.");
        }
    }
}
