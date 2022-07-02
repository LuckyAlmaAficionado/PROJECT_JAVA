/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login;

import java.sql.*; //gunakan untuk mengimport library sql

/**
 *
 * @author Devan
 */
public class Koneksi {
   //digunakan untuk menyambungkan java ke sql menggunakan jdbc  
    public static Connection getConnection() {
        Connection Con = null;
        try {
            Con = DriverManager.getConnection("jdbc:mysql://localhost/db_telebot", "root", "");
        } catch (Exception e) {
            System.out.print("Eror: " + e.getMessage());
        }
        return Con;
    }
    public static void main(String[] args) {
        getConnection();
    }
}
