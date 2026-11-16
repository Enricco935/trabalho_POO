package com.andre_enricco.visao.gui.modelos;

import javax.swing.table.DefaultTableModel;

public class ModeloTabelaSomenteLeitura extends DefaultTableModel{
    
    public ModeloTabelaSomenteLeitura(String[] colunas) {
        super(colunas, 0);
    }

    @Override
    public boolean isCellEditable(int linha, int coluna) {
        return false;
    }
}
