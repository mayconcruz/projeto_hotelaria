package com.maycon.hotelaria.dao;

import com.maycon.hotelaria.estruturas.Usuario;
import java.util.Collections;
import java.util.List;
import javax.persistence.*;
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
        boolean sucesso = true;
        GerenciaEntity entity = new GerenciaEntity();
        EntityManager manager = entity.constroiManager();
        
        try {                      
            manager.getTransaction().begin();
            manager.persist(usuario);
            manager.getTransaction().commit();
            
        } catch (PersistenceException p) {
            JOptionPane.showMessageDialog(null, "Erro no Banco de Dados: " + p.getMessage(), "Problema no Banco de Dados", JOptionPane.ERROR_MESSAGE);
            sucesso = false;
        } finally {
            if (manager != null) {
                 entity.closable(manager);
            }
        }
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
        Usuario usuario = new Usuario();
        GerenciaEntity entity = new GerenciaEntity();
        EntityManager manager = entity.constroiManager();

        try {
            String sql = "FROM Usuario u WHERE u.email = :email and u.senha = :senha";
            TypedQuery<Usuario> query = manager.createQuery(sql, Usuario.class);
            query.setParameter("email", email);
            query.setParameter("senha", senha);
            List<Usuario> usuarios = query.getResultList();
            
            if (!usuarios.isEmpty()) {
                usuario = usuarios.get(0);
            } else {
                usuario = null;
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro na consulta da tabela Usuários! " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (manager != null) {
                entity.closable(manager);
            }
        }
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
        GerenciaEntity entity = new GerenciaEntity();
        EntityManager manager = entity.constroiManager();

        try {
            String sql = "FROM Usuario u WHERE lower(u.nome) like lower(:nome)";
            TypedQuery<Usuario> query = manager.createQuery(sql, Usuario.class);
            query.setParameter("nome", "%" + filtro + "%");
            usuarios = query.getResultList();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro na consulta da tabela Usuários! " + ex.getMessage(), "Problema no Banco de Dados", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (manager != null) {
                entity.closable(manager);
            }
        }
        return usuarios != null ? usuarios : Collections.<Usuario>emptyList();
    }

    /**
     * Método responsável por verificar se um usuário já está cadastrado no
     * Banco de Dados
     *
     * @param email String que representa o email a ser buscado na Base de Dados
     * @return TRUE ou FALSE indicando se o usuário foi ou não encontrado na Base de Dados
     */
    
    public boolean verificaUsuario(String email) {
        boolean cadastrado = false;
        GerenciaEntity entity = new GerenciaEntity();
        EntityManager manager = entity.constroiManager();
        
        try {
            String sql = "FROM Usuario u WHERE u.email = :email";
            TypedQuery<Usuario> query = manager.createQuery(sql, Usuario.class);
            query.setParameter("email", email);

            if (!query.getResultList().isEmpty()) {
                cadastrado = true;
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro na consulta da tabela Usuários! " + ex.getMessage(), "Problema no Banco de Dados", JOptionPane.ERROR_MESSAGE);

        } finally {
            if (manager != null) {
                entity.closable(manager);
            }
        }
        return cadastrado;
    }
}
