/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvcmysql.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author francesc
 */

@Entity
public class Solicitud implements Serializable {

    public boolean is3_acepta() {
        return _3_acepta;
    }

    public void set3_acepta(boolean _3_acepta) {
        this._3_acepta = _3_acepta;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int _1_solicitud_id;
    Date _2_data;
    boolean _3_acepta;
    
    
    public int get1_post_id() {
        return _1_solicitud_id;
    }

    public void set1_post_id(int _1_solicitud_id) {
        this._1_solicitud_id = _1_solicitud_id;
    }

    public Date get2_data() {
        return _2_data;
    }

    public void set2_data(Date _2_data) {
        this._2_data = _2_data;
    }

    
    
}
