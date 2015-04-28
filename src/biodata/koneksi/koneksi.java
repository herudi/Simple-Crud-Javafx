/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biodata.koneksi;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author herudi-pc
 */
public class koneksi {
    private Connection con;
    
    public koneksi(){
    }
    
    public Connection connect(){
        if(con == null){
            MysqlDataSource db = new MysqlDataSource();
            db.setDatabaseName("biodata");
            db.setUser("root");
            db.setPassword("qwerty");
            try {
                con = db.getConnection();
            } catch (SQLException e) {
            }
        }
        return con;
    }
    
}
