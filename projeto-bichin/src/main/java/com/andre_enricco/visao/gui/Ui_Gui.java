package com.andre_enricco.visao.gui;

import javax.swing.*;
import java.awt.*;

import com.andre_enricco.visao.gui.cliente.MenuCliente;
import com.andre_enricco.visao.gui.pet.MenuPet;
import com.andre_enricco.visao.gui.servico.MenuServico;
import com.andre_enricco.visao.gui.servicoPrestado.MenuServicoPrestado;

public class Ui_Gui extends JFrame {

    private CardLayout cardLayout;
    private JPanel painelPrincipal;

    public Ui_Gui() {

        setTitle("Pet Shop Bichin");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        painelPrincipal = new JPanel(cardLayout);

        // Criando os menus

        MenuPrincipal menuPrincipal = new MenuPrincipal(
            new java.awt.event.ActionListener[] {
                e -> mostrarTela("clientes"),
                e -> mostrarTela("pets"),
                e -> mostrarTela("servicos"),
                e -> mostrarTela("servicos-prestados"),
                e -> System.exit(0)
            }
        );

        MenuCliente menuCliente = new MenuCliente(
            new java.awt.event.ActionListener[] {
                e -> mostrarAcao("Listar clientes"),
                e -> mostrarAcao("Buscar cliente"),
                e -> mostrarAcao("Alterar cliente"),
                e -> mostrarAcao("Remover cliente"),
                e -> mostrarTela("principal")
            }
        );

        MenuPet menuPet = new MenuPet(
            new java.awt.event.ActionListener[] {
                e -> mostrarAcao("Listar pets"),
                e -> mostrarAcao("Buscar pet"),
                e -> mostrarAcao("Adicionar pet"),
                e -> mostrarAcao("Alterar pet"),
                e -> mostrarAcao("Remover pet"),
                e -> mostrarTela("principal")
            }
        );

        MenuServico menuServico = new MenuServico(
            new java.awt.event.ActionListener[] {
                e -> mostrarAcao("Listar serviços"),
                e -> mostrarAcao("Buscar serviço"),
                e -> mostrarAcao("Adicionar serviço"),
                e -> mostrarAcao("Alterar serviço"),
                e -> mostrarAcao("Remover serviço"),
                e -> mostrarTela("principal")
            }
        );

        MenuServicoPrestado menuServicoPrestado = new MenuServicoPrestado(
            new java.awt.event.ActionListener[] {
                e -> mostrarAcao("Listar serviços prestados"),
                e -> mostrarAcao("Buscar serviço prestado"),
                e -> mostrarAcao("Adicionar serviço prestado"),
                e -> mostrarAcao("Remover serviço prestado"),
                e -> mostrarAcao("Animais do dono"),
                e -> mostrarAcao("Serviços por período"),
                e -> mostrarAcao("Maior valor por cliente"),
                e -> mostrarAcao("Serviços do cliente"),
                e -> mostrarTela("principal")
            }
        );

        // Registrando as telas

        painelPrincipal.add(menuPrincipal, "principal");
        painelPrincipal.add(menuCliente, "clientes");
        painelPrincipal.add(menuPet, "pets");
        painelPrincipal.add(menuServico, "servicos");
        painelPrincipal.add(menuServicoPrestado, "servicos-prestados");

        add(painelPrincipal);

        // Tela inicial
        mostrarTela("principal");

        setVisible(true);
    }

    private void mostrarTela(String nome) {
        cardLayout.show(painelPrincipal, nome);
    }

    private void mostrarAcao(String acao) {
        JOptionPane.showMessageDialog(this, acao);
    }
}