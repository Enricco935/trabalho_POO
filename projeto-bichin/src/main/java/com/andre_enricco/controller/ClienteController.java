package com.andre_enricco.controller;

import java.util.ArrayList;

import com.andre_enricco.model.Cliente;
import com.andre_enricco.repository.ClienteRepository;
import com.andre_enricco.repository.PetRepository;

public class ClienteController {
    private ClienteRepository clienteRepository;
    private PetRepository petRepository;

    public ClienteController(ClienteRepository clienteRepository, PetRepository petRepository) {
        this.clienteRepository = clienteRepository;
        this.petRepository = petRepository;
    }

    public boolean remover(int id) {
        if (clienteRepository.buscarPorId(id) != null && !temPetsAssociados(id)) {
            clienteRepository.remover(clienteRepository.buscarPorId(id));
            return true;
        }
        return false;
    }

    public boolean temPetsAssociados(int idCliente) {
        return petRepository.listar().stream()
                .anyMatch(pet -> pet.getCliente() != null && pet.getCliente().getId() == idCliente);
    }

    public ArrayList<Cliente> listar() {
        return clienteRepository.listar();
    }

    public Cliente buscar(int id) {
        return clienteRepository.buscarPorId(id);
    }

    public void alterar(int id, String nome, String telefone) {
        if (clienteRepository.buscarPorId(id) != null) {
            clienteRepository.alterar(new Cliente(id, nome, telefone));
        }
    }
}
