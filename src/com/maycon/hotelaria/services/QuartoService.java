package com.maycon.hotelaria.services;

import com.maycon.hotelaria.estruturas.Quarto;
import java.util.List;

/**
 * Interface que define os métodos utilizados para acessar dados referentes aos quartos cadastrados no sistema.
 * Métodos definidos: {@linkplain #cadastraQuarto(com.maycon.hotelaria.estruturas.Quarto)}, {@linkplain #consultaTodosQuartos()}
 * {@linkplain #consultaQuartosDisponiveis(java.lang.String, java.lang.String, java.lang.String)} 
 * @author Maycon
 */
public interface QuartoService {

    /**
     * O método realiza o cadastro de um quarto no Banco de Dados, a partir do
     * quarto dado.
     *
     * @param quarto Objeto que contém as informações do quarto a serem
     * cadastradas
     * @return TRUE ou FALSE, indicando sucesso ou falha no cadastro.
     */
    boolean cadastraQuarto(Quarto quarto);

    /**
     * Método utilizado para consultar todos os quartos.
     *
     * @return List com todos os quartos cadastrados no sistema.
     */
    List<Quarto> consultaTodosQuartos();

    /**
     * Com base nos parâmetros definidos pelo usuário, como tipo do quarto e
     * data inicial e final de interesse da reserva, o método retorna os quartos
     * disponíveis no hotel.
     *
     * @param tipo String que representa o tipo de quarto escolhido
     * @param dataInicio String que representa a data inicial da reserva de
     * interesse
     * @param dataFinal String que representa a data final da reserva de
     * interesse
     * @return List com todos os quartos que seguem os parâmetros definidos pelo
     * usuário.
     */
    List<Quarto> consultaQuartosDisponiveis(String tipo, String dataInicio, String dataFinal);

}
