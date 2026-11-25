package com.andre_enricco.service;

import java.time.LocalDate;
import java.util.ArrayList;

import com.andre_enricco.model.Cliente;
import com.andre_enricco.model.Pet;
import com.andre_enricco.model.Servico;
import com.andre_enricco.model.ServicoPrestado;
import com.andre_enricco.repository.PetRepository;
import com.andre_enricco.repository.ServicoPrestadoRepository;
import com.andre_enricco.repository.ServicoRepository;

public class ServicoPrestadoService {
    private final PetRepository petRepository;
    private final ServicoRepository servicoRepository;
    private final ServicoPrestadoRepository servicoPrestadoRepository;

    public ServicoPrestadoService(PetRepository petRepository, ServicoRepository servicoRepository,
            ServicoPrestadoRepository servicoPrestadoRepository) {
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
        if (servicoPrestado == null) {
            return false;
        }

        servicoPrestadoRepository.remover(servicoPrestado);
        return true;
    }

    public ServicoPrestado buscar(int id) {
        return servicoPrestadoRepository.buscarPorId(id);
    }

    public Servico buscarServico(int id) {
        return servicoRepository.buscarPorId(id);
    }

    public Servico buscarServicoParaPrestacao(int id) {
        Servico servico = servicoRepository.buscarPorId(id);
        if (servico == null) {
            throw new IllegalArgumentException("Serviço não encontrado. Confira o ID informado.");
        }
        return servico;
    }

    public ArrayList<Pet> buscarBichosPorNomeDono(String nomeDono) {
        return servicoPrestadoRepository.buscarBichosPorNomeDono(nomeDono);
    }

    public ArrayList<ServicoPrestado> buscarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        return servicoPrestadoRepository.buscarPorPeriodo(dataInicio, dataFim);
    }

    public boolean adicionar(int id, LocalDate data, int idPet, int idServico, double valor) {
        if (data == null) {
            throw new IllegalArgumentException("Informe uma data válida para o serviço prestado.");
        }
        if (!Double.isFinite(valor)) {
            throw new IllegalArgumentException("O valor cobrado precisa ser um número válido.");
        }
        if (valor < 0) {
            throw new IllegalArgumentException("O valor cobrado não pode ser negativo.");
        }
        if (servicoPrestadoRepository.buscarPorId(id) != null) {
            throw new IllegalArgumentException("Já existe um serviço prestado com esse ID.");
        }

        Pet pet = petRepository.buscarPorId(idPet);
        if (pet == null) {
            throw new IllegalArgumentException("Pet não encontrado. Confira o ID informado.");
        }

        Servico servico = buscarServicoParaPrestacao(idServico);

        if (data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("A data do serviço prestado não pode estar no passado.");
        }

        servicoPrestadoRepository.adicionar(new ServicoPrestado(id, servico, data, valor, pet));
        return true;
    }

    public Pet buscarPet(int id) {
        return petRepository.buscarPorId(id);
    }

    public ArrayList<ServicoPrestado> buscarPorCliente(Cliente cliente) {
        return servicoPrestadoRepository.buscarPorCliente(cliente);
    }

    public ArrayList<ServicoPrestado> listar() {
        return servicoPrestadoRepository.listar();
    }
}
