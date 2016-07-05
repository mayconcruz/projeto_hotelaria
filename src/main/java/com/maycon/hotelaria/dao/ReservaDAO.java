package com.maycon.hotelaria.dao;

import com.maycon.hotelaria.estruturas.Reserva;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import javax.persistence.*;
import javax.swing.JOptionPane;

/**
 * Esta classe apresenta métodos utilizados para fazerem a interação entre a aplicação e a fonte de dados que armazena os dados
 * das reservas armazenados no sistema.
 * @author Maycon
 */
public class ReservaDAO {

    /**
     * Método responsável por realizar o cadastro de reservas do hotel a partir
     * das informações da reserva
     *
     * @param reserva Objeto do tipo Reserva que contém as informações da
     * reserva
     * @return TRUE ou FALSE, indicando sucesso ou falha no cadastro da reserva
     */
    public boolean cadastraReservas(Reserva reserva) {
        GerenciaEntity entity = new GerenciaEntity();
        EntityManager manager = entity.constroiManager();
        boolean sucesso = true;

        try {
            manager.getTransaction().begin();
            manager.persist(reserva);
            manager.getTransaction().commit();
            
        } catch (Exception e) {
            // TODO A forma de mostrar o erro é da camada de view, e se eu estiver em um sistema WEB e for reutilizar essa DAO, vai mostrar um JOptionPane?
            // TODO Cade o rollback em caso de erro?
            sucesso = false;
            JOptionPane.showMessageDialog(null, "Erro na tabela de reservas: " + e.getMessage(), "Problema no Banco de Dados", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (manager != null) {
                entity.closable(manager);
            }
        }
        return sucesso;
    }

    /**
     * Método responsável por consultar as reservas de um determinado usuário do sistema. A consulta pode ou não ser 
     * filtrada pela data de início e de término da reserva.
     *
     * @param idUsuario Inteiro que representa o id do usuário a ser pesquisadas as reservas
     * @param dataInicio String que representa a data de inicio das reservas a serem pesquisadas
     * @param dataFinal String que representa a data final das reservas a serem pesquisadas
     * @return List de reservas feitas por um usuário específico. Caso não haja nenhuma reserva realizada pelo usuário, 
     * retorna uma lista vazia
     */
    public List<Reserva> consultaReservasUsuario(Integer idUsuario, String dataInicio, String dataFinal) {
        List<Reserva> reservasUsuarios = null;
        GerenciaEntity entity = new GerenciaEntity();
        EntityManager manager = entity.constroiManager();
        
        try {
            
            if (!"".equals(dataInicio) || !"".equals(dataFinal)) {

                String sql = " FROM Reserva r"
                            +" WHERE r.usuario.idUsuario = :usuario and dataHoraEntrada >= :entrada and dataHoraSaida <= :saida"
                            +" ORDER BY dataHoraEntrada";

                Timestamp dataInicioTs, dataFinalTs;
                dataInicioTs = Timestamp.valueOf(dataInicio + " 14:00:00");
                dataFinalTs = Timestamp.valueOf(dataFinal + " 12:00:00");

                TypedQuery<Reserva> query = manager.createQuery(sql, Reserva.class);
                query.setParameter("usuario", idUsuario);
                query.setParameter("entrada", dataInicioTs);
                query.setParameter("saida", dataFinalTs);
                reservasUsuarios = query.getResultList();
            }
            else{

                String sql = " FROM Reserva r"
                            +" WHERE r.usuario.idUsuario = :cliente"
                            +" ORDER BY dataHoraEntrada";

                TypedQuery<Reserva> query = manager.createQuery(sql, Reserva.class); 
                query.setParameter("cliente", idUsuario);
                reservasUsuarios = query.getResultList();
            }                    

        } catch (Exception ex) {
            // TODO A forma de mostrar o erro é da camada de view, e se eu estiver em um sistema WEB e for reutilizar essa DAO, vai mostrar um JOptionPane?
            // TODO Cade o rollback em caso de erro?
            JOptionPane.showMessageDialog(null, "Erro na consulta da tabela Reservas! " + ex.getMessage(), "Problema no Banco de Dados", JOptionPane.ERROR_MESSAGE);

        } finally {
            if (manager!= null) {
                entity.closable(manager);

            }
          }
        return reservasUsuarios != null ? reservasUsuarios : Collections.<Reserva>emptyList();
    }

    /**
     * Método responsável por listar todas as reservas feitas no hotel. Se o
     * usuário escolher uma data de início e de final o método retorna as
     * reservas naquele período, se não retorna todas as reservas.
     *
     * @param dataInicio String que representa a data de inicio das reservas a
     * serem pesquisadas
     * @param dataFinal String que representa a data final das reservas a serem
     * pesquisadas
     * @return List contendo todas as reservas. Caso não haja nenhuma reserva realizada no período escolhido, 
     * retorna uma lista vazia
     */
    public List<Reserva> consultaTodasReservas(String dataInicio, String dataFinal) {
        List<Reserva> reservas = null;
        GerenciaEntity entity = new GerenciaEntity();
        EntityManager manager = entity.constroiManager();

        try {
            if (!"".equals(dataInicio) || !"".equals(dataFinal)) {
                String sql = "FROM Reserva r "
                        + "WHERE dataHoraEntrada>= :entrada AND dataHoraSaida <= :saida"
                        + " ORDER BY r.usuario.nome";


                Timestamp dataInicioTs, dataFinalTs;
                dataInicioTs = Timestamp.valueOf(dataInicio + " 14:00:00");
                dataFinalTs = Timestamp.valueOf(dataFinal + " 12:00:00");

                TypedQuery<Reserva> query = manager.createQuery(sql, Reserva.class);
                query.setParameter("entrada", dataInicioTs);
                query.setParameter("saida", dataFinalTs);
                reservas = query.getResultList();

            } else {
                String sql = "FROM Reserva r "
                        + " ORDER BY r.usuario.nome";

                TypedQuery<Reserva> query = manager.createQuery(sql, Reserva.class);
                reservas = query.getResultList();
            }

        } catch (Exception ex) {
            // TODO A forma de mostrar o erro é da camada de view, e se eu estiver em um sistema WEB e for reutilizar essa DAO, vai mostrar um JOptionPane?
            // TODO Cade o rollback em caso de erro?
            JOptionPane.showMessageDialog(null, "Erro na consulta da tabela Reservas! " + ex.getMessage(), "Problema no Banco de Dados", JOptionPane.ERROR_MESSAGE);

        } finally {
            if (manager != null) {
                entity.closable(manager);
            } 

        }
        return reservas != null ? reservas : Collections.<Reserva>emptyList();
    }
}
