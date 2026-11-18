package com.andre_enricco.visao.gui.pet;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.swing.*;

import com.andre_enricco.controller.PetController;
import com.andre_enricco.model.Pet;
import com.andre_enricco.util.FormatoUtils;
import com.andre_enricco.visao.gui.modelos.ModeloFormulario;

public class AlterarPet extends JPanel {

    private final PetController controller;
    private final ModeloFormulario formularioBusca;
    private final ModeloFormulario formularioEdicao;

    private int idPetSelecionado;

    public AlterarPet(PetController controller) {
        this.controller = controller;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        formularioBusca = new ModeloFormulario(
                new String[] { "ID do pet:" },
                "Buscar");

        formularioEdicao = new ModeloFormulario(
                new String[] { "Nome:", "Espécie:", "Raça:", "Nascimento (dd/MM/aaaa):" },
                "Salvar alterações");

        formularioBusca.getBotao().addActionListener(e -> buscarPet());
        formularioEdicao.getBotao().addActionListener(e -> salvarAlteracoes());

        add(formularioBusca);
        add(Box.createVerticalGlue());
        add(formularioEdicao);
    }

    private void buscarPet() {
        try {
            int id = Integer.parseInt(formularioBusca.getValor(0).trim());
            Pet pet = controller.buscar(id);

            if (pet == null) {
                JOptionPane.showMessageDialog(this, "Pet não encontrado.");
                return;
            }

            idPetSelecionado = pet.getId();
            formularioEdicao.setValor(0, pet.getNome());
            formularioEdicao.setValor(1, pet.getEspecie());
            formularioEdicao.setValor(2, pet.getRaca());
            formularioEdicao.setValor(3, FormatoUtils.formatarData(pet.getNascimento()));

        } catch (NumberFormatException erro) {
            JOptionPane.showMessageDialog(this, "Digite um ID válido.");
        }
    }

    private void salvarAlteracoes() {
        String nome = formularioEdicao.getValor(0).trim();
        String especie = formularioEdicao.getValor(1).trim();
        String raca = formularioEdicao.getValor(2).trim();
        String textoNascimento = formularioEdicao.getValor(3).trim();

        if (nome.isEmpty() || especie.isEmpty() || raca.isEmpty() || textoNascimento.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            return;
        }

        try {
            LocalDate nascimento = FormatoUtils.converterData(textoNascimento);
            controller.alterar(idPetSelecionado, nome, especie, raca, nascimento);
            JOptionPane.showMessageDialog(this, "Pet alterado com sucesso.");
            formularioBusca.limparCampos();
            formularioEdicao.limparCampos();
        } catch (DateTimeParseException erro) {
            JOptionPane.showMessageDialog(this, "Use a data no formato dd/MM/aaaa.");
        }
    }
}
