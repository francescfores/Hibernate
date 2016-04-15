/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mvcmysql.model;


import java.io.Serializable;
import java.util.*;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


/**
 *
 * @author profe
 */

@Entity
public class Usuari implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int _1_usuari_id;
    String _2_nom;
    String _3_cognoms;
    String _4_dataNaixement;
    int _5_edat;
    String _6_treball;
    
    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(length=100000)
    private byte[]  _7_foto;
    
    String _8_pass;
    
    @Column(length=1000000)
    ArrayList addUser = new ArrayList();

    public ArrayList getAddUser() {
        return addUser;
    }

    public void setAddUser(ArrayList addUser) {
        this.addUser = addUser;
    }
    
    public String get8_pass() {
        return _8_pass;
    }

    public void set8_pass(String _8_pass) {
        this._8_pass = _8_pass;
    }
        
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Grup> grups =new ArrayList<>();

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Post> posts = new ArrayList<>();
    
    public List<Grup> getGrups() {
        return grups;
    }

    @Override
    public String toString() {
        return _2_nom + " " + _3_cognoms;
    }
    

    public void setGrups(List<Grup> grups) {
        this.grups = grups;
    }
        
    public int get1_usuari_id() {
        return _1_usuari_id;
    }

    public void set1_usuari_id(int _1_usuari_id) {
        this._1_usuari_id = _1_usuari_id;
    }

    public String get2_nom() {
        return _2_nom;
    }

    public void set2_nom(String _2_nom) {
        this._2_nom = _2_nom;
    }

    public String get3_cognoms() {
        return _3_cognoms;
    }

    public void set3_cognoms(String _3_cognoms) {
        this._3_cognoms = _3_cognoms;
    }

    public String get4_dataNaixement() {
        return _4_dataNaixement;
    }

    public void set4_dataNaixement(String _4_dataNaixement) {
        this._4_dataNaixement = _4_dataNaixement;
    }

    public int get5_edat() {
        return _5_edat;
    }

    public void set5_edat(int _5_edat) {
        this._5_edat = _5_edat;
    }

    public String get6_treball() {
        return _6_treball;
    }

    public void set6_treball(String _6_treball) {
        this._6_treball = _6_treball;
    }

    public  byte[] get7_foto() {
        return _7_foto;
    }

    public void set7_foto( byte[] _7_foto) {
        this._7_foto = _7_foto;
    }

    
    
}