package com.andre_enricco.visao.gui;

import javax.swing.*;
import java.awt.*;

import com.andre_enricco.visao.gui.cliente.*;
import com.andre_enricco.visao.gui.pet.*;
import com.andre_enricco.visao.gui.servico.*;
import com.andre_enricco.visao.gui.servicoPrestado.*;
import com.andre_enricco.visao.gui.telaPrincipal.*;
import com.andre_enricco.controller.ClienteController;
import com.andre_enricco.controller.PetController;
import com.andre_enricco.controller.ServicoController;
import com.andre_enricco.controller.ServicoPrestadoController;

public class Ui_Gui extends JFrame {

    private CardLayout cardLayout;
    private JPanel painelPrincipal;

    public Ui_Gui(
            ClienteController clienteController,
            PetController petController,
            ServicoController servicoController,
            ServicoPrestadoController servicoPrestadoController) {

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
                e -> mostrarTela("tabela-cliente"),
                e -> mostrarTela("busca-cliente"),
                e -> mostrarAcao("Alterar cliente"),
                e -> mostrarAcao("Remover cliente"),
            }
        );

        MenuPet menuPet = new MenuPet(
            new java.awt.event.ActionListener[] {
                e -> mostrarTela("tabela-pets"),
                e -> mostrarAcao("Buscar pet"),
                e -> mostrarAcao("Adicionar pet"),
                e -> mostrarAcao("Alterar pet"),
                e -> mostrarAcao("Remover pet"),
            }
        );

        MenuServico menuServico = new MenuServico(
            new java.awt.event.ActionListener[] {
                e -> mostrarTela("tabela-servicos"),
                e -> mostrarAcao("Buscar serviço"),
                e -> mostrarAcao("Adicionar serviço"),
                e -> mostrarAcao("Alterar serviço"),
                e -> mostrarAcao("Remover serviço"),
            }
        );

        MenuServicoPrestado menuServicoPrestado = new MenuServicoPrestado(
            new java.awt.event.ActionListener[] {
                e -> mostrarTela("tabela-servicos-prestados"),
                e -> mostrarAcao("Buscar serviço prestado"),
                e -> mostrarAcao("Adicionar serviço prestado"),
                e -> mostrarAcao("Remover serviço prestado"),
                e -> mostrarAcao("Animais do dono"),
                e -> mostrarAcao("Serviços por período"),
                e -> mostrarAcao("Maior valor por cliente"),
                e -> mostrarAcao("Serviços do cliente"),
            }
        );

        ListaClientes tabelaCliente = new ListaClientes(clienteController);
        ListaPets tabelaPets = new ListaPets(petController);
        ListaServicos tabelaServicos = new ListaServicos(servicoController);
        ListaServicosPrestados tabelaServicosPrestados = new ListaServicosPrestados(servicoPrestadoController);

        BuscaCliente buscaCliente = new BuscaCliente(clienteController);
        // Registrando as telas

        painelPrincipal.add(menuPrincipal, "principal");
        painelPrincipal.add(criarTelaSecundaria(menuCliente), "clientes");
        painelPrincipal.add(criarTelaSecundaria(menuPet), "pets");
        painelPrincipal.add(criarTelaSecundaria(menuServico), "servicos");
        painelPrincipal.add(criarTelaSecundaria(menuServicoPrestado), "servicos-prestados");
        painelPrincipal.add(criarTelaSecundaria(tabelaCliente), "tabela-cliente");
        painelPrincipal.add(criarTelaSecundaria(tabelaPets), "tabela-pets");
        painelPrincipal.add(criarTelaSecundaria(tabelaServicos), "tabela-servicos");
        painelPrincipal.add(criarTelaSecundaria(tabelaServicosPrestados), "tabela-servicos-prestados");
        painelPrincipal.add(criarTelaSecundaria(buscaCliente), "busca-cliente");

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

    private JPanel criarTelaSecundaria(JPanel conteudo) {
        JPanel tela = new JPanel(new BorderLayout());

        JButton voltar = new JButton("Voltar ao menu principal");
        voltar.addActionListener(e -> mostrarTela("principal"));

        JPanel topo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topo.add(voltar);

        tela.add(topo, BorderLayout.NORTH);
        tela.add(conteudo, BorderLayout.CENTER);

        return tela;
    }
}
