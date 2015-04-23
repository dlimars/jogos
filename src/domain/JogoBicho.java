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

    private String numeroApostado;
    private float valorApostado;

    public JogoBicho() {
    }

    public JogoBicho(String numeroApostado, float valorAcumulado) {
        this.numeroApostado = numeroApostado;
        this.valorApostado = valorAcumulado;
    }

    public String getNumeroApostado() {
        return numeroApostado;
    }

    public void setNumeroApostado(String numeroApostado) {
        this.numeroApostado = numeroApostado;
    }

    public float getValorApostado() {
        return valorApostado;
    }

    public void setValorApostado(float valorApostado) {
        this.valorApostado = valorApostado;
    }
}
