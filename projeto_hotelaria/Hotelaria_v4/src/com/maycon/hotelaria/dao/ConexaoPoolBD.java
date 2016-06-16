package com.maycon.hotelaria.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 * Classe responsável por representar a estrutura do Pool de conexões, que irá se comunicar com o banco de dados
 * e armazenar as conexões necessárias.
*/

public class ConexaoPoolBD {

    private final Properties propriedades = lePropriedadesBD();
    private final String BANCO_DADOS = propriedades.getProperty("prop.bancoDados.host");
    private final String USUARIO = propriedades.getProperty("prop.bancoDados.usuario");
    private final String SENHA = propriedades.getProperty("prop.bancoDados.senha");
    private final int TAMANHO_POOL = 10;

    
    private final List CONEXOES_DISPONIVEIS_POOL = new ArrayList();
    private final List CONEXOES_UTILIZADAS_POOL = new ArrayList();
   

    public ConexaoPoolBD(){
        inicializaConexaoPool();
    }
    /**
     * Método que verifica se o Pool está cheio e, caso não esteja, cria uma
     * nova conexão com o Banco de Dados.
     */
    private void inicializaConexaoPool() {
        
        if(verificaPoolCheio()) {
            if(CONEXOES_DISPONIVEIS_POOL.isEmpty()){
                //ADICIONA UMA NOVA CONEXÃO AO POOL DE CONEXÕES
                CONEXOES_DISPONIVEIS_POOL.add(criaNovaConexaoPool());
            }
        }
    }

    /**
     * Método responsável por verificar se o Pool atingiu o número máximo de
     * conexões com o Banco de Dados
     *
     * @return TRUE se o Pool não está cheio ou FALSE se o Pool atingiu o limite
     */
    private boolean verificaPoolCheio() {                
        return (CONEXOES_DISPONIVEIS_POOL.size() + CONEXOES_UTILIZADAS_POOL.size()) < TAMANHO_POOL;
    }

    /**
     * Método responsável por criar conexões com o Banco de Dados
     *
     * @return Object do tipo Connection com a conexão criada com o Banco de
     * Dados
     */
    //CRIA UMA CONEXÃO COM O BANCO DE DADOS
    private Connection criaNovaConexaoPool() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(BANCO_DADOS, USUARIO, SENHA);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problema no acesso ao Banco de Dados: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException cn) {
            JOptionPane.showMessageDialog(null, "Driver do Banco de Dados não encontrado: " + cn.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
        return connection;
    }

    /**
     * Método responsável por pegar uma conexão com o Banco de Dados do Pool
     *
     * @return Object do tipo Connection que representa a conexão, caso o Pool a
     * tenha, ou NULL caso todas as conexões tenham sido emprestadas
     */
    public Connection getConexaoPool() {
        Connection connection = null;

        //VERIFICA SE HÁ CONEXÕES DISPONÍVEIS NO POOL
        if (CONEXOES_DISPONIVEIS_POOL.size() > 0) {
            connection = (Connection) CONEXOES_DISPONIVEIS_POOL.get(0);
            //REMOVE DO POOL A CONEXÃO QUE SERÁ DISPONIBILIZADA E A TRANSFERE PRA LISTA DE CONEXÕES UTILIZADAS
            CONEXOES_DISPONIVEIS_POOL.remove(0);
            CONEXOES_UTILIZADAS_POOL.add(connection);
        } else {
            try {
                new Thread().sleep(2000); //AGUARDA DOIS SEGUNDOS PARA TENTAR OBTER UMA NOVA CONEXÃO
                if (CONEXOES_DISPONIVEIS_POOL.size() > 0) {
                    connection = (Connection) CONEXOES_DISPONIVEIS_POOL.get(0);
                    //REMOVE DO POOL A CONEXÃO QUE SERÁ DISPONIBILIZADA E A TRANSFERE PRA LISTA DE CONEXÕES UTILIZADAS
                    CONEXOES_DISPONIVEIS_POOL.remove(0);
                    CONEXOES_UTILIZADAS_POOL.add(connection);
                }else{
                    inicializaConexaoPool();
                }
                
            } catch (InterruptedException ex) {}           
        }
        return connection;
    }

    /**
     * Método que recebe a conexão a ser retornada para o Pool
     *
     * @param connection Objeto Connection que será retornado ao Pool
     */
    public void devolveConexaoPool(Connection connection) {
        //RETORNA UMA CONEXÃO EMPRESTADA PARA O POOL DE CONEXÕES
        CONEXOES_DISPONIVEIS_POOL.add(connection);
        CONEXOES_UTILIZADAS_POOL.remove(connection);
    }
    
    /**
     * Método responsável por ler um arquivo de propriedades e pegar as informações do Banco de Dados para se conectar.
     * @return Properties que representa os dados do Banco de Dados que será conectado, ou null caso não tenha conseguido acessar o arquivo.
     */
    public Properties lePropriedadesBD(){
        Properties props = new Properties();
        try{
            FileInputStream file = new FileInputStream("src/com/maycon/hotelaria/bancodedados/ConexaoBD.properties");
            props.load(file);
        }
        catch(IOException io){
            JOptionPane.showMessageDialog(null, "Erro na abertura do arquivo! "+io.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            props = null;
        }
	return props;
    }
    
    /**
     * Método responsável por fechar os ResultSets utilizados no sistema
     * @param rs ResultSet que será finalizado
     * @param ps PreparedStatment que será finalizado
     */
    public void fechaClosables(ResultSet rs, PreparedStatement ps){
        try {
            rs.close();
            ps.close();
        } catch (SQLException ex) {}
    }

}
