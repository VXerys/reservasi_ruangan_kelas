package com.universitas.models;

public class Reservasi {
   private int idReservasi;
   private String nimMahasiswa;
   private int idJadwal;
   private String keperluan;

   public Reservasi(int idReservasi, String nimMahasiswa, int idJadwal, String keperluan) {
      this.idReservasi = idReservasi;
      this.nimMahasiswa = nimMahasiswa;
      this.idJadwal = idJadwal;
      this.keperluan = keperluan;
   }

   public int getIdReservasi() {
      return idReservasi;
   }

   public void setIdReservasi(int idReservasi) {
      this.idReservasi = idReservasi;
   }

   public String getNimMahasiswa() {
      return nimMahasiswa;
   }

   public void setNimMahasiswa(String nimMahasiswa) {
      this.nimMahasiswa = nimMahasiswa;
   }

   public int getIdJadwal() {
      return idJadwal;
   }

   public void setIdJadwal(int idJadwal) {
      this.idJadwal = idJadwal;
   }

   public String getKeperluan() {
      return keperluan;
   }

   public void setKeperluan(String keperluan) {
      this.keperluan = keperluan;
   }

   
}  
