package com.maycon.hotelaria.dao;

import javax.persistence.*;

/**
 * Classe responsável por criar um EntityManager a partir de um persistence-unit pré-definido
 * @author Maycon
 */
// TODO Essa classe deveria ser um singleton
public class GerenciaEntity {
    
    private EntityManagerFactory factory;
    
    public GerenciaEntity(){
        
    }
    
    /**
     * Método responsável por criar um EntityManager a ser utilizado nas consultas JPQL
     * @return EntityManager gerado com base no persistence-unit
    */
    // TODO Mudar nome do método, use verbo, tipo construir
    public EntityManager constroiManager(){
        // TODO Você está criando 1 EntityManagerFactory para cada consulta, só deve criar 1 para cada banco de dados a ser utilizado
        factory = Persistence.createEntityManagerFactory("HotelariaMaven");
        return factory.createEntityManager();
    }
    
    /**
     * Método responsável por fechar o manager e o factory criados no método constroiManager()
     * @param manager EntityManager que possui as configurações necessárias para se criar uma consulta JPQL 
     */
    // TODO Mudar o nome do método, os nomes de métodos que fazem alguma ação devem ser verbos, tipo fechar
    public void closable(EntityManager manager){
        manager.close();
        factory.close();
    }
    
}
