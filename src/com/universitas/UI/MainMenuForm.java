
package com.universitas.UI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;



public class MainMenuForm extends JFrame{

   private static MainMenuForm instance;

   private JButton btnMahasiswa;
   private JButton btnReservasi;
   private JButton btnDataView;
   private JButton btnExit;

   private MainMenuForm(){
      setTitle("Sistem Reservasi ruangan kelas Universitas");
      setSize(500, 400);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      initComponents();
   }

   public static MainMenuForm getInstance(){
      if( instance == null) {
         instance = new MainMenuForm();
      }
      return instance;
   }

   private void initComponents(){
      setLayout(new BorderLayout());

      JPanel headerPanel = new JPanel();
      headerPanel.setBackground(new Color( 70, 130, 180));
      headerPanel.setPreferredSize(new Dimension(500, 80));

      JLabel titleLabel = new JLabel("SISTEM RESERVASI RUANGAN KELAS");
      titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
      titleLabel.setForeground(Color.WHITE);
      add(headerPanel, BorderLayout.NORTH);

      JPanel centerPanel = new JPanel( new GridBagLayout());
      centerPanel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.insets = new Insets(15, 15, 15, 15);
      gbc.fill = GridBagConstraints.HORIZONTAL;

      btnMahasiswa = new JButton("Kelola Data Mahasiswa");
      btnMahasiswa.setPreferredSize(new Dimension(250,50));
      btnMahasiswa.setFont(new Font("Arial", Font.PLAIN, 14));
      btnMahasiswa.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            setVisible(false);
            new MahasiswaForm(MainMenuForm.this).setVisible(true);
         }
      });

      gbc.gridx = 0; gbc.gridy = 0;
      centerPanel.add(btnMahasiswa,gbc);

      btnReservasi = new JButton("Kelola Reservasi Ruangan");
      btnReservasi.setPreferredSize(new Dimension(250,50));
      btnReservasi.setFont(new Font("Arial", Font.PLAIN, 14));
      btnReservasi.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            setVisible(false);
            new ReservasiForm(MainMenuForm.this).setVisible(true);  
         }
      });

      gbc.gridx = 0; gbc.gridy = 1;
      centerPanel.add(btnReservasi,gbc);

      btnDataView = new JButton("Lihat Semua Data");
      btnDataView.setPreferredSize(new Dimension(250,50));
      btnDataView.setFont(new Font("Arial", Font.PLAIN, 14));
      btnDataView.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            setVisible(false);
            new DataViewForm(MainMenuForm.this).setVisible(true);  
         }
      });

      gbc.gridx = 0; gbc.gridy = 2;
      centerPanel.add(btnDataView,gbc);

      btnExit = new JButton("Keluar");
      btnExit.setPreferredSize(new Dimension(250,50));
      btnExit.setFont(new Font("Arial", Font.PLAIN, 14));
      btnExit.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            int option = JOptionPane.showConfirmDialog(
               MainMenuForm.this,
               "Apakah anda yakin ingin keluar dari aplikasi?",
               "Konfirmasi Keluar",
               JOptionPane.YES_NO_OPTION,
               JOptionPane.QUESTION_MESSAGE
            );

            if(option == JOptionPane.YES_OPTION){
               System.exit(0);
            }
         }
      });

      gbc.gridx = 0; gbc.gridy = 3;
      centerPanel.add(btnExit,gbc);

      add(centerPanel, BorderLayout.CENTER);


      JPanel footerPanel = new JPanel();
      footerPanel.setBackground(new Color(248, 249, 250));
      footerPanel.setPreferredSize(( new Dimension(500, 40)));

      JLabel footerLabel = new JLabel("Gedung Informatika");
      btnExit.setFont(new Font("Arial", Font.ITALIC, 12));
      footerPanel.add(footerLabel);

      add(footerPanel, BorderLayout.SOUTH);

   }

   public void showMainMenu(){
      setVisible(true);
      toFront();
      requestFocus();
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
            MainMenuForm.getInstance().setVisible(true);
         }
      });


   }

}
