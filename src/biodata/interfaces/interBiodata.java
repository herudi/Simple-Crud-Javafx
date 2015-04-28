/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biodata.interfaces;

import biodata.model.modelBiodata;
import javafx.collections.ObservableList;

/**
 *
 * @author herudi-pc
 */
public interface interBiodata {
    void insert(modelBiodata m);
    void delete(modelBiodata m);
    void update(modelBiodata m);
    ObservableList<modelBiodata> getAll();
    ObservableList<modelBiodata> likeByNama(String a);
    void autoId(modelBiodata m);
}
