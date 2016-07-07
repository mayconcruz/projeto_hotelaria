package com.maycon.hotelaria.services;

import com.maycon.hotelaria.dao.ReservaDAO;
import com.maycon.hotelaria.estruturas.Reserva;
import com.maycon.hotelaria.estruturas.Usuario;
import java.util.List;

/**
 * Implementação dos métodos da interface ReservaService
 * Como não há consideráveis regras de negócio neste case, a implementação dos métodos limita-se a criar um objeto
 * do tipo ReservaDAO {@link com.maycon.hotelaria.dao.ReservaDAO.java} para acessar os métodos desta classe e retorna seus valores. 
 * @author Maycon
 */
public class ReservaServiceImpl implements ReservaService {

    private final ReservaDAO reservaDAO;

    public ReservaServiceImpl(ReservaDAO reservaDAO) {
        this.reservaDAO = reservaDAO;
    }

    @Override
    public boolean cadastraReservas(Reserva reserva) {
        return reservaDAO.cadastrarReservas(reserva);
    }

    @Override
    public List<Reserva> consultaReservasUsuario(Usuario usuario, String dataInicio, String dataFinal) {
        return reservaDAO.consultarReservasUsuario(usuario, dataInicio, dataFinal);
    }

    @Override
    public List<Reserva> consultaTodasReservas(String dataInicio, String dataFinal) {
        return reservaDAO.consultarTodasReservas(dataInicio, dataFinal);
    }

}
