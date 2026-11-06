package com.andre_enricco.controller;

import java.util.ArrayList;

import com.andre_enricco.model.Servico;
import com.andre_enricco.repository.ServicoPrestadoRepository;
import com.andre_enricco.repository.ServicoRepository;

public class ServicoController {
    private ServicoRepository servicoRepository;
    private ServicoPrestadoRepository servicoPrestadoRepository;

    public ServicoController(ServicoRepository servicoRepository,
            ServicoPrestadoRepository servicoPrestadoRepository) {
        this.servicoRepository = servicoRepository;
        this.servicoPrestadoRepository = servicoPrestadoRepository;
    }

    public boolean remover(int id) {
        if (servicoRepository.buscarPorId(id) != null && !temServicosPrestados(id)) {
            servicoRepository.remover(servicoRepository.buscarPorId(id));
            return true;
        }
        return false;
    }

    public boolean temServicosPrestados(int idServico) {
        return servicoPrestadoRepository.listar().stream()
                .anyMatch(servicoPrestado -> servicoPrestado.getServico() != null
                        && servicoPrestado.getServico().getId() == idServico);
    }

    public void adicionar(int id, String nome, double preco) {
        servicoRepository.adicionar(new Servico(id, nome, preco));
    }

    public void alterar(int id, String descricao, double valor) {
        Servico servico = servicoRepository.buscarPorId(id);
        if (servico != null) {
            servico.setDescricao(descricao);
            servico.setValor(valor);
            servicoRepository.alterar(servico);
        }
    }

    public ArrayList<Servico> listar() {
        return servicoRepository.listar();
    }

    public Servico buscar(int id) {
        return servicoRepository.buscarPorId(id);
    }
}
