package com.enricco.visao.cmd;

import java.time.LocalDate;
import java.util.Scanner;

import com.enricco.controller.ClienteController;
import com.enricco.controller.PetController;

public class UiCmdPet {
    private Scanner teclado;
    private ClienteController controllerCliente;
    private PetController controllerPet;

    public UiCmdPet(ClienteController controllerCliente, PetController controllerPet) {
        this.teclado = new Scanner(System.in);
        this.controllerCliente = controllerCliente;
        this.controllerPet = controllerPet;
    }

    private void listar() {
        System.out.println("Lista de pets:");
        for (var pet : controllerPet.listar()) {
            System.out.println(pet.getId() + " - " + pet.getNome() + " - " + pet.getEspecie()
                    + " - " + pet.getRaca() + " - " + pet.getCliente().getNome()
                    + " - " + pet.getNascimento());
        }
    }

    private void adicionar() {
        System.out.print("Digite o ID do pet: ");
        int idPet = Integer.parseInt(teclado.next());
        if (controllerPet.buscar(idPet) != null) {
            System.out.println("Ja existe um pet com esse ID.");
            return;
        }

        System.out.print("Digite o nome do pet: ");
        String nomePet = teclado.next();
        System.out.print("Digite a especie do pet: ");
        String especie = teclado.next();
        System.out.print("Digite a raca do pet: ");
        String raca = teclado.next();
        System.out.print("Digite a data de nascimento do pet (AAAA-MM-DD): ");
        LocalDate nascimento = LocalDate.parse(teclado.next());

        System.out.print("Digite o ID do cliente: ");
        int idCliente = Integer.parseInt(teclado.next());
        if (controllerCliente.buscar(idCliente) != null) {
            System.out.println("Ja existe um cliente com esse ID.");
            return;
        }

        System.out.print("Digite o nome do cliente: ");
        String nomeCliente = teclado.next();
        System.out.print("Digite o telefone do cliente: ");
        String telefone = teclado.next();

        controllerCliente.adicionar(idCliente, nomeCliente, telefone);
        controllerPet.adicionar(idPet, nomePet, especie, raca, nascimento, idCliente);
        System.out.println("Cliente e pet adicionados com sucesso.");
    }

    private void buscar() {
        System.out.print("Digite o ID do pet que deseja buscar: ");
        int id = Integer.parseInt(teclado.next());
        var pet = controllerPet.buscar(id);

        if (pet != null) {
            System.out.println("Pet encontrado: " + pet.getNome() + " - " + pet.getEspecie()
                    + " - " + pet.getRaca() + " - Tutor: " + pet.getCliente().getNome()
                    + " - Nascimento: " + pet.getNascimento());
        } else {
            System.out.println("Pet nao encontrado.");
        }
    }

    private void alterar() {
        System.out.print("Digite o ID do pet que deseja alterar: ");
        int id = Integer.parseInt(teclado.next());
        var pet = controllerPet.buscar(id);

        if (pet == null) {
            System.out.println("Pet nao encontrado.");
            return;
        }

        System.out.print("Digite o novo nome do pet: ");
        String nome = teclado.next();
        System.out.print("Digite a nova especie do pet: ");
        String especie = teclado.next();
        System.out.print("Digite a nova raca do pet: ");
        String raca = teclado.next();
        System.out.print("Digite a nova data de nascimento (AAAA-MM-DD): ");
        LocalDate nascimento = LocalDate.parse(teclado.next());

        controllerPet.alterar(id, nome, especie, raca, nascimento);
        System.out.println("Pet alterado com sucesso.");
    }

    private void apagar() {
        System.out.print("Digite o ID do pet que deseja remover: ");
        int id = Integer.parseInt(teclado.next());

        if (controllerPet.remover(id)) {
            System.out.println("Pet removido com sucesso.");
        } else {
            System.out.println("Pet nao encontrado.");
        }
    }

    public void iniciar() {
        int opcao = 0;

        while (opcao != 6) {
            System.out.println();
            System.out.println("==============================");
            System.out.println("          MENU PETS");
            System.out.println("==============================");
            System.out.println("1 - Listar pets");
            System.out.println("2 - Buscar pet");
            System.out.println("3 - Adicionar pet");
            System.out.println("4 - Alterar pet");
            System.out.println("5 - Remover pet");
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
