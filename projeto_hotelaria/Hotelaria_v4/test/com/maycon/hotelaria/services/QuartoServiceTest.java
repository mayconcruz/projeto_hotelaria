package com.maycon.hotelaria.services;

import com.maycon.hotelaria.dao.QuartoDAO;
import com.maycon.hotelaria.estruturas.Quarto;
import com.maycon.hotelaria.estruturas.Usuario;
import com.sun.jmx.remote.internal.ArrayQueue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.junit.Test;
import static org.junit.Assert.*;


public class QuartoServiceTest {
  
    @Test
    public void testCadastrarQuarto() {
        QuartoDAO quartoDAO = new QuartoDAO() {
            
            @Override
            public boolean cadastraQuarto(Quarto quarto) {
                    return true;
            }

        };
        
        List<Usuario> usuarios;
        
        usuarios = new ArrayList<>();
        
        QuartoService quartoService = new QuartoServiceREST();
        
        Quarto quarto = new Quarto();
        
        assertTrue(quartoService.cadastraQuarto(quarto));
    }
    
}
