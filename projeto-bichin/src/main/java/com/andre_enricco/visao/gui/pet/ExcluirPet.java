package com.andre_enricco.visao.gui.pet;

import javax.swing.*;

import com.andre_enricco.controller.PetController;
import com.andre_enricco.model.Pet;
import com.andre_enricco.util.FormatoUtils;
import com.andre_enricco.visao.gui.modelos.ModeloFormulario;

public class ExcluirPet extends JPanel {

    private final PetController controller;
    private final ModeloFormulario formularioBusca;
    private final ModeloFormulario formularioDados;

    private int idPetSelecionado;

    public ExcluirPet(PetController controller) {
        this.controller = controller;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        formularioBusca = new ModeloFormulario(
                new String[] { "ID do pet:" },
                "Visualizar dados");

        formularioDados = new ModeloFormulario(
                new String[] { "Nome:", "Espécie:", "Raça:", "Nascimento:", "Dono:" },
                "Excluir");
        formularioDados.setCamposEditaveis(false);

        formularioBusca.getBotao().addActionListener(e -> buscarPet());
        formularioDados.getBotao().addActionListener(e -> excluirPet());

        add(formularioBusca);
        add(Box.createVerticalGlue());
        add(formularioDados);
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
            formularioDados.setValor(0, pet.getNome());
            formularioDados.setValor(1, pet.getEspecieDescricao());
            formularioDados.setValor(2, pet.getRaca());
            formularioDados.setValor(3, FormatoUtils.formatarData(pet.getNascimento()));
            formularioDados.setValor(4,
                    pet.getCliente() == null ? "" : pet.getCliente().getNome());

        } catch (NumberFormatException erro) {
            JOptionPane.showMessageDialog(this, "Digite um ID válido.");
        }
    }

    private void excluirPet() {
        int confirmacao = JOptionPane.showConfirmDialog(
                this,
                "A exclusão também removerá os serviços prestados deste pet e, se ele for o único do dono, o cliente. Continuar?",
                "Confirmar exclusão",
                JOptionPane.YES_NO_OPTION);

        if (confirmacao != JOptionPane.YES_OPTION) {
            return;
        }

        if (controller.remover(idPetSelecionado)) {
            JOptionPane.showMessageDialog(this, "Pet excluído com sucesso.");
            formularioBusca.limparCampos();
            formularioDados.limparCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Não foi possível excluir o pet.");
        }
    }
}
