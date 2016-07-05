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
    public boolean cadastraQuarto(Quarto quarto) {
        GerenciaEntity entity = new GerenciaEntity();
        EntityManager manager = entity.constroiManager();
        boolean sucesso = true;

        try {
            manager.getTransaction().begin();
            manager.persist(quarto);
            manager.getTransaction().commit();
        } catch (Exception e) {
            // TODO A forma de mostrar o erro é da camada de view, e se eu estiver em um sistema WEB e for reutilizar essa DAO, vai mostrar um JOptionPane?
            JOptionPane.showMessageDialog(null, "Erro na tabela de quartos: " + e.getMessage(), "Problema no Banco de Dados", JOptionPane.ERROR_MESSAGE);
            sucesso = false;
        } finally {
            // TODO Crie uma classe utilitária para esse tipo de coisa
            if (manager != null) {
                entity.closable(manager);
            }
        }
        return sucesso;
    }

    /**
     * Método responsável por listar todos os quartos do hotel.
     *
     * @return List contendo todos os quartos cadastrados. Caso não haja nenhum quarto, retorna uma lista vazia.
     */
    //UTILIZAR UM TIPO DE COLLECTION PERMITE QUE O CÓDIGO FIQUE MAIS FLEXÍVEL PARA O TRATAMENTO DOS DADOS RETORNADOS
    public List<Quarto> consultaTodosQuartos() {
        GerenciaEntity entity = new GerenciaEntity();
        EntityManager manager = entity.constroiManager();
        List<Quarto> quartos = null;

        try {
            String sql = "FROM Quarto";
            TypedQuery<Quarto> query = manager.createQuery(sql, Quarto.class);
            quartos = query.getResultList();
        } catch (Exception ex) {
            // TODO A forma de mostrar o erro é da camada de view, e se eu estiver em um sistema WEB e for reutilizar essa DAO, vai mostrar um JOptionPane?
            JOptionPane.showMessageDialog(null, "Erro na consulta da tabela Quartos! " + ex.getMessage(), "Problema no Banco de Dados", JOptionPane.ERROR_MESSAGE);
        } finally {
            // TODO Crie uma classe utilitária para esse tipo de coisa
            if (manager != null) {
                entity.closable(manager);
            }
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
    public List<Quarto> consultaQuartosDisponiveis(String tipo, String dataInicio, String dataFinal) {

        List<Quarto> quartos = null;
        GerenciaEntity entity = new GerenciaEntity();
        EntityManager manager = entity.constroiManager();

        try {

            Timestamp dataInicioTs, dataFinalTs;
            dataInicioTs = Timestamp.valueOf(dataInicio + " 14:00:00");
            dataFinalTs = Timestamp.valueOf(dataFinal + " 12:00:00");
            
            String sql =  "FROM Quarto q WHERE NOT EXISTS( "
                        + " SELECT 'T' FROM Reserva r WHERE :data_entrada < dataHoraSaida AND :data_saida > dataHoraEntrada "
                        + "AND q = r.quarto) "
                        + "AND (q.tipoQuarto = :tipo OR :tipo IS NULL)";
            
            TypedQuery<Quarto> query = manager.createQuery(sql, Quarto.class);
            query.setParameter("data_entrada", dataInicioTs);
            query.setParameter("data_saida", dataFinalTs);
            query.setParameter("tipo", tipo);
            quartos = query.getResultList();
        } catch (Exception ex) {
            // TODO A forma de mostrar o erro é da camada de view, e se eu estiver em um sistema WEB e for reutilizar essa DAO, vai mostrar um JOptionPane?
            JOptionPane.showMessageDialog(null, "Erro na consulta da tabela Reservas! " + ex.getMessage(), "Problema no Banco de Dados", JOptionPane.ERROR_MESSAGE);

        } finally {
            // TODO Crie uma classe utilitária para esse tipo de coisa
            if (manager != null) {
                entity.closable(manager);
            }
        }
        return quartos != null ? quartos : Collections.<Quarto>emptyList();
    }
}
