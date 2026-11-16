package com.andre_enricco.visao.gui.pet;

import com.andre_enricco.controller.PetController;
import com.andre_enricco.model.Pet;
import com.andre_enricco.visao.gui.modelos.ModeloTabelaGenerica;

public class ListaPets extends ModeloTabelaGenerica {

    public ListaPets(PetController controller) {
        super(new String[] {
            "ID",
            "Nome",
            "Espécie",
            "Raça",
            "Nascimento",
            "Dono"
        });

        for (Pet pet : controller.listar()) {
            adicionarLinha(new Object[] {
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
