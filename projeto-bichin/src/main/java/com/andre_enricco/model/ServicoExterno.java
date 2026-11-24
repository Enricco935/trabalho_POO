package com.andre_enricco.model;

public class ServicoExterno extends Servico {
    private double valorTerceirizado;

    public ServicoExterno() {
    }

    public ServicoExterno(int id, String descricao, double valor, double valorTerceirizado) {
        super(id, descricao, valor);
        this.valorTerceirizado = valorTerceirizado;
    }

    public double getValorTerceirizado() {
        return valorTerceirizado;
    }
    public void setValorTerceirizado(double valorTerceirizado) {
        this.valorTerceirizado = valorTerceirizado;
    }

    @Override
    public double valorFinalDoServico() {
        return getValor() + valorTerceirizado;
    }

    @Override
    public String toString() {
        return "ServicoExterno [valorTerceirizado=" + valorTerceirizado + ", " + super.toString() + "]";
    }
}
