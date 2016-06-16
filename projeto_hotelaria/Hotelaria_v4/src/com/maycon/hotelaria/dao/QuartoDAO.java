package com.maycon.hotelaria.dao;

import com.maycon.hotelaria.estruturas.Quarto;
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
        InstanciaPool instancia = InstanciaPool.getInstance();
        ConexaoPoolBD pool = instancia.getConexao();
        Connection c = pool.getConexaoPool();
        PreparedStatement st = null;
        boolean sucesso = true;

        try {
            String sql = "INSERT INTO quartos (numero_quarto, tipo_quarto, valor_diaria, informacoes)"
                    + "VALUES (?, ?, ?, ?)";

            st = c.prepareStatement(sql);
            st.setInt(1, quarto.getNumQuarto());
            st.setString(2, quarto.getTipoQuarto());
            st.setDouble(3, quarto.getValorDiaria());
            st.setString(4, quarto.getInformacoes());
            st.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro na tabela de quartos: " + e.getMessage(), "Problema no Banco de Dados", JOptionPane.ERROR_MESSAGE);
            sucesso = false;
        } finally {
            if (st != null) {
                    pool.fechaClosables(null, st);
            }
        }
        pool.devolveConexaoPool(c);
        return sucesso;
    }

    /**
     * Método responsável por listar todos os quartos do hotel.
     *
     * @return List contendo todos os quartos cadastrados. Caso não haja nenhum quarto, retorna uma lista vazia.
     */
    //UTILIZAR UM TIPO DE COLLECTION PERMITE QUE O CÓDIGO FIQUE MAIS FLEXÍVEL PARA O TRATAMENTO DOS DADOS RETORNADOS
    public List<Quarto> consultaTodosQuartos() {
        InstanciaPool instancia = InstanciaPool.getInstance();
        ConexaoPoolBD pool = instancia.getConexao();
        Connection c = pool.getConexaoPool();
        PreparedStatement st = null;
        ResultSet consulta = null;
        String sql;

        List<Quarto> quartos = null;

        try {

            sql = "SELECT * FROM quartos";

            st = c.prepareStatement(sql);
            consulta = st.executeQuery(); 

            quartos = new ArrayList<>();

            while (consulta.next()) {
                Quarto quarto = new Quarto();
                quarto.setInformacoes(!"".equals(consulta.getString("informacoes")) ? consulta.getString("informacoes") : "Não disponível");
                quarto.setNumQuarto(consulta.getInt("numero_quarto"));
                quarto.setTipoQuarto(consulta.getString("tipo_quarto"));
                quarto.setValorDiaria(consulta.getDouble("valor_diaria"));

                quartos.add(quarto);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na consulta da tabela Quartos! " + ex.getMessage(), "Problema no Banco de Dados", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (st != null) {
                    pool.fechaClosables(consulta, st);
            }
        }
        pool.devolveConexaoPool(c);
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
    //UTILIZAR UM TIPO DE COLLECTION PERMITE QUE O CÓDIGO FIQUE MAIS FLEXÍVEL PARA O TRATAMENTO DOS DADOS RETORNADOS
    public List<Quarto> consultaQuartosDisponiveis(String tipo, String dataInicio, String dataFinal) {

        List<Quarto> quartos = null;
        InstanciaPool instancia = InstanciaPool.getInstance();
        ConexaoPoolBD pool = instancia.getConexao();
        Connection c = pool.getConexaoPool();
        PreparedStatement st = null;
        ResultSet consulta = null;
        String sql;

        try {

            sql = "SELECT q.* "
                    + "FROM quartos q "
                    + "WHERE NOT EXISTS( "
                    + "SELECT 'T' "
                    + "FROM reservas r "
                    + "WHERE ? < data_hora_saida AND ? > data_hora_entrada "
                    + "AND q.id_quarto = r.id_quarto) "
                    + "AND (q.tipo_quarto = ? OR ? IS NULL)";

            Timestamp dataInicioTs, dataFinalTs;
            dataInicioTs = Timestamp.valueOf(dataInicio + " 14:00:00");
            dataFinalTs = Timestamp.valueOf(dataFinal + " 12:00:00");

            //UTILIZANDO OS PARÂMETROS NA VARIÁVEL DE LIGAÇÃO
            st = c.prepareStatement(sql);
            st.setTimestamp(1, dataInicioTs);
            st.setTimestamp(2, dataFinalTs);
            st.setString(3, tipo);
            st.setString(4, tipo);
            consulta = st.executeQuery(); 

            quartos = new ArrayList<>();

            while (consulta.next()) {
                Quarto quarto = new Quarto();
                quarto.setIdQuarto(consulta.getInt("id_quarto"));
                quarto.setNumQuarto(consulta.getInt("numero_quarto"));
                quarto.setTipoQuarto(consulta.getString("tipo_quarto"));
                quarto.setValorDiaria(consulta.getDouble("valor_diaria"));
                quarto.setInformacoes(!"".equals(consulta.getString("informacoes")) ? consulta.getString("informacoes") : "Não disponível");

                quartos.add(quarto);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na consulta da tabela Reservas! " + ex.getMessage(), "Problema no Banco de Dados", JOptionPane.ERROR_MESSAGE);

        } finally {
            if (st != null) {
                    pool.fechaClosables(consulta, st);
            }
        }
        pool.devolveConexaoPool(c);
        return quartos != null ? quartos : Collections.emptyList();
    }
}
