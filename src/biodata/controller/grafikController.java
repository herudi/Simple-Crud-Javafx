/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biodata.controller;

import biodata.implement.implGrafik;
import biodata.interfaces.interGrafik;
import biodata.model.modelGrafik;
import de.jensd.fx.fontawesome.AwesomeDude;
import de.jensd.fx.fontawesome.AwesomeIcon;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author herudi-pc
 */
public class grafikController implements Initializable {
    @FXML
    private TableView<modelGrafik> tableDetail;
    @FXML
    private TableColumn<modelGrafik, String> colDetailTahun;
    @FXML
    private TableColumn<modelGrafik, String> colDetailJumlah;
    @FXML
    private StackedBarChart bar;
    @FXML
    private NumberAxis barY;
    @FXML
    private CategoryAxis barX;
    @FXML
    private Button btnRefresh;
    ObservableList<Object> dataGrafik = FXCollections.observableArrayList();
    ObservableList<modelGrafik> dataDetail = FXCollections.observableArrayList();
    interGrafik crudGrafik = new implGrafik();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colDetailJumlah.setCellValueFactory(
                (TableColumn.CellDataFeatures<modelGrafik, String> cellData) ->
                        cellData.getValue().jumlahNamaProperty().asString());
        colDetailTahun.setCellValueFactory(
                (TableColumn.CellDataFeatures<modelGrafik, String> cellData) ->
                        cellData.getValue().tahunProperty());
        AwesomeDude.setIcon(btnRefresh, AwesomeIcon.CHAIN_BROKEN, "15px");
        tampilData();
        // TODO
    }  
    
    private void tampilData(){
        dataDetail = crudGrafik.getTahunKelahiran();
        dataGrafik = crudGrafik.tahunKelahiranToGrafik();
        bar.setData(dataGrafik);
        tableDetail.setItems(dataDetail);
    }
    
    @FXML
    private void aksiRefresh(ActionEvent e){
        bar.setAnimated(true);
        barY.setAnimated(true);
        barX.setAnimated(false);
        tampilData();
    }
    
}
