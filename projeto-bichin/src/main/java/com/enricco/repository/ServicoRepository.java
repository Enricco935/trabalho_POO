package com.enricco.repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.enricco.model.Servico;

public class ServicoRepository {
    private ArrayList<Servico> servicos;

    public ServicoRepository() {
        this.servicos = new ArrayList<>();
    }

    public Servico buscarPorId(int id) {
        for (Servico servico : servicos) {
            if (servico.getId() == id) {
                return servico;
            }
        }
        return null;
    }

    public ArrayList<Servico> listar() {
        return servicos;
    }

    public void adicionar(Servico servico) {
        servicos.add(servico);
    }

    public void remover(Servico servico) {
        servicos.remove(servico);
    }

    public void carregarDados(String caminho) {
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminho))) {
            String linha;
            leitor.readLine();

            while ((linha = leitor.readLine()) != null) {
                String[] dados = linha.split(";");

                int id = Integer.parseInt(dados[0]);
                String descricao = dados[1].trim();
                double valor = Double.parseDouble(dados[2]);

                servicos.add(new Servico(id, descricao, valor));
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar serviços: " + e.getMessage());
        }
    }


}