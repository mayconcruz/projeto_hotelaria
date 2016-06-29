package com.maycon.hotelaria.estruturas;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * A classe representa a estrutura de informa√ß√µes sobre uma reserva que ser√° armazenada no Banco de Dados
 * @author Maycon
 */

@Entity
@Table(name = "reservas")
public class Reserva implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private int idReserva;
    /**
     * O atributo usuario, do tipo Usuario representado na classe Usuario, È utilizado para se obter o identificador 
     * do usu·rio do sistema que est· realizando a reserva do quarto.
     */
    @OneToOne
    @JoinColumn(name = "id_cliente")
    private Usuario usuario;
    /**
     * O atributo quarto, do tipo Quarto representado na classe Quarto, È utilizado para se obter o identificador 
     * do quarto que ser· reservado.
     */
    @OneToOne
    @JoinColumn(name = "id_quarto")
    private Quarto quarto;
    @Column(name = "data_hora_entrada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraEntrada;
    @Column(name = "data_hora_saida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraSaida;

    public Reserva() {
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public Date getDataHoraEntrada() {
        return dataHoraEntrada;
    }

    public void setDataHoraEntrada(Date dataHoraEntrada) {
        this.dataHoraEntrada = dataHoraEntrada;
    }

    public Date getDataHoraSaida() {
        return dataHoraSaida;
    }

    public void setDataHoraSaida(Date dataHoraSaida) {
        this.dataHoraSaida = dataHoraSaida;
    }

}
