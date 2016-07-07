package com.maycon.hotelaria.gui;

import com.maycon.hotelaria.dao.ReservaDAO;
import com.maycon.hotelaria.estruturas.Reserva;
import com.maycon.hotelaria.estruturas.Sessao;
import com.maycon.hotelaria.estruturas.TipoUsuario;
import com.maycon.hotelaria.tabelas.TabelaFactory;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.PersistenceException;
import javax.swing.JOptionPane;

public class ConsultaReservaUsuario extends javax.swing.JFrame {

    public ConsultaReservaUsuario() {
        initComponents();

        Sessao sessao = Sessao.getInstance();

        try{
            if (sessao.getUsuario().getTipoUsuario() == TipoUsuario.ADMINISTRADOR) {
                Object cabecalho[] = {"Nome", "CPF", "Email", "Telefone", "Quarto", "Data Inicial", "Data Final"};
                List<Reserva> reservas = new ReservaDAO().consultarTodasReservas("", "");
                Object[][] dadosTable = new Object[reservas.size()][7];

                for (int i = 0; i < reservas.size(); i++) {
                    Reserva reservaTmp = reservas.get(i);
                    dadosTable[i] = new Object[]{reservaTmp.getUsuario().getNome(), reservaTmp.getUsuario().getCpf(),
                        reservaTmp.getUsuario().getEmail(), reservaTmp.getUsuario().getTelefone(), reservaTmp.getQuarto().getNumQuarto(),
                        new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(reservaTmp.getDataHoraEntrada()), 
                        new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(reservaTmp.getDataHoraSaida())};
                }
                TabelaFactory.createTable(cabecalho, dadosTable, tbUsuarioReservas);
            }
            else{
                Object cabecalho[] = {"N�mero do Quarto", "Tipo do Quarto", "Data Inicial da Reserva", "Data Final da Reserva"};
                List<Reserva> reservas_usuario = new ReservaDAO().consultarReservasUsuario(sessao.getUsuario(), "", "");
                Object[][] dadosTable = new Object[reservas_usuario.size()][4];

                for (int i = 0; i < reservas_usuario.size(); i++) {
                    Reserva reservaTmp = reservas_usuario.get(i);
                    dadosTable[i] = new Object[]{reservaTmp.getQuarto().getNumQuarto(), reservaTmp.getQuarto().getTipoQuarto(),
                        new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(reservaTmp.getDataHoraEntrada()),
                        new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(reservaTmp.getDataHoraSaida())};
                }

                TabelaFactory.createTable(cabecalho, dadosTable, tbUsuarioReservas);
            }
        }catch(PersistenceException p){
            JOptionPane.showMessageDialog(null, "Erro na consulta da tabela Reservas! " + p.getMessage(), "Problema no Banco de Dados", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbUsuarioReservas = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnVoltar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnConsultarReservas = new javax.swing.JButton();
        jdInicio = new com.toedter.calendar.JDateChooser();
        jdFinal = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gerenciador de Hotelaria");

        tbUsuarioReservas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [][] {}
        ));
        tbUsuarioReservas.setEnabled(false);
        jScrollPane1.setViewportView(tbUsuarioReservas);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel1.setText("Reservas Realizadas");

        btnVoltar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnVoltar.setText("Voltar");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Escolha a data de interesse:");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("a");

        btnConsultarReservas.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnConsultarReservas.setText("Consultar Reservas");
        btnConsultarReservas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarReservasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 872, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jdInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnConsultarReservas)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(1, 1, 1)
                                    .addComponent(jLabel5)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jdInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1))
                            .addComponent(jdFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4))
                    .addComponent(btnConsultarReservas, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        Sessao sessao = Sessao.getInstance();
        this.dispose();
        if (TipoUsuario.COMUM == sessao.getUsuario().getTipoUsuario()) {
            new MenuCliente().setVisible(true);
        } else {
            new MenuAdmin().setVisible(true);
        }
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void btnConsultarReservasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarReservasActionPerformed

        //PROGRAMAÇÃO DEFENSIVA
        if (jdInicio.getDate() == null) {
            JOptionPane.showMessageDialog(rootPane, "Escolha uma data inicial de reserva!", "Campo Vazio!", JOptionPane.WARNING_MESSAGE);
            jdInicio.requestFocus();
            return;
        }
        if (jdFinal.getDate() == null) {
            JOptionPane.showMessageDialog(rootPane, "Escolha uma data final de reserva!", "Campo Vazio!", JOptionPane.WARNING_MESSAGE);
            jdFinal.requestFocus();
            return;
        }

        if (jdInicio.getDate().compareTo(jdFinal.getDate()) > 0) {
            JOptionPane.showMessageDialog(rootPane, "Data Final da reserva maior do que a Data Inicial!", "Intervalo Incorreto!", JOptionPane.WARNING_MESSAGE);
            jdInicio.setDate(null);
            jdFinal.setDate(null);
            return;
        }

        Date dataFinalOriginal = jdFinal.getDate();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        if (jdInicio.getDate().getDate() == dataFinalOriginal.getDate()) {

            Calendar c = Calendar.getInstance();
            c.setTime(jdFinal.getDate());
            c.add(Calendar.DAY_OF_MONTH, +1);
            dataFinalOriginal = c.getTime();
        }

        String dataInicio = formato.format(jdInicio.getDate());
        String dataFinal = formato.format(dataFinalOriginal);
        Sessao sessao = Sessao.getInstance();
        if (TipoUsuario.ADMINISTRADOR == sessao.getUsuario().getTipoUsuario()) {
            Object cabecalho[] = {"Nome", "CPF", "Email", "Telefone", "Quarto", "Data Inicial", "Data Final"};
            List<Reserva> reservas = new ReservaDAO().consultarTodasReservas(dataInicio, dataFinal);
            Object[][] dadosTable = new Object[reservas.size()][7];

            for (int i = 0; i < reservas.size(); i++) {
                Reserva reservaTmp = reservas.get(i);
                dadosTable[i] = new Object[]{reservaTmp.getUsuario().getNome(), reservaTmp.getUsuario().getCpf(),
                    reservaTmp.getUsuario().getEmail(), reservaTmp.getUsuario().getTelefone(), reservaTmp.getQuarto().getNumQuarto(),
                    new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(reservaTmp.getDataHoraEntrada()),
                    new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(reservaTmp.getDataHoraSaida())};
            }

            TabelaFactory.createTable(cabecalho, dadosTable, tbUsuarioReservas);
        } else {
            Object cabecalho[] = {"N�mero do Quarto", "Tipo do Quarto", "Data Inicial da Reserva", "Data Final da Reserva"};
            List<Reserva> reservas_usuario = new ReservaDAO().consultarReservasUsuario(sessao.getUsuario(), dataInicio, dataFinal);
            Object[][] dadosTable = new Object[reservas_usuario.size()][4];

            for (int i = 0; i < reservas_usuario.size(); i++) {
                Reserva reservaTmp = reservas_usuario.get(i);
                dadosTable[i] = new Object[]{reservaTmp.getQuarto().getNumQuarto(), reservaTmp.getQuarto().getTipoQuarto(),
                    new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(reservaTmp.getDataHoraEntrada()), 
                    new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(reservaTmp.getDataHoraSaida())};
            }

            TabelaFactory.createTable(cabecalho, dadosTable, tbUsuarioReservas);
        }
    }//GEN-LAST:event_btnConsultarReservasActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ConsultaReservaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultaReservaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultaReservaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultaReservaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsultaReservaUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsultarReservas;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdFinal;
    private com.toedter.calendar.JDateChooser jdInicio;
    private javax.swing.JTable tbUsuarioReservas;
    // End of variables declaration//GEN-END:variables
}
