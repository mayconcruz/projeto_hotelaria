package com.maycon.hotelaria.dao;

import com.maycon.hotelaria.estruturas.Quarto;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import javax.persistence.*;
import javax.swing.JOptionPane;

/**
 * Esta classe apresenta métodos utilizados para fazerem a interação entre a aplicação e a fonte de dados que armazena os dados
 * dos quartos armazenados no sistema.
 * @author Maycon
 */
public class QuartoDAO {

    /**
     * Método responsável por realizar o cadastro de quartos do hotel a partir
     * das informações dele
     *
     * @param quarto Objeto do tipo Quarto que contém as informações do quarto
     * @return TRUE ou FALSE, indicando sucesso ou falha no cadastro do quarto
     */
    public boolean cadastrarQuarto(Quarto quarto) throws PersistenceException {
        GerenciaEntity entity = GerenciaEntity.obterEntity();
       entity.setManager(entity.getFactory());
        boolean sucesso = true;

        try {
            entity.getManager().getTransaction().begin();
            entity.getManager().persist(quarto);
            entity.getManager().getTransaction().commit();
        } catch (PersistenceException p) {
            sucesso = false;
            entity.getManager().getTransaction().rollback();
        } finally {
            FinalizaManager.finalizarManager(entity);
        }
        return sucesso;
    }

    /**
     * Método responsável por listar todos os quartos do hotel.
     *
     * @return List contendo todos os quartos cadastrados. Caso não haja nenhum quarto, retorna uma lista vazia.
     */
    //UTILIZAR UM TIPO DE COLLECTION PERMITE QUE O CÓDIGO FIQUE MAIS FLEXÍVEL PARA O TRATAMENTO DOS DADOS RETORNADOS
    public List<Quarto> consultarTodosQuartos() throws PersistenceException {
        GerenciaEntity entity = GerenciaEntity.obterEntity();
        entity.setManager(entity.getFactory());
        List<Quarto> quartos = null;

        try {
            String sql = "FROM Quarto";
            TypedQuery<Quarto> query = entity.getManager().createQuery(sql, Quarto.class);
            quartos = query.getResultList();
        } catch (PersistenceException p) {
            entity.getManager().getTransaction().rollback();
        } finally {
            FinalizaManager.finalizarManager(entity);
        }
        return quartos != null ? quartos : Collections.emptyList();
    }

    /**
     * Método responsável por listar todas os quartos do hotel, a partir dos
     * parâmetros definidos pelo usuário.
     *
     * @param tipo String que indica o tipo de quarto de interesse
     * @param dataInicio String que indica a data de início da reserva de
     * interesse
     * @param dataFinal String que indica a data final da reserva de interesse
     * @return List contendo todos os quartos disponíveis para reserva. Caso não haja nenhum quarto disponível, retorna uma lista vazia
     */
    //UTILIZAR UM TIPO DE COLLECTION PERMITE QUE O CÓDIGO FIQUE MAIS FLEXÃ?VEL PARA O TRATAMENTO DOS DADOS RETORNADOS
    public List<Quarto> consultarQuartosDisponiveis(String tipo, String dataInicio, String dataFinal) throws PersistenceException {

        List<Quarto> quartos = null;
        GerenciaEntity entity = GerenciaEntity.obterEntity();
        entity.setManager(entity.getFactory());

        try {

            Timestamp dataInicioTs, dataFinalTs;
            dataInicioTs = Timestamp.valueOf(dataInicio + " 14:00:00");
            dataFinalTs = Timestamp.valueOf(dataFinal + " 12:00:00");
            
            String sql =  "FROM Quarto q WHERE NOT EXISTS( "
                        + " SELECT 'T' FROM Reserva r WHERE :data_entrada < dataHoraSaida AND :data_saida > dataHoraEntrada "
                        + "AND q = r.quarto) "
                        + "AND (q.tipoQuarto = :tipo OR :tipo IS NULL)";
            
            TypedQuery<Quarto> query = entity.getManager().createQuery(sql, Quarto.class);
            query.setParameter("data_entrada", dataInicioTs);
            query.setParameter("data_saida", dataFinalTs);
            query.setParameter("tipo", tipo);
            quartos = query.getResultList();
        } catch (PersistenceException p) {
            entity.getManager().getTransaction().rollback();
        } finally {
            FinalizaManager.finalizarManager(entity);
        }
        return quartos != null ? quartos : Collections.<Quarto>emptyList();
    }
}
