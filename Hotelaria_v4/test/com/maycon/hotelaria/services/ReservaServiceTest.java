package com.maycon.hotelaria.services;

import com.maycon.hotelaria.dao.ReservaDAO;
import com.maycon.hotelaria.estruturas.Quarto;
import com.maycon.hotelaria.estruturas.Reserva;
import com.maycon.hotelaria.estruturas.Usuario;
import org.junit.Test;
import static org.junit.Assert.*;


public class ReservaServiceTest {
    
    @Test
    public void testCadastraReservas() {
        QuartoService quartoService = null;
        Quarto quarto = new Quarto();
        Usuario usuario = new Usuario();
        ReservaDAO reservaDAO = new ReservaDAO(){
            
            @Override
            public boolean cadastraReservas(Reserva reserva){
                if(reserva != null)
                    return true;
                else
                    return false;
            }
        };
        
        ReservaService reservaService = new ReservaServiceImpl(quartoService, reservaDAO);
        
        Reserva reserva = new Reserva();
        Reserva reservaNula = null;
        reserva.setDataHoraEntrada("2016-06-06 14:00:00");
        reserva.setDataHoraSaida("2016-06-06 12:00:00");
        reserva.setQuarto(quarto);
        reserva.setUsuario(usuario);
        assertTrue(reservaService.cadastraReservas(reserva));
        assertFalse(reservaService.cadastraReservas(reservaNula));
    }  
}
