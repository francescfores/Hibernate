/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvcmysql.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static mvcmysql.controller.HomeController.GrupView;
import mvcmysql.model.Model;
import mvcmysql.model.Usuari;
import mvcmysql.view.Grup;
import mvcmysql.view.Home;
import mvcmysql.view.Login;
import mvcmysql.view.Register;

/**
 *
 * @author francesc
 */
public class GroupController {
    private Model odb;
    private Home Homeview;
    private Grup GrupView;
    private ActionListener actionListener;
    
    public GroupController(Model odb, Grup vm) {

        this.odb = odb;
        this.GrupView = vm;
        
        control();
    }
    
     public void control() {
        actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if (actionEvent.getActionCommand().equals(Login.login)) {
                    
                } else if (actionEvent.getActionCommand().equals(GrupView.insertarGrup)) {
                     if (!GrupView.nomgrup.equals("")) {
                        odb.insertGrup(GrupView.nomgrup, GrupView.event, GrupView.foto, GrupView.addedusers);
                        //GrupView.borrarCamps();
                    } else {
                        JOptionPane.showMessageDialog(null, "No pots introduir una usauri sense nom !!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    try {
                        odb.finalize();
                    } catch (Throwable ex) {
                        Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };

        GrupView.CreateGroup().addActionListener(actionListener);

    }
}
