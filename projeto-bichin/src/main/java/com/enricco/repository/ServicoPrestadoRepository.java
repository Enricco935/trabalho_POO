package com.enricco.repository;

import java.time.LocalDate;
import java.util.ArrayList;

import com.enricco.model.Cliente;
import com.enricco.model.Pet;
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
        // Implementar a lógica para carregar os dados do arquivo. Versao 3
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
