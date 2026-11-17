package com.andre_enricco.visao.gui.modelos;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class ModeloFormulario extends JPanel {

    private final List<JTextField> campos = new ArrayList<>();
    private final JButton botao;

    public ModeloFormulario(String[] rotulos, String textoBotao) {
        setLayout(new GridLayout(rotulos.length + 1, 2, 8, 8));

        for (String rotulo : rotulos) {
            add(new JLabel(rotulo));

            JTextField campo = new JTextField(15);
            campos.add(campo);
            add(campo);
        }

        add(new JLabel());
        botao = new JButton(textoBotao);
        add(botao);
    }

    public String getValor(int indice) {
        return campos.get(indice).getText();
    }

    public void setValor(int indice, String valor) {
        campos.get(indice).setText(valor);
    }

    public JButton getBotao() {
        return botao;
    }

    public void limparCampos(){
        for (int i = 0; i < campos.size(); i++) {
            campos.get(i).setText("");
        }
    }

    public void setCamposEditaveis(boolean editaveis) {
        for (JTextField campo : campos) {
            campo.setEditable(editaveis);
        }
    }
}
