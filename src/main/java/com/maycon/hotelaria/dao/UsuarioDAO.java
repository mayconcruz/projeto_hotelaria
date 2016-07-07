package com.maycon.hotelaria.dao;

import com.maycon.hotelaria.estruturas.Usuario;
import java.util.Collections;
import java.util.List;
import javax.persistence.*;

/**
 * Esta classe apresenta m�todos utilizados para fazerem a intera��o entre a aplica��o e a fonte de dados que armazena os dados
 * dos usu�rios armazenados no sistema.
 * @author Maycon
 */
public class UsuarioDAO {

    /**
     * M�todo respons�vel por realizar o cadastro de usu�rios do sistema.
     *
     * @param usuario Objeto do tipo Usuario que cont�m as informa��es do
     * usuario
     * @return TRUE ou FALSE, indicando sucesso ou falha no cadastro de
     * usu�rios.
     */
    public boolean cadastrarUsuario(Usuario usuario) throws PersistenceException {
        boolean sucesso = true;
        GerenciaEntity entity = GerenciaEntity.obterEntity();
        
        try {                      
            entity.getManager().getTransaction().begin();
            entity.getManager().persist(usuario);
            entity.getManager().getTransaction().commit();
            
        } catch (PersistenceException p) {            
            sucesso = false;
            entity.getManager().getTransaction().rollback();
        } finally {
            FinalizaManager.finalizarManager(entity);
        }
        return sucesso;
    }

    /**
     * M�todo respons�vel por verificar se um usu�rio est� cadastrado no Banco
     * de Dados.
     *
     * @param email String de email do usu�rio a ser verificado
     * @param senha String de senha do usu�rio a ser verificada
     * @return objeto Usuario que representa o usu�rio que possui as credenciais
     * passadas. Retorna NULL caso n�o encontre o usu�rio
     */
    public Usuario realizarLoginUsuario(String email, String senha) throws PersistenceException {
        Usuario usuario = new Usuario();
        GerenciaEntity entity = GerenciaEntity.obterEntity();
        entity.setFactory();
        entity.setManager(entity.getFactory());

        try {
            String sql = "FROM Usuario u WHERE u.email = :email and u.senha = :senha";
            TypedQuery<Usuario> query = entity.getManager().createQuery(sql, Usuario.class);
            query.setParameter("email", email);
            query.setParameter("senha", senha);
            List<Usuario> usuarios = query.getResultList();
            
            if (!usuarios.isEmpty()) {
                usuario = usuarios.get(0);
            } else {
                usuario = null;
            }
            
        } catch (PersistenceException p) {
            entity.getManager().getTransaction().rollback();
        } finally {
            FinalizaManager.finalizarManager(entity);
        }
        return usuario;
    }

    /**
     * M�todo respons�vel por consultar os usu�rios da base a partir de uma
     * substring
     *
     * @param filtro String que representa o nome completo ou parte do nome do
     * usu�rio a ser procurado
     * @return um List com os usu�rios da base que possuem a determinada
     * substring em seu nome. Caso n�o haja nenhum usu�rio com o nome passado, retorna uma lista vazia
     */
    public List<Usuario> consultarUsuarios(String filtro) throws PersistenceException {
        List<Usuario> usuarios = null;
        GerenciaEntity entity = GerenciaEntity.obterEntity();
        entity.setManager(entity.getFactory());

        try {
            String sql = "FROM Usuario u WHERE lower(u.nome) like lower(:nome)";
            TypedQuery<Usuario> query = entity.getManager().createQuery(sql, Usuario.class);
            query.setParameter("nome", "%" + filtro + "%");
            usuarios = query.getResultList();
        } catch (PersistenceException p) {
            entity.getManager().getTransaction().rollback();
        } finally {
            FinalizaManager.finalizarManager(entity);
        }
        return usuarios != null ? usuarios : Collections.<Usuario>emptyList();
    }

    /**
     * M�todo respons�vel por verificar se um usu�rio j� est� cadastrado no
     * Banco de Dados
     *
     * @param email String que representa o email a ser buscado na Base de Dados
     * @return TRUE ou FALSE indicando se o usu�rio foi ou n�o encontrado na Base de Dados
     */
    
    public boolean verificarUsuario(String email) throws PersistenceException {
        boolean cadastrado = false;
        GerenciaEntity entity = GerenciaEntity.obterEntity();
        entity.setManager(entity.getFactory());
        
        try {
            String sql = "FROM Usuario u WHERE u.email = :email";
            TypedQuery<Usuario> query = entity.getManager().createQuery(sql, Usuario.class);
            query.setParameter("email", email);

            if (!query.getResultList().isEmpty()) {
                cadastrado = true;
            }
            
        } catch (PersistenceException p) {
            entity.getManager().getTransaction().rollback();

        } finally {
            FinalizaManager.finalizarManager(entity);
        }
        return cadastrado;
    }
}
