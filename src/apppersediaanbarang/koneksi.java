/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apppersediaanbarang;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author WILLY PERMANA
 */
public class koneksi {
    Connection conn;
    Statement st;
    ResultSet rs;
    
    public Connection setKoneksi()
    {
    try{
    Class.forName("com.mysql.jdbc.Driver");
    conn = DriverManager.getConnection("jdbc:mysql://localhost/dbapppersediaanbarang","root","");
    st=conn.createStatement();
    }
    catch(Exception e){
    JOptionPane.showMessageDialog(null,"Koneksi Gagal : " +e);
    }
    return conn;
    }
}
