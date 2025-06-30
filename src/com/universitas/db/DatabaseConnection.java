package com.universitas.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {
   private static final String DB_URL = "jdbc:mysql://localhost:3306/reservasi_ruangan_kelas";
   private static final String DB_USER = "root";
   private static final String DB_PASSWORD = ""; 

   public static Connection getConnection() throws SQLException {
      return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
   }

   public static void main(String[] args) {
      try(Connection connection = DatabaseConnection.getConnection()) {
         System.out.println("Koneksi ke database berhasil");
      } catch (SQLException ex) {
         System.out.println("Koneksi ke database gagal: " + ex.getMessage());
         ex.printStackTrace();
      }
   }
}
