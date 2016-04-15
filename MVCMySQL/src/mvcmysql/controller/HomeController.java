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
import mvcmysql.model.Post;
import mvcmysql.model.Usuari;
import mvcmysql.view.Grup;
import mvcmysql.view.Home;
import mvcmysql.view.Login;
import mvcmysql.view.Register;
import mvcmysql.view.Register;
import mvcmysql.view.UsuariView;

/**
 *
 * @author francesc
 */
public class HomeController {

    private Model odb;
    static Home vista;
    static Grup GrupView;
    private ActionListener actionListener;
    public static Usuari usuari;

    public HomeController(Model odb, Home vm, Usuari usuari) {
        this.odb = odb;
        this.vista = vm;
        this.usuari = usuari;
        control();
        vista.carregaUsusari(usuari);
        vista.carregaPost((ArrayList<Post>) odb.llistarPosts());
        vista.carregaUsuaris((ArrayList<Usuari>) odb.llistarUsuaris());
        vista.carregaGrups((ArrayList) odb.llistarGrups());
        vista.loadContacts(usuari);
        vista.loadGroups(usuari);

    }

    public void control() {
        actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if (actionEvent.getActionCommand().equals(Home.post)) {
                    if (!vista.nomPost.equals("") && !vista.fotoPost.equals("") && !vista.comentaris.equals("")) {
                        odb.insertPost(vista.nomPost, vista.comentaris, vista.dataPost, vista.fotoPost, usuari);
                        //updatePost()   
                        vista.carregaPost((ArrayList<Post>) odb.llistarPosts());
                        vista.carregaUsuaris((ArrayList<Usuari>) odb.llistarUsuaris());
                    } else {
                        JOptionPane.showMessageDialog(null, "Introduiex tots els camps!!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (actionEvent.getActionCommand().equals(Home.uploadPhoto)) {

                } else if (actionEvent.getActionCommand().equals(Home.addUser)) {
                    odb.addUser(usuari, vista.addedusers);
                    vista.loadContacts(usuari);
                } else if (actionEvent.getActionCommand().equals(Home.addGroup)) {
                    odb.addGroup(usuari, vista.addedGroup);
                    vista.loadGroups(usuari);
                } else if (actionEvent.getActionCommand().equals(Home.insertarGrup)) {
                    try {
                        GrupView = new Grup();
                        GrupView.ListUsers((ArrayList<Usuari>) odb.llistarUsuaris());
                        GroupController GroupController = new GroupController(odb, GrupView);
                        vista.carregaGrups((ArrayList) odb.llistarGrups());
                    } catch (Exception ex) {
                        Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    GrupView.setVisible(true);

                } else {
                    try {
                        odb.finalize();
                    } catch (Throwable ex) {
                        Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };

        vista.passarBotoPost().addActionListener(actionListener);
        vista.passarBotoUploadPhoto().addActionListener(actionListener);
        vista.CreateGroup().addActionListener(actionListener);

        vista.passarBotoAddUser().addActionListener(actionListener);
         vista.passarBotoAddGroup().addActionListener(actionListener);

    }

    public static Usuari carregaUsuari() {
        return usuari;
    }

}
