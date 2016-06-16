package com.maycon.hotelaria.estruturas;


/**
 * A classe representa a estrutura de informações sobre um quarto que será armazenado no Banco de Dados
 * @author Maycon
 */
public class Quarto {

    private int idQuarto;
    private int numQuarto;    
    /**
     * O atributo tipoQuarto deve receber os valores Solteiro, Duplo, Triplo ou Casal.
    */
    private String tipoQuarto;
    private double valorDiaria;
    private String informacoes;

    public Quarto() {
    }

    public int getIdQuarto() {
        return idQuarto;
    }

    public void setIdQuarto(int idQuarto) {
        this.idQuarto = idQuarto;
    }

    public int getNumQuarto() {
        return numQuarto;
    }

    public void setNumQuarto(int numQuarto) {
        this.numQuarto = numQuarto;
    }

    public String getTipoQuarto() {
        return tipoQuarto;
    }

    public void setTipoQuarto(String tipoQuarto) {
        this.tipoQuarto = tipoQuarto;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public String getInformacoes() {
        return informacoes;
    }

    public void setInformacoes(String informacoes) {
        this.informacoes = informacoes;
    }

}
