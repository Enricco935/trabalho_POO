package com.enricco.repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.enricco.model.Cliente;
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

    public Pet buscarPorClienteId(int idCliente) {
        for (Pet pet : pets) {
            if (pet.getCliente() != null && pet.getCliente().getId() == idCliente) {
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

    public void carregarDados(String caminho, ClienteRepository clienteRepository) {
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminho))) {
            String linha;
            leitor.readLine();

            while ((linha = leitor.readLine()) != null) {
                String[] dados = linha.split(";");

                int id = Integer.parseInt(dados[0]);
                String nome = dados[1].trim();
                String especie = dados[2].trim();
                String raca = dados[3].trim();
                int idCliente = Integer.parseInt(dados[4]);
                LocalDate nascimento = LocalDate.parse(dados[5].trim());
                Cliente cliente = clienteRepository.buscarPorId(idCliente);

                if (cliente != null) {
                    pets.add(new Pet(id, nome, especie, raca, cliente, nascimento));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar animais: " + e.getMessage());
        }
    }

    public void salvarDados(String caminho) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(caminho))) {
            escritor.write("id;nome;especie;raca;id_cliente;nascimento");
            escritor.newLine();

            for (Pet pet : pets) {
                escritor.write(
                    pet.getId() + ";" +
                    pet.getNome() + ";" +
                    pet.getEspecie() + ";" +
                    pet.getRaca() + ";" +
                    pet.getCliente().getId() + ";" +
                    pet.getNascimento()
                );
                escritor.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar animais: " + e.getMessage());
        }
    }

}
