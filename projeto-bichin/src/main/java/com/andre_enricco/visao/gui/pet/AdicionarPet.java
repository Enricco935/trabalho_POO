package com.andre_enricco.visao.gui.pet;

import java.awt.BorderLayout;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.andre_enricco.controller.PetController;
import com.andre_enricco.util.FormatoUtils;
import com.andre_enricco.visao.gui.modelos.ModeloFormulario;

public class AdicionarPet extends JPanel {

    private final PetController controller;
    private final ModeloFormulario formulario;

    public AdicionarPet(PetController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        formulario = new ModeloFormulario(new String[] {
            "ID do pet:",
            "Nome do pet:",
            "Espécie:",
            "Raça:",
            "Nascimento (dd/MM/aaaa):",
            "ID do cliente:",
            "Nome do cliente:",
            "Telefone do cliente:"
        }, "Cadastrar");

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
        String nomeCliente = formulario.getValor(6).trim();
        String telefoneCliente = formulario.getValor(7).trim();

        if (idPetTexto.isEmpty() || nomePet.isEmpty() || especie.isEmpty()
                || raca.isEmpty() || nascimentoTexto.isEmpty() || idClienteTexto.isEmpty()
                || nomeCliente.isEmpty() || telefoneCliente.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            return;
        }

        if (!FormatoUtils.telefoneValido(telefoneCliente)) {
            JOptionPane.showMessageDialog(this, "Use o telefone no formato (dd) ddddd-dddd.");
            return;
        }

        try {
            boolean cadastrado = controller.adicionar(
                    Integer.parseInt(idPetTexto),
                    nomePet,
                    especie,
                    raca,
                    FormatoUtils.converterData(nascimentoTexto),
                    Integer.parseInt(idClienteTexto),
                    nomeCliente,
                    telefoneCliente);

            if (!cadastrado) {
                JOptionPane.showMessageDialog(
                        this,
                        "Não foi possível cadastrar. O ID do pet ou do cliente já existe.");
                return;
            }

            JOptionPane.showMessageDialog(this, "Pet e cliente cadastrados com sucesso.");
            formulario.limparCampos();
        } catch (NumberFormatException erro) {
            JOptionPane.showMessageDialog(this, "Os IDs do pet e do cliente precisam ser números.");
        } catch (DateTimeParseException erro) {
            JOptionPane.showMessageDialog(this, "Use a data no formato dd/MM/aaaa.");
        }
    }
}
