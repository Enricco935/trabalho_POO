package com.andre_enricco.repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.andre_enricco.model.Servico;
import com.andre_enricco.model.ServicoInterno;
import com.andre_enricco.model.ServicoExterno;

public class ServicoRepository {
    private ArrayList<Servico> servicos;
    private String caminhoArquivo;

    public ServicoRepository() {
        this.servicos = new ArrayList<>();
    }

    public Servico buscarPorId(int id) {
        for (Servico servico : servicos) {
            if (servico.getId() == id) {
                return servico;
            }
        }
        return null;
    }

    public ArrayList<Servico> listar() {
        return servicos;
    }

    public void adicionar(Servico servico) {
        servicos.add(servico);
        persistirAlteracao();
    }

    public void remover(Servico servico) {
        if (servicos.remove(servico)) {
            persistirAlteracao();
        }
    }

    public void alterar(Servico servico) {
        for (int i = 0; i < servicos.size(); i++) {
            if (servicos.get(i).getId() == servico.getId()) {
                servicos.set(i, servico);
                persistirAlteracao();
                return;
            }
        }
    }

    public void carregarDados(String caminho) {
        this.caminhoArquivo = caminho;
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminho))) {
            String linha;
            leitor.readLine();

            while ((linha = leitor.readLine()) != null) {
                String[] dados = linha.split(";", -1);

                int id = Integer.parseInt(dados[0]);
                String descricao = dados[1].trim();
                double valor = Double.parseDouble(dados[2]);

                String crmv = dados.length > 3 ? dados[3].trim() : "";
                double valorTerceirizado = dados.length > 4 ? Double.parseDouble(dados[4]) : 0;

                // Arquivos antigos nao possuem CRMV nem valor terceirizado.
                if (crmv.isBlank()) {
                    servicos.add(new ServicoExterno(id, descricao, valor, valorTerceirizado));
                } else {
                    servicos.add(new ServicoInterno(id, descricao, valor, crmv));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar serviços: " + e.getMessage());
        }
    }

    public void salvarDados(String caminho) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(caminho))) {
            escritor.write("id;descricao;valor;crmv;valorTerceirizado");
            escritor.newLine();

            for (Servico servico : servicos) {
                String crmv = "";
                double valorTerceirizado = 0;
                if (servico instanceof ServicoInterno) {
                    crmv = ((ServicoInterno) servico).getCrmv();
                } else {
                    valorTerceirizado = ((ServicoExterno) servico).getValorTerceirizado();
                }
                escritor.write(
                    servico.getId() + ";" +
                    servico.getDescricao() + ";" +
                    servico.getValor() + ";" +
                    crmv + ";" +
                    valorTerceirizado
                );
                escritor.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar serviços: " + e.getMessage());
        }
    }

    private void persistirAlteracao() {
        if (caminhoArquivo != null) {
            salvarDados(caminhoArquivo);
        }
    }

}
