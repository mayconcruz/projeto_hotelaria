package com.maycon.hotelaria.dao;

import javax.persistence.*;

/**
 * Classe responsável por criar um EntityManager a partir de um persistence-unit pré-definido
 * @author Maycon
 */
public class GerenciaEntity {
    
    private EntityManagerFactory factory;
    
    public GerenciaEntity(){
        
    }
    
    /**
     * Método responsável por criar um EntityManager a ser utilizado nas consultas JPQL
     * @return EntityManager gerado com base no persistence-unit
    */
    public EntityManager constroiManager(){
        factory = Persistence.createEntityManagerFactory("HotelariaMaven");
        return factory.createEntityManager();
    }
    
    /**
     * Método responsável por fechar o manager e o factory criados no método constroiManager()
     * @param manager EntityManager que possui as configurações necessárias para se criar uma consulta JPQL 
     */
    public void closable(EntityManager manager){
        manager.close();
        factory.close();
    }
    
}
