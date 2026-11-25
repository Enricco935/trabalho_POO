package com.andre_enricco.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import com.andre_enricco.model.Cliente;
import com.andre_enricco.model.Pet;
import com.andre_enricco.model.Servico;
import com.andre_enricco.model.ServicoPrestado;
import com.andre_enricco.service.ServicoPrestadoService;

public class ServicoPrestadoController {
    private final ServicoPrestadoService servicoPrestadoService;

    public ServicoPrestadoController(ServicoPrestadoService servicoPrestadoService) {
        this.servicoPrestadoService = servicoPrestadoService;
    }

    public double maiorValorPorCliente(Cliente cliente) {
        return servicoPrestadoService.maiorValorPorCliente(cliente);
    }

    public boolean removerServicoPrestado(int id) {
        return servicoPrestadoService.removerServicoPrestado(id);
    }

    public ServicoPrestado buscar(int id) {
        return servicoPrestadoService.buscar(id);
    }

    public Servico buscarServico(int id) {
        return servicoPrestadoService.buscarServico(id);
    }

    public Servico buscarServicoParaPrestacao(int id) {
        return servicoPrestadoService.buscarServicoParaPrestacao(id);
    }

    public ArrayList<Pet> buscarBichosPorNomeDono(String nomeDono) {
        return servicoPrestadoService.buscarBichosPorNomeDono(nomeDono);
    }

    public ArrayList<ServicoPrestado> buscarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        return servicoPrestadoService.buscarPorPeriodo(dataInicio, dataFim);
    }

    public boolean adicionar(int id, LocalDate data, int idPet, int idServico, double valor) {
        return servicoPrestadoService.adicionar(id, data, idPet, idServico, valor);
    }

    public Pet buscarPet(int id) {
        return servicoPrestadoService.buscarPet(id);
    }

    public ArrayList<ServicoPrestado> buscarPorCliente(Cliente cliente) {
        return servicoPrestadoService.buscarPorCliente(cliente);
    }

    public ArrayList<ServicoPrestado> listar() {
        return servicoPrestadoService.listar();
    }
}
