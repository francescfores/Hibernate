/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvcmysql.model;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import mvchibernate.HibernateUtil;
import org.hibernate.Query;

/**
 *
 * @author profe
 */
public class Model {

    private static Session session;
    public static Transaction tx;

    public Model() {
        System.out.println("Connectant a la BD...");
        session = HibernateUtil.getSessionFactory().openSession();
    }

    @Override
    public void finalize() throws Throwable {
        if (session != null) {
            System.out.println("Desconnectant de la BD...");
            session.close();
        }
    }

    public static void iniciaOperacion() throws HibernateException {
        //sesion = HibernateUtil.getSessionFactory().openSession(); 
        tx = session.beginTransaction();
    }

    public static void confirmaOperacion() throws HibernateException {
        tx.commit();
    }

    private void manejaExcepcion(HibernateException he) throws HibernateException {
        tx.rollback();
        throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he);
    }

    public List<Usuari> llistarUsuaris() {
        List<Usuari> result = null;
        try {
            iniciaOperacion();
            Query query = session.createQuery("FROM Usuari");

            result = query.list();
            confirmaOperacion();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        }
        return result;
    }

    public List<Grup> llistarGrups() {
        List<Grup> result = null;
        try {
            iniciaOperacion();
            Query query = session.createQuery("FROM Grup");

            result = query.list();
            confirmaOperacion();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        }
        return result;
    }

    public List<Post> llistarPosts() {
        List<Post> result = null;
        try {
            iniciaOperacion();
            Query query = session.createQuery("FROM Post");

            result = query.list();
            confirmaOperacion();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        }
        return result;
    }

    public List<Solicitud> llistarSolicituds() {
        List<Solicitud> result = null;
        try {
            iniciaOperacion();
            Query query = session.createQuery("FROM Solicitud");

            result = query.list();
            confirmaOperacion();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        }
        return result;
    }

    public void borrarUsuari(int _1_usuari_id) {

        List<Usuari> result = null;
        try {
            iniciaOperacion();
            Query query = session.createQuery("FROM Usuari WHERE _1_usuari_id = " + _1_usuari_id);

            Usuari c = (Usuari) query.uniqueResult();
            session.delete(c);
            confirmaOperacion();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        }

    }

    public void borrarGrup(int _1_grup_id) {

        List<Grup> result = null;
        try {
            iniciaOperacion();
            Query query = session.createQuery("FROM Grup WHERE _1_grup_id = " + _1_grup_id);

            Grup c = (Grup) query.uniqueResult();
            session.delete(c);
            confirmaOperacion();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        }

    }

    public void borrarPost(int _1_post_id) {

        List<Post> result = null;
        try {
            iniciaOperacion();
            Query query = session.createQuery("FROM Post WHERE _1_post_id = " + _1_post_id);

            Post c = (Post) query.uniqueResult();
            session.delete(c);
            confirmaOperacion();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        }

    }

    public void borrarSolicitud(int _1_id) {

        List<Solicitud> result = null;
        try {
            iniciaOperacion();
            Query query = session.createQuery("FROM Solicitud WHERE _1_id = " + _1_id);

            Grup c = (Grup) query.uniqueResult();
            session.delete(c);
            confirmaOperacion();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        }

    }

    public void insertUsuari(String nom, String cognoms, String dataNaixement, int edat, String treball, String foto, String pass) {

        try {
            iniciaOperacion();

            Usuari usuari = new Usuari();
            usuari.set2_nom(nom);
            usuari.set3_cognoms(cognoms);
            usuari.set4_dataNaixement(dataNaixement);
            usuari.set5_edat(edat);
            usuari.set6_treball(treball);
            usuari.set8_pass(pass);

            File file = new File(foto);
            byte[] bFile = new byte[(int) file.length()];
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                fileInputStream.read(bFile);
                fileInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            usuari.set7_foto(bFile);

            session.save(usuari);
            confirmaOperacion();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        }
    }

    public void insertGrup(String nom, String events, String foto, ArrayList<Usuari> users) {

        try {
            iniciaOperacion();
            Grup grup = new Grup();
            grup.set2_nom(nom);
            grup.set3_events(events);
            File file = new File(foto);
            byte[] bFile = new byte[(int) file.length()];
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                fileInputStream.read(bFile);
                fileInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            grup.set5_foto(bFile);
            grup.setUsuari(users);

            session.save(grup);
            confirmaOperacion();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        }
    }

    public void modificarUsuari(int _1_usuari_id, String nom, String cognoms, String dataNaixement, int edat, String treball, String foto) {

        try {
            iniciaOperacion();
            Query query = session.createQuery("FROM Usuari WHERE _1_usuari_id = " + _1_usuari_id);

            Usuari usuari = (Usuari) query.uniqueResult();
            usuari.set2_nom(nom);
            usuari.set3_cognoms(cognoms);
            usuari.set4_dataNaixement(dataNaixement);
            usuari.set5_edat(edat);
            usuari.set6_treball(treball);

            File file = new File(foto);
            byte[] bFile = new byte[(int) file.length()];
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                fileInputStream.read(bFile);
                fileInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            usuari.set7_foto(bFile);

            session.update(usuari);
            confirmaOperacion();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        }

    }

    public void insertPost(String nom, String comentari, String data, String foto, Usuari usuari) {

        try {
            iniciaOperacion();

            Post post = new Post();
            post.set2_nom(nom);
            post.set3_comentari(comentari);
            post.set4_data(data);
            
            File file = new File(foto);
            byte[] bFile = new byte[(int) file.length()];
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                fileInputStream.read(bFile);
                fileInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            post.set5_foto(bFile);
            post.setUsuari(usuari);

            session.save(post);
            confirmaOperacion();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        }
    }

    public void insertSolicitud(String comentari, Date data, boolean acepta) {

        try {
            iniciaOperacion();

            Solicitud solicitud = new Solicitud();
            solicitud.set2_data(data);
            solicitud.set3_acepta(acepta);

            session.save(solicitud);
            confirmaOperacion();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        }
    }

    public void modificarGrup(int _1_grup_id, String nom, String events, String fotos) {

        try {
            iniciaOperacion();
            Query query = session.createQuery("FROM Grup WHERE _1_grup_id = " + _1_grup_id);

            Grup grup = (Grup) query.uniqueResult();
            grup.set2_nom(nom);
            grup.set3_events(events);
            grup.set4_fotos(fotos);

            session.update(grup);
            confirmaOperacion();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        }

    }

    public void modificarPost(int _1_post_id, String nom, String comentari, String data, String foto) {

        try {
            iniciaOperacion();
            Query query = session.createQuery("FROM Post WHERE _1_post_id = " + _1_post_id);

            Post post = (Post) query.uniqueResult();
            post.set2_nom(nom);
            post.set3_comentari(comentari);
            post.set4_data(data);
            //post.set5_foto(foto);

            session.update(post);
            confirmaOperacion();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        }

    }

    public void modificarSolicitud(int _1_solicitud_id, String comentari, Date data, boolean acepta) {

        try {
            iniciaOperacion();
            Query query = session.createQuery("FROM Solicitud WHERE _1_solicitud_id = " + _1_solicitud_id);

            Solicitud solicitud = (Solicitud) query.uniqueResult();
            solicitud.set2_data(data);
            solicitud.set3_acepta(acepta);

            session.update(solicitud);
            confirmaOperacion();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        }

    }

    public Usuari LoginUser(String nom, String pass) {
        List<Usuari> result = null;
        try {
            iniciaOperacion();
            Query query = session.createQuery("FROM Usuari WHERE _2_nom = '" + nom + "' AND _8_pass = '" + pass + "'");

            Usuari c = (Usuari) query.uniqueResult();
            confirmaOperacion();
            return c;
        } catch (HibernateException he) {
            manejaExcepcion(he);
        }
        return null;
    }

    public Usuari addUser(Usuari user1, /*Usuari user2 */ ArrayList<Usuari> users) {
        try {
            iniciaOperacion();
            Query query = session.createQuery("FROM Usuari WHERE _1_usuari_id = " + user1.get1_usuari_id());

            Usuari usuari = (Usuari) query.uniqueResult();
            ArrayList<Usuari> e = user1.getAddUser();
            //e.add(users);
            user1.setAddUser(users);

            session.update(user1);
            confirmaOperacion();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        }
        return null;
    }
        public Usuari addGroup(Usuari user1, /*Usuari user2 */ List<Grup> users) {
        try {
            iniciaOperacion();
            Query query = session.createQuery("FROM Usuari WHERE _1_usuari_id = " + user1.get1_usuari_id());

            //Usuari usuari = (Usuari) query.uniqueResult();
            List<Grup> e = user1.getGrups();
            e.add((Grup) users);
            user1.setGrups(users);

            session.update(user1);
            confirmaOperacion();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        }
        return null;
    }

}
