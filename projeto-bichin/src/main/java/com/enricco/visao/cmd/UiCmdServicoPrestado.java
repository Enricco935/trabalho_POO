package com.enricco.visao.cmd;

import java.time.LocalDate;
import java.util.Scanner;

import com.enricco.controller.ClienteController;
import com.enricco.controller.ServicoPrestadoController;

public class UiCmdServicoPrestado {
    private ClienteController controllerCliente;
    private ServicoPrestadoController controllerServicoPrestado;
    private Scanner teclado;

    public UiCmdServicoPrestado(ClienteController controllerCliente,
            ServicoPrestadoController controllerServicoPrestado) {
        this.teclado = new Scanner(System.in);
        this.controllerCliente = controllerCliente;
        this.controllerServicoPrestado = controllerServicoPrestado;
    }

    private void servicosPorPeriodo() {
        System.out.print("Digite a data inicial (AAAA-MM-DD): ");
        String dataInicio = teclado.next();
        System.out.print("Digite a data final (AAAA-MM-DD): ");
        String dataFim = teclado.next();

        var servicos = controllerServicoPrestado.buscarPorPeriodo(dataInicio, dataFim);
        System.out.println("Servicos prestados no periodo:");
        for (var servico : servicos) {
            System.out.println(servico.getId() + " - " + servico.getServico().getDescricao()
                    + " - " + servico.getData() + " - R$ " + servico.getValorCobrado());
        }
        if (servicos.isEmpty()) {
            System.out.println("Nenhum servico encontrado nesse periodo.");
        }
    }

    private void adicionar() {
        System.out.print("Digite o ID do servico prestado: ");
        int id = Integer.parseInt(teclado.next());
        System.out.print("Digite a data do servico (AAAA-MM-DD): ");
        LocalDate data = LocalDate.parse(teclado.next());
        System.out.print("Digite o ID do pet: ");
        int idPet = Integer.parseInt(teclado.next());
        System.out.print("Digite o ID do servico: ");
        int idServico = Integer.parseInt(teclado.next());
        System.out.print("Digite o valor cobrado: ");
        double valor = Double.parseDouble(teclado.next());

        controllerServicoPrestado.adicionar(id, data, idPet, idServico, valor);
        System.out.println("Servico prestado adicionado.");
    }

    private void animaisDoDono() {
        System.out.print("Digite o nome do dono: ");
        String nomeDono = teclado.next();
        var pets = controllerServicoPrestado.buscarBichosPorNomeDono(nomeDono);

        System.out.println("Animais do dono:");
        for (var pet : pets) {
            System.out.println(pet.getId() + " - " + pet.getNome() + " - " + pet.getEspecie()
                    + " - " + pet.getRaca());
        }
        if (pets.isEmpty()) {
            System.out.println("Nenhum animal encontrado para esse dono.");
        }
    }

    private void apagar() {
        System.out.print("Digite o ID do servico prestado que deseja remover: ");
        int id = Integer.parseInt(teclado.next());

        if (controllerServicoPrestado.removerServicoPrestado(id)) {
            System.out.println("Servico prestado removido com sucesso.");
        } else {
            System.out.println("Servico prestado nao encontrado.");
        }
    }

    private void maiorValor() {
        System.out.print("Digite o ID do cliente: ");
        int idCliente = Integer.parseInt(teclado.next());
        var cliente = controllerCliente.buscar(idCliente);

        if (cliente == null) {
            System.out.println("Cliente nao encontrado.");
            return;
        }

        double valor = controllerServicoPrestado.maiorValorPorCliente(cliente);
        System.out.println("Maior valor de servico do cliente: R$ " + valor);
    }

    private void listar() {
        System.out.println("Lista de servicos prestados:");
        for (var servico : controllerServicoPrestado.listar()) {
            System.out.println(servico.getId() + " - " + servico.getServico().getDescricao()
                    + " - " + servico.getPet().getNome() + " - " + servico.getNomeDonoDoPet()
                    + " - " + servico.getData() + " - R$ " + servico.getValorCobrado());
        }
    }

    private void servicosDoCliente() {
        System.out.print("Digite o ID do cliente: ");
        int idCliente = Integer.parseInt(teclado.next());
        var cliente = controllerCliente.buscar(idCliente);

        if (cliente == null) {
            System.out.println("Cliente nao encontrado.");
            return;
        }

        var servicos = controllerServicoPrestado.buscarPorCliente(cliente);
        System.out.println("Servicos prestados para " + cliente.getNome() + ":");
        for (var servico : servicos) {
            System.out.println(servico.getId() + " - " + servico.getServico().getDescricao()
                    + " - Pet: " + servico.getPet().getNome() + " - " + servico.getData()
                    + " - R$ " + servico.getValorCobrado());
        }
        if (servicos.isEmpty()) {
            System.out.println("Nenhum servico encontrado para esse cliente.");
        }
    }

    private void buscar() {
        System.out.print("Digite o ID do servico prestado que deseja buscar: ");
        int id = Integer.parseInt(teclado.next());
        var servico = controllerServicoPrestado.buscar(id);

        if (servico != null) {
            System.out.println("Servico prestado encontrado: " + servico.getServico().getDescricao()
                    + " - Pet: " + servico.getPet().getNome() + " - Dono: "
                    + servico.getNomeDonoDoPet() + " - " + servico.getData()
                    + " - R$ " + servico.getValorCobrado());
        } else {
            System.out.println("Servico prestado nao encontrado.");
        }
    }

    public void iniciar() {
        int opcao = 0;

        while (opcao != 9) {
            System.out.println();
            System.out.println("==============================");
            System.out.println("   MENU SERVICOS PRESTADOS");
            System.out.println("==============================");
            System.out.println("1 - Listar servicos prestados");
            System.out.println("2 - Buscar servico prestado");
            System.out.println("3 - Adicionar servico prestado");
            System.out.println("4 - Remover servico prestado");
            System.out.println("5 - Animais do dono");
            System.out.println("6 - Servicos por periodo");
            System.out.println("7 - Maior valor por cliente");
            System.out.println("8 - Servicos do cliente");
            System.out.println("9 - Voltar ao menu principal");
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
                    apagar();
                    break;
                case 5:
                    animaisDoDono();
                    break;
                case 6:
                    servicosPorPeriodo();
                    break;
                case 7:
                    maiorValor();
                    break;
                case 8:
                    servicosDoCliente();
                    break;
                case 9:
                    System.out.println("Voltando ao menu principal.");
                    break;
                default:
                    System.out.println("Opcao invalida. Digite um numero de 1 a 9.");
            }
        }
    }

}
