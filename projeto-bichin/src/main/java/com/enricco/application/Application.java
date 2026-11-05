package com.enricco.application;
import com.enricco.controller.*;
import com.enricco.visao.cmd.*;
import com.enricco.repository.*;

public class Application {

    //centralizamos a montagem do sistema em uma unica classe, para facilitar a manutencao e a extensao do sistema
    public void iniciar() {
        
        /*
        * ========================================
        * AQUI DEVEMOS CRIAR OS OBJETOS DAS CLASSES DA CAMADA REPOSITORIES
        *Lembre-se, essa camada representa a persistência dos dados.
        *As classes desse nível serão injetadas na camada de controller
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
        clienteRepository.carregarDados("dados/clientes.txt");
        petRepository.carregarDados("dados/pets.txt", clienteRepository);
        servicoRepository.carregarDados("dados/servicos.txt");
        servicoPrestadoRepository.carregarDados("dados/servicos_prestados.txt", servicoRepository, petRepository);


        /*
        * ========================================
        * AQUI DEVEMOS CRIAR OS OBJETOS DAS CLASSES DA CAMADA CONTROLLERS
        *Lembre-se, essa camada representa o objeto que vai intermediar a comunicação entre visão e repository.
        *Os construtores das classes, dessa camada, recebem o objeto que salva os dados, Arraylist, BD, arquivos.
        *Logo abaixo, as classes dessa camada serão injetadas nas classes da camada de visão.
        * ========================================
        */
        ClienteController clienteController = new ClienteController(clienteRepository);
        PetController petController = new PetController(petRepository, clienteRepository);
        ServicoController servicoController = new ServicoController(servicoRepository);
        ServicoPrestadoController servicoPrestadoController = new ServicoPrestadoController(petRepository, servicoRepository, servicoPrestadoRepository);


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
