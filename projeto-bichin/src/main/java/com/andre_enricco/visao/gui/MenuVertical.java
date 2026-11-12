package com.andre_enricco.visao.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuVertical extends JPanel {

    public MenuVertical(String[] opcoes, ActionListener[] acoes) {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalGlue());

        for (int i = 0; i < opcoes.length; i++) {

            JButton botao = new JButton(opcoes[i]);

            botao.setAlignmentX(Component.CENTER_ALIGNMENT);

            botao.addActionListener(acoes[i]);

            add(botao);
            add(Box.createVerticalStrut(15));
        }

        add(Box.createVerticalGlue());
    }
}