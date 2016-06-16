package com.maycon.hotelaria.services;

import com.maycon.hotelaria.dao.UsuarioDAO;
import com.maycon.hotelaria.estruturas.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;


public class UsuarioServiceTest {
    
    @Test
    public void testConsultaUsuarios(){
        String email = "maycon-cruz@hotmail.com";
        String emailVazio = "";
        UsuarioDAO usuarioDAO = new UsuarioDAO(){
            
            @Override
            public List<Usuario> consultaUsuarios(String email){
                List<Usuario> usuarios = new ArrayList<>();
                if(!"".equals(email)){
                    for(int i=0; i<3; i++){
                        Usuario usuario = new Usuario();  
                        usuario.setNome("Usuario "+i);
                        usuarios.add(usuario);
                        System.out.println(usuarios.get(i).getNome());
                    }
                }
                else
                    usuarios = null;
                return usuarios;                         
            }
        };
        
        UsuarioService usuarioService = new UsuarioServiceImpl(usuarioDAO);
        assertNotNull("Lista vazia", usuarioService.consultaUsuarios(email));
        assertNull("Lista vazia", usuarioService.consultaUsuarios(emailVazio));
    }

    
}
