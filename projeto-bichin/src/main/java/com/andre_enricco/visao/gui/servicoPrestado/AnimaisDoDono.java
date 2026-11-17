package com.andre_enricco.visao.gui.servicoPrestado;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.andre_enricco.controller.ServicoPrestadoController;
import com.andre_enricco.model.Pet;
import com.andre_enricco.visao.gui.modelos.ModeloFormulario;
import com.andre_enricco.visao.gui.modelos.ModeloTabelaGenerica;

public class AnimaisDoDono extends JPanel {

    private final ModeloFormulario formulario;
    private final ModeloTabelaGenerica tabela;
    private final ServicoPrestadoController controller;

    public AnimaisDoDono(ServicoPrestadoController controller) {
        this.controller = controller;
        setLayout(new BorderLayout(10, 10));

        formulario = new ModeloFormulario(
                new String[] { "Nome do dono:" },
                "Buscar animais");
        tabela = new ModeloTabelaGenerica(new String[] {
            "ID", "Nome", "Espécie", "Raça", "Nascimento", "Dono"
        });

        formulario.getBotao().addActionListener(e -> buscar());
        add(formulario, BorderLayout.NORTH);
        add(tabela, BorderLayout.CENTER);
    }

    private void buscar() {
        tabela.limparLinhas();
        String nomeDono = formulario.getValor(0).trim();

        if (nomeDono.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite o nome do dono.");
            return;
        }

        ArrayList<Pet> pets = controller.buscarBichosPorNomeDono(nomeDono);
        if (pets.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum animal encontrado para esse dono.");
            return;
        }

        Set<Integer> idsJaExibidos = new HashSet<>();
        for (Pet pet : pets) {
            if (!idsJaExibidos.add(pet.getId())) {
                continue;
            }

            tabela.adicionarLinha(new Object[] {
                pet.getId(),
                pet.getNome(),
                pet.getEspecie(),
                pet.getRaca(),
                pet.getNascimento(),
                pet.getCliente() == null ? "" : pet.getCliente().getNome()
            });
        }
    }
}
