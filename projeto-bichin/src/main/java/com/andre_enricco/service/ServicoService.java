package com.andre_enricco.service;

import java.util.ArrayList;

import com.andre_enricco.model.Servico;
import com.andre_enricco.model.ServicoPrestado;
import com.andre_enricco.repository.ServicoPrestadoRepository;
import com.andre_enricco.repository.ServicoRepository;

public class ServicoService {
    private final ServicoRepository servicoRepository;
    private final ServicoPrestadoRepository servicoPrestadoRepository;

    public ServicoService(ServicoRepository servicoRepository,
            ServicoPrestadoRepository servicoPrestadoRepository) {
        this.servicoRepository = servicoRepository;
        this.servicoPrestadoRepository = servicoPrestadoRepository;
    }

    public boolean remover(int id) {
        Servico servico = servicoRepository.buscarPorId(id);
        if (servico == null || temServicosPrestados(id)) {
            return false;
        }

        servicoRepository.remover(servico);
        return true;
    }

    public boolean temServicosPrestados(int idServico) {
        for (ServicoPrestado servicoPrestado : servicoPrestadoRepository.listar()) {
            if (servicoPrestado.getServico() != null
                    && servicoPrestado.getServico().getId() == idServico) {
                return true;
            }
        }
        return false;
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
