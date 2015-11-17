package br.senac.pi.carros.domain;

/**
 * Created by Aluno on 06/11/2015.
 */
public class Carros {
    private long id;
    private String modelo;
    private String fabricante;
    private String ano;
    private String cor;
    private  String motor;

    public Carros() {
    }

    public Carros(long id, String modelo, String fabricante, String ano,String cor, String motor) {
        this.id = id;
        this.modelo = modelo;
        this.fabricante = fabricante;
        this.ano = ano;
        this.cor = cor;
        this.motor = motor;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}