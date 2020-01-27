/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package background;
import java.awt.*;
import javax.swing.*;
/**
 *
 * @author Willy Permana
 */
public class GambarMenu extends JPanel {
Image gambar;
public GambarMenu(){
        gambar=new ImageIcon(getClass().getResource("/gambar/phone.jpg")).getImage();
    }
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D gd=(Graphics2D)g.create();
        gd.drawImage(gambar, 0, 0, getWidth(), getHeight(), null);
        gd.dispose();
    }
}
