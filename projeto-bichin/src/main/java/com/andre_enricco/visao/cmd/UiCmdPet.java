package com.andre_enricco.visao.cmd;

import java.time.LocalDate;

import com.andre_enricco.controller.ClienteController;
import com.andre_enricco.controller.PetController;
import com.andre_enricco.util.InputUtils;
import com.andre_enricco.util.OutputUtils;

public class UiCmdPet {
    private ClienteController controllerCliente;
    private PetController controllerPet;

    public UiCmdPet(ClienteController controllerCliente, PetController controllerPet) {
        this.controllerCliente = controllerCliente;
        this.controllerPet = controllerPet;
    }

    private void listar() {
        System.out.println("Lista de pets:");
        for (var pet : controllerPet.listar()) {
            System.out.println(pet.getId() + " - " + pet.getNome() + " - " + pet.getEspecie()
                    + " - " + pet.getRaca() + " - " + pet.getCliente().getNome()
                    + " - " + OutputUtils.formatarData(pet.getNascimento()));
        }
    }

    private void adicionar() {
        int idPet = InputUtils.lerInteiro("Digite o ID do pet: ");
        if (controllerPet.buscar(idPet) != null) {
            System.out.println("Ja existe um pet com esse ID.");
            return;
        }

        String nomePet = InputUtils.lerTexto("Digite o nome do pet: ");
        String especie = InputUtils.lerTexto("Digite a especie do pet: ");
        String raca = InputUtils.lerTexto("Digite a raca do pet: ");
        LocalDate nascimento = InputUtils.lerData("Digite a data de nascimento do pet (dd/MM/aaaa): ");

        int idCliente = InputUtils.lerInteiro("Digite o ID do novo cliente: ");
        if (controllerCliente.buscar(idCliente) != null) {
            System.out.println("Ja existe um cliente com esse ID.");
            return;
        }

        String nomeCliente = InputUtils.lerTexto("Digite o nome do cliente: ");
        String telefoneCliente = InputUtils.lerTelefone("Digite o telefone do cliente (dd) ddddd-ddddd: ");

        if (controllerPet.adicionar(idPet, nomePet, especie, raca, nascimento,
                idCliente, nomeCliente, telefoneCliente)) {
            System.out.println("Cliente e pet adicionados com sucesso.");
        } else {
            System.out.println("Nao foi possivel adicionar o cliente e o pet. Verifique os IDs informados.");
        }
    }

    private void buscar() {
        int id = InputUtils.lerInteiro("Digite o ID do pet que deseja buscar: ");
        var pet = controllerPet.buscar(id);

        if (pet != null) {
            System.out.println("Pet encontrado: " + pet.getNome() + " - " + pet.getEspecie()
                    + " - " + pet.getRaca() + " - Tutor: " + pet.getCliente().getNome()
                    + " - Nascimento: " + OutputUtils.formatarData(pet.getNascimento()));
        } else {
            System.out.println("Pet nao encontrado.");
        }
    }

    private void alterar() {
        int id = InputUtils.lerInteiro("Digite o ID do pet que deseja alterar: ");
        var pet = controllerPet.buscar(id);

        if (pet == null) {
            System.out.println("Pet nao encontrado.");
            return;
        }

        String nome = InputUtils.lerTexto("Digite o novo nome do pet: ");
        String especie = InputUtils.lerTexto("Digite a nova especie do pet: ");
        String raca = InputUtils.lerTexto("Digite a nova raca do pet: ");
        LocalDate nascimento = InputUtils.lerData("Digite a nova data de nascimento (dd/MM/aaaa): ");

        controllerPet.alterar(id, nome, especie, raca, nascimento);
        System.out.println("Pet alterado com sucesso.");
    }

    private void apagar() {
        int id = InputUtils.lerInteiro("Digite o ID do pet que deseja remover: ");
        var pet = controllerPet.buscar(id);

        if (pet == null) {
            System.out.println("Pet nao encontrado.");
        } else if (controllerPet.remover(id)) {
            System.out.println("Pet removido com sucesso.");
            if (controllerCliente.buscar(pet.getCliente().getId()) == null) {
                System.out.println("O cliente associado tambem foi removido.");
            } else {
                System.out.println("O cliente foi mantido porque esta associado a outro pet.");
            }
            System.out.println("Os servicos prestados associados a esse pet tambem foram removidos.");
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
