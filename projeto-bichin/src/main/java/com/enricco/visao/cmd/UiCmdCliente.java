package com.enricco.visao.cmd;

import java.util.Scanner;

import com.enricco.controller.ClienteController;
import com.enricco.controller.ServicoPrestadoController;

public class UiCmdCliente {
    ClienteController controllerCliente;
    ServicoPrestadoController controllerServicoPrestado;
    Scanner teclado;

    public UiCmdCliente(ClienteController controllerCliente, ServicoPrestadoController controllerServicoPrestado) {
        this.controllerCliente = controllerCliente;
        this.controllerServicoPrestado = controllerServicoPrestado;
        this.teclado = new Scanner(System.in);
    }

   private void buscar(){
        System.out.println("Digite o ID do cliente que deseja buscar: ");
        int id = Integer.parseInt(teclado.next());
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
        System.out.println("Digite o ID do cliente que deseja remover: ");
        int id = Integer.parseInt(teclado.next());
        if (controllerCliente.remover(id)) {
            System.out.println("Cliente removido com sucesso.");
        } else {
            System.out.println("Cliente nao encontrado.");
        }
    }

    private void alterar() {
        System.out.println("Digite o ID do cliente que deseja alterar: ");
        int id = Integer.parseInt(teclado.next());
        if (controllerCliente.buscar(id) != null) {
            System.out.println("Digite o novo nome do cliente: ");
            String nome = teclado.next();
            System.out.println("Digite o novo telefone do cliente: ");
            String telefone = teclado.next();
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
