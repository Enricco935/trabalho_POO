package com.andre_enricco.visao.gui.servicoPrestado;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.andre_enricco.controller.ServicoPrestadoController;
import com.andre_enricco.model.ServicoPrestado;
import com.andre_enricco.util.FormatoUtils;
import com.andre_enricco.visao.gui.modelos.ModeloFormulario;
import com.andre_enricco.visao.gui.modelos.ModeloTabelaGenerica;

public class BuscaServicoPrestado extends JPanel {

    private final ModeloTabelaGenerica tabela;

    public BuscaServicoPrestado(ServicoPrestadoController controller) {
        setLayout(new BorderLayout(10, 10));

        tabela = new ModeloTabelaGenerica(new String[] {
            "ID", "Serviço", "Data", "Valor cobrado", "Pet", "Dono"
        });

        ModeloFormulario formulario = new ModeloFormulario(
                new String[] { "ID do serviço prestado:" },
                "Buscar");

        formulario.getBotao().addActionListener(e -> {
            tabela.limparLinhas();

            try {
                int id = Integer.parseInt(formulario.getValor(0).trim());
                ServicoPrestado servicoPrestado = controller.buscar(id);

                if (servicoPrestado == null) {
                    JOptionPane.showMessageDialog(this, "Serviço prestado não encontrado.");
                    return;
                }

                tabela.adicionarLinha(new Object[] {
                    servicoPrestado.getId(),
                    servicoPrestado.getServico() == null
                            ? ""
                            : servicoPrestado.getServico().getDescricao(),
                    FormatoUtils.formatarData(servicoPrestado.getData()),
                    servicoPrestado.getValorCobrado(),
                    servicoPrestado.getPet() == null
                            ? ""
                            : servicoPrestado.getPet().getNome(),
                    servicoPrestado.getPet() == null || servicoPrestado.getPet().getCliente() == null
                            ? ""
                            : servicoPrestado.getPet().getCliente().getNome()
                });
            } catch (NumberFormatException erro) {
                JOptionPane.showMessageDialog(this, "Digite um ID válido.");
            }
        });

        add(formulario, BorderLayout.NORTH);
        add(tabela, BorderLayout.CENTER);
    }
}
