package com.andre_enricco.visao.gui.servico;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.andre_enricco.controller.ServicoController;
import com.andre_enricco.model.Servico;
import com.andre_enricco.visao.gui.modelos.ModeloFormulario;
import com.andre_enricco.visao.gui.modelos.ModeloTabelaGenerica;

public class BuscaServico extends JPanel {

    private final ModeloTabelaGenerica tabela;

    public BuscaServico(ServicoController controller) {
        setLayout(new BorderLayout(10, 10));

        tabela = new ModeloTabelaGenerica(new String[] {
            "ID", "Descrição", "Valor"
        });

        ModeloFormulario formulario = new ModeloFormulario(
                new String[] { "ID do serviço:" },
                "Buscar");

        formulario.getBotao().addActionListener(e -> {
            tabela.limparLinhas();

            try {
                int id = Integer.parseInt(formulario.getValor(0).trim());
                Servico servico = controller.buscar(id);

                if (servico == null) {
                    JOptionPane.showMessageDialog(this, "Serviço não encontrado.");
                    return;
                }

                tabela.adicionarLinha(new Object[] {
                    servico.getId(),
                    servico.getDescricao(),
                    servico.getValor()
                });
            } catch (NumberFormatException erro) {
                JOptionPane.showMessageDialog(this, "Digite um ID válido.");
            }
        });

        add(formulario, BorderLayout.NORTH);
        add(tabela, BorderLayout.CENTER);
    }
}
