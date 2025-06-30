package com.universitas.UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.universitas.db.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DataViewForm extends JFrame {
    // Komponen GUI
    private JTabbedPane tabbedPane;
    private JTable tblMahasiswa;
    private JTable tblKelas;
    private JTable tblJadwal;
    private JTable tblReservasi;
    private DefaultTableModel modelMahasiswa;
    private DefaultTableModel modelKelas;
    private DefaultTableModel modelJadwal;
    private DefaultTableModel modelReservasi;
    private JButton btnRefreshAll;
    private JButton btnKembali;
    
    // Reference ke MainMenuForm
    private MainMenuForm parentForm;

    // Constructor untuk membuat form tampilan data
    public DataViewForm(MainMenuForm parent) {
        this.parentForm = parent;
        // Mengatur judul window
        setTitle("Data Viewer - Sistem Reservasi Ruangan Kelas");
        // Mengatur ukuran window
        setSize(900, 650);
        // Mengatur posisi window di tengah layar
        setLocationRelativeTo(null);
        // Mengatur operasi default saat window ditutup
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        // Handle window closing event
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                kembaliKeMenu();
            }
        });
        
        // Memanggil method untuk inisialisasi komponen GUI
        initComponents();
        // Memuat semua data ke tabel
        loadAllData();
    }

    // Method untuk inisialisasi komponen GUI
    private void initComponents() {
        // Membuat layout utama
        setLayout(new BorderLayout());
        
        // Membuat tabbed pane untuk menampilkan berbagai tabel
        tabbedPane = new JTabbedPane();
        
        // Tab untuk data mahasiswa
        JPanel panelMahasiswa = createMahasiswaPanel();
        tabbedPane.addTab("Data Mahasiswa", panelMahasiswa);
        
        // Tab untuk data kelas
        JPanel panelKelas = createKelasPanel();
        tabbedPane.addTab("Data Kelas", panelKelas);
        
        // Tab untuk data jadwal
        JPanel panelJadwal = createJadwalPanel();
        tabbedPane.addTab("Data Jadwal", panelJadwal);
        
        // Tab untuk data reservasi
        JPanel panelReservasi = createReservasiPanel();
        tabbedPane.addTab("Data Reservasi", panelReservasi);
        
        // Menambahkan tabbed pane ke frame
        add(tabbedPane, BorderLayout.CENTER);
        
        // Panel untuk tombol refresh dan kembali
        JPanel buttonPanel = new JPanel(new FlowLayout());
        
        btnRefreshAll = new JButton("Refresh Semua Data");
        btnRefreshAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadAllData();
            }
        });
        buttonPanel.add(btnRefreshAll);
        
        btnKembali = new JButton("Kembali ke Menu Utama");
        btnKembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kembaliKeMenu();
            }
        });
        buttonPanel.add(btnKembali);
        
        // Menambahkan panel tombol ke frame
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Method untuk membuat panel data mahasiswa
    private JPanel createMahasiswaPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Membuat model tabel untuk mahasiswa
        String[] columnsMahasiswa = {"NIM", "Nama", "Jurusan"};
        modelMahasiswa = new DefaultTableModel(columnsMahasiswa, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        // Membuat tabel mahasiswa
        tblMahasiswa = new JTable(modelMahasiswa);
        tblMahasiswa.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Membuat scroll pane untuk tabel
        JScrollPane scrollPane = new JScrollPane(tblMahasiswa);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }

    // Method untuk membuat panel data kelas
    private JPanel createKelasPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Membuat model tabel untuk kelas - disesuaikan dengan struktur database
        String[] columnsKelas = {"ID Kelas", "Nama Kelas", "Lantai"};
        modelKelas = new DefaultTableModel(columnsKelas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        // Membuat tabel kelas
        tblKelas = new JTable(modelKelas);
        tblKelas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Membuat scroll pane untuk tabel
        JScrollPane scrollPane = new JScrollPane(tblKelas);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }

    // Method untuk membuat panel data jadwal
    private JPanel createJadwalPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Membuat model tabel untuk jadwal
        String[] columnsJadwal = {"ID Jadwal", "Nama Kelas", "Tanggal", "Jam Mulai", "Jam Selesai"};
        modelJadwal = new DefaultTableModel(columnsJadwal, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        // Membuat tabel jadwal
        tblJadwal = new JTable(modelJadwal);
        tblJadwal.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Membuat scroll pane untuk tabel
        JScrollPane scrollPane = new JScrollPane(tblJadwal);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }

    // Method untuk membuat panel data reservasi
    private JPanel createReservasiPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Membuat model tabel untuk reservasi
        String[] columnsReservasi = {"ID Reservasi", "NIM", "Nama Mahasiswa", "Nama Kelas", "Tanggal", "Jam Mulai", "Jam Selesai", "Keperluan"};
        modelReservasi = new DefaultTableModel(columnsReservasi, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        // Membuat tabel reservasi
        tblReservasi = new JTable(modelReservasi);
        tblReservasi.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Membuat scroll pane untuk tabel
        JScrollPane scrollPane = new JScrollPane(tblReservasi);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }

    // Method untuk memuat semua data
    private void loadAllData() {
        loadMahasiswaData();
        loadKelasData();
        loadJadwalData();
        loadReservasiData();
    }

    // Method untuk memuat data mahasiswa
    private void loadMahasiswaData() {
        String sql = "SELECT nim, nama, jurusan FROM mahasiswa ORDER BY nim";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            // Membersihkan tabel terlebih dahulu
            modelMahasiswa.setRowCount(0);
            
            // Menambahkan data ke tabel
            while (rs.next()) {
                Object[] row = {
                    rs.getString("nim"),
                    rs.getString("nama"),
                    rs.getString("jurusan")
                };
                modelMahasiswa.addRow(row);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading mahasiswa data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // Method untuk memuat data kelas - diperbaiki SQL query
    private void loadKelasData() {
        String sql = "SELECT id_kelas, nama_kelas, lantai FROM kelas ORDER BY lantai, nama_kelas";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            // Membersihkan tabel terlebih dahulu
            modelKelas.setRowCount(0);
            
            // Menambahkan data ke tabel
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("id_kelas"),
                    rs.getString("nama_kelas"),
                    rs.getInt("lantai")
                };
                modelKelas.addRow(row);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading kelas data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // Method untuk memuat data jadwal
    private void loadJadwalData() {
        String sql = "SELECT j.id_jadwal, k.nama_kelas, j.tanggal, j.jam_mulai, j.jam_selesai " +
                     "FROM jadwal j " +
                     "JOIN kelas k ON j.id_kelas = k.id_kelas " +
                     "ORDER BY j.tanggal, j.jam_mulai";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            // Membersihkan tabel terlebih dahulu
            modelJadwal.setRowCount(0);
            
            // Menambahkan data ke tabel
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("id_jadwal"),
                    rs.getString("nama_kelas"),
                    rs.getDate("tanggal"),
                    rs.getTime("jam_mulai"),
                    rs.getTime("jam_selesai")
                };
                modelJadwal.addRow(row);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading jadwal data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // Method untuk memuat data reservasi
    private void loadReservasiData() {
        String sql = "SELECT r.id_reservasi, r.nim, m.nama, k.nama_kelas, j.tanggal, " +
                     "j.jam_mulai, j.jam_selesai, r.keperluan " +
                     "FROM reservasi r " +
                     "JOIN mahasiswa m ON r.nim = m.nim " +
                     "JOIN jadwal j ON r.id_jadwal = j.id_jadwal " +
                     "JOIN kelas k ON j.id_kelas = k.id_kelas " +
                     "ORDER BY j.tanggal, j.jam_mulai";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            // Membersihkan tabel terlebih dahulu
            modelReservasi.setRowCount(0);
            
            // Menambahkan data ke tabel
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("id_reservasi"),
                    rs.getString("nim"),
                    rs.getString("nama"),
                    rs.getString("nama_kelas"),
                    rs.getDate("tanggal"),
                    rs.getTime("jam_mulai"),
                    rs.getTime("jam_selesai"),
                    rs.getString("keperluan")
                };
                modelReservasi.addRow(row);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading reservasi data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // Method untuk kembali ke menu utama
    private void kembaliKeMenu() {
        this.setVisible(false);
        parentForm.showMainMenu();
    }

    // Method main untuk menjalankan aplikasi
    public static void main(String[] args) {
        // Mengatur Look and Feel sesuai dengan sistem operasi
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Menjalankan GUI di Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DataViewForm(null).setVisible(true);
            }
        });
    }
}