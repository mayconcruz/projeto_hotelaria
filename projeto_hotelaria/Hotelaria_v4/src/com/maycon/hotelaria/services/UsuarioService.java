package com.maycon.hotelaria.services;

import com.maycon.hotelaria.estruturas.Usuario;
import java.util.List;

/**
 * Interface que define os métodos utilizados para acessar dados referentes aos usuários cadastrados no sistema.
 * Métodos definidos: {@linkplain #cadastraUsuario(com.maycon.hotelaria.estruturas.Usuario)}, {@linkplain #consultaUsuarios(java.lang.String) }
 * {@linkplain #realizaLoginUsuario(java.lang.String, java.lang.String)}, {@linkplain #verificaUsuario(java.lang.String)} 
 * @author Maycon
 */
public interface UsuarioService {

    /**
     * O método realiza o cadastro de um usuário no Banco de Dados, a partir do
     * usuário dado.
     *
     * @param usuario Objeto que contém as informações do usuário a ser
     * cadastrado.
     * @return TRUE ou FALSE, indicando sucesso ou falha no cadastro.
     */
    boolean cadastraUsuario(Usuario usuario);

    /**
     * O método realiza a consulta de usuários no Banco de Dados, a partir de
     * uma string dada.
     *
     * @param nomeUsuario String que representa o nome ou parte do nome de interesse dos usuários. Caso o filtro seja vazio, 
     * o método retorna todos os usuários cadastrados no sistema.
     * @return List com os usuários que possuem a substring dada em seu nome.
     */
    List<Usuario> consultaUsuarios(String nomeUsuario);

    /**
     * O método realiza a verificação das credenciais do usuários para lhe dar
     * acesso ao sistema
     *
     * @param email String que representa o email do usuário que deseja acessar
     * o sistema
     * @param senha String que representa a senha do usuário que deseja acessar
     * o sistema
     * @return Um objeto do tipo Usuario que indica se o usuário está presente
     * ou não no banco de dados.
     */
    Usuario realizaLoginUsuario(String email, String senha);

    /**
     * Método responsável por verificar se um usuário já está cadastrado no
     * Banco de Dados
     *
     * @param email String que representa o email a ser buscado no Banco de
     * Dados
     * @return String com o email procurado se ele já foi cadastrado, ou retorna
     * uma String vazia caso o email ainda não exista na Base de Dados
     */
    boolean verificaUsuario(String email);

}
