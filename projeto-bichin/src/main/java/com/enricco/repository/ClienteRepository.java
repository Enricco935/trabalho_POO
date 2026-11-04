package com.enricco.repository;

import java.util.ArrayList;

import com.enricco.model.Cliente;

public class ClienteRepository {
    private ArrayList<Cliente> clientes;

    public ClienteRepository() {
        this.clientes = new ArrayList<>();
    }

    public Cliente buscarPorId(int id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        return null;
    }

    public ArrayList<Cliente> listar() {
        return clientes;
    }

    public void adicionar(Cliente cliente) {
        clientes.add(cliente);
    }

    public void remover(Cliente cliente) {
        clientes.remove(cliente);
    }

    public void carregarDados(String caminho) {
        // Implementar a lógica para carregar os dados do arquivo. Versao 3
    }

}
