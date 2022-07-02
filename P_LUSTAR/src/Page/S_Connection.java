/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Page;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Lucky
 */

public class S_Connection {
    public static Connection getConnection(){
        Connection con = null;
        try {
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/db_hotel", "root", "");
//            JOptionPane.showMessageDialog(null, "Koneksi Berhasil");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Koneksi Gagal = " + ex.getMessage());
        }
        return con;
    }
    
    public static void main(String[] args) {
        S_Connection.getConnection();
    }
    
}
