package com.maycon.hotelaria.estruturas;

import com.maycon.hotelaria.gui.Index;

public class Hotelaria {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //FORMA CORRETA DE SE INICIAR UMA THREAD SWING, O QUE EVITA QUE OCORRA PROBLEMAS EM SUA EXECUÇÃO.
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Index().setVisible(true);
            }
        });
    }

}
