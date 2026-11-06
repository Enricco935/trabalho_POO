package com.enricco.repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

    public void alterar(Cliente cliente) {
        for (Cliente clienteExistente : clientes) {
            if (clienteExistente.getId() == cliente.getId()) {
                clienteExistente.setNome(cliente.getNome());
                clienteExistente.setTelefone(cliente.getTelefone());
                return;
            }
        }
    }

    public void carregarDados(String caminho) {
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminho))) {

            String linha;
            leitor.readLine();

            while ((linha = leitor.readLine()) != null) {
                String[] dados = linha.split(";");

                int id = Integer.parseInt(dados[0]);
                String nome = dados[1];
                String telefone = dados[2];

                clientes.add(new Cliente(id, nome, telefone));
            }

        } catch (IOException e) {
            System.out.println("Erro ao carregar clientes: " + e.getMessage());
        }
    }

    public void salvarDados(String caminho) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(caminho))) {

            escritor.write("id;nome;telefone");
            escritor.newLine();

            for (Cliente cliente : clientes) {
                escritor.write(
                    cliente.getId() + ";" +
                    cliente.getNome() + ";" +
                    cliente.getTelefone()
                );
                escritor.newLine();
            }

        } catch (IOException e) {
            System.out.println("Erro ao salvar clientes: " + e.getMessage());
        }
    }
}
