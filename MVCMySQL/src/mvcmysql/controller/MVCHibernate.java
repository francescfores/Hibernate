/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mvcmysql.controller;

import mvcmysql.model.Model;
import mvcmysql.view.*;
import org.hibernate.HibernateException;

/**
 *
 * @author profe
 */
public class MVCHibernate {
    
    static RegisterController controlador=null;
    static LoginController LoginController=null;
    static Model modelo;//=new Modeldb4o("localhost","user","mp8","bd.db4o",7000);
    static Register vista_m ;
    static Login LoginView ;
    static Grup GrupView;


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            /*modelo=new Model();
            //vista_a=new VistaActors();
            //vista_p=new VistaPelis();
            //vista_pr=new VistaPrincipal();
            vista_m =new UsuariView();
            
            controlador=new Controlador(modelo, 
                    //vista_a, vista_p, vista_pr, 
                    vista_m);*/
            
            modelo= new Model();
            //vista_a=new VistaActors();
            //vista_p=new VistaPelis();
            //vista_pr=new VistaPrincipal();
            //vista_m = new Register();
            LoginView = new Login();
            
            /*controlador=new RegisterController(modelo, 
                    //vista_a, vista_p, vista_pr, 
                    vista_m);*/
            
           LoginController=new LoginController(modelo, 
                    //vista_a, vista_p, vista_pr, 
                    LoginView);
        }
        catch(HibernateException e){
            System.err.println("No s'ha pogut establir la connexió a la BD!!\nTancant l'aplicació...");
        }
        
    }
    
}
