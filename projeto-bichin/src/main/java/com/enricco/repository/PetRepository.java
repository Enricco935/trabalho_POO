package com.enricco.repository;

import java.util.ArrayList;

import com.enricco.model.Pet;

public class PetRepository {
    private ArrayList<Pet> pets;

    public PetRepository() {
        this.pets = new ArrayList<>();
    }

    public Pet buscarPorId(int id) {
        for (Pet pet : pets) {
            if (pet.getId() == id) {
                return pet;
            }
        }
        return null;
    }

    public ArrayList<Pet> listar() {
        return pets;
    }

    public void adicionar(Pet pet) {
        pets.add(pet);
    }

    public void remover(Pet pet) {
        pets.remove(pet);
    }

    public void carregarDados(String caminho) {
        // Implementar a lógica para carregar os dados do arquivo. Versao 3
    }
}
