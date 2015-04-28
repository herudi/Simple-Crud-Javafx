/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biodata.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author herudi-pc
 */
public class modelBiodata {
    private final StringProperty id = new SimpleStringProperty();
    private final StringProperty nama = new SimpleStringProperty();
    private final StringProperty alamat = new SimpleStringProperty();
    private final ObjectProperty<Date> tanggalLahir = new SimpleObjectProperty<>();
    private String formatTanggal;

    public modelBiodata() {
    }
    

    public String getId() {
        return id.get();
    }

    public void setId(String value) {
        id.set(value);
    }

    public StringProperty idProperty() {
        return id;
    }
    
    public String getNama() {
        return nama.get();
    }

    public void setNama(String value) {
        nama.set(value);
    }

    public StringProperty namaProperty() {
        return nama;
    }
    
    public String getAlamat() {
        return alamat.get();
    }

    public void setAlamat(String value) {
        alamat.set(value);
    }

    public StringProperty alamatProperty() {
        return alamat;
    }

    public Date getTanggalLahir() {
        return tanggalLahir.get();
    }

    public void setTanggalLahir(Date value) {
        tanggalLahir.set(value);
    }

    public ObjectProperty tanggalLahirProperty() {
        return tanggalLahir;
    }
    
    public String getFormatTanggal() {
        Date tanggal = getTanggalLahir();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMMM-yyyy");
        String format = df.format(tanggal);
        return format;
    }

    public void setFormatTanggal(String formatTanggal) {
        this.formatTanggal = formatTanggal;
    }

    
    
}
