package com.andre_enricco.visao.gui.servicoPrestado;

import java.awt.BorderLayout;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.andre_enricco.controller.ServicoPrestadoController;
import com.andre_enricco.model.ServicoPrestado;
import com.andre_enricco.util.FormatoUtils;
import com.andre_enricco.visao.gui.modelos.ModeloFormulario;
import com.andre_enricco.visao.gui.modelos.ModeloTabelaGenerica;

public class ServicosPorPeriodo extends JPanel {

    private final ModeloFormulario formulario;
    private final ModeloTabelaGenerica tabela;
    private final ServicoPrestadoController controller;

    public ServicosPorPeriodo(ServicoPrestadoController controller) {
        this.controller = controller;
        setLayout(new BorderLayout(10, 10));

        formulario = new ModeloFormulario(new String[] {
            "Data inicial (dd/MM/aaaa):",
            "Data final (dd/MM/aaaa):"
        }, "Buscar serviços");
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
            LocalDate dataInicio = FormatoUtils.converterData(formulario.getValor(0));
            LocalDate dataFim = FormatoUtils.converterData(formulario.getValor(1));

            if (dataInicio.isAfter(dataFim)) {
                JOptionPane.showMessageDialog(this, "A data inicial deve ser anterior à data final.");
                return;
            }

            ArrayList<ServicoPrestado> servicos = controller.buscarPorPeriodo(dataInicio, dataFim);
            if (servicos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nenhum serviço encontrado nesse período.");
                return;
            }

            for (ServicoPrestado servico : servicos) {
                adicionarLinha(servico);
            }
        } catch (DateTimeParseException erro) {
            JOptionPane.showMessageDialog(this, "Use as datas no formato dd/MM/aaaa.");
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
