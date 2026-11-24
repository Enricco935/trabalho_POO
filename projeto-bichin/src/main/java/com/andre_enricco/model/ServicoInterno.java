package com.andre_enricco.model;

public class ServicoInterno extends Servico {
    private String crmv;

    public ServicoInterno() {
    }

    public ServicoInterno(int id, String descricao, double valor, String crmv) {
        super(id, descricao, valor);
        this.crmv = crmv;
    }

    public String getCrmv() {
        return crmv;
    }
    public void setCrmv(String crmv) {
        this.crmv = crmv;
    }

    @Override
    public double valorFinalDoServico() {
        return getValor();
    }

    @Override
    public String toString() {
        return "ServicoInterno [crmv=" + crmv + ", " + super.toString() + "]";
    }
}
