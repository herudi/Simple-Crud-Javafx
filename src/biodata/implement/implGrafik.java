/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biodata.implement;

import biodata.interfaces.interGrafik;
import biodata.koneksi.koneksi;
import biodata.model.modelGrafik;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;


public class implGrafik implements interGrafik {
    koneksi k;

    @Override
    public ObservableList<modelGrafik> getTahunKelahiran() {
        k = new koneksi();
        ObservableList<modelGrafik> listData = FXCollections.observableArrayList();
        try {
            String sql = "select distinct(extract(year from tanggalLahir)) as tahun, "
                        + "count(nama) as jumlahNama "
                        + "from tablebiodata "
                        + "group by tahun "
                        + "order by tahun";
            ResultSet rs = k.connect().createStatement().executeQuery(sql);
            while (rs.next()) {       
                modelGrafik m = new modelGrafik();
                m.setTahun(rs.getString("tahun"));
                m.setJumlahNama(rs.getInt("jumlahNama"));
                listData.add(m);
            }
        } catch (Exception e) {
        }
        return listData;
    }

    @Override
    public ObservableList<Object> tahunKelahiranToGrafik() {
        ObservableList<Object> barCar = FXCollections.observableArrayList();
        try {
            k = new koneksi();
            String sql = "select distinct(extract(year from tanggalLahir)) as tahun, "
                        + "count(nama) as jumlahNama "
                        + "from tablebiodata "
                        + "group by tahun "
                        + "order by tahun";
            ResultSet rs = k.connect().createStatement().executeQuery(sql);
            while (rs.next()) {       
                XYChart.Series<String, Integer> aSeries = new XYChart.Series<>();
                aSeries.getData().add(new XYChart.Data(rs.getString("tahun"), rs.getInt("jumlahNama")));
                barCar.add(aSeries);
            }
        } catch (Exception e) {
        }
        return barCar;
    }
}
