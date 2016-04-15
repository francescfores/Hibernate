/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvcmysql.view;

import static com.mchange.v2.c3p0.impl.C3P0Defaults.user;
import java.awt.Component;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import mvcmysql.model.Grup;

/**
 *
 * @author francesc
 */
public class GroupRenderer extends JLabel implements ListCellRenderer<Grup> {
        public GroupRenderer() {
        setOpaque(true);
    }


    @Override
    public Component getListCellRendererComponent(JList<? extends Grup> list, Grup grup, int index, boolean isSelected, boolean cellHasFocus) {
                  
        //int code = user.get1_usuari_id();
        
                //= new ImageIcon(getClass().getResource("/mvcmysql/img/Camera-48.png"));
       BufferedImage img = null;
        try {
            //FileOutputStream fos = new FileOutputStream("images\\output.jpg");  //windows
          ImageIcon imageIcon = new ImageIcon(grup.get5_foto());
           setIcon(imageIcon);
        } catch (Exception e) {
            e.printStackTrace();
        }
      
        
        setText(grup.get2_nom());        
         
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
}
