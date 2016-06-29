package com.maycon.hotelaria.estruturas;

import java.io.Serializable;
import javax.persistence.*;

/**
 * A classe representa a estrutura de informações sobre um quarto que será armazenado no Banco de Dados
 * @author Maycon
 */

@Entity
@Table(name = "quartos")
public class Quarto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_quarto")
    private int idQuarto;
    @Column(name = "numero_quarto")
    private int numQuarto;    
    /**
     * O atributo tipoQuarto deve receber os valores Solteiro, Duplo, Triplo ou Casal.
    */
    @Column(name = "tipo_quarto")
    private String tipoQuarto;
    @Column(name = "valor_diaria")
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
