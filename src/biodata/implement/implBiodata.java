/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biodata.implement;

import biodata.interfaces.interBiodata;
import biodata.koneksi.koneksi;
import biodata.model.modelBiodata;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class implBiodata implements interBiodata {
    koneksi k;

    @Override
    public void insert(modelBiodata m) {
        k = new koneksi();
        PreparedStatement ps;
        try {
            ps = k.connect().prepareStatement("insert into tablebiodata values(?,?,?,?)");
            ps.setString(1, m.getId());
            ps.setString(2, m.getNama());
            ps.setString(3, m.getAlamat());
            ps.setDate(4, (Date) m.getTanggalLahir());
            ps.execute();
        } catch (Exception e) {
            Logger.getLogger(implBiodata.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void delete(modelBiodata m) {
        k = new koneksi();
        PreparedStatement ps;
        try {
            ps = k.connect().prepareStatement("delete from tablebiodata where id = ?");
            ps.setString(1, m.getId());
            ps.execute();
        } catch (Exception e) {
            Logger.getLogger(implBiodata.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void update(modelBiodata m) {
        k = new koneksi();
        PreparedStatement ps;
        try {
            ps = k.connect().prepareStatement("update tablebiodata set nama=?, alamat=?, tanggalLahir=? where id = ?");
            ps.setString(4, m.getId());
            ps.setString(1, m.getNama());
            ps.setString(2, m.getAlamat());
            ps.setDate(3, (Date) m.getTanggalLahir());
            ps.execute();
        } catch (Exception e) {
            Logger.getLogger(implBiodata.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public ObservableList<modelBiodata> getAll() {
        k = new koneksi();
        ObservableList<modelBiodata> listData = FXCollections.observableArrayList();
        try {
            String sql = "select * from tablebiodata";
            ResultSet rs = k.connect().createStatement().executeQuery(sql);
            while (rs.next()) {   
                modelBiodata m = new modelBiodata();
                m.setId(rs.getString(1));
                m.setNama(rs.getString(2));
                m.setAlamat(rs.getString(3));
                m.setTanggalLahir(rs.getDate(4));
                listData.add(m);
            }
        } catch (Exception ex) {
            Logger.getLogger(implBiodata.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listData;
    }

    @Override
    public ObservableList<modelBiodata> likeByNama(String a) {
        k = new koneksi();
        ObservableList<modelBiodata> listData = FXCollections.observableArrayList();
        try {
            String sql = "select * from tablebiodata where nama like '%"+a+"%'";
            ResultSet rs = k.connect().createStatement().executeQuery(sql);
            while (rs.next()) {   
                modelBiodata m = new modelBiodata();
                m.setId(rs.getString(1));
                m.setNama(rs.getString(2));
                m.setAlamat(rs.getString(3));
                m.setTanggalLahir(rs.getDate(4));
                listData.add(m);
            }
        } catch (Exception ex) {
            Logger.getLogger(implBiodata.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listData;
    }

    @Override
    public void autoId(modelBiodata m) {
        k = new koneksi();
        try {
            ResultSet rs = k.connect().createStatement().executeQuery("select * from tablebiodata");
            while(rs.next()){
                String kode = rs.getString(1).substring(2);
                String auto = ""+(Integer.parseInt(kode)+1);
                String nol = "";
                if (auto.length()==1) {
                    nol = "00";
                }else if (auto.length()==2) {
                    nol = "0";
                }else if (auto.length()==3) {
                    nol = "";
                }
                m.setId("B."+nol+auto);
            }
            if (m.getId()==null) {
                m.setId("B.001");
            }
        } catch (SQLException ex) {
            Logger.getLogger(implBiodata.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
}
