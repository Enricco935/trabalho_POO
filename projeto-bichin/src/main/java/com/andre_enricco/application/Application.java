package com.andre_enricco.application;

import com.andre_enricco.config.CaminhosArquivo;
import com.andre_enricco.controller.*;
import com.andre_enricco.repository.*;
import com.andre_enricco.service.ClienteService;
import com.andre_enricco.service.PetService;
import com.andre_enricco.service.ServicoService;
import com.andre_enricco.service.ServicoPrestadoService;
import com.andre_enricco.visao.cmd.*;

public class Application {

    //centralizamos a montagem do sistema em uma unica classe, para facilitar a manutencao e a extensao do sistema
    public void iniciar() {

        /*
        * ========================================
        * AQUI DEVEMOS CRIAR OS OBJETOS DAS CLASSES DA CAMADA REPOSITORIES
        *Lembre-se, essa camada representa a persistência dos dados.
        *As classes desse nível serão injetadas na camada de service quando ela existir
        * ========================================
        */
        ClienteRepository clienteRepository = new ClienteRepository();
        PetRepository petRepository = new PetRepository();
        ServicoRepository servicoRepository = new ServicoRepository();
        ServicoPrestadoRepository servicoPrestadoRepository = new ServicoPrestadoRepository();

        /*
        * ========================================
        * CARREGAMENTO DOS DADOS INICIAIS
        * Aqui são chamados os métodos de carregamento de dados, a partir dos aquivos texto.
        * Exemplo: clienteRepository.carregarDados("dados/clientes.txt");
        * ========================================
        */
        clienteRepository.carregarDados(CaminhosArquivo.CLIENTES);
        petRepository.carregarDados(CaminhosArquivo.PETS, clienteRepository);
        servicoRepository.carregarDados(CaminhosArquivo.SERVICOS);
        servicoPrestadoRepository.carregarDados(CaminhosArquivo.SERVICOS_PRESTADOS, servicoRepository, petRepository);


        /*
        * ========================================
        * AQUI DEVEMOS CRIAR OS OBJETOS DAS CLASSES DA CAMADA CONTROLLERS
        *Lembre-se, essa camada representa o objeto que vai intermediar a comunicação entre visão e repository.
        *Os construtores das classes, dessa camada, recebem o objeto que salva os dados, Arraylist, BD, arquivos.
        *Logo abaixo, as classes dessa camada serão injetadas nas classes da camada de visão.
        * ========================================
        */
        ClienteService clienteService = new ClienteService(clienteRepository, petRepository);
        ClienteController clienteController = new ClienteController(clienteService);
        PetService petService = new PetService(petRepository, clienteRepository, servicoPrestadoRepository);
        PetController petController = new PetController(petService);
        ServicoService servicoService = new ServicoService(servicoRepository, servicoPrestadoRepository);
        ServicoController servicoController = new ServicoController(servicoService);
        ServicoPrestadoService servicoPrestadoService = new ServicoPrestadoService(
                petRepository, servicoRepository, servicoPrestadoRepository);
        ServicoPrestadoController servicoPrestadoController =
                new ServicoPrestadoController(servicoPrestadoService);


        /*
        * ========================================
        * Cria cada classe da interface do usuário informando a classe de controller que ela deve manipular (MENUS)
        * Exemplo: UiCmdCliente uiCmdCliente = new UiCmdCliente( clienteController );
        * ========================================
        */
        UiCmdCliente uiCmdCliente = new UiCmdCliente(clienteController, servicoPrestadoController);
        UiCmdPet uiCmdPet = new UiCmdPet(clienteController, petController);
        UiCmdServico uiCmdServico = new UiCmdServico(servicoController);
        UiCmdServicoPrestado uiCmdServicoPrestado = new UiCmdServicoPrestado(clienteController, servicoPrestadoController);

        UI_Cmd UICmd =
            new UI_Cmd(
                uiCmdCliente,
                uiCmdPet,
                uiCmdServico,
                uiCmdServicoPrestado
            );

        UICmd.iniciar();

    }

    

}
