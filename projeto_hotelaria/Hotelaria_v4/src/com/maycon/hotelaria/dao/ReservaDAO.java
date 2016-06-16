package com.maycon.hotelaria.dao;

import com.maycon.hotelaria.estruturas.Quarto;
import com.maycon.hotelaria.estruturas.Reserva;
import com.maycon.hotelaria.estruturas.Sessao;
import com.maycon.hotelaria.estruturas.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
        InstanciaPool instancia = InstanciaPool.getInstance();
        ConexaoPoolBD pool = instancia.getConexao();
        Connection c = pool.getConexaoPool();
        Sessao sessao = Sessao.getInstance();
        PreparedStatement st = null;
        boolean sucesso = true;

        try {

            String sql = "INSERT into reservas (id_cliente, id_quarto, data_hora_entrada, data_hora_saida)"
                    + "VALUES (?, ?, ?, ?)";

            Timestamp dataInicioTs, dataFinalTs;
            dataInicioTs = Timestamp.valueOf(reserva.getDataHoraEntrada() + " 14:00:00");
            dataFinalTs = Timestamp.valueOf(reserva.getDataHoraSaida() + " 12:00:00");

            st = c.prepareStatement(sql);
            st.setInt(1, sessao.getUsuario().getIdUsuario());
            st.setInt(2, reserva.getQuarto().getIdQuarto());
            st.setTimestamp(3, dataInicioTs);
            st.setTimestamp(4, dataFinalTs);
            st.execute();
        } catch (SQLException e) {
            sucesso = false;
            JOptionPane.showMessageDialog(null, "Erro na tabela de reservas: " + e.getMessage(), "Problema no Banco de Dados", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (st != null) {
                    pool.fechaClosables(null, st);
            }
        }
        pool.devolveConexaoPool(c);
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
        InstanciaPool instancia = InstanciaPool.getInstance();
        ConexaoPoolBD pool = instancia.getConexao();
        Connection c = pool.getConexaoPool();
        PreparedStatement st = null;
        ResultSet consulta = null;

        if (c != null) {
            try {
                if (!"".equals(dataInicio) || !"".equals(dataFinal)) {
                    String sql = "SELECT (to_char(r.data_hora_entrada, 'DD-MM-YYYY') || ' às ' || to_char(r.data_hora_entrada, 'HH24:MI:SS')) as data_hora_entrada, (to_char(r.data_hora_saida, 'DD-MM-YYYY') || ' às ' || to_char(r.data_hora_saida, 'HH24:MI:SS')) as data_hora_saida, q.numero_quarto, q.tipo_quarto "
                            + "FROM reservas r "
                            + "JOIN quartos q ON (q.id_quarto = r.id_quarto) "
                            + "WHERE r.id_cliente= ? and data_hora_entrada>=? AND data_hora_saida <= ?"
                            + " ORDER BY data_hora_entrada";

                    st = c.prepareStatement(sql);
                    Timestamp dataInicioTs, dataFinalTs;
                    dataInicioTs = Timestamp.valueOf(dataInicio + " 14:00:00");
                    dataFinalTs = Timestamp.valueOf(dataFinal + " 12:00:00");

                    st.setInt(1, idUsuario);
                    st.setTimestamp(2, dataInicioTs);
                    st.setTimestamp(3, dataFinalTs);
                }
                else{
                    String sql = "SELECT (to_char(r.data_hora_entrada, 'DD-MM-YYYY') || ' às ' || to_char(r.data_hora_entrada, 'HH24:MI:SS')) as data_hora_entrada, (to_char(r.data_hora_saida, 'DD-MM-YYYY') || ' às ' || to_char(r.data_hora_saida, 'HH24:MI:SS')) as data_hora_saida, q.numero_quarto, q.tipo_quarto "
                            + "FROM reservas r "
                            + "JOIN quartos q ON (q.id_quarto = r.id_quarto) "
                            + "WHERE r.id_cliente= ?"
                            + " ORDER BY data_hora_entrada";

                    st = c.prepareStatement(sql);

                    st.setInt(1, idUsuario);
                }                    
                consulta = st.executeQuery(); 

                reservasUsuarios = new ArrayList<>();
                while (consulta.next()) {
                    Reserva reserva = new Reserva();
                    Quarto quarto = new Quarto();

                    quarto.setNumQuarto(consulta.getInt("numero_quarto"));
                    quarto.setTipoQuarto(consulta.getString("tipo_quarto"));
                    reserva.setDataHoraEntrada(consulta.getString("data_hora_entrada"));
                    reserva.setDataHoraSaida(consulta.getString("data_hora_saida"));
                    reserva.setQuarto(quarto);

                    reservasUsuarios.add(reserva);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro na consulta da tabela Reservas! " + ex.getMessage(), "Problema no Banco de Dados", JOptionPane.ERROR_MESSAGE);

            } finally {
                if (st != null) {
                        pool.fechaClosables(consulta, st);

                }
              }
        }

        pool.devolveConexaoPool(c);
        return reservasUsuarios != null ? reservasUsuarios : Collections.emptyList();
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
        InstanciaPool instancia = InstanciaPool.getInstance();
        ConexaoPoolBD pool = instancia.getConexao();
        Connection c = pool.getConexaoPool();
        PreparedStatement st = null;
        ResultSet consulta = null;

        if (c != null) {
            try {
                if (!"".equals(dataInicio) || !"".equals(dataFinal)) {
                    String sql = "SELECT u.nome_usuario, u.cpf, u.email, u.telefone, q.numero_quarto, (to_char(r.data_hora_entrada, 'DD-MM-YYYY') || ' às ' || to_char(r.data_hora_entrada, 'HH24:MI:SS')) as data_hora_entrada, (to_char(r.data_hora_saida, 'DD-MM-YYYY') || ' às ' || to_char(r.data_hora_saida, 'HH24:MI:SS')) as data_hora_saida "
                            + "FROM reservas r "
                            + "JOIN quartos q ON (q.id_quarto = r.id_quarto) "
                            + "JOIN usuarios u ON (u.id_usuario = r.id_cliente) "
                            + "WHERE data_hora_entrada>=? AND data_hora_saida <= ?"
                            + " ORDER BY u.nome_usuario";

                    st = c.prepareStatement(sql);

                    Timestamp dataInicioTs, dataFinalTs;
                    dataInicioTs = Timestamp.valueOf(dataInicio + " 14:00:00");
                    dataFinalTs = Timestamp.valueOf(dataFinal + " 12:00:00");

                    st.setTimestamp(1, dataInicioTs);
                    st.setTimestamp(2, dataFinalTs);
                } else {
                    String sql = "SELECT u.nome_usuario, u.cpf, u.email, u.telefone, q.numero_quarto, (to_char(r.data_hora_entrada, 'DD-MM-YYYY') || ' às ' || to_char(r.data_hora_entrada, 'HH24:MI:SS')) as data_hora_entrada, (to_char(r.data_hora_saida, 'DD-MM-YYYY') || ' às ' || to_char(r.data_hora_saida, 'HH24:MI:SS')) as data_hora_saida "
                            + "FROM reservas r "
                            + "JOIN quartos q ON (q.id_quarto = r.id_quarto) "
                            + "JOIN usuarios u ON (u.id_usuario = r.id_cliente) "
                            + " ORDER BY u.nome_usuario";

                    st = c.prepareStatement(sql);
                }

                consulta = st.executeQuery(); 
                reservas = new ArrayList<>();
                while (consulta.next()) {
                    Usuario usuario = new Usuario();
                    Quarto quarto = new Quarto();
                    Reserva reserva = new Reserva();

                    usuario.setNome(consulta.getString("nome_usuario"));
                    usuario.setCpf(consulta.getString("cpf"));
                    usuario.setEmail(consulta.getString("email"));
                    usuario.setTelefone((consulta.getString("telefone") == null || "".equals(consulta.getString("telefone"))) ? "Não informado" : consulta.getString("telefone"));
                    quarto.setNumQuarto((consulta.getInt("numero_quarto")));
                    reserva.setDataHoraEntrada(consulta.getString("data_hora_entrada"));
                    reserva.setDataHoraSaida(consulta.getString("data_hora_saida"));
                    reserva.setUsuario(usuario);
                    reserva.setQuarto(quarto);

                    reservas.add(reserva);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro na consulta da tabela Reservas! " + ex.getMessage(), "Problema no Banco de Dados", JOptionPane.ERROR_MESSAGE);

            } finally {
                if (st != null) {
                        pool.fechaClosables(consulta, st);
                } 
                    
                }
            }
        pool.devolveConexaoPool(c);
        return reservas != null ? reservas : Collections.emptyList();
    }
}
