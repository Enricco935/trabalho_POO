package com.andre_enricco.visao.gui.pet;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.andre_enricco.controller.PetController;
import com.andre_enricco.model.Pet;
import com.andre_enricco.util.FormatoUtils;
import com.andre_enricco.visao.gui.modelos.ModeloFormulario;
import com.andre_enricco.visao.gui.modelos.ModeloTabelaGenerica;

public class BuscaPet extends JPanel {

    private final ModeloTabelaGenerica tabela;

    public BuscaPet(PetController controller) {
        setLayout(new BorderLayout(10, 10));

        tabela = new ModeloTabelaGenerica(new String[] {
            "ID", "Nome", "Espécie", "Raça", "Nascimento", "Dono"
        });

        ModeloFormulario formulario = new ModeloFormulario(
                new String[] { "ID do pet:" },
                "Buscar");

        formulario.getBotao().addActionListener(e -> {
            tabela.limparLinhas();

            try {
                int id = Integer.parseInt(formulario.getValor(0).trim());
                Pet pet = controller.buscar(id);

                if (pet == null) {
                    JOptionPane.showMessageDialog(this, "Pet não encontrado.");
                    return;
                }

                tabela.adicionarLinha(new Object[] {
                    pet.getId(),
                    pet.getNome(),
                    pet.getEspecie(),
                    pet.getRaca(),
                    FormatoUtils.formatarData(pet.getNascimento()),
                    pet.getCliente() == null ? "" : pet.getCliente().getNome()
                });
            } catch (NumberFormatException erro) {
                JOptionPane.showMessageDialog(this, "Digite um ID válido.");
            }
        });

        add(formulario, BorderLayout.NORTH);
        add(tabela, BorderLayout.CENTER);
    }
}
