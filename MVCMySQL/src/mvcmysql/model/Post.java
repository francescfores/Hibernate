/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvcmysql.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author francesc
 */
@Entity
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int _1_post_id;
    String _2_nom;
    @Column( length = 400 )
    String _3_comentari;
    @Column( length = 400)
    String _4_data;
    
    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(length=100000)
    byte[] _5_foto;

    public int get1_post_id() {
        return _1_post_id;
    }

    public void set1_post_id(int _1_post_id) {
        this._1_post_id = _1_post_id;
    }

    public String get2_nom() {
        return _2_nom;
    }

    public void set2_nom(String _2_nom) {
        this._2_nom = _2_nom;
    }

    public String get3_comentari() {
        return _3_comentari;
    }

    public void set3_comentari(String _3_comentari) {
        this._3_comentari = _3_comentari;
    }

    public String get4_data() {
        return _4_data;
    }

    public void set4_data(String _4_data) {
        this._4_data = _4_data;
    }

    public byte[] get5_foto() {
        return _5_foto;
    }

    public void set5_foto(byte[] _5_foto) {
        this._5_foto = _5_foto;
    }

    public Usuari getUsuari() {
        return usuari;
    }

    public void setUsuari(Usuari usuari) {
        this.usuari = usuari;
    }
    
    
    
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Usuari usuari = new Usuari();
    



 

   
}
