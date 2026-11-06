package com.enricco.visao.cmd;

import com.enricco.controller.ServicoController;
import com.enricco.util.InputUtils;

public class UiCmdServico {
    private ServicoController controllerServico;

    public UiCmdServico(ServicoController controllerServico) {
        this.controllerServico = controllerServico;
    }

    private void listar() {
        System.out.println("Lista de servicos:");
        for (var servico : controllerServico.listar()) {
            System.out.println(servico.getId() + " - " + servico.getDescricao() + " - R$ " + servico.getValor());
        }
    }

    private void buscar() {
        int id = InputUtils.lerInteiro("Digite o ID do servico que deseja buscar: ");
        var servico = controllerServico.buscar(id);

        if (servico != null) {
            System.out.println("Servico encontrado: " + servico.getDescricao() + " - R$ " + servico.getValor());
        } else {
            System.out.println("Servico nao encontrado.");
        }
    }

    private void apagar() {
        int id = InputUtils.lerInteiro("Digite o ID do servico que deseja remover: ");

        if (controllerServico.buscar(id) == null) {
            System.out.println("Servico nao encontrado.");
        } else if (controllerServico.temServicosPrestados(id)) {
            System.out.println("Nao e possivel remover o servico: existem registros de servicos prestados associados a ele.");
        } else if (controllerServico.remover(id)) {
            System.out.println("Servico removido com sucesso.");
        } else {
            System.out.println("Servico nao encontrado.");
        }
    }

    private void adicionar() {
        int id = InputUtils.lerInteiro("Digite o ID do servico: ");
        String descricao = InputUtils.lerTexto("Digite a descricao do servico: ");
        double valor = InputUtils.lerDouble("Digite o valor do servico: ");

        controllerServico.adicionar(id, descricao, valor);
        System.out.println("Servico adicionado com sucesso.");
    }

    private void alterar() {
        int id = InputUtils.lerInteiro("Digite o ID do servico que deseja alterar: ");
        var servico = controllerServico.buscar(id);

        if (servico != null) {
            String descricao = InputUtils.lerTexto("Digite a nova descricao: ");
            double valor = InputUtils.lerDouble("Digite o novo valor: ");

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
            opcao = InputUtils.lerInteiro("Escolha uma opcao: ");

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
