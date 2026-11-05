package com.enricco.visao.cmd;

import java.util.Scanner;

import com.enricco.controller.ServicoController;

public class UiCmdServico {
    private ServicoController controllerServico;
    private Scanner teclado;

    public UiCmdServico(ServicoController controllerServico) {
        this.controllerServico = controllerServico;
        this.teclado = new Scanner(System.in);
    }

    private void listar() {
        System.out.println("Lista de servicos:");
        for (var servico : controllerServico.listar()) {
            System.out.println(servico.getId() + " - " + servico.getDescricao() + " - R$ " + servico.getValor());
        }
    }

    private void buscar() {
        System.out.print("Digite o ID do servico que deseja buscar: ");
        int id = Integer.parseInt(teclado.next());
        var servico = controllerServico.buscar(id);

        if (servico != null) {
            System.out.println("Servico encontrado: " + servico.getDescricao() + " - R$ " + servico.getValor());
        } else {
            System.out.println("Servico nao encontrado.");
        }
    }

    private void apagar() {
        System.out.print("Digite o ID do servico que deseja remover: ");
        int id = Integer.parseInt(teclado.next());

        if (controllerServico.remover(id)) {
            System.out.println("Servico removido com sucesso.");
        } else {
            System.out.println("Servico nao encontrado.");
        }
    }

    private void adicionar() {
        System.out.print("Digite o ID do servico: ");
        int id = Integer.parseInt(teclado.next());
        System.out.print("Digite a descricao do servico: ");
        String descricao = teclado.next();
        System.out.print("Digite o valor do servico: ");
        double valor = Double.parseDouble(teclado.next());

        controllerServico.adicionar(id, descricao, valor);
        System.out.println("Servico adicionado com sucesso.");
    }

    private void alterar() {
        System.out.print("Digite o ID do servico que deseja alterar: ");
        int id = Integer.parseInt(teclado.next());
        var servico = controllerServico.buscar(id);

        if (servico != null) {
            System.out.print("Digite a nova descricao: ");
            String descricao = teclado.next();
            System.out.print("Digite o novo valor: ");
            double valor = Double.parseDouble(teclado.next());

            controllerServico.alterar(id, descricao, valor);
            System.out.println("Servico alterado com sucesso.");
        } else {
            System.out.println("Servico nao encontrado.");
        }
    }

    public void iniciar() {
        int opcao = 0;

        while (opcao != 6) {
            System.out.println();
            System.out.println("==============================");
            System.out.println("        MENU SERVICOS");
            System.out.println("==============================");
            System.out.println("1 - Listar servicos");
            System.out.println("2 - Buscar servico");
            System.out.println("3 - Adicionar servico");
            System.out.println("4 - Alterar servico");
            System.out.println("5 - Remover servico");
            System.out.println("6 - Voltar ao menu principal");
            System.out.print("Escolha uma opcao: ");

            try {
                opcao = Integer.parseInt(teclado.next());
            } catch (NumberFormatException e) {
                opcao = 0;
            }

            switch (opcao) {
                case 1:
                    listar();
                    break;
                case 2:
                    buscar();
                    break;
                case 3:
                    adicionar();
                    break;
                case 4:
                    alterar();
                    break;
                case 5:
                    apagar();
                    break;
                case 6:
                    System.out.println("Voltando ao menu principal.");
                    break;
                default:
                    System.out.println("Opcao invalida. Digite um numero de 1 a 6.");
            }
        }
    }
}
