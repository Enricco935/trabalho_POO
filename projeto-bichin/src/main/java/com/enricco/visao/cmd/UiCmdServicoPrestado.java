package com.enricco.visao.cmd;

import java.time.LocalDate;

import com.enricco.controller.ClienteController;
import com.enricco.controller.ServicoPrestadoController;
import com.enricco.model.Servico;
import com.enricco.util.InputUtils;
import com.enricco.util.OutputUtils;

public class UiCmdServicoPrestado {
    private ClienteController controllerCliente;
    private ServicoPrestadoController controllerServicoPrestado;

    public UiCmdServicoPrestado(ClienteController controllerCliente,
            ServicoPrestadoController controllerServicoPrestado) {
        this.controllerCliente = controllerCliente;
        this.controllerServicoPrestado = controllerServicoPrestado;
    }

    private void servicosPorPeriodo() {
        LocalDate dataInicio = InputUtils.lerData("Digite a data inicial (dd/MM/aaaa): ");
        LocalDate dataFim = InputUtils.lerData("Digite a data final (dd/MM/aaaa): ");

        var servicos = controllerServicoPrestado.buscarPorPeriodo(dataInicio, dataFim);
        System.out.println("Servicos prestados no periodo:");
        for (var servico : servicos) {
            System.out.println(servico.getId() + " - " + servico.getServico().getDescricao()
                    + " - " + OutputUtils.formatarData(servico.getData())
                    + " - R$ " + servico.getValorCobrado());
        }
        if (servicos.isEmpty()) {
            System.out.println("Nenhum servico encontrado nesse periodo.");
        }
    }

    private void adicionar() {
        int id = InputUtils.lerInteiro("Digite o ID do servico prestado: ");
        if (controllerServicoPrestado.buscar(id) != null) {
            System.out.println("Ja existe um servico prestado com esse ID.");
            return;
        }

        LocalDate data = InputUtils.lerData("Digite a data do servico (dd/MM/aaaa): ");
        int idPet = InputUtils.lerInteiro("Digite o ID do pet: ");
        if (controllerServicoPrestado.buscarPet(idPet) == null) {
            System.out.println("Pet nao encontrado. O servico prestado nao foi cadastrado.");
            return;
        }

        int idServico;
        Servico servico = null;
        do {
            idServico = InputUtils.lerInteiro("Digite o ID do servico: ");
            servico = controllerServicoPrestado.buscarServico(idServico);
            if (servico == null) {
                System.out.println("Servico nao encontrado. Informe um ID valido.");
            }
        } while (servico == null);

        System.out.printf("Servico escolhido: %s - Valor cadastrado: R$ %.2f%n",
                servico.getDescricao(), servico.getValor());
        double valor = InputUtils.lerDouble("Digite o valor cobrado: ");

        if (controllerServicoPrestado.adicionar(id, data, idPet, idServico, valor)) {
            System.out.println("Servico prestado adicionado.");
        } else {
            System.out.println("Nao foi possivel cadastrar o servico prestado. Confira os IDs informados.");
        }
    }

    private void animaisDoDono() {
        String nomeDono = InputUtils.lerTexto("Digite o nome do dono: ");
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
        int id = InputUtils.lerInteiro("Digite o ID do servico prestado que deseja remover: ");

        if (controllerServicoPrestado.removerServicoPrestado(id)) {
            System.out.println("Servico prestado removido com sucesso.");
        } else {
            System.out.println("Servico prestado nao encontrado.");
        }
    }

    private void maiorValor() {
        int idCliente = InputUtils.lerInteiro("Digite o ID do cliente: ");
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
                    + " - " + OutputUtils.formatarData(servico.getData())
                    + " - R$ " + servico.getValorCobrado());
        }
    }

    private void servicosDoCliente() {
        int idCliente = InputUtils.lerInteiro("Digite o ID do cliente: ");
        var cliente = controllerCliente.buscar(idCliente);

        if (cliente == null) {
            System.out.println("Cliente nao encontrado.");
            return;
        }

        var servicos = controllerServicoPrestado.buscarPorCliente(cliente);
        System.out.println("Servicos prestados para " + cliente.getNome() + ":");
        for (var servico : servicos) {
            System.out.println(servico.getId() + " - " + servico.getServico().getDescricao()
                    + " - Pet: " + servico.getPet().getNome() + " - "
                    + OutputUtils.formatarData(servico.getData())
                    + " - R$ " + servico.getValorCobrado());
        }
        if (servicos.isEmpty()) {
            System.out.println("Nenhum servico encontrado para esse cliente.");
        }
    }

    private void buscar() {
        int id = InputUtils.lerInteiro("Digite o ID do servico prestado que deseja buscar: ");
        var servico = controllerServicoPrestado.buscar(id);

        if (servico != null) {
            System.out.println("Servico prestado encontrado: " + servico.getServico().getDescricao()
                    + " - Pet: " + servico.getPet().getNome() + " - Dono: "
                    + servico.getNomeDonoDoPet() + " - " + OutputUtils.formatarData(servico.getData())
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
