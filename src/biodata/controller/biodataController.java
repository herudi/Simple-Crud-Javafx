/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biodata.controller;

import biodata.implement.implBiodata;
import biodata.interfaces.interBiodata;
import biodata.model.modelBiodata;
import de.jensd.fx.fontawesome.AwesomeDude;
import de.jensd.fx.fontawesome.AwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author herudi-pc
 */

//controller Biodata
public class biodataController implements Initializable {
    @FXML
    private Tab tabGrafikKelahiran,tabBiodata;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtNama;
    @FXML
    private TextArea txtAlamat;
    @FXML
    private DatePicker dateTanggal;
    @FXML
    private Button btnSimpan;
    @FXML
    private TableView<modelBiodata> tableData;
    @FXML
    private TableColumn<modelBiodata, String> colId;
    @FXML
    private TableColumn<modelBiodata, String> colNama;
    @FXML
    private TableColumn<modelBiodata, String> colAlamat;
    @FXML
    private TableColumn<modelBiodata, String> colTanggal;
    @FXML
    private TableColumn colAction;
    @FXML
    private TextField txtCari;
    @FXML
    private Button btnRefresh;
    @FXML
    private AnchorPane paneLoadGrafik;
    interBiodata crudData = new implBiodata();
    ObservableList<modelBiodata> listData;
    private String StatusKode,statusKlik;
    ObservableList<modelBiodata> listDelete;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colId.setCellValueFactory(
                (TableColumn.CellDataFeatures<modelBiodata, String> cellData) ->
                        cellData.getValue().idProperty());
        colNama.setCellValueFactory(
                (TableColumn.CellDataFeatures<modelBiodata, String> cellData) ->
                        cellData.getValue().namaProperty());
        colAlamat.setCellValueFactory(
                (TableColumn.CellDataFeatures<modelBiodata, String> cellData) ->
                        cellData.getValue().alamatProperty());
        colTanggal.setCellValueFactory(new PropertyValueFactory("formatTanggal"));
        colAction.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Object, Boolean>,
                ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Object, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });
 
        colAction.setCellFactory(new Callback<TableColumn<Object, Boolean>, TableCell<Object, Boolean>>() {
            @Override
            public TableCell<Object, Boolean> call(TableColumn<Object, Boolean> p) {
                return new ButtonCell(tableData);
            }
        });
        listData = FXCollections.observableArrayList();
        AwesomeDude.setIcon(btnSimpan, AwesomeIcon.CHECK_SQUARE, "15px");
        AwesomeDude.setIcon(btnRefresh, AwesomeIcon.CHAIN_BROKEN, "15px");
        dateTanggal.setValue(LocalDate.of(1990, 01, 01));
        StatusKode = "0";
        statusKlik = "0";
        tampilData();
        autoId();
        tableData.getSelectionModel().clearSelection();
        loadGrafik();       
    } 
    
    //load view grafik.fxml
    private void loadGrafik(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent p = fxmlLoader.load(getClass().getResourceAsStream("/biodata/view/grafik.fxml"));
            paneLoadGrafik.getChildren().add(p);   
        } catch (IOException e) {
        }   
    }
    
    private void dialog(Alert.AlertType alertType,String s){
        Alert alert = new Alert(alertType,s);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Info");
        alert.showAndWait();
    }
    
    private void clear(){
        txtId.clear();
        txtNama.clear();
        txtAlamat.clear();
        txtCari.clear();
        dateTanggal.setValue(LocalDate.of(1990, 01, 01));
        StatusKode = "0";
    }
    
    private void tampilData(){
        listData = crudData.getAll();
        tableData.setItems(listData);
    }
    
    private void autoId(){
        modelBiodata m = new modelBiodata();
        crudData.autoId(m);
        txtId.setText(m.getId());
    }

    @FXML
    private void aksiSimpan(ActionEvent event) {
        modelBiodata m = new modelBiodata();
        m.setId(txtId.getText());
        m.setNama(txtNama.getText());
        m.setAlamat(txtAlamat.getText());
        m.setTanggalLahir(Date.valueOf(dateTanggal.getValue()));
        if (StatusKode.equals("0")) {
            crudData.insert(m);
        }else{
            crudData.update(m);
        }
        dialog(Alert.AlertType.INFORMATION, "Data Telah Tersimpan");
        tampilData();
        clear();
        autoId();
        
    }

    @FXML
    private void klikTableData(MouseEvent event) {
        if (statusKlik.equals("1")) {
            StatusKode = "1";
            try {
                modelBiodata klik = tableData.getSelectionModel().getSelectedItems().get(0);
                txtId.setText(klik.getId());
                txtNama.setText(klik.getNama());
                txtAlamat.setText(klik.getAlamat());
                dateTanggal.setValue(LocalDate.parse(klik.getTanggalLahir().toString()));
            } catch (Exception e) {
            }
        }
        
    }

    @FXML
    private void aksiCari(KeyEvent event) {
        listData = crudData.likeByNama(txtCari.getText());
        tableData.setItems(listData);
    }

    @FXML
    private void aksiRefresh(ActionEvent event) {
        clear();
        tampilData();
        autoId();
    }
    
    private class ButtonCell extends TableCell<Object, Boolean> {
        final Hyperlink cellButtonDelete = new Hyperlink("Delete");
        final Hyperlink cellButtonEdit = new Hyperlink("Edit");
        final HBox hb = new HBox(cellButtonDelete,cellButtonEdit);
        ButtonCell(final TableView tblView){
            hb.setSpacing(4);
            
            //cell delete
            cellButtonDelete.setOnAction((ActionEvent t) -> {
                statusKlik = "1";
                int row = getTableRow().getIndex();
                tableData.getSelectionModel().select(row);
                klikTableData(null);
                modelBiodata m = new modelBiodata();
                m.setId(txtId.getText());
                crudData.delete(m);
                tampilData();
                clear();
                autoId();
                dialog(Alert.AlertType.INFORMATION, "Data Berhasil Dihapus");
                statusKlik = "0";
                StatusKode = "0";
            });
            
            //cell edit
            cellButtonEdit.setOnAction((ActionEvent event) -> {
                statusKlik = "1";
                int row = getTableRow().getIndex();
                tableData.getSelectionModel().select(row);
                klikTableData(null);
                statusKlik = "0";
            });
        }

        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
                setGraphic(hb);
            }else{
                setGraphic(null);
            }
        }
    }
    
}
