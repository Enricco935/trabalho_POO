package com.andre_enricco.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import com.andre_enricco.model.Cliente;
import com.andre_enricco.model.Pet;
import com.andre_enricco.repository.ClienteRepository;
import com.andre_enricco.repository.PetRepository;
import com.andre_enricco.repository.ServicoPrestadoRepository;

public class PetController {
    private PetRepository petRepository;
    private ClienteRepository clienteRepository;
    private ServicoPrestadoRepository servicoPrestadoRepository;

    public PetController(PetRepository petRepository, ClienteRepository clienteRepository,
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
        return servicoPrestadoRepository.listar().stream()
                .anyMatch(servico -> servico.getPet() != null && servico.getPet().getId() == idPet);
    }

    private boolean temPetsAssociados(int idCliente) {
        return petRepository.listar().stream()
                .anyMatch(outroPet -> outroPet.getCliente() != null
                        && outroPet.getCliente().getId() == idCliente);
    }

    public boolean adicionar(int idPet, String nomePet, String especie, String raca, LocalDate dataNascimento,
            int idCliente, String nomeCliente, String telefoneCliente) {
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
            pet.setNome(nome);
            pet.setEspecie(especie);
            pet.setRaca(raca);
            pet.setNascimento(dataNascimento);
        }
    }

    public ArrayList<Pet> listar() {
        return petRepository.listar();
    }
}
