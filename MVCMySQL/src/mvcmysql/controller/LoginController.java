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
import mvcmysql.model.Model;
import mvcmysql.model.Usuari;
import mvcmysql.view.Grup;
import mvcmysql.view.Home;
import mvcmysql.view.Login;
import mvcmysql.view.Register;
import mvcmysql.view.UsuariView;

/**
 *
 * @author francesc
 */
public class LoginController {

    private Model odb;
    private Login vista;
    private Home Homeview;
    private Grup GrupVIew;
    private ActionListener actionListener;

    public LoginController(Model odb, Login vm) {

        this.odb = odb;
        this.vista = vm;
        
        control();
    }

    public void control() {
        actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if (actionEvent.getActionCommand().equals(Login.login)) {
                    if (!vista.nom.equals("") && !vista.pass.equals("")) {
                         Usuari user = new Usuari();
                         user = odb.LoginUser(vista.nom, vista.pass);
                        
                        if (user == null) {
                            JOptionPane.showMessageDialog(null, "Usuari o contrasenya incorrectes", "Error", JOptionPane.ERROR_MESSAGE);                                
                        } else if (vista.nom.equals(user.get2_nom()) && vista.pass.equals(user.get8_pass())) {
                            
                            try {                                
                                Homeview = new Home();                                    
                                HomeController HomeController = new HomeController(odb,Homeview, user);
                               
                            } catch (Exception ex) {
                                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            Homeview.setVisible(true);
                             vista.setVisible(false);
                        } else {

                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No pots introduir una usauri sense nom !!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (actionEvent.getActionCommand().equals(Login.registre)) {
                    Register Registerview = new Register();
                    RegisterController RegisterController = new RegisterController(odb,Registerview);
                     //vista.setVisible(false);
                } else {
                    try {
                        odb.finalize();
                    } catch (Throwable ex) {
                        Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };

        vista.passarBotoLogin().addActionListener(actionListener);
        vista.passarBotoRegistre().addActionListener(actionListener);

    }

}
