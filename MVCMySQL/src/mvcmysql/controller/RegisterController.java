/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvcmysql.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import mvcmysql.model.Model;
import mvcmysql.model.Usuari;
import mvcmysql.view.Home;
import mvcmysql.view.Login;
import mvcmysql.view.Register;
import mvcmysql.view.Register;
import mvcmysql.view.UsuariView;

/**
 *
 * @author francesc
 */
public class RegisterController {
    private Model odb;
    private Register vista;
    static Login LoginView ;
    private ActionListener actionListener;
    
    static LoginController LoginController=null;
    
    public RegisterController(Model odb, Register vm) {

        this.odb = odb;
        this.vista = vm;
        //odb.insertaUsuaris();
        //vista.carregaTaula((ArrayList) odb.llistarUsuaris());

        control();
    }
        
    public void control() {
        actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if (actionEvent.getActionCommand().equals(Register.registre)) {
                    if (!vista.nom.equals("")) {
                         odb.insertUsuari(vista.nom, vista.cognoms, vista.data, vista.edat, vista.treball, vista.foto, vista.pass);
                         LoginView = new Login();
                         
                         LoginController=new LoginController(odb,LoginView);
                    } else {
                        JOptionPane.showMessageDialog(null, "No pots introduir una usauri sense nom !!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
               
                }else if (actionEvent.getActionCommand().equals(Login.login)) {
                    
               
                } else if (actionEvent.getActionCommand().equals(UsuariView.borrarPost)) {
                   
                } else {
                    try {
                        odb.finalize();
                    } catch (Throwable ex) {
                        Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                    }                    
                }
            }
        };
        

        vista.passarBotoRegistre().addActionListener(actionListener);
//        vistaLogin.passarBotoLogin().addActionListener(actionListener);

    }
}
