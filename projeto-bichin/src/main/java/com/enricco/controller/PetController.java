package com.enricco.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import com.enricco.model.Cliente;
import com.enricco.model.Pet;
import com.enricco.repository.ClienteRepository;
import com.enricco.repository.PetRepository;

public class PetController {
    private PetRepository petRepository;
    private ClienteRepository clienteRepository;

    public PetController(PetRepository petRepository, ClienteRepository clienteRepository) {
        this.petRepository = petRepository;
        this.clienteRepository = clienteRepository;
    }

    public boolean remover(int id) {
        if (petRepository.buscarPorId(id) != null) {
            petRepository.remover(petRepository.buscarPorId(id));
            return true;
        }
        return false;
    }

    public void adicionar(int id, String nome, String especie, String raca, LocalDate dataNascimento, int idCliente) {
        Cliente cliente = clienteRepository.buscarPorId(idCliente);
        if (cliente != null) {
            petRepository.adicionar(new Pet(id, nome, especie, raca, cliente, dataNascimento));
        }
    }

    public Pet buscar(int id) {
        return petRepository.buscarPorId(id);
    }

    public void alterar(int id, String nome, String especie, String raca, LocalDate dataNascimento) {
        Pet pet = petRepository.buscarPorId(id);
        if (pet != null) {
            pet.setNome(nome);
            pet.setEspecie(especie);
            pet.setRaca(raca);
            pet.setNascimento(dataNascimento);
        }
    }

    public ArrayList<Pet> listar() {
        return petRepository.listar();
    }
}
