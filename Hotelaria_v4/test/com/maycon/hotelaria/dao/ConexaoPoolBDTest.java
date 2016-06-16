package com.maycon.hotelaria.dao;

import static org.junit.Assert.*;
import org.junit.Test;

public class ConexaoPoolBDTest {
    
    
    // TESTE DE INTEGRAÇÃO REALIZADO, UMA VEZ QUE HÁ UM ACESSO AO BANCO DE DADOS
    @Test
    public void testGetMaisConexaoQuePoolDispoe() {
        ConexaoPoolBD pool = new ConexaoPoolBD();
        for(int i=0; i<15; i++)
            if(i < 10)
                assertNotNull("Erro. Null detectado!", pool.getConexaoPool()); 
            else
                assertNull("Conexão não disponível!", pool.getConexaoPool()); 
    }
    
    @Test
    public void testGetMenosConexaoQuePoolDispoe() {
        ConexaoPoolBD pool = new ConexaoPoolBD();
        for(int i=0; i<10; i++)
            assertNotNull("Erro na obtenção da conexão!", pool.getConexaoPool());                
    }
}
