package com.maycon.hotelaria.estruturas;

public class Sessao {

    // Variável estática que conterá a instancia da classe
    private static final Sessao INSTANCE = new Sessao();
    private Usuario usuario;

    // Construtor privado (suprime o construtor público padrão).
    private Sessao() {

    }

    // Método público estático de acesso único ao objeto!
    public static Sessao getInstance() {
        return INSTANCE;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
