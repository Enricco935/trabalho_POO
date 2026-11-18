package com.andre_enricco.service;

import java.time.LocalDate;
import java.util.ArrayList;

import com.andre_enricco.model.Cliente;
import com.andre_enricco.model.Pet;
import com.andre_enricco.model.ServicoPrestado;
import com.andre_enricco.repository.ClienteRepository;
import com.andre_enricco.repository.PetRepository;
import com.andre_enricco.repository.ServicoPrestadoRepository;

public class PetService {
    private final PetRepository petRepository;
    private final ClienteRepository clienteRepository;
    private final ServicoPrestadoRepository servicoPrestadoRepository;

    public PetService(PetRepository petRepository, ClienteRepository clienteRepository,
            ServicoPrestadoRepository servicoPrestadoRepository) {
        this.petRepository = petRepository;
        this.clienteRepository = clienteRepository;
        this.servicoPrestadoRepository = servicoPrestadoRepository;
    }

    public boolean remover(int id) {
        Pet pet = petRepository.buscarPorId(id);
        if (pet == null) {
            return false;
        }

        Cliente cliente = pet.getCliente();
        servicoPrestadoRepository.removerPorPetId(id);
        petRepository.remover(pet);

        if (cliente != null && !temPetsAssociados(cliente.getId())) {
            clienteRepository.remover(cliente);
        }
        return true;
    }

    public boolean temServicosPrestados(int idPet) {
        for (ServicoPrestado servico : servicoPrestadoRepository.listar()) {
            if (servico.getPet() != null && servico.getPet().getId() == idPet) {
                return true;
            }
        }
        return false;
    }

    private boolean temPetsAssociados(int idCliente) {
        for (Pet pet : petRepository.listar()) {
            if (pet.getCliente() != null && pet.getDonoId() == idCliente) {
                return true;
            }
        }
        return false;
    }

    public boolean adicionar(int idPet, String nomePet, String especie, String raca, LocalDate dataNascimento,
            int idCliente, String nomeCliente, String telefoneCliente) {
        return adicionarComNovoCliente(idPet, nomePet, especie, raca, dataNascimento,
                idCliente, nomeCliente, telefoneCliente);
    }

    public boolean adicionarAClienteExistente(int idPet, String nomePet, String especie, String raca,
            LocalDate dataNascimento, int idCliente) {
        Cliente cliente = clienteRepository.buscarPorId(idCliente);
        if (petRepository.buscarPorId(idPet) != null || cliente == null) {
            return false;
        }

        petRepository.adicionar(new Pet(idPet, nomePet, especie, raca, cliente, dataNascimento));
        return true;
    }

    public boolean adicionarComNovoCliente(int idPet, String nomePet, String especie, String raca,
            LocalDate dataNascimento, int idCliente, String nomeCliente, String telefoneCliente) {
        if (petRepository.buscarPorId(idPet) != null || clienteRepository.buscarPorId(idCliente) != null) {
            return false;
        }

        Cliente cliente = new Cliente(idCliente, nomeCliente, telefoneCliente);
        clienteRepository.adicionar(cliente);
        petRepository.adicionar(new Pet(idPet, nomePet, especie, raca, cliente, dataNascimento));
        return true;
    }

    public Pet buscar(int id) {
        return petRepository.buscarPorId(id);
    }

    public void alterar(int id, String nome, String especie, String raca, LocalDate dataNascimento) {
        Pet pet = petRepository.buscarPorId(id);
        if (pet != null) {
            petRepository.alterar(new Pet(
                    pet.getId(), nome, especie, raca, pet.getCliente(), dataNascimento));
        }
    }

    public ArrayList<Pet> listar() {
        return petRepository.listar();
    }
}
