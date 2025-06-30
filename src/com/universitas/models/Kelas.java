package com.universitas.models;

public class Kelas {
   private int idKelas;
   private String namaKelas;
   private int lantai;
   private String gedung;

   public Kelas(int idKelas, String namaKelas, int lantai, String gedung){
      this.idKelas = idKelas;
      this.namaKelas = namaKelas;
      this.lantai = lantai;
      this.gedung = gedung;
   }

   public int getIdKelas() {
      return idKelas;
   }

   public void setIdKelas(int idKelas) {
      this.idKelas = idKelas;
   }

   public String getNamaKelas() {
      return namaKelas;
   }

   public void setNamaKelas(String namaKelas) {
      this.namaKelas = namaKelas;
   }

   public int getLantai() {
      return lantai;
   }

   public void setLantai(int lantai) {
      this.lantai = lantai;
   }

   public String getGedung() {
      return gedung;
   }

   public void setGedung(String gedung) {
      this.gedung = gedung;
   }

   
}
