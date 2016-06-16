package com.maycon.hotelaria.dao;


/**
 * Classe singleton responsável por armazenar uma instância do POOL de conexões e mantê-la ativa no sistema.
 * Seus métodos são: {@linkplain #getInstance()}, {@linkplain #getConexao()} 
 * @author Maycon
 */
public class InstanciaPool {
    
    private static final InstanciaPool INSTANCE = new InstanciaPool();
    private ConexaoPoolBD conexao;

    public InstanciaPool() {
    }

    public static InstanciaPool getInstance() {
        return INSTANCE;
    }

    public ConexaoPoolBD getConexao(){
        return conexao;
    }  

    public void setConexao(ConexaoPoolBD conexao) {
        this.conexao = conexao;
    }
    
}
