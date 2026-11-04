package com.enricco.controller;

import java.util.ArrayList;

import com.enricco.model.Servico;
import com.enricco.repository.ServicoRepository;

public class ServicoController {
    private ServicoRepository servicoRepository;

    public ServicoController(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    public boolean remover(int id) {
        if (servicoRepository.buscarPorId(id) != null) {
            servicoRepository.remover(servicoRepository.buscarPorId(id));
            return true;
        }
        return false;
    }

    public void adicionar(int id, String nome, double preco) {
        servicoRepository.adicionar(new Servico(id, nome, preco));
    }

    public ArrayList<Servico> listar() {
        return servicoRepository.listar();
    }

    public Servico buscar(int id) {
        return servicoRepository.buscarPorId(id);
    }
}
