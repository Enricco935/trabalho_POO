package com.andre_enricco.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import com.andre_enricco.model.Especie;
import com.andre_enricco.model.Pet;
import com.andre_enricco.service.PetService;

public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    public boolean remover(int id) {
        return petService.remover(id);
    }

    public boolean temServicosPrestados(int idPet) {
        return petService.temServicosPrestados(idPet);
    }

    public boolean adicionar(int idPet, String nomePet, Especie especie, String raca, LocalDate dataNascimento,
            int idCliente, String nomeCliente, String telefoneCliente) {
        return petService.adicionar(idPet, nomePet, especie, raca, dataNascimento,
                idCliente, nomeCliente, telefoneCliente);
    }

    public boolean adicionarAClienteExistente(int idPet, String nomePet, Especie especie, String raca,
            LocalDate dataNascimento, int idCliente) {
        return petService.adicionarAClienteExistente(idPet, nomePet, especie, raca,
                dataNascimento, idCliente);
    }

    public boolean adicionarComNovoCliente(int idPet, String nomePet, Especie especie, String raca,
            LocalDate dataNascimento, int idCliente, String nomeCliente, String telefoneCliente) {
        return petService.adicionarComNovoCliente(idPet, nomePet, especie, raca, dataNascimento,
                idCliente, nomeCliente, telefoneCliente);
    }

    public Pet buscar(int id) {
        return petService.buscar(id);
    }

    public void alterar(int id, String nome, Especie especie, String raca, LocalDate dataNascimento) {
        petService.alterar(id, nome, especie, raca, dataNascimento);
    }

    public ArrayList<Pet> listar() {
        return petService.listar();
    }
}
