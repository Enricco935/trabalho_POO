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

    public void adicionarInterno(int id, String descricao, double valor, String crmv) {
        if (crmv == null || crmv.isBlank()) {
            throw new IllegalArgumentException("O CRMV do responsável é obrigatório para serviço interno.");
        }
        servicoService.adicionar(new ServicoInterno(id, descricao, valor, crmv.trim()));
    }

    public void adicionarExterno(int id, String descricao, double valor, double valorTerceirizado) {
        if (valorTerceirizado < 0) {
            throw new IllegalArgumentException("O valor terceirizado não pode ser negativo.");
        }
        servicoService.adicionar(new ServicoExterno(id, descricao, valor, valorTerceirizado));
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
