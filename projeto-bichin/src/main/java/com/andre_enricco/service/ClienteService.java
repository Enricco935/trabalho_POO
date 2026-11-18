package com.andre_enricco.service;

import java.util.ArrayList;

import com.andre_enricco.model.Cliente;
import com.andre_enricco.model.Pet;
import com.andre_enricco.repository.ClienteRepository;
import com.andre_enricco.repository.PetRepository;

public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final PetRepository petRepository;

    public ClienteService(ClienteRepository clienteRepository, PetRepository petRepository) {
        this.clienteRepository = clienteRepository;
        this.petRepository = petRepository;
    }

    public boolean remover(int id) {
        Cliente cliente = clienteRepository.buscarPorId(id);
        if (cliente != null && !temPetsAssociados(id)) {
            clienteRepository.remover(cliente);
            return true;
        }
        return false;
    }

    public boolean temPetsAssociados(int idCliente) {
        for (Pet pet : petRepository.listar()) {
            if (pet.getCliente() != null
                    && pet.getDonoId() == idCliente) {
                return true;
            }
        }

        return false;
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
