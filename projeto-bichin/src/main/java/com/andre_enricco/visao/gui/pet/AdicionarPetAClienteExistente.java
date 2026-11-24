package com.andre_enricco.visao.gui.pet;

import java.awt.BorderLayout;
import java.time.format.DateTimeParseException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.andre_enricco.controller.PetController;
import com.andre_enricco.util.FormatoUtils;
import com.andre_enricco.visao.gui.modelos.ModeloFormulario;

public class AdicionarPetAClienteExistente extends JPanel {

    private final PetController controller;
    private final ModeloFormulario formulario;

    public AdicionarPetAClienteExistente(PetController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        formulario = new ModeloFormulario(new String[] {
            "ID do pet:",
            "Nome do pet:",
            "Espécie:",
            "Raça:",
            "Nascimento (dd/MM/aaaa):",
            "ID do cliente existente:"
        }, "Cadastrar pet");

        formulario.getBotao().addActionListener(e -> cadastrar());
        add(formulario, BorderLayout.NORTH);
    }

    private void cadastrar() {
        String idPetTexto = formulario.getValor(0).trim();
        String nomePet = formulario.getValor(1).trim();
        String especie = formulario.getValor(2).trim();
        String raca = formulario.getValor(3).trim();
        String nascimentoTexto = formulario.getValor(4).trim();
        String idClienteTexto = formulario.getValor(5).trim();

        if (idPetTexto.isEmpty() || nomePet.isEmpty() || especie.isEmpty() || raca.isEmpty()
                || nascimentoTexto.isEmpty() || idClienteTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            return;
        }

        try {
            boolean cadastrado = controller.adicionarAClienteExistente(
                    Integer.parseInt(idPetTexto), nomePet, FormatoUtils.parseEspecie(especie), raca,
                    FormatoUtils.converterData(nascimentoTexto), Integer.parseInt(idClienteTexto));

            if (!cadastrado) {
                JOptionPane.showMessageDialog(this,
                        "Não foi possível cadastrar. Verifique se o ID do pet já existe e se o cliente está cadastrado.");
                return;
            }

            JOptionPane.showMessageDialog(this, "Pet associado ao cliente cadastrado com sucesso.");
            formulario.limparCampos();
        } catch (NumberFormatException erro) {
            JOptionPane.showMessageDialog(this, "Os IDs do pet e do cliente precisam ser números.");
        } catch (DateTimeParseException erro) {
            JOptionPane.showMessageDialog(this, "Use a data no formato dd/MM/aaaa.");
        }
    }
}
