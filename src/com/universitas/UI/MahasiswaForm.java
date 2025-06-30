package com.universitas.UI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.universitas.db.DatabaseConnection;
import com.universitas.models.Mahasiswa;
public class MahasiswaForm extends JFrame{
   
   private JTextField txtNim;
   private JTextField txtNama;
   private JTextField txtJurusan;
   private JButton btnSimpan;
   private JButton btnBersihkan;
   private JButton btnKembali;

   private final MainMenuForm parentForm;

   public MahasiswaForm(MainMenuForm parent){
      this.parentForm = parent;

      setTitle("Form Input Mahasiswa");
      setSize(500, 400);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      addWindowListener(new java.awt.event.WindowAdapter() {
         @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent){
               //kembaliKeMenu();
            }
      });

      initComponent();

   }

   private void initComponent(){
      JPanel panel = new JPanel(new GridBagLayout());
      GridBagConstraints gbc = new GridBagConstraints();

      gbc.insets = new Insets(10, 10, 10, 10);

      gbc.gridx = 0; gbc.gridy = 0;
      panel.add(new JLabel("NIM"), gbc);
      gbc.gridx = 1; gbc.gridy =0;
      txtNim = new JTextField(15);
      panel.add(txtNim,gbc);

      gbc.gridx = 0; gbc.gridy = 1;
      panel.add(new JLabel("Nama"), gbc);
      gbc.gridx = 1; gbc.gridy =1;
      txtNama = new JTextField(15);
      panel.add(txtNama,gbc);

      gbc.gridx = 0; gbc.gridy = 2;
      panel.add(new JLabel("Jurusan"), gbc);
      gbc.gridx = 1; gbc.gridy =2;
      txtJurusan = new JTextField(15);
      panel.add(txtJurusan, gbc);

      JPanel buttonPanel = new JPanel( new FlowLayout());

      btnSimpan = new JButton("Simpan");

      btnSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               simpanMahasiswa();
            }
        });
      
      buttonPanel.add(btnSimpan);
      
      btnBersihkan = new JButton("Bersihkan");

      btnBersihkan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               bersihkanForm();
            }
      });

      buttonPanel.add(btnBersihkan);

      btnKembali = new JButton("Kembali ke Menu Utama");
      btnKembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               kembaliKeMenu();
            }
        });

      buttonPanel.add(btnKembali);

      gbc.gridx = 0; gbc.gridy =3;
      gbc.gridwidth = 2;
      panel.add(buttonPanel, gbc);

      add(panel);
   }

   private void simpanMahasiswa(){
      Mahasiswa mhs = new Mahasiswa("", "", "");
      mhs.setNim(txtNim.getText().trim());
      mhs.setNama(txtNama.getText().trim());
      mhs.setJurusan(txtJurusan.getText().trim());

      if( mhs.getNim().isEmpty() || mhs.getNama().isEmpty() || mhs.getJurusan().isEmpty()){
         JOptionPane.showMessageDialog(this, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
         return;
      }

      String sql = "INSERT INTO MAHASISWA (nim, nama, jurusan) VALUES (?,?,?)";

      try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
               pstmt.setString(1, mhs.getNim());
               pstmt.setString(2, mhs.getNama());
               pstmt.setString(3, mhs.getJurusan());

               int rowsAffected = pstmt.executeUpdate();

               if(rowsAffected > 0){
                  JOptionPane.showMessageDialog(this, "Data mahasiswa berhasil disimpan!","Sukses", JOptionPane.INFORMATION_MESSAGE);

               } else {
                  JOptionPane.showMessageDialog(this, "Gagal menyimpan data mahasiswa!","Error", JOptionPane.ERROR_MESSAGE);
               }
          
      } catch (Exception e) {
         JOptionPane.showMessageDialog(this, "Error databae " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
         e.printStackTrace();
      }
   }

   private void bersihkanForm(){
      txtNim.setText("");
      txtNama.setText("");
      txtJurusan.setText("");

      txtNim.requestFocus();
   }

   private void kembaliKeMenu(){
      this.setVisible(false);
      parentForm.showMainMenu();
   }

   public static void main(String[] args) {
      try {
          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      } catch (Exception e) {
         e.printStackTrace();
      }

      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run(){
            new MahasiswaForm(null).setVisible(true);
         }
      });


   }
   
}
