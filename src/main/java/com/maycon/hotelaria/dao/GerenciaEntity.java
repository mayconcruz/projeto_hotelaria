package com.maycon.hotelaria.dao;

import javax.persistence.*;

/**
 * Classe respons�vel por criar um EntityManager a partir de um persistence-unit pr�-definido
 * @author Maycon
 */
// TODO Essa classe deveria ser um singleton
public class GerenciaEntity {
    
    private EntityManagerFactory factory;
    
    public GerenciaEntity(){
        
    }
    
    /**
     * M�todo respons�vel por criar um EntityManager a ser utilizado nas consultas JPQL
     * @return EntityManager gerado com base no persistence-unit
    */
    // TODO Mudar nome do m�todo, use verbo, tipo construir
    public EntityManager constroiManager(){
        // TODO Voc� est� criando 1 EntityManagerFactory para cada consulta, s� deve criar 1 para cada banco de dados a ser utilizado
        factory = Persistence.createEntityManagerFactory("HotelariaMaven");
        return factory.createEntityManager();
    }
    
    /**
     * M�todo respons�vel por fechar o manager e o factory criados no m�todo constroiManager()
     * @param manager EntityManager que possui as configura��es necess�rias para se criar uma consulta JPQL 
     */
    // TODO Mudar o nome do m�todo, os nomes de m�todos que fazem alguma a��o devem ser verbos, tipo fechar
    public void closable(EntityManager manager){
        manager.close();
        factory.close();
    }
    
}
