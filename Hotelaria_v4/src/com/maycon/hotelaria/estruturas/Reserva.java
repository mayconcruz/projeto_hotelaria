package com.maycon.hotelaria.estruturas;

/**
 * A classe representa a estrutura de informações sobre uma reserva que será armazenada no Banco de Dados
 * @author Maycon
 */
public class Reserva {

    private int idReserva;
    /**
     * O atributo usuario, do tipo Usuario representado na classe Usuario, é utilizado para se obter o identificador 
     * do usuário do sistema que está realizando a reserva do quarto.
     */
    private Usuario usuario;
    /**
     * O atributo quarto, do tipo Quarto representado na classe Quarto, é utilizado para se obter o identificador 
     * do quarto que será reservado.
     */
    private Quarto quarto;
    private String dataHoraEntrada;
    private String dataHoraSaida;

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

    public String getDataHoraEntrada() {
        return dataHoraEntrada;
    }

    public void setDataHoraEntrada(String dataHoraEntrada) {
        this.dataHoraEntrada = dataHoraEntrada;
    }

    public String getDataHoraSaida() {
        return dataHoraSaida;
    }

    public void setDataHoraSaida(String dataHoraSaida) {
        this.dataHoraSaida = dataHoraSaida;
    }

}
