package com.enricco.visao.cmd;

import java.util.Scanner;

public class UI_Cmd {

    private UiCmdServico uiCmdServico;
    private UiCmdPet uiCmdPet;
    private Scanner teclado;
    private UiCmdCliente uiCmdCliente;
    private UiCmdServicoPrestado uiCmdServicoPrestado;

    public UI_Cmd(UiCmdCliente uiCmdCliente, UiCmdPet uiCmdPet, UiCmdServico uiCmdServico, UiCmdServicoPrestado uiCmdServicoPrestado) {
        this.uiCmdCliente = uiCmdCliente;
        this.uiCmdPet = uiCmdPet;
        this.uiCmdServico = uiCmdServico;
        this.uiCmdServicoPrestado = uiCmdServicoPrestado;
    }

    public void iniciar(){
        teclado = new Scanner(System.in);
        int opcao = 0;

        while (opcao != 5) {
            System.out.println();
            System.out.println("==============================");
            System.out.println("        SISTEMA BICHIN");
            System.out.println("==============================");
            System.out.println("1 - Clientes");
            System.out.println("2 - Pets");
            System.out.println("3 - Servicos");
            System.out.println("4 - Servicos prestados");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opcao: ");

            try {
                opcao = Integer.parseInt(teclado.next());
            } catch (NumberFormatException e) {
                opcao = 0;
            }

            switch (opcao) {
                case 1:
                    System.out.println("Menu de clientes ainda nao implementado.");
                    break;
                case 2:
                    System.out.println("Menu de pets ainda nao implementado.");
                    break;
                case 3:
                    System.out.println("Menu de servicos ainda nao implementado.");
                    break;
                case 4:
                    System.out.println("Menu de servicos prestados ainda nao implementado.");
                    break;
                case 5:
                    System.out.println("Saindo do sistema.");
                    break;
                default:
                    System.out.println("Opcao invalida. Digite um numero de 1 a 5.");
            }
        }
    }


}
