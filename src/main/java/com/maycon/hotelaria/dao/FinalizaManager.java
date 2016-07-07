package com.maycon.hotelaria.dao;


/**
 * Classe que possui métodos responsáveis por manipular objetos JTable
 * @author Maycon
 */
public class FinalizaManager {

    public static void finalizarManager(GerenciaEntity entity) {
        if (entity.getManager() != null) {
                entity.destruirManager();
        }
    }

}
