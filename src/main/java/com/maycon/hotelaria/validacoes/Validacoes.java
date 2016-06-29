package com.maycon.hotelaria.validacoes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe utilitária que possui métodos para validar formato de email ou verificar se um campo input possui apenas números. 
 */
public class Validacoes {

    private static Matcher matcher; // MATCHERS SÃO CUSTOSOS... DEIXAR FIXO NA CLASSE

    private Validacoes() {
    }

    /**
     * Método que verifica se uma String de email dada está no formato básico de
     * um email
     *
     * @param email String a ser verificada
     * @return TRUE ou FALSE, indicando se o email digitado possui um formato válido ou não.
     */
    public static boolean validaEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(email);

        return matcher.matches();
    }

    /**
     * Método que verifica se o número do quarto não possui letras vinculadas
     *
     * @param numQuarto String a ser verificada
     * @return TRUE se o número do quarto não possui letras, ou FALSE caso
     * alguma letra tenha sido encontrada
     */
    public static boolean validaNumQuarto(String numQuarto) {

        String expression = "\\d+";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(numQuarto);

        return matcher.matches();
    }

}
