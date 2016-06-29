package com.maycon.hotelaria.dao;

import javax.persistence.*;

/**
 * Classe respons�vel por criar um EntityManager a partir de um persistence-unit pr�-definido
 * @author Maycon
 */
public class GerenciaEntity {
    
    private EntityManagerFactory factory;
    
    public GerenciaEntity(){
        
    }
    
    /**
     * M�todo respons�vel por criar um EntityManager a ser utilizado nas consultas JPQL
     * @return EntityManager gerado com base no persistence-unit
    */
    public EntityManager constroiManager(){
        factory = Persistence.createEntityManagerFactory("HotelariaMaven");
        return factory.createEntityManager();
    }
    
    /**
     * M�todo respons�vel por fechar o manager e o factory criados no m�todo constroiManager()
     * @param manager EntityManager que possui as configura��es necess�rias para se criar uma consulta JPQL 
     */
    public void closable(EntityManager manager){
        manager.close();
        factory.close();
    }
    
}
