/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apppersediaanbarang;
import java.io.File;
import javax.swing.JOptionPane;
import java.sql.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
/**
 *
 * @author WILLY PERMANA
 */
public class App_Ganti_Password extends javax.swing.JFrame {
koneksi kon = new koneksi();
    /**
     * Creates new form App_Ganti_Password
     */
    public App_Ganti_Password() {
        initComponents();
        kon.setKoneksi();
    }
    
    public String KodeUser;
    public String getKodeUser()
    {
        return KodeUser;
    }
    
    void nonaktif()
    {
        tkd_user.setEnabled(false);
        tpasslama.setEnabled(false);
        tpassbaru.setEnabled(false);
        tkonfirm.setEnabled(false);
    }
    
    void Bersih()
    {
        tpasslama.setText("");
        tpassbaru.setText("");
        tkonfirm.setText("");
    }
    
    private void passlama()
    {
        String Password = "";
        Password = tpasslama.getText();
        try{
            String sql = "Select * from user where password='" + Password + "'";
            kon.rs=kon.st.executeQuery(sql);
            if(kon.rs.next()){
                tpassbaru.setEnabled(true);
                tpassbaru.requestFocus();
            }
            else {
                JOptionPane.showMessageDialog(null, "Kode Password Tidak Ada, Silahkan"
                + "Isi Data");
                tpasslama.requestFocus();
            }
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void konfirmpassword()
    {
        try{
            if(tkonfirm.getText().equals(tpassbaru.getText()))
            {
                String sql = "Update user set password='" +tkonfirm.getText()
                        + "'where id_user='" +tkd_user.getText() +"'";
                kon.st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Password Telah TerUpdate");
                nonaktif();
                Bersih();
                dispose();
                App_Login login = new App_Login();
                login.setLocationRelativeTo(null);
                login.setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(null, "Password Baru dan konfirmasi"
                        + "Password Harus Sama");
                nonaktif();
                Bersih();
            }
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e);
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tkd_user = new javax.swing.JTextField();
        bkeluar = new javax.swing.JButton();
        tpasslama = new javax.swing.JPasswordField();
        tpassbaru = new javax.swing.JPasswordField();
        tkonfirm = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Aplikasi Edit Password User");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));

        jPanel2.setBackground(new java.awt.Color(255, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "* Ganti Password *", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Silahkan Input Data");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Kode User");
        jLabel2.setToolTipText("");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Password Lama");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Password Baru");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Konfirmasi Password");

        tkd_user.setBackground(new java.awt.Color(0, 0, 0));
        tkd_user.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tkd_user.setForeground(new java.awt.Color(255, 153, 153));

        bkeluar.setBackground(new java.awt.Color(255, 51, 51));
        bkeluar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        bkeluar.setForeground(new java.awt.Color(255, 255, 255));
        bkeluar.setText("Keluar");
        bkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bkeluarActionPerformed(evt);
            }
        });

        tpasslama.setBackground(new java.awt.Color(0, 0, 0));
        tpasslama.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tpasslama.setForeground(new java.awt.Color(255, 153, 153));
        tpasslama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tpasslamaActionPerformed(evt);
            }
        });

        tpassbaru.setBackground(new java.awt.Color(0, 0, 0));
        tpassbaru.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tpassbaru.setForeground(new java.awt.Color(255, 153, 153));
        tpassbaru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tpassbaruActionPerformed(evt);
            }
        });

        tkonfirm.setBackground(new java.awt.Color(0, 0, 0));
        tkonfirm.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tkonfirm.setForeground(new java.awt.Color(255, 153, 153));
        tkonfirm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tkonfirmKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(tpassbaru, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(tkd_user, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(bkeluar))
                    .addComponent(tpasslama, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tkonfirm))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(86, 86, 86))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tkd_user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bkeluar))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tpasslama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tpassbaru, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tkonfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        tkd_user.setText(KodeUser);
        nonaktif();
        tpasslama.setEnabled(true);
        tpasslama.requestFocus();
    }//GEN-LAST:event_formWindowActivated

    private void tpasslamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tpasslamaActionPerformed
        // TODO add your handling code here:
        passlama();
    }//GEN-LAST:event_tpasslamaActionPerformed

    private void tpassbaruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tpassbaruActionPerformed
        // TODO add your handling code here:
        tkonfirm.setEnabled(true);
        tkonfirm.requestFocus();
    }//GEN-LAST:event_tpassbaruActionPerformed

    private void bkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bkeluarActionPerformed
        // TODO add your handling code here:
        App_Login l = new App_Login();
        l.setLocationRelativeTo(null);
        l.setVisible(true);
    }//GEN-LAST:event_bkeluarActionPerformed

    private void tkonfirmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tkonfirmKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
        {
            konfirmpassword();
        }
    }//GEN-LAST:event_tkonfirmKeyPressed

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
            java.util.logging.Logger.getLogger(App_Ganti_Password.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(App_Ganti_Password.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(App_Ganti_Password.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(App_Ganti_Password.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new App_Ganti_Password().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bkeluar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField tkd_user;
    private javax.swing.JPasswordField tkonfirm;
    private javax.swing.JPasswordField tpassbaru;
    private javax.swing.JPasswordField tpasslama;
    // End of variables declaration//GEN-END:variables
}
