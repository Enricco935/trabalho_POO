package com.enricco.controller;

import java.util.ArrayList;

import com.enricco.model.Cliente;
import com.enricco.repository.ClienteRepository;

public class ClienteController {
    private ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public boolean remover(int id) {
        if (clienteRepository.buscarPorId(id) != null) {
            clienteRepository.remover(clienteRepository.buscarPorId(id));
            return true;
        }
        return false;
    }

    public void adicionar(int id, String nome, String telefone) {
        clienteRepository.adicionar(new Cliente(id, nome, telefone));
    }

    public ArrayList<Cliente> listar() {
        return clienteRepository.listar();
    }

    public Cliente buscar(int id) {
        return clienteRepository.buscarPorId(id);
    }
}
