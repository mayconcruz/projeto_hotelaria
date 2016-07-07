package com.maycon.hotelaria.dao;

import javax.persistence.*;

/**
 * Classe responsável por criar um EntityManager a partir de um persistence-unit pré-definido
 * @author Maycon
 */

public class GerenciaEntity {
    
    private static final GerenciaEntity instancia = new GerenciaEntity();
    private static EntityManagerFactory factory;
    private EntityManager manager;
    
    
    public GerenciaEntity(){
        
    }
    
    /**
     * Método responsável por criar um EntityManager a ser utilizado nas consultas JPQL
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
     * Método responsável por fechar o manager criado no método setManager()
     * 
     */
    
    public void destruirManager(){
        manager.close();

    }
    
}
