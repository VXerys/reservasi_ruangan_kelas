
package com.universitas.models;

import java.sql.Date;
import java.sql.Time;

public class Jadwal {
   private int idJadwal;
   private int idKelas;
   private Date tanggal;
   private Time jamMulai;
   private Time jamSelesai;


   public Jadwal(int idJadwal, int idKelas, Date tanggal, Time jamMulai, Time jamSelesai){
      this.idJadwal = idJadwal;
      this.idKelas = idKelas;
      this.tanggal = tanggal;
      this.jamMulai = jamMulai;
      this.jamSelesai = jamSelesai;
   }


   public int getIdJadwal() {
      return idJadwal;
   }


   public void setIdJadwal(int idJadwal) {
      this.idJadwal = idJadwal;
   }


   public int getIdKelas() {
      return idKelas;
   }


   public void setIdKelas(int idKelas) {
      this.idKelas = idKelas;
   }


   public Date getTanggal() {
      return tanggal;
   }


   public void setTanggal(Date tanggal) {
      this.tanggal = tanggal;
   }


   public Time getJamMulai() {
      return jamMulai;
   }


   public void setJamMulai(Time jamMulai) {
      this.jamMulai = jamMulai;
   }


   public Time getJamSelesai() {
      return jamSelesai;
   }


   public void setJamSelesai(Time jamSelesai) {
      this.jamSelesai = jamSelesai;
   }

   
}
