package com.maycon.hotelaria.validacoes;
import org.junit.Test;
import static org.junit.Assert.*;

public class ValidacoesTest {

    @Test
    public void testValidaEmailNuloVazioDeveRetornarFalse() {       
        String email = null;
        assertFalse(Validacoes.validaEmail(email));

        String emailVazio = "";

        assertFalse(Validacoes.validaEmail(emailVazio));
    }
    
    @Test
    public void testEmailForaFormatoDeveRetornarFalse(){
        String emailSemArroba = "mayconcruz.com";
        String emailSemPonto = "mayconcruz@";
        String emailSemFormatacaoFinal = "maycon-cruz@gmail.coooooom";
        
        assertFalse(Validacoes.validaEmail(emailSemArroba));
        assertFalse(Validacoes.validaEmail(emailSemPonto));
        assertFalse(Validacoes.validaEmail(emailSemFormatacaoFinal));
                
    }
              
    @Test
    public void testEmailCorreto(){
        String email = "mayconcruz@hotmail.com";
        assertTrue(Validacoes.validaEmail(email));
    }
    
    @Test
    public void testTextoEmCampoDeNumero(){
        String texto = "validando texto em campo numérico";
        assertFalse(Validacoes.validaNumQuarto(texto));
    }
    
    @Test
    public void testCaracterEspecialEmCampoDeNumero(){
        String caracteres = "/@!#$%&*><:+_-}{[]ªº=,.;|'";
        assertFalse(Validacoes.validaNumQuarto(caracteres));
        
    }
    
    @Test
    public void testNumerosEmCampoDeNumero(){
        String numeros = "0123456789";
        assertTrue(Validacoes.validaNumQuarto(numeros));
    }
    
    @Test
    public void testNumerosAPartirDoUmEmCampoDeNumero(){
        String numeros = "123456789";
        assertTrue(Validacoes.validaNumQuarto(numeros));
    }
    
    @Test
    public void testVazioEmCampoDeNumero(){
        String numeroQuarto= "";
        assertFalse(Validacoes.validaNumQuarto(numeroQuarto));
    }
    
    @Test
    public void testNuloEmCampoDeNumero(){
        String numero = null;
        assertNull(Validacoes.validaNumQuarto(numero));
    }
    

}
