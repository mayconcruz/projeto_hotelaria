package com.maycon.hotelaria.services;

import com.maycon.hotelaria.dao.ReservaDAO;
import com.maycon.hotelaria.estruturas.Reserva;
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
        return reservaDAO.cadastraReservas(reserva);
    }

    @Override
    public List<Reserva> consultaReservasUsuario(Integer idUsuario, String dataInicio, String dataFinal) {
        return reservaDAO.consultaReservasUsuario(idUsuario, dataInicio, dataFinal);
    }

    @Override
    public List<Reserva> consultaTodasReservas(String dataInicio, String dataFinal) {
        return reservaDAO.consultaTodasReservas(dataInicio, dataFinal);
    }

}
