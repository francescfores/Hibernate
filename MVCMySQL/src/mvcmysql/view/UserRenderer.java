/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvcmysql.view;

import java.awt.Component;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import static mvcmysql.controller.HomeController.usuari;
import mvcmysql.model.Country;
import mvcmysql.model.Usuari;

/**
 *
 * @author francesc
 */
public class UserRenderer extends JLabel implements ListCellRenderer<Usuari> {
     
    public UserRenderer() {
        setOpaque(true);
    }
    
    @Override
    public Component getListCellRendererComponent(JList<? extends Usuari> list, Usuari user, int index,
        boolean isSelected, boolean cellHasFocus) {
          
        int code = user.get1_usuari_id();
        
                //= new ImageIcon(getClass().getResource("/mvcmysql/img/Camera-48.png"));
       BufferedImage img = null;
        try {
            //FileOutputStream fos = new FileOutputStream("images\\output.jpg");  //windows
          ImageIcon imageIcon = new ImageIcon(user.get7_foto());
           setIcon(imageIcon);
        } catch (Exception e) {
            e.printStackTrace();
        }
      
        
        setText(user.get2_nom());        
         
        if (list != null) {
             isSelected = list.isSelectedIndex(index);
        }
         
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
            //add list us
            //Grup.addedusers.add(user);
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
            //Grup.addedusers.remove(user);
        }
        
        return this;
    }

    /*  
        @Override
        public Component getListCellRendererComponent(JList<? extends Country> list, Country country, int index,
        boolean isSelected, boolean cellHasFocus) {
          
        String code = country.getCode();
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/images/" + code + ".png"));
         
        setIcon(imageIcon);
        setText(country.getName());
         
        return this;
    }
    */
    
     
}
