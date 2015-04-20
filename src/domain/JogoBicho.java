/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Daniel
 */
public class JogoBicho {

    private int numeroApostado;
    private float valorApostado;
    private String dataAposta;

    public JogoBicho() {
    }

    public JogoBicho(int numeroApostado, float valorAcumulado, String date) {
        this.numeroApostado = numeroApostado;
        this.valorApostado = valorAcumulado;
        this.dataAposta = date;
    }

    public int getNumeroApostado() {
        return numeroApostado;
    }

    public void setNumeroApostado(int numeroApostado) {
        this.numeroApostado = numeroApostado;
    }

    public float getValorApostado() {
        return valorApostado;
    }

    public void setValorApostado(float valorApostado) {
        this.valorApostado = valorApostado;
    }

    public String getDataAposta() {
        return dataAposta;
    }

    public void setDataAposta(String dataAposta) {
        this.dataAposta = dataAposta;
    }

}
