package com.maycon.hotelaria.dao;

import javax.persistence.*;

/**
 * Classe respons�vel por criar um EntityManager a partir de um persistence-unit pr�-definido
 * @author Maycon
 */

public class GerenciaEntity {
    
    private static final GerenciaEntity instancia = new GerenciaEntity();
    private static EntityManagerFactory factory;
    private EntityManager manager;
    
    
    public GerenciaEntity(){
        
    }
    
    /**
     * M�todo respons�vel por criar um EntityManager a ser utilizado nas consultas JPQL
     * @return EntityManager gerado com base no persistence-unit
    */
    
    public static GerenciaEntity obterEntity(){
        return instancia;
    }
    
    public EntityManagerFactory getFactory(){
        return factory;
    }
    
    public void setFactory(){
        factory = Persistence.createEntityManagerFactory("HotelariaMaven");
    }
    
    public EntityManager getManager(){
        return manager;
    }
    
    public void setManager(EntityManagerFactory fac){
        this.manager = fac.createEntityManager();
    }
    
    /**
     * M�todo respons�vel por fechar o manager criado no m�todo setManager()
     * 
     */
    
    public void destruirManager(){
        manager.close();

    }
    
}
