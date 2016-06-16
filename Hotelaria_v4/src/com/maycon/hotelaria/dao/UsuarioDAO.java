package com.maycon.hotelaria.dao;

import com.maycon.hotelaria.estruturas.TipoUsuario;
import com.maycon.hotelaria.estruturas.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Esta classe apresenta métodos utilizados para fazerem a interação entre a aplicação e a fonte de dados que armazena os dados
 * dos usuários armazenados no sistema.
 * @author Maycon
 */
public class UsuarioDAO {

    /**
     * Método responsável por realizar o cadastro de usuários do sistema.
     *
     * @param usuario Objeto do tipo Usuario que contém as informações do
     * usuario
     * @return TRUE ou FALSE, indicando sucesso ou falha no cadastro de
     * usuários.
     */
    public boolean cadastraUsuario(Usuario usuario) {
//        ConexaoPoolBD pool = new ConexaoPoolBD();
//        Connection c = pool.getConexaoPool();
        InstanciaPool instancia = InstanciaPool.getInstance();
        ConexaoPoolBD pool = instancia.getConexao();
        Connection c = pool.getConexaoPool();
        PreparedStatement st = null;
        boolean sucesso = true;

        try {
            String sql = "INSERT into usuarios (nome_usuario, cpf, email, senha, endereco, numero, cep, bairro, cidade, estado, tipo_usuario, telefone)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            st = c.prepareStatement(sql);
            st.setString(1, usuario.getNome());
            st.setString(2, usuario.getCpf());
            st.setString(3, usuario.getEmail());
            st.setString(4, usuario.getSenha());
            st.setString(5, usuario.getEndereco());
            st.setString(6, usuario.getNumero());
            st.setString(7, usuario.getCep());
            st.setString(8, usuario.getBairro());
            st.setString(9, usuario.getCidade());
            st.setString(10, usuario.getEstado());
            st.setString(11, usuario.getTipoUsuario().name());
            st.setString(12, usuario.getTelefone());
            st.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro no Banco de Dados: " + e.getMessage(), "Problema no Banco de Dados", JOptionPane.ERROR_MESSAGE);
            sucesso = false;
        } finally {
            if (st != null) {
                    pool.fechaClosables(null, st);
            }
        }
        pool.devolveConexaoPool(c);
        return sucesso;
    }

    /**
     * Método responsável por verificar se um usuário está cadastrado no Banco
     * de Dados.
     *
     * @param email String de email do usuário a ser verificado
     * @param senha String de senha do usuário a ser verificada
     * @return objeto Usuario que representa o usuário que possui as credenciais
     * passadas. Retorna NULL caso não encontre o usuário
     */
    public Usuario realizaLoginUsuario(String email, String senha) {
        InstanciaPool instancia = InstanciaPool.getInstance();
        ConexaoPoolBD pool = new ConexaoPoolBD();
        instancia.setConexao(pool);
        Connection c = pool.getConexaoPool();
        PreparedStatement st = null;
        ResultSet consulta = null;
        Usuario usuario = new Usuario();

        if (c != null) {
            try {
                String sql = "SELECT * FROM usuarios WHERE email = ? and senha = ?";
                st = c.prepareStatement(sql);
                st.setString(1, email);
                st.setString(2, senha);

                consulta = st.executeQuery();

                if (consulta.next()) {
                    usuario.setSenha(consulta.getString("senha"));
                    usuario.setTipoUsuario((!"".equals(consulta.getString("tipo_usuario")) ? TipoUsuario.valueOf(consulta.getString("tipo_usuario")) : null));
                    usuario.setNome(consulta.getString("nome_usuario"));
                    usuario.setIdUsuario(Integer.parseInt(consulta.getString("id_usuario")));
                } else {
                    usuario = null;
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro na consulta da tabela Usuários! " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            } finally {
                if (st != null) {
                        pool.fechaClosables(consulta, st);
                }
            }
        }
        pool.devolveConexaoPool(c);
        return usuario;
    }

    /**
     * Método responsável por consultar os usuários da base a partir de uma
     * substring
     *
     * @param filtro String que representa o nome completo ou parte do nome do
     * usuário a ser procurado
     * @return um List com os usuários da base que possuem a determinada
     * substring em seu nome. Caso não haja nenhum usuário com o nome passado, retorna uma lista vazia
     */
    public List<Usuario> consultaUsuarios(String filtro) {
        List<Usuario> usuarios = null;
        InstanciaPool instancia = InstanciaPool.getInstance();
        ConexaoPoolBD pool = instancia.getConexao();
        Connection c = pool.getConexaoPool();
        Statement s = null;
        PreparedStatement st = null;
        ResultSet consulta = null;

        if (c != null) {
            try {

                String sql = "SELECT * "
                        + "FROM usuarios "
                        + "WHERE nome_usuario ilike ?";

                st = c.prepareStatement(sql);
                st.setString(1, "%" + filtro + "%");
                consulta = st.executeQuery();

                usuarios = new ArrayList<>();
                while (consulta.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setNome(consulta.getString("nome_usuario"));
                    usuario.setCpf(consulta.getString("cpf"));
                    usuario.setEmail(consulta.getString("email"));
                    usuario.setTelefone(!"".equals(consulta.getString("telefone")) ? consulta.getString("telefone") : "Não informado");

                    usuarios.add(usuario);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro na consulta da tabela Usuários! " + ex.getMessage(), "Problema no Banco de Dados", JOptionPane.ERROR_MESSAGE);

            } finally {
                if (st != null && s != null) {
                        pool.fechaClosables(consulta, st);
                }
            }
        }
        pool.devolveConexaoPool(c);
        return usuarios != null ? usuarios : Collections.emptyList();
    }

    /**
     * Método responsável por verificar se um usuário já está cadastrado no
     * Banco de Dados
     *
     * @param email String que representa o email a ser buscado na Base de Dados
     * @return TRUE ou FALSE indicando se o usuário foi ou não encontrado na Base de Dados
     */
    
    public boolean verificaUsuario(String email) {
        InstanciaPool instancia = InstanciaPool.getInstance();
        ConexaoPoolBD pool = new ConexaoPoolBD();
        instancia.setConexao(pool);
        Connection c = pool.getConexaoPool();
        PreparedStatement st = null;
        ResultSet consulta = null;
        boolean cadastrado = false;

        try {
            String sql = "SELECT email FROM usuarios WHERE email = ?";
            st = c.prepareStatement(sql);
            st.setString(1, email);

            consulta = st.executeQuery();

            if (consulta.next()) {
                cadastrado = true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na consulta da tabela Usuários! " + ex.getMessage(), "Problema no Banco de Dados", JOptionPane.ERROR_MESSAGE);

        } finally {
            if (st != null) {
                    pool.fechaClosables(consulta, st);
            }
        }
        pool.devolveConexaoPool(c);
        return cadastrado;
    }
}
