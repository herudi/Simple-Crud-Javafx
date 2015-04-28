/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biodata.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author herudi-pc
 */
public class modelGrafik {
    private final StringProperty tahun = new SimpleStringProperty();
    private final IntegerProperty jumlahNama = new SimpleIntegerProperty();
    

    public modelGrafik() {
    }

    public String getTahun() {
        return tahun.get();
    }

    public void setTahun(String value) {
        tahun.set(value);
    }

    public StringProperty tahunProperty() {
        return tahun;
    }
    
    public Integer getJumlahNama() {
        return jumlahNama.get();
    }

    public void setJumlahNama(Integer value) {
        jumlahNama.set(value);
    }

    public IntegerProperty jumlahNamaProperty() {
        return jumlahNama;
    }
    
}
