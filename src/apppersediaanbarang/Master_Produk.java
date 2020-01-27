/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apppersediaanbarang;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.KeyEvent;
import java.util.HashMap;
/**
 *
 * @author WILLY PERMANA
 */
public class Master_Produk extends javax.swing.JFrame {
koneksi kon = new koneksi();
private Object[][] dataproduk = null;
private String[] label={"ID Produk","Nama Produk","Stok","ID Kategori","ID Supplier"};
    /**
     * Creates new form Master_Produk
     */
    public Master_Produk() {
        initComponents();
        kon.setKoneksi();
        BacaTabelProduk();
    }
    
    public String IDKat;
    public String IDSup;
    public String getIDKat(){
        return IDKat;
    }
    public String getIDSup(){
        return IDSup;
    }
    
    public String nomor(){
        String urutan=null;
            try{
                kon.rs=kon.st.executeQuery("select right(id_produk,1)+1 "
                        + "from produk as Nomor order by id_produk desc");
                if(kon.rs.next())
                {
                    urutan=kon.rs.getString(1);
                    while(urutan.length()<1)
                        urutan="0"+urutan;
                    urutan=urutan;
                }else
                {
                    urutan="1";
                }
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
            return urutan;
    }
    
    private void BacaTabelProduk()
    {
        try{
            String sql="Select * from produk order by id_produk";
            kon.rs=kon.st.executeQuery(sql);
            ResultSetMetaData m=kon.rs.getMetaData();
            int kolom=m.getColumnCount();
            int baris=0;
            while(kon.rs.next()){
                baris=kon.rs.getRow();
            }
            dataproduk=new Object[baris][kolom];
            int x=0;
            kon.rs.beforeFirst();
            while(kon.rs.next()){
                dataproduk[x][0]=kon.rs.getString("id_produk");
                dataproduk[x][1]=kon.rs.getString("nama_produk");
                dataproduk[x][2]=kon.rs.getString("stok");
                dataproduk[x][3]=kon.rs.getString("id_kategori");
                dataproduk[x][4]=kon.rs.getString("id_supplier");
                x++;
            }
            tbl_produk.setModel(new DefaultTableModel(dataproduk,label));
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void BacaTabelCari()
    {
        try{
            String sql="Select * from produk where nama_produk like '%" +tcari.getText()+ "%' ";
            kon.rs=kon.st.executeQuery(sql);
            ResultSetMetaData m=kon.rs.getMetaData();
            int kolom=m.getColumnCount();
            int baris=0;
            while(kon.rs.next()){
                baris=kon.rs.getRow();
            }
            dataproduk=new Object[baris][kolom];
            int x=0;
            kon.rs.beforeFirst();
            while(kon.rs.next()){
                dataproduk[x][0]=kon.rs.getString("id_produk");
                dataproduk[x][1]=kon.rs.getString("nama_produk");
                dataproduk[x][2]=kon.rs.getString("stok");
                dataproduk[x][3]=kon.rs.getString("id_kategori");
                dataproduk[x][4]=kon.rs.getString("id_supplier");
                x++;
            }
            tbl_produk.setModel(new DefaultTableModel(dataproduk,label));
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void SetTabel()
    {
        int row=tbl_produk.getSelectedRow();
        tidpro.setText((String)tbl_produk.getValueAt(row, 0));
        tnama.setText((String)tbl_produk.getValueAt(row, 1));
        tstok.setText((String)tbl_produk.getValueAt(row, 2));
        tidkat.setText((String)tbl_produk.getValueAt(row, 3));
        tidsup.setText((String)tbl_produk.getValueAt(row, 4));
    }
    
    private void Bersih()
    {
        tidpro.setText("");
        tnama.setText("");
        tstok.setText("");
        tidkat.setText("");
        tidsup.setText("");
    }
    
    private void aktif()
    {
        tidpro.setEnabled(true);
        tnama.setEnabled(true);
        tstok.setEnabled(true);
        tidkat.setEnabled(true);
        tidsup.setEnabled(true);
    }
    
    private void nonaktif()
    {
        tidpro.setEnabled(false);
        tnama.setEnabled(false);
        tstok.setEnabled(false);
        tidkat.setEnabled(false);
        tidsup.setEnabled(false);
    }
    
    private void SimpanData()
    {
        try{
            String sql="insert into produk value('"+tidpro.getText()+"',"
                    +"'"+tnama.getText()+"','"+tstok.getText()+"',"
                    +"'"+tidkat.getText()+"','"+tidsup.getText()+"')";
            kon.st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
            Bersih();
            BacaTabelProduk();
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void UpdateData()
    {
        try{
            String sql="update produk set id_produk='"+tidpro.getText()+"',"
                    +"nama_produk='"+tnama.getText()+"',"
                    +"stok='"+tstok.getText()+"',"
                    +"id_kategori='"+tidkat.getText()+"',"
                    +"id_supplier='"+tidsup.getText()+"' where id_produk='"+tidpro.getText()+"'";
            kon.st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Data Berhasil Diedit");
            Bersih();
            BacaTabelProduk();
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void HapusData()
    {
        try{
            String sql="delete from produk where id_produk='"+tidpro.getText()+"'";
            kon.st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
            Bersih();
            BacaTabelProduk();
        }
        catch(SQLException e)
        {
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_produk = new javax.swing.JTable();
        tcari = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tidpro = new javax.swing.JTextField();
        tnama = new javax.swing.JTextField();
        tstok = new javax.swing.JTextField();
        tidkat = new javax.swing.JTextField();
        tidsup = new javax.swing.JTextField();
        bcari1 = new javax.swing.JButton();
        bcari2 = new javax.swing.JButton();
        bkeluar = new javax.swing.JButton();
        btambah = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Aplikasi Master Produk");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 153, 153));
        jLabel1.setText("PRODUK");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbl_produk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID Produk", "Nama Produk", "QTY", "ID Kategori", "ID Supplier"
            }
        ));
        tbl_produk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_produkMouseClicked(evt);
            }
        });
        tbl_produk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbl_produkKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_produk);

        tcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tcariKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Cari Nama Produk");

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 153), 2), ".: Input Data Produk :.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 153, 153))); // NOI18N
        jPanel3.setForeground(new java.awt.Color(255, 153, 153));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 153, 153));
        jLabel3.setText("ID Produk");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 153, 153));
        jLabel4.setText("Nama Produk");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 153, 153));
        jLabel5.setText("Stok");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 153, 153));
        jLabel6.setText("ID Kategori");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 153, 153));
        jLabel7.setText("ID Supplier");

        bcari1.setBackground(new java.awt.Color(102, 255, 102));
        bcari1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        bcari1.setForeground(new java.awt.Color(255, 255, 255));
        bcari1.setText("Cari");
        bcari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcari1ActionPerformed(evt);
            }
        });

        bcari2.setBackground(new java.awt.Color(102, 255, 102));
        bcari2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        bcari2.setForeground(new java.awt.Color(255, 255, 255));
        bcari2.setText("Cari");
        bcari2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcari2ActionPerformed(evt);
            }
        });

        bkeluar.setBackground(new java.awt.Color(255, 51, 51));
        bkeluar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        bkeluar.setForeground(new java.awt.Color(255, 255, 255));
        bkeluar.setText("Keluar");
        bkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bkeluarActionPerformed(evt);
            }
        });

        btambah.setBackground(new java.awt.Color(51, 102, 255));
        btambah.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btambah.setForeground(new java.awt.Color(255, 255, 255));
        btambah.setText("Tambah");
        btambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btambahActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tidpro)
                            .addComponent(tnama)
                            .addComponent(tstok)
                            .addComponent(tidsup)
                            .addComponent(tidkat))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(bcari1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bcari2))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 124, Short.MAX_VALUE)
                        .addComponent(btambah, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bkeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tidpro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tstok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tidkat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bcari1))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tidsup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bcari2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bkeluar)
                    .addComponent(btambah))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(tcari)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        //nonaktif();
        BacaTabelProduk();
        tstok.setEditable(false);
        tidpro.setEditable(false);
        tidkat.setEditable(false);
        tidsup.setEditable(false);
        btambah.requestFocus();
    }//GEN-LAST:event_formWindowActivated

    private void bcari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcari1ActionPerformed
        // TODO add your handling code here:
        aktif();
        Data_Kategori dk= new Data_Kategori(this, rootPaneCheckingEnabled);
        dk.pro = this;
        dk.setVisible(true);
        dk.setResizable(true);
        tidkat.setText(IDKat);
    }//GEN-LAST:event_bcari1ActionPerformed

    private void bcari2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcari2ActionPerformed
        // TODO add your handling code here:
        aktif();
        Data_Supplier ds= new Data_Supplier(this, rootPaneCheckingEnabled);
        ds.prod = this;
        ds.setVisible(true);
        ds.setResizable(true);
        tidsup.setText(IDSup);
    }//GEN-LAST:event_bcari2ActionPerformed

    private void tcariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tcariKeyTyped
        // TODO add your handling code here:
        kon.setKoneksi();
        BacaTabelCari();
    }//GEN-LAST:event_tcariKeyTyped

    private void tbl_produkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_produkMouseClicked
        // TODO add your handling code here:
        SetTabel();
        aktif();
        btambah.setText("Update");
        bkeluar.setText("Batal");
    }//GEN-LAST:event_tbl_produkMouseClicked

    private void tbl_produkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbl_produkKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_DELETE)
        {
            HapusData();
        }
        bkeluar.setText("Keluar");
        btambah.setText("Tambah");
    }//GEN-LAST:event_tbl_produkKeyPressed

    private void btambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btambahActionPerformed
        // TODO add your handling code here:
        if(btambah.getText().equals("Tambah"))
        {
            btambah.setText("Simpan");
            bkeluar.setText("Batal");
            Bersih();
            aktif();
            tidpro.setText(nomor());
            tnama.requestFocus();
            tstok.setEditable(false);
            tstok.setText("0");
        }
        else if(btambah.getText().equals("Simpan"))
        {
            SimpanData();
            BacaTabelProduk();
            btambah.setText("Tambah");
            bkeluar.setText("Keluar");
            Bersih();
            nonaktif();
        }
        else if(btambah.getText().equals("Update"))
        {
            UpdateData();
            BacaTabelProduk();
            btambah.setText("Tambah");
            bkeluar.setText("Keluar");
            Bersih();
            nonaktif();
        }
    }//GEN-LAST:event_btambahActionPerformed

    private void bkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bkeluarActionPerformed
        // TODO add your handling code here:
        if(bkeluar.getText().equals("Keluar"))
        {
            dispose();
        }
        else if(bkeluar.getText().equals("Batal"))
        {
            Bersih();
            nonaktif();
            bkeluar.setText("Keluar");
            btambah.setText("Tambah");
        }
    }//GEN-LAST:event_bkeluarActionPerformed

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
            java.util.logging.Logger.getLogger(Master_Produk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Master_Produk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Master_Produk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Master_Produk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Master_Produk().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bcari1;
    private javax.swing.JButton bcari2;
    private javax.swing.JButton bkeluar;
    private javax.swing.JButton btambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_produk;
    private javax.swing.JTextField tcari;
    private javax.swing.JTextField tidkat;
    private javax.swing.JTextField tidpro;
    private javax.swing.JTextField tidsup;
    private javax.swing.JTextField tnama;
    private javax.swing.JTextField tstok;
    // End of variables declaration//GEN-END:variables
}
