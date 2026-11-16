package com.andre_enricco.visao.gui.modelos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ModeloTabelaGenerica extends JPanel {

    private JTable tabela;
    private DefaultTableModel modelo;

    public ModeloTabelaGenerica(String[] colunas) {

        setLayout(new BorderLayout());

        modelo = new ModeloTabelaSomenteLeitura(colunas);

        tabela = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(tabela);

        add(scroll, BorderLayout.CENTER);
    }

    public void adicionarLinha(Object[] dados) {
        modelo.addRow(dados);
    }

    public void limparLinhas() {
        modelo.setRowCount(0);
    }

}
