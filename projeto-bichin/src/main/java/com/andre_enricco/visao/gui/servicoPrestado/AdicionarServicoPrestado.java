package com.andre_enricco.visao.gui.servicoPrestado;

import java.awt.BorderLayout;
import java.time.format.DateTimeParseException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.andre_enricco.controller.ServicoPrestadoController;
import com.andre_enricco.util.FormatoUtils;
import com.andre_enricco.visao.gui.modelos.ModeloFormulario;

public class AdicionarServicoPrestado extends JPanel {

    private final ServicoPrestadoController controller;
    private final ModeloFormulario formulario;

    public AdicionarServicoPrestado(ServicoPrestadoController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        formulario = new ModeloFormulario(new String[] {
            "ID do serviço prestado:",
            "Data (dd/MM/aaaa):",
            "ID do pet:",
            "ID do serviço:"
        }, "Cadastrar");

        formulario.getBotao().addActionListener(e -> cadastrar());
        add(formulario, BorderLayout.NORTH);
    }

    private void cadastrar() {
        String idTexto = formulario.getValor(0).trim();
        String dataTexto = formulario.getValor(1).trim();
        String idPetTexto = formulario.getValor(2).trim();
        String idServicoTexto = formulario.getValor(3).trim();

        if (idTexto.isEmpty() || dataTexto.isEmpty() || idPetTexto.isEmpty() || idServicoTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);
            var data = FormatoUtils.converterData(dataTexto);
            int idPet = Integer.parseInt(idPetTexto);
            int idServico = Integer.parseInt(idServicoTexto);

            var servico = controller.buscarServicoParaPrestacao(idServico);

            String valorTexto = JOptionPane.showInputDialog(
                    this,
                    String.format("Serviço escolhido: %s%nValor cadastrado: R$ %.2f%n%nDigite o valor cobrado:",
                            servico.getDescricao(), servico.valorFinalDoServico()),
                    "Valor do serviço prestado",
                    JOptionPane.QUESTION_MESSAGE);
            if (valorTexto == null) {
                return;
            }

            double valor = Double.parseDouble(valorTexto.trim());
            controller.adicionar(id, data, idPet, idServico, valor);

            JOptionPane.showMessageDialog(this, "Serviço prestado cadastrado com sucesso.");
            formulario.limparCampos();
        } catch (NumberFormatException erro) {
            JOptionPane.showMessageDialog(
                    this,
                    "IDs precisam ser números e o valor deve usar ponto para decimais.");
        } catch (DateTimeParseException erro) {
            JOptionPane.showMessageDialog(this, "Use a data no formato dd/MM/aaaa.");
        } catch (IllegalArgumentException erro) {
            JOptionPane.showMessageDialog(this, erro.getMessage());
        }
    }
}
