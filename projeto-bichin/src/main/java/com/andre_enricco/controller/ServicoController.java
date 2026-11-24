package com.andre_enricco.controller;

import java.util.ArrayList;

import com.andre_enricco.model.Servico;
import com.andre_enricco.model.ServicoInterno;
import com.andre_enricco.model.ServicoExterno;
import com.andre_enricco.service.ServicoService;

public class ServicoController {
    private final ServicoService servicoService;

    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    public boolean remover(int id) {
        return servicoService.remover(id);
    }

    public boolean temServicosPrestados(int idServico) {
        return servicoService.temServicosPrestados(idServico);
    }

    public void adicionar(int id, String nome, double preco, String responsavel, double valorTerceirizado) {
        Servico servico;
        if (responsavel == null || responsavel.isBlank()) {
            servico = new ServicoExterno(id, nome, preco, valorTerceirizado);
        } else {
            servico = new ServicoInterno(id, nome, preco, responsavel);
        }
        servicoService.adicionar(servico);
    }

    public void alterar(int id, String descricao, double valor) {
        servicoService.alterar(id, descricao, valor);
    }

    public ArrayList<Servico> listar() {
        return servicoService.listar();
    }

    public Servico buscar(int id) {
        return servicoService.buscar(id);
    }
}
