package com.enricco.repository;

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
        // Implementar a lógica para carregar os dados do arquivo. Versao 3
    }


}