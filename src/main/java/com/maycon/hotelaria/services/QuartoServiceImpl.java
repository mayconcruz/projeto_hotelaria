package com.maycon.hotelaria.services;

import com.maycon.hotelaria.dao.QuartoDAO;
import com.maycon.hotelaria.estruturas.Quarto;
import java.util.List;

/**
 * Implementação dos métodos da interface QuartoService
 * Como não há consideráveis regras de negócio neste case, a implementação dos métodos limita-se a criar um objeto
 * do tipo QuartoDAO {@link com.maycon.hotelaria.dao.QuartoDAO.java} para acessar os métodos desta classe e retorna seus valores. 
 * @author Maycon
 */
public class QuartoServiceImpl implements QuartoService {

    private final QuartoDAO quartoDAO;

    public QuartoServiceImpl(QuartoDAO quartoDAO) {
        this.quartoDAO = quartoDAO;
    }

    @Override
    public boolean cadastraQuarto(Quarto quarto) {
        return quartoDAO.cadastrarQuarto(quarto);
    }

    @Override
    public List<Quarto> consultaTodosQuartos() {
        return quartoDAO.consultarTodosQuartos();
    }

    @Override
    public List<Quarto> consultaQuartosDisponiveis(String tipo, String data_inicio, String data_final) {
        return quartoDAO.consultarQuartosDisponiveis(tipo, data_inicio, data_final);
    }
}
