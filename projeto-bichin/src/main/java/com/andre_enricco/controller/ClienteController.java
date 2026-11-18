package com.andre_enricco.controller;

import java.util.ArrayList;

import com.andre_enricco.model.Cliente;
import com.andre_enricco.service.ClienteService;

public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    public boolean remover(int id) {
        return clienteService.remover(id);
    }

    public boolean temPetsAssociados(int idCliente) {
        return clienteService.temPetsAssociados(idCliente);
    }

    public ArrayList<Cliente> listar() {
        return clienteService.listar();
    }

    public Cliente buscar(int id) {
        return clienteService.buscar(id);
    }

    public void alterar(int id, String nome, String telefone) {
        clienteService.alterar(id, nome, telefone);
    }
}
