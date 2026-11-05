package com.enricco.repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.enricco.model.Cliente;
import com.enricco.model.Pet;
import com.enricco.model.Servico;
import com.enricco.model.ServicoPrestado;

public class ServicoPrestadoRepository {
    private ArrayList<ServicoPrestado> servicosPrestados;

    public ServicoPrestadoRepository() {
        this.servicosPrestados = new ArrayList<>();
    }

    public ServicoPrestado buscarPorId(int id) {
        for (ServicoPrestado servicoPrestado : servicosPrestados) {
            if (servicoPrestado.getId() == id) {
                return servicoPrestado;
            }
        }
        return null;
    }

    public ArrayList<ServicoPrestado> listar() {
        return servicosPrestados;
    }

    public void adicionar(ServicoPrestado servicoPrestado) {
        servicosPrestados.add(servicoPrestado);
    }

    public void remover(ServicoPrestado servicoPrestado) {
        servicosPrestados.remove(servicoPrestado);
    }

    public void carregarDados(String caminho, ServicoRepository servicoRepository, PetRepository petRepository) {
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminho))) {
            String linha;
            leitor.readLine();

            while ((linha = leitor.readLine()) != null) {
                String[] dados = linha.split(";");

                int id = Integer.parseInt(dados[0].trim());
                int idServico = Integer.parseInt(dados[1].trim());
                LocalDate data = LocalDate.parse(dados[2].trim());
                double valorCobrado = Double.parseDouble(dados[3].trim());
                int idPet = Integer.parseInt(dados[4].trim());
                Servico servico = servicoRepository.buscarPorId(idServico);
                Pet pet = petRepository.buscarPorId(idPet);

                if (servico != null && pet != null) {
                    servicosPrestados.add(new ServicoPrestado(id, servico, data, valorCobrado, pet));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar serviços prestados: " + e.getMessage());
        }
    }

    public ArrayList<ServicoPrestado> buscarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        ArrayList<ServicoPrestado> servicosNoPeriodo = new ArrayList<>();
        for (ServicoPrestado servicoPrestado : servicosPrestados) {
            if (!servicoPrestado.getData().isBefore(dataInicio) && !servicoPrestado.getData().isAfter(dataFim)) {
                servicosNoPeriodo.add(servicoPrestado);
            }
        }
        return servicosNoPeriodo;
    }

    public ArrayList<Pet> buscarBichosPorNomeDono(String nomeDono) {
        ArrayList<Pet> bichosDoDono = new ArrayList<>();
        for (ServicoPrestado servicoPrestado : servicosPrestados) {
            if (servicoPrestado.getNomeDonoDoPet().equalsIgnoreCase(nomeDono)) {
                bichosDoDono.add(servicoPrestado.getPet());
            }
        }
        return bichosDoDono;
    }

    public ArrayList<ServicoPrestado> buscarPorCliente(Cliente cliente) {
        ArrayList<ServicoPrestado> servicosDoCliente = new ArrayList<>();
        for (ServicoPrestado servicoPrestado : servicosPrestados) {
            if (servicoPrestado.getDonoDoPet().equals(cliente)) {
                servicosDoCliente.add(servicoPrestado);
            }
        }
        return servicosDoCliente;
    }

    public double maiorValorPorCliente(Cliente cliente) {
        double maiorValor = 0.0;
        for (ServicoPrestado servicoPrestado : servicosPrestados) {
            if (servicoPrestado.getDonoDoPet().equals(cliente)) {
                if (servicoPrestado.getValorCobrado() > maiorValor) {
                    maiorValor = servicoPrestado.getValorCobrado();
                }
            }
        }
        return maiorValor;
    }

}
