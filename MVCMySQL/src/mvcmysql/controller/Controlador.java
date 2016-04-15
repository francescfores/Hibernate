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
import mvcmysql.view.UsuariView;

/**
 *
 * @author profe
 */
public class Controlador {

    private Model odb;//=new Model("jdbc:mysql://localhost:3306/sakila", "root", "mp8");
    private UsuariView vista;
    private ActionListener actionListener;

    public Controlador(Model odb, UsuariView vm) {

        this.odb = odb;
        this.vista = vm;
        //odb.insertaUsuaris();
        /*vista.carregaTaula((ArrayList) odb.llistarUsuaris());
        vista.carregaTaulaGrups((ArrayList) odb.llistarGrups());
        vista.carregaTaulaPost((ArrayList) odb.llistarPosts());*/

       // control();
    }

    public void control() {
        actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if (actionEvent.getActionCommand().equals(UsuariView.borrar)) {
                    if (vista.filasel != -1) {
                        odb.borrarUsuari(vista.id);
                        vista.borrarCamps();
                        vista.carregaTaula((ArrayList) odb.llistarUsuaris());
                    } else {
                        JOptionPane.showMessageDialog(null, "Per borrar una ciutat primer l'has de seleccionar!!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (actionEvent.getActionCommand().equals(UsuariView.borrarGrup)) {
                    if (vista.filasel != -1) {
                        odb.borrarGrup(vista.id);
                        vista.borrarCamps();
                        vista.carregaTaulaGrups((ArrayList) odb.llistarGrups());
                    } else {
                        JOptionPane.showMessageDialog(null, "Per borrar una ciutatt primer l'has de seleccionar!!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (actionEvent.getActionCommand().equals(UsuariView.borrarPost)) {
                    if (vista.filasel != -1) {
                        odb.borrarPost(vista.id);
                        vista.borrarCamps();
                        vista.carregaTaulaPost((ArrayList) odb.llistarPosts());
                    } else {
                        JOptionPane.showMessageDialog(null, "Per borrar una post primer l'has de seleccionar!!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (actionEvent.getActionCommand().equals(UsuariView.insertar)) {
                    if (!vista.nom.equals("")) {
                        odb.insertUsuari(vista.nom, vista.cognoms, vista.dataNaixement, vista.edat, vista.treball, vista.foto, vista.pass);
                        vista.borrarCamps();
                        vista.carregaTaula((ArrayList) odb.llistarUsuaris());
                    } else {
                        JOptionPane.showMessageDialog(null, "No pots introduir una usauri sense nom !!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (actionEvent.getActionCommand().equals(UsuariView.insertarGrup)) {
                    if (!vista.nomgrup.equals("")) {
                        //odb.insertGrup(vista.nomgrup, vista.event, vista.fotogrup);
                        vista.borrarCamps();
                        vista.carregaTaulaGrups((ArrayList) odb.llistarGrups());
                    } else {
                        JOptionPane.showMessageDialog(null, "No pots introduir una usauri sense nom !!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (actionEvent.getActionCommand().equals(UsuariView.insertarPost)) {
                    if (!vista.nomPost.equals("")) {
                        //odb.insertPost(vista.nomPost, vista.comentaris, vista.dataPost, vista.fotoPost);
                        vista.borrarCamps();
                        vista.carregaTaulaPost((ArrayList) odb.llistarPosts());
                    } else {
                        JOptionPane.showMessageDialog(null, "No pots introduir una usauri sense nom !!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (actionEvent.getActionCommand().equals(UsuariView.modificar)) {
                    if (vista.filasel != -1 && (!vista.nom.equals(""))) {
                        odb.modificarUsuari(vista.id, vista.nom, vista.cognoms, vista.dataNaixement, vista.edat, vista.treball, vista.foto);
                        vista.borrarCamps();
                        vista.carregaTaula((ArrayList) odb.llistarUsuaris());
                    } else {
                        JOptionPane.showMessageDialog(null, "Per modificar una ciutat primer l'has de seleccionar i posar algun valor al nom !!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (actionEvent.getActionCommand().equals(UsuariView.modificarGrup)) {
                    if (vista.filasel != -1 && (!vista.nomgrup.equals(""))) {
                        odb.modificarGrup(vista.id, vista.nomgrup, vista.event, vista.fotogrup);
                        vista.borrarCamps();
                        vista.carregaTaulaGrups((ArrayList) odb.llistarGrups());
                    } else {
                        JOptionPane.showMessageDialog(null, "Per modificar una ciutat primer l'has de seleccionar i posar algun valor al nom !!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (actionEvent.getActionCommand().equals(UsuariView.modificarPost)) {
                    if (vista.filasel != -1 && (!vista.nomPost.equals(""))) {
                        odb.modificarPost(vista.id,vista.nomPost, vista.comentaris, vista.dataPost, vista.fotoPost);
                        vista.borrarCamps();
                        vista.carregaTaulaPost((ArrayList) odb.llistarPosts());
                    } else {
                        JOptionPane.showMessageDialog(null, "Per modificar una ciutat primer l'has de seleccionar i posar algun valor al nom !!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    try {
                        odb.finalize();
                    } catch (Throwable ex) {
                        Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    vista.sortir();
                }
            }
        };
        vista.passarBotoBorrar().addActionListener(actionListener);
        vista.passarBotoBorrarGrup().addActionListener(actionListener);
        vista.passarBotoBorrarPost().addActionListener(actionListener);

        vista.passarBotoInsertar().addActionListener(actionListener);
        vista.passarBotoInsertarGrup().addActionListener(actionListener);
        vista.passarBotoInsertarPost().addActionListener(actionListener);

        vista.passarBotoModificar().addActionListener(actionListener);
        vista.passarBotoModificarGrup().addActionListener(actionListener);
        vista.passarBotoModificarPost().addActionListener(actionListener);

        vista.passarBotoSortir().addActionListener(actionListener);

    }
}
