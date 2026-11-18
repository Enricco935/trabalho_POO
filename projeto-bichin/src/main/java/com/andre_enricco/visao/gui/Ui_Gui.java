package com.andre_enricco.visao.gui;

import javax.swing.*;
import java.awt.*;

import com.andre_enricco.visao.gui.cliente.*;
import com.andre_enricco.visao.gui.pet.*;
import com.andre_enricco.visao.gui.servico.*;
import com.andre_enricco.visao.gui.servicoPrestado.*;
import com.andre_enricco.visao.gui.telaPrincipal.*;
import com.andre_enricco.visao.gui.modelos.TelaAtualizavel;
import com.andre_enricco.controller.*;

public class Ui_Gui extends JFrame {

    private CardLayout cardLayout;
    private JPanel painelPrincipal;
    private final java.util.Map<String, TelaAtualizavel> telasAtualizaveis = new java.util.HashMap<>();

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
                });

        MenuCliente menuCliente = new MenuCliente(
                new java.awt.event.ActionListener[] {
                        e -> mostrarTela("tabela-cliente"),
                        e -> mostrarTela("busca-cliente"),
                        e -> mostrarTela("alterar-cliente"),
                        e -> mostrarTela("excluir-cliente"),
                });

        MenuPet menuPet = new MenuPet(
                new java.awt.event.ActionListener[] {
                        e -> mostrarTela("tabela-pets"),
                        e -> mostrarTela("busca-pet"),
                        e -> mostrarTela("adicionar-pet"),
                        e -> mostrarTela("alterar-pet"),
                        e -> mostrarTela("excluir-pet"),
                });

        MenuServico menuServico = new MenuServico(
                new java.awt.event.ActionListener[] {
                        e -> mostrarTela("tabela-servicos"),
                        e -> mostrarTela("busca-servico"),
                        e -> mostrarTela("adicionar-servico"),
                        e -> mostrarTela("alterar-servico"),
                        e -> mostrarTela("excluir-servico"),
                });

        MenuServicoPrestado menuServicoPrestado = new MenuServicoPrestado(
                new java.awt.event.ActionListener[] {
                        e -> mostrarTela("tabela-servicos-prestados"),
                        e -> mostrarTela("busca-servico-prestado"),
                        e -> mostrarTela("adicionar-servico-prestado"),
                        e -> mostrarTela("excluir-servico-prestado"),
                        e -> mostrarTela("animais-do-dono"),
                        e -> mostrarTela("servicos-por-periodo"),
                        e -> mostrarTela("maior-valor-por-cliente"),
                        e -> mostrarTela("servicos-do-cliente"),
                });

        ListaClientes tabelaCliente = new ListaClientes(clienteController);
        ListaPets tabelaPets = new ListaPets(petController);
        ListaServicos tabelaServicos = new ListaServicos(servicoController);
        ListaServicosPrestados tabelaServicosPrestados = new ListaServicosPrestados(servicoPrestadoController);

        telasAtualizaveis.put("tabela-cliente", tabelaCliente);
        telasAtualizaveis.put("tabela-pets", tabelaPets);
        telasAtualizaveis.put("tabela-servicos", tabelaServicos);
        telasAtualizaveis.put("tabela-servicos-prestados", tabelaServicosPrestados);

        AdicionarPet adicionarPet = new AdicionarPet(petController);
        AdicionarServico adicionarServico = new AdicionarServico(servicoController);
        AdicionarServicoPrestado adicionarServicoPrestado = new AdicionarServicoPrestado(servicoPrestadoController);
        BuscaCliente buscaCliente = new BuscaCliente(clienteController);
        BuscaPet buscaPet = new BuscaPet(petController);
        BuscaServico buscaServico = new BuscaServico(servicoController);
        BuscaServicoPrestado buscaServicoPrestado = new BuscaServicoPrestado(servicoPrestadoController);
        AnimaisDoDono animaisDoDono = new AnimaisDoDono(servicoPrestadoController);
        ServicosPorPeriodo servicosPorPeriodo = new ServicosPorPeriodo(servicoPrestadoController);
        MaiorValorPorCliente maiorValorPorCliente = new MaiorValorPorCliente(clienteController, servicoPrestadoController);
        ServicosDoCliente servicosDoCliente = new ServicosDoCliente(clienteController, servicoPrestadoController);

        AlterarCliente alterarCliente = new AlterarCliente(clienteController);
        AlterarPet alterarPet = new AlterarPet(petController);
        AlterarServico alterarServico = new AlterarServico(servicoController);

        ExcluirCliente excluirCliente = new ExcluirCliente(clienteController);
        ExcluirPet excluirPet = new ExcluirPet(petController);
        ExcluirServico excluirServico = new ExcluirServico(servicoController);
        ExcluirServicoPrestado excluirServicoPrestado = new ExcluirServicoPrestado(servicoPrestadoController);

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
        painelPrincipal.add(criarTelaSecundaria(adicionarPet), "adicionar-pet");
        painelPrincipal.add(criarTelaSecundaria(adicionarServico), "adicionar-servico");
        painelPrincipal.add(criarTelaSecundaria(adicionarServicoPrestado), "adicionar-servico-prestado");

        painelPrincipal.add(criarTelaSecundaria(buscaCliente), "busca-cliente");
        painelPrincipal.add(criarTelaSecundaria(buscaPet), "busca-pet");
        painelPrincipal.add(criarTelaSecundaria(buscaServico), "busca-servico");
        painelPrincipal.add(criarTelaSecundaria(buscaServicoPrestado), "busca-servico-prestado");
        painelPrincipal.add(criarTelaSecundaria(animaisDoDono), "animais-do-dono");
        painelPrincipal.add(criarTelaSecundaria(servicosPorPeriodo), "servicos-por-periodo");
        painelPrincipal.add(criarTelaSecundaria(maiorValorPorCliente), "maior-valor-por-cliente");
        painelPrincipal.add(criarTelaSecundaria(servicosDoCliente), "servicos-do-cliente");

        painelPrincipal.add(criarTelaSecundaria(alterarCliente), "alterar-cliente");
        painelPrincipal.add(criarTelaSecundaria(alterarPet), "alterar-pet");
        painelPrincipal.add(criarTelaSecundaria(alterarServico), "alterar-servico");

        painelPrincipal.add(criarTelaSecundaria(excluirCliente), "excluir-cliente");
        painelPrincipal.add(criarTelaSecundaria(excluirPet), "excluir-pet");
        painelPrincipal.add(criarTelaSecundaria(excluirServico), "excluir-servico");
        painelPrincipal.add(criarTelaSecundaria(excluirServicoPrestado), "excluir-servico-prestado");

        add(painelPrincipal);

        // Tela inicial
        mostrarTela("principal");

        setVisible(true);
    }

    private void mostrarTela(String nome) {
        TelaAtualizavel telaAtualizavel = telasAtualizaveis.get(nome);
        if (telaAtualizavel != null) {
            telaAtualizavel.atualizar();
        }

        cardLayout.show(painelPrincipal, nome);
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
