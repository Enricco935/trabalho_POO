package com.andre_enricco.visao.gui.pet;

import com.andre_enricco.controller.PetController;
import com.andre_enricco.model.Pet;
import com.andre_enricco.util.FormatoUtils;
import com.andre_enricco.visao.gui.modelos.ModeloTabelaGenerica;
import com.andre_enricco.visao.gui.modelos.TelaAtualizavel;

public class ListaPets extends ModeloTabelaGenerica implements TelaAtualizavel {

    private final PetController controller;

    public ListaPets(PetController controller) {
        super(new String[] {
            "ID",
            "Nome",
            "Espécie",
            "Raça",
            "Nascimento",
            "Dono"
        });

        this.controller = controller;
        atualizar();
    }

    @Override
    public void atualizar() {
        limparLinhas();
        for (Pet pet : controller.listar()) {
            adicionarLinha(new Object[] {
                pet.getId(),
                pet.getNome(),
                pet.getEspecie(),
                pet.getRaca(),
                FormatoUtils.formatarData(pet.getNascimento()),
                pet.getCliente() == null ? "" : pet.getCliente().getNome()
            });
        }
    }
}
