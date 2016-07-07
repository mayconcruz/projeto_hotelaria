package com.maycon.hotelaria.dao;

import com.maycon.hotelaria.estruturas.Reserva;
import com.maycon.hotelaria.estruturas.Usuario;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import javax.persistence.*;
import javax.swing.JOptionPane;

/**
 * Esta classe apresenta m�todos utilizados para fazerem a intera��o entre a aplica��o e a fonte de dados que armazena os dados
 * das reservas armazenados no sistema.
 * @author Maycon
 */
public class ReservaDAO {

    /**
     * M�todo respons�vel por realizar o cadastro de reservas do hotel a partir
     * das informa��es da reserva
     *
     * @param reserva Objeto do tipo Reserva que cont�m as informa��es da
     * reserva
     * @return TRUE ou FALSE, indicando sucesso ou falha no cadastro da reserva
     */
    public boolean cadastrarReservas(Reserva reserva) throws PersistenceException {
        GerenciaEntity entity = GerenciaEntity.obterEntity();
        entity.setManager(entity.getFactory());
        boolean sucesso = true;

        try {
            entity.getManager().getTransaction().begin();
            entity.getManager().persist(reserva);
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
     * M�todo respons�vel por consultar as reservas de um determinado usu�rio do sistema. A consulta pode ou n�o ser 
     * filtrada pela data de in�cio e de t�rmino da reserva.
     *
     * @param usuario Usu�rio que deseja-se saber as reservas
     * @param dataInicio String que representa a data de inicio das reservas a serem pesquisadas
     * @param dataFinal String que representa a data final das reservas a serem pesquisadas
     * @return List de reservas feitas por um usu�rio espec�fico. Caso n�o haja nenhuma reserva realizada pelo usu�rio, 
     * retorna uma lista vazia
     */
    public List<Reserva> consultarReservasUsuario(Usuario usuario, String dataInicio, String dataFinal) throws PersistenceException {
        List<Reserva> reservasUsuarios = null;
        GerenciaEntity entity = GerenciaEntity.obterEntity();
        entity.setManager(entity.getFactory());
        
        try {
            
            if (!"".equals(dataInicio) || !"".equals(dataFinal)) {

                String sql = " FROM Reserva r"
                            +" WHERE r.usuario.idUsuario = :usuario and dataHoraEntrada >= :entrada and dataHoraSaida <= :saida"
                            +" ORDER BY dataHoraEntrada";

                Timestamp dataInicioTs, dataFinalTs;
                dataInicioTs = Timestamp.valueOf(dataInicio + " 14:00:00");
                dataFinalTs = Timestamp.valueOf(dataFinal + " 12:00:00");

                TypedQuery<Reserva> query = entity.getManager().createQuery(sql, Reserva.class);
                query.setParameter("usuario", usuario.getIdUsuario());
                query.setParameter("entrada", dataInicioTs);
                query.setParameter("saida", dataFinalTs);
                reservasUsuarios = query.getResultList();
            }
            else{

                String sql = " FROM Reserva r"
                            +" WHERE r.usuario.idUsuario = :cliente"
                            +" ORDER BY dataHoraEntrada";

                TypedQuery<Reserva> query = entity.getManager().createQuery(sql, Reserva.class); 
                query.setParameter("cliente", usuario.getIdUsuario());
                reservasUsuarios = query.getResultList();
            }                    

        } catch (PersistenceException p) {         
            entity.getManager().getTransaction().rollback();  
        } finally {
            FinalizaManager.finalizarManager(entity);
          }
        return reservasUsuarios != null ? reservasUsuarios : Collections.<Reserva>emptyList();
    }

    /**
     * M�todo respons�vel por listar todas as reservas feitas no hotel. Se o
     * usu�rio escolher uma data de in�cio e de final o m�todo retorna as
     * reservas naquele per�odo, se n�o retorna todas as reservas.
     *
     * @param dataInicio String que representa a data de inicio das reservas a
     * serem pesquisadas
     * @param dataFinal String que representa a data final das reservas a serem
     * pesquisadas
     * @return List contendo todas as reservas. Caso n�o haja nenhuma reserva realizada no per�odo escolhido, 
     * retorna uma lista vazia
     */
    public List<Reserva> consultarTodasReservas(String dataInicio, String dataFinal) throws PersistenceException {
        List<Reserva> reservas = null;
        GerenciaEntity entity = GerenciaEntity.obterEntity();
        entity.setManager(entity.getFactory());

        try {
            if (!"".equals(dataInicio) || !"".equals(dataFinal)) {
                String sql = "FROM Reserva r "
                        + "WHERE dataHoraEntrada>= :entrada AND dataHoraSaida <= :saida"
                        + " ORDER BY r.usuario.nome";


                Timestamp dataInicioTs, dataFinalTs;
                dataInicioTs = Timestamp.valueOf(dataInicio + " 14:00:00");
                dataFinalTs = Timestamp.valueOf(dataFinal + " 12:00:00");

                TypedQuery<Reserva> query = entity.getManager().createQuery(sql, Reserva.class);
                query.setParameter("entrada", dataInicioTs);
                query.setParameter("saida", dataFinalTs);
                reservas = query.getResultList();

            } else {
                String sql = "FROM Reserva r "
                        + " ORDER BY r.usuario.nome";

                TypedQuery<Reserva> query = entity.getManager().createQuery(sql, Reserva.class);
                reservas = query.getResultList();
            }

        } catch (PersistenceException p) {       
            entity.getManager().getTransaction().rollback();
        } finally {
            FinalizaManager.finalizarManager(entity);

        }
        return reservas != null ? reservas : Collections.<Reserva>emptyList();
    }
}
