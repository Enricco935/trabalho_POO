package com.enricco.model;

public class Servico {
    private String descricao;
    private int id;
    private double valor;

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }   


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
        result = prime * result + id;
        long temp;
        temp = Double.doubleToLongBits(valor);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Servico other = (Servico) obj;
        if (descricao == null) {
            if (other.descricao != null)
                return false;
        } else if (!descricao.equals(other.descricao))
            return false;
        if (id != other.id)
            return false;
        if (Double.doubleToLongBits(valor) != Double.doubleToLongBits(other.valor))
            return false;
        return true;
    }
    
    
    @Override
    public String toString() {
        return "Servico [descricao=" + descricao + ", id=" + id + ", valor=" + valor + "]";
    }

}
