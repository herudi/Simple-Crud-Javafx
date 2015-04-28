/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biodata.interfaces;

import biodata.model.modelGrafik;
import javafx.collections.ObservableList;

/**
 *
 * @author herudi-pc
 */
public interface interGrafik {
    ObservableList<modelGrafik> getTahunKelahiran();
    ObservableList<Object> tahunKelahiranToGrafik();
}
