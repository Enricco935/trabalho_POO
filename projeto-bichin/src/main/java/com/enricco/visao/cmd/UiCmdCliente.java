package com.enricco.visao.cmd;

import com.enricco.controller.ClienteController;
import com.enricco.controller.ServicoPrestadoController;
import com.enricco.util.InputUtils;

public class UiCmdCliente {
    ClienteController controllerCliente;
    ServicoPrestadoController controllerServicoPrestado;

    public UiCmdCliente(ClienteController controllerCliente, ServicoPrestadoController controllerServicoPrestado) {
        this.controllerCliente = controllerCliente;
        this.controllerServicoPrestado = controllerServicoPrestado;
    }

   private void buscar(){
        int id = InputUtils.lerInteiro("Digite o ID do cliente que deseja buscar: ");
        if (controllerCliente.buscar(id) != null) {
            System.out.println("Cliente encontrado: " + controllerCliente.buscar(id).getNome());
        } else {
            System.out.println("Cliente nao encontrado.");
        }
    }

    private void listar() {
        System.out.println("Lista de clientes:");
        for (var cliente : controllerCliente.listar()) {
            System.out.println(cliente.getId() + " - " + cliente.getNome() + " - " + cliente.getTelefone());
        }
    }

    private void apagar() {
        int id = InputUtils.lerInteiro("Digite o ID do cliente que deseja remover: ");
        if (controllerCliente.buscar(id) == null) {
            System.out.println("Cliente nao encontrado.");
        } else if (controllerCliente.temPetsAssociados(id)) {
            System.out.println("Nao e possivel remover o cliente: ainda existem pets associados a ele.");
        } else if (controllerCliente.remover(id)) {
            System.out.println("Cliente removido com sucesso.");
        } else {
            System.out.println("Cliente nao encontrado.");
        }
    }

    private void alterar() {
        int id = InputUtils.lerInteiro("Digite o ID do cliente que deseja alterar: ");
        if (controllerCliente.buscar(id) != null) {
            String nome = InputUtils.lerTexto("Digite o novo nome do cliente: ");
            String telefone = InputUtils.lerTelefone("Digite o novo telefone do cliente (dd) ddddd-ddddd: ");
            controllerCliente.alterar(id, nome, telefone);
            System.out.println("Cliente alterado com sucesso.");
        } else {
            System.out.println("Cliente nao encontrado.");
        }
    }

    public void iniciar() {
        int opcao = 0;

        while (opcao != 5) {
            System.out.println();
            System.out.println("==============================");
            System.out.println("        MENU CLIENTES");
            System.out.println("==============================");
            System.out.println("1 - Listar clientes");
            System.out.println("2 - Buscar cliente");
            System.out.println("3 - Alterar cliente");
            System.out.println("4 - Remover cliente");
            System.out.println("5 - Voltar ao menu principal");
            opcao = InputUtils.lerInteiro("Escolha uma opcao: ");

            switch (opcao) {
                case 1:
                    listar();
                    break;
                case 2:
                    buscar();
                    break;
                case 3:
                    alterar();
                    break;
                case 4:
                    apagar();
                    break;
                case 5:
                    System.out.println("Voltando ao menu principal.");
                    break;
                default:
                    System.out.println("Opcao invalida. Digite um numero de 1 a 5.");
            }
        }
    }
   

}
