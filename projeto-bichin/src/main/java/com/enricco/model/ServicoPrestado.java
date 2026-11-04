package com.enricco.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ServicoPrestado {
    private int id;
    private Servico servico;
    private LocalDate data;
    private double valorCobrado;
    private Pet pet;

    public ServicoPrestado(){

    }
    public ServicoPrestado(int id, Servico servico, LocalDate data, double valorCobrado, Pet pet) {
        this.id = id;
        this.servico = servico;
        this.data = data;
        this.valorCobrado = valorCobrado;
        this.pet = pet;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Servico getServico() {
        return servico;
    }
    public void setServico(Servico servico) {
        this.servico = servico;
    }
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    public double getValorCobrado() {
        return valorCobrado;
    }
    public void setValorCobrado(double valorCobrado) {
        this.valorCobrado = valorCobrado;
    }
    public Pet getPet() {
        return pet;
    }
    public void setPet(Pet pet) {
        this.pet = pet;
    }
    public int getDiasDaExecucaoDoServico(){
        return Math.toIntExact(ChronoUnit.DAYS.between(data, LocalDate.now()));
    }
    public String getNomeDonoDoPet() {
        return this.pet.getCliente().getNome();
    }
    public Cliente getDonoDoPet() {
        return this.pet.getCliente();
    }
    public double getValorServico() {
        return this.servico.getValor();
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((servico == null) ? 0 : servico.hashCode());
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        long temp;
        temp = Double.doubleToLongBits(valorCobrado);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((pet == null) ? 0 : pet.hashCode());
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
        ServicoPrestado other = (ServicoPrestado) obj;
        if (id != other.id)
            return false;
        if (servico == null) {
            if (other.servico != null)
                return false;
        } else if (!servico.equals(other.servico))
            return false;
        if (data == null) {
            if (other.data != null)
                return false;
        } else if (!data.equals(other.data))
            return false;
        if (Double.doubleToLongBits(valorCobrado) != Double.doubleToLongBits(other.valorCobrado))
            return false;
        if (pet == null) {
            if (other.pet != null)
                return false;
        } else if (!pet.equals(other.pet))
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        return "ServicoPrestado [id=" + id + ", servico=" + servico + ", data=" + data + ", valorCobrado="
                + valorCobrado + ", pet=" + pet + "]";
    }

}
