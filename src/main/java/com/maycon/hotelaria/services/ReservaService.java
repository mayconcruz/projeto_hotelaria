package com.maycon.hotelaria.services;

import com.maycon.hotelaria.estruturas.Reserva;
import com.maycon.hotelaria.estruturas.Usuario;
import java.util.List;

/**
 * Interface que define os métodos utilizados para acessar dados referentes às reservas cadastradas no sistema.
 * Métodos definidos: {@linkplain #cadastraReservas(com.maycon.hotelaria.estruturas.Reserva)}, {@linkplain #consultaReservasUsuario(java.lang.Integer) }
 * {@linkplain #consultaTodasReservas(java.lang.String, java.lang.String)}
 * @author Maycon
 */
public interface ReservaService {

    /**
     * O método realiza a reserva de um quarto, a partir das informações da
     * reserva.
     *
     * @param reserva Objeto que contém as informações da reserva a ser
     * cadastrada.
     * @return TRUE ou FALSE, indicando sucesso ou falha no cadastro.
     */
    boolean cadastraReservas(Reserva reserva);

    /**
     * O método realiza a consulta das reservas realizadas por um determinado
     * usuário, no Banco de Dados, a partir do identificador deste usuário.
     *
     * @param usuario Usu�rio que deseja-se saber as reservas
     * @param dataInicio String que representa a data inicial das reservas de interesse
     * @param dataFinal String que representa a data final das reservas de interesse
     * @return List com as reservas realizadas pelo usuário.
     */
    List<Reserva> consultaReservasUsuario(Usuario usuario, String dataInicio, String dataFinal); // TODO Mudar para receber o usuário

    /**
     * O método realiza a consulta de todas as reservas contidas no Banco de
     * Dados em um determinado momento.
     *
     * @param dataInicio String que indica a data de início da reserva de
     * interesse
     * @param dataFinal String que indica a data final da reserva de interesse
     * @return List com todos as reservas cadastradas no Banco de Dados.
     */
    List<Reserva> consultaTodasReservas(String dataInicio, String dataFinal);

}
