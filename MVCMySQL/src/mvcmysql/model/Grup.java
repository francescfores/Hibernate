/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvcmysql.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author francesc
 */
@Entity
public class Grup implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int _1_grup_id;
    String _2_nom;
    String _3_events;
    String _4_fotos;
    
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(length = 100000)
    private byte[] _5_foto;

    public byte[] get5_foto() {
        return _5_foto;
    }

    public void set5_foto(byte[] _5_foto) {
        this._5_foto = _5_foto;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Usuari> usuaris = new ArrayList<>();

    public int get1_grup_id() {
        return _1_grup_id;
    }

    public void set1_grup_id(int _1_grup_id) {
        this._1_grup_id = _1_grup_id;
    }

    public String get2_nom() {
        return _2_nom;
    }

    public void set2_nom(String _2_nom) {
        this._2_nom = _2_nom;
    }

    public String get3_events() {
        return _3_events;
    }

    public void set3_events(String _3_events) {
        this._3_events = _3_events;
    }

    public String get4_fotos() {
        return _4_fotos;
    }

    public void set4_fotos(String _4_fotos) {
        this._4_fotos = _4_fotos;
    }

    public List<Usuari> getUsuari() {
        return usuaris;
    }

    public void setUsuari(List<Usuari> usuari) {
        this.usuaris = usuari;
    }

}
