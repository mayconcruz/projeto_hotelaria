package com.maycon.hotelaria.gui;

import com.maycon.hotelaria.dao.UsuarioDAO;
import com.maycon.hotelaria.estruturas.Usuario;
import com.maycon.hotelaria.tabelas.TabelaFactory;
import java.util.List;
import javax.persistence.PersistenceException;
import javax.swing.JOptionPane;

public class ConsultaUsuarios extends javax.swing.JFrame {

    public ConsultaUsuarios() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbUsuarios = new javax.swing.JTable();
        btnConsultarNome = new javax.swing.JButton();
        txtNome = new javax.swing.JTextField();
        btnConsultar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gerenciador de Hotelaria");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel7.setText("Consulta de Usu√°rios");

        tbUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nome", "CPF", "Email", "Telefone"
            }
        )
        {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };
        }
    );
    tbUsuarios.setRowSelectionAllowed(false);
    tbUsuarios.setEnabled(false);
    jScrollPane1.setViewportView(tbUsuarios);

    btnConsultarNome.setText("Consultar por Nome");
    btnConsultarNome.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnConsultarNomeActionPerformed(evt);
        }
    });

    btnConsultar.setText("Consultar");
    btnConsultar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnConsultarActionPerformed(evt);
        }
    });

    jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jButton1.setText("Voltar");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton1ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(btnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 156, Short.MAX_VALUE)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jLabel7))
            .addGap(10, 10, 10)
            .addComponent(btnConsultarNome)
            .addContainerGap())
        .addGroup(layout.createSequentialGroup()
            .addGap(27, 27, 27)
            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGap(14, 14, 14)
            .addComponent(jLabel7)
            .addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(btnConsultar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConsultarNome)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(5, 5, 5))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        Object cabecalho[] = {"Nome", "CPF", "Email", "Telefone"};
        
        try{
            List<Usuario> usuarios = new UsuarioDAO().consultarUsuarios("");

            Object[][] dadosTable = new Object[usuarios.size()][4];

            for (int i = 0; i < usuarios.size(); i++) {
                Usuario usuarioTmp = usuarios.get(i);
                dadosTable[i] = new Object[]{usuarioTmp.getNome(), usuarioTmp.getCpf(), usuarioTmp.getEmail(), usuarioTmp.getTelefone()};
            }
            TabelaFactory.createTable(cabecalho, dadosTable, tbUsuarios);
        }catch(PersistenceException p){
            JOptionPane.showMessageDialog(null, "Erro na consulta da tabela Usu·rios! " + p.getMessage(), "Problema no Banco de Dados", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnConsultarNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarNomeActionPerformed
        Object cabecalho[] = {"Nome", "CPF", "Email", "Telefone"};
        
        try{
            List<Usuario> usuarios = new UsuarioDAO().consultarUsuarios(txtNome.getText());

            Object[][] dadosTable = new Object[usuarios.size()][4];

            for (int i = 0; i < usuarios.size(); i++) {
                Usuario usuarioTmp = usuarios.get(i);
                dadosTable[i] = new Object[]{usuarioTmp.getNome(), usuarioTmp.getCpf(), usuarioTmp.getEmail(), usuarioTmp.getTelefone()};
            }
            TabelaFactory.createTable(cabecalho, dadosTable, tbUsuarios);
        }catch(PersistenceException p){
            JOptionPane.showMessageDialog(null, "Erro na consulta da tabela Usu·rios! " + p.getMessage(), "Problema no Banco de Dados", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnConsultarNomeActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
        new MenuAdmin().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(ConsultaUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultaUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultaUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultaUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsultaUsuarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnConsultarNome;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbUsuarios;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
