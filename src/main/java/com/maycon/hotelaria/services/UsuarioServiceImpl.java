package com.maycon.hotelaria.services;

import com.maycon.hotelaria.dao.UsuarioDAO;
import com.maycon.hotelaria.estruturas.Usuario;
import java.util.List;

/**
 * Implementação dos métodos da interface UsuarioService
 * Como não há consideráveis regras de negócio neste case, a implementação dos métodos limita-se a criar um objeto
 * do tipo UsuarioDAO {@link com.maycon.hotelaria.dao.UsuarioDAO.java} para acessar os métodos desta classe e retorna seus valores. 
 * @author Maycon
 */
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioDAO usuarioDAO;

    public UsuarioServiceImpl(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    @Override
    public boolean cadastraUsuario(Usuario usuario) {
        return usuarioDAO.cadastrarUsuario(usuario);
    }

    @Override
    public Usuario realizaLoginUsuario(String email, String senha) {
        return usuarioDAO.realizarLoginUsuario(email, senha);
    }

    @Override
    public List<Usuario> consultaUsuarios(String filtro) {
        return usuarioDAO.consultarUsuarios(filtro);
    }

    @Override
    public boolean verificaUsuario(String email) {
        return usuarioDAO.verificarUsuario(email);
    }

}
