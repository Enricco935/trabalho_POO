package com.andre_enricco.visao.gui.servicoPrestado;

import java.awt.BorderLayout;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.andre_enricco.controller.ServicoPrestadoController;
import com.andre_enricco.visao.gui.modelos.ModeloFormulario;

public class AdicionarServicoPrestado extends JPanel {

    private final ServicoPrestadoController controller;
    private final ModeloFormulario formulario;

    public AdicionarServicoPrestado(ServicoPrestadoController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        formulario = new ModeloFormulario(new String[] {
            "ID do serviço prestado:",
            "Data (AAAA-MM-DD):",
            "ID do pet:",
            "ID do serviço:",
            "Valor cobrado (use ponto para decimais):"
        }, "Cadastrar");

        formulario.getBotao().addActionListener(e -> cadastrar());
        add(formulario, BorderLayout.NORTH);
    }

    private void cadastrar() {
        String idTexto = formulario.getValor(0).trim();
        String dataTexto = formulario.getValor(1).trim();
        String idPetTexto = formulario.getValor(2).trim();
        String idServicoTexto = formulario.getValor(3).trim();
        String valorTexto = formulario.getValor(4).trim();

        if (idTexto.isEmpty() || dataTexto.isEmpty() || idPetTexto.isEmpty()
                || idServicoTexto.isEmpty() || valorTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            return;
        }

        try {
            boolean cadastrado = controller.adicionar(
                    Integer.parseInt(idTexto),
                    LocalDate.parse(dataTexto),
                    Integer.parseInt(idPetTexto),
                    Integer.parseInt(idServicoTexto),
                    Double.parseDouble(valorTexto));

            if (!cadastrado) {
                JOptionPane.showMessageDialog(
                        this,
                        "Não foi possível cadastrar. Confira se o ID já existe e se pet e serviço estão cadastrados.");
                return;
            }

            JOptionPane.showMessageDialog(this, "Serviço prestado cadastrado com sucesso.");
            formulario.limparCampos();
        } catch (NumberFormatException erro) {
            JOptionPane.showMessageDialog(
                    this,
                    "IDs precisam ser números e o valor deve usar ponto para decimais.");
        } catch (DateTimeParseException erro) {
            JOptionPane.showMessageDialog(this, "Use a data no formato AAAA-MM-DD.");
        }
    }
}
