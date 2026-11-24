
package com.andre_enricco.model;

public enum Especie {
    CAO("Cão"),
    GATO("Gato"),
    PEIXE("Peixe"),
    PASSARO("Pássaro");

    private final String descricao;

    Especie(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override 
    public String toString() {
        return descricao;
    }

    //o value of é gerado automaticamente entao nao implementamos
}
