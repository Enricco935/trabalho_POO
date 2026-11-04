package com.enricco.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import com.enricco.model.Cliente;
import com.enricco.model.Pet;
import com.enricco.model.Servico;
import com.enricco.model.ServicoPrestado;
import com.enricco.repository.PetRepository;
import com.enricco.repository.ServicoPrestadoRepository;
import com.enricco.repository.ServicoRepository;

public class ServicoPrestadoController {
    private PetRepository petRepository;
    private ServicoRepository servicoRepository;
    private ServicoPrestadoRepository servicoPrestadoRepository;

    public ServicoPrestadoController(PetRepository petRepository, ServicoRepository servicoRepository, ServicoPrestadoRepository servicoPrestadoRepository) {
        this.petRepository = petRepository;
        this.servicoRepository = servicoRepository;
        this.servicoPrestadoRepository = servicoPrestadoRepository;
    }

    public double maiorValorPorCliente(Cliente cliente) {
        double maiorValor = 0.0;
        for (ServicoPrestado servicoPrestado : servicoPrestadoRepository.listar()) {
            if (servicoPrestado.getDonoDoPet().equals(cliente)) {
                double valorServico = servicoPrestado.getValorServico();
                if (valorServico > maiorValor) {
                    maiorValor = valorServico;
                }
            }
        }
        return maiorValor;
    }

    public boolean removerServicoPrestado(int id) {
        ServicoPrestado servicoPrestado = servicoPrestadoRepository.buscarPorId(id);
        if (servicoPrestado != null) {
            servicoPrestadoRepository.remover(servicoPrestado);
            return true;
        }
        return false;
    }

    public ServicoPrestado buscar(int id) {
        return servicoPrestadoRepository.buscarPorId(id);
    }

    public ArrayList<Pet> buscarBichosPorNomeDono(String nomeDono) {
        return servicoPrestadoRepository.buscarBichosPorNomeDono(nomeDono);
    }

    public ArrayList<ServicoPrestado> buscarPorPeriodo(String dataInicio, String dataFim) {
        return servicoPrestadoRepository.buscarPorPeriodo(LocalDate.parse(dataInicio), LocalDate.parse(dataFim));
    }

    public void adicionar(int id, LocalDate data, int idPet, int idServico, double valor) {
        Pet pet = petRepository.buscarPorId(idPet);
        Servico servico = servicoRepository.buscarPorId(idServico);
        if (pet != null) {
            servicoPrestadoRepository.adicionar(new ServicoPrestado(id, servico, data, valor, pet));
        }
    }

    public ArrayList<ServicoPrestado> buscarPorCliente(Cliente cliente) {
        return servicoPrestadoRepository.buscarPorCliente(cliente);
    }

    public ArrayList<ServicoPrestado> listar() {
        return servicoPrestadoRepository.listar();
    }

}
