package com.enricco.visao.cmd;

import com.enricco.util.InputUtils;

public class UI_Cmd {

    private UiCmdServico uiCmdServico;
    private UiCmdPet uiCmdPet;
    private UiCmdCliente uiCmdCliente;
    private UiCmdServicoPrestado uiCmdServicoPrestado;

    public UI_Cmd(UiCmdCliente uiCmdCliente, UiCmdPet uiCmdPet, UiCmdServico uiCmdServico, UiCmdServicoPrestado uiCmdServicoPrestado) {
        this.uiCmdCliente = uiCmdCliente;
        this.uiCmdPet = uiCmdPet;
        this.uiCmdServico = uiCmdServico;
        this.uiCmdServicoPrestado = uiCmdServicoPrestado;
    }

    public void iniciar(){
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
            opcao = InputUtils.lerInteiro("Escolha uma opcao: ");

            switch (opcao) {
                case 1:
                    uiCmdCliente.iniciar();
                    break;
                case 2:
                    uiCmdPet.iniciar();
                    break;
                case 3:
                    uiCmdServico.iniciar();
                    break;
                case 4:
                    uiCmdServicoPrestado.iniciar();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Opcao invalida. Digite um numero de 1 a 5.");
            }
        }
    }


}
