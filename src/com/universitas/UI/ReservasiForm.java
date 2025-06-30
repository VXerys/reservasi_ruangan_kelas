
package com.universitas.UI;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.universitas.db.DatabaseConnection;
import com.universitas.models.Reservasi;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservasiForm extends JFrame{

    private JComboBox<String> cmbKelas;
    private JTextField txtNim;
    private JTextField txtTanggal;
    private JTextField txtJamMulai;
    private JTextField txtJamSelesai;
    private JTextField txtKeperluan;
    private JButton btnSimpan;
    private JButton btnBersihkan;
    private JButton btnRefresh;
    private JButton btnKembali;
    private JTable tblJadwal;
    private DefaultTableModel tableModel;
    
    private final MainMenuForm parentForm;
    
    private final List<Reservasi> reservasiList;

    public ReservasiForm(MainMenuForm parent) {
        this.parentForm = parent;
        this.reservasiList = new ArrayList<>();
        
        setTitle("Sistem Reservasi Ruangan Kelas");
        setSize(800, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                kembaliKeMenu();
            }
        });
        
        initComponents();
        loadKelasData();
        loadJadwalDataWithModel();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        
        JPanel inputPanel = createInputPanel();
        add(inputPanel, BorderLayout.NORTH);
        
        JPanel tablePanel = createTablePanel();
        add(tablePanel, BorderLayout.CENTER);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Form Reservasi"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Kelas:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 0;
        cmbKelas = new JComboBox<>();
        cmbKelas.setPreferredSize(new Dimension(150, 25));
        panel.add(cmbKelas, gbc);
        
        gbc.gridx = 2; gbc.gridy = 0;
        panel.add(new JLabel("NIM:"), gbc);
        
        gbc.gridx = 3; gbc.gridy = 0;
        txtNim = new JTextField(10);
        panel.add(txtNim, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Tanggal (YYYY-MM-DD):"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 1;
        txtTanggal = new JTextField(10);
        panel.add(txtTanggal, gbc);
        
        gbc.gridx = 2; gbc.gridy = 1;
        panel.add(new JLabel("Jam Mulai (HH:MM):"), gbc);
        
        gbc.gridx = 3; gbc.gridy = 1;
        txtJamMulai = new JTextField(10);
        panel.add(txtJamMulai, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Jam Selesai (HH:MM):"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 2;
        txtJamSelesai = new JTextField(10);
        panel.add(txtJamSelesai, gbc);
        
        gbc.gridx = 2; gbc.gridy = 2;
        panel.add(new JLabel("Keperluan:"), gbc);
        
        gbc.gridx = 3; gbc.gridy = 2;
        txtKeperluan = new JTextField(15);
        panel.add(txtKeperluan, gbc);
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        
        btnSimpan = new JButton("Simpan Reservasi");
        btnSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simpanReservasiWithModel();
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
        
        btnRefresh = new JButton("Refresh Data");
        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadJadwalDataWithModel();
            }
        });
        buttonPanel.add(btnRefresh);
        
        btnKembali = new JButton("Kembali ke Menu Utama");
        btnKembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kembaliKeMenu();
            }
        });
        buttonPanel.add(btnKembali);
        
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 4;
        panel.add(buttonPanel, gbc);
        
        return panel;
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Data Jadwal dan Reservasi"));
        
        String[] columnNames = {"ID Jadwal", "Kelas", "Tanggal", "Jam Mulai", "Jam Selesai", "Status", "NIM", "Nama Mahasiswa", "Keperluan"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tblJadwal = new JTable(tableModel);
        tblJadwal.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane scrollPane = new JScrollPane(tblJadwal);
        scrollPane.setPreferredSize(new Dimension(750, 300));
        
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }

    private void loadKelasData() {
        String sql = "SELECT id_kelas, nama_kelas FROM kelas ORDER BY lantai, nama_kelas";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            cmbKelas.removeAllItems();
            cmbKelas.addItem("-- Pilih Kelas --");
            
            while (rs.next()) {
                String item = rs.getInt("id_kelas") + " - " + rs.getString("nama_kelas");
                cmbKelas.addItem(item);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading kelas data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void loadJadwalDataWithModel() {
        String sql = "SELECT j.id_jadwal, k.nama_kelas, j.tanggal, j.jam_mulai, j.jam_selesai, " +
                     "r.id_reservasi, r.nim, m.nama, r.keperluan " +
                     "FROM jadwal j " +
                     "JOIN kelas k ON j.id_kelas = k.id_kelas " +
                     "LEFT JOIN reservasi r ON j.id_jadwal = r.id_jadwal " +
                     "LEFT JOIN mahasiswa m ON r.nim = m.nim " +
                     "ORDER BY j.tanggal, j.jam_mulai";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            tableModel.setRowCount(0);
            reservasiList.clear();
            
            while (rs.next()) {
                Object[] row = new Object[9];
                row[0] = rs.getInt("id_jadwal");
                row[1] = rs.getString("nama_kelas");
                row[2] = rs.getDate("tanggal");
                row[3] = rs.getTime("jam_mulai");
                row[4] = rs.getTime("jam_selesai");
                
                String nim = rs.getString("nim");
                if (nim != null && !nim.isEmpty()) {
                    int idReservasi = rs.getInt("id_reservasi");
                    int idJadwal = rs.getInt("id_jadwal");
                    String keperluan = rs.getString("keperluan");
                    
                    Reservasi reservasi = new Reservasi(idReservasi, nim, idJadwal, keperluan);
                    reservasiList.add(reservasi);
                    
                    row[5] = "Direservasi";
                    row[6] = reservasi.getNimMahasiswa();
                    row[7] = rs.getString("nama");
                    row[8] = reservasi.getKeperluan();
                } else {
                    row[5] = "Tersedia";
                    row[6] = "-";
                    row[7] = "-";
                    row[8] = "-";
                }
                
                tableModel.addRow(row);
            }
            
            System.out.println("Total reservasi yang dimuat menggunakan model: " + reservasiList.size());
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading jadwal data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void simpanReservasiWithModel() {
        if (cmbKelas.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Pilih kelas terlebih dahulu!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String nim = txtNim.getText().trim();
        String tanggal = txtTanggal.getText().trim();
        String jamMulai = txtJamMulai.getText().trim();
        String jamSelesai = txtJamSelesai.getText().trim();
        String keperluan = txtKeperluan.getText().trim();
        
        if (nim.isEmpty() || tanggal.isEmpty() || jamMulai.isEmpty() || jamSelesai.isEmpty() || keperluan.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String selectedKelas = (String) cmbKelas.getSelectedItem();
        int idKelas = Integer.parseInt(selectedKelas.split(" - ")[0]);
        
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);
            
            if (!isMahasiswaExists(conn, nim)) {
                JOptionPane.showMessageDialog(this, "NIM mahasiswa tidak ditemukan! Silakan daftarkan mahasiswa terlebih dahulu.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String sqlJadwal = "INSERT INTO jadwal (id_kelas, tanggal, jam_mulai, jam_selesai) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmtJadwal = conn.prepareStatement(sqlJadwal, Statement.RETURN_GENERATED_KEYS);
            pstmtJadwal.setInt(1, idKelas);
            pstmtJadwal.setDate(2, Date.valueOf(tanggal));
            pstmtJadwal.setTime(3, Time.valueOf(jamMulai + ":00"));
            pstmtJadwal.setTime(4, Time.valueOf(jamSelesai + ":00"));
            
            int rowsAffected = pstmtJadwal.executeUpdate();
            
            if (rowsAffected > 0) {
                ResultSet generatedKeys = pstmtJadwal.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int idJadwal = generatedKeys.getInt(1);
                    
                    Reservasi reservasiBaru = new Reservasi(0, nim, idJadwal, keperluan);
                    
                    String sqlReservasi = "INSERT INTO reservasi (nim, id_jadwal, keperluan) VALUES (?, ?, ?)";
                    PreparedStatement pstmtReservasi = conn.prepareStatement(sqlReservasi, Statement.RETURN_GENERATED_KEYS);
                    pstmtReservasi.setString(1, reservasiBaru.getNimMahasiswa());
                    pstmtReservasi.setInt(2, reservasiBaru.getIdJadwal());
                    pstmtReservasi.setString(3, reservasiBaru.getKeperluan());
                    
                    int reservasiRows = pstmtReservasi.executeUpdate();
                    
                    if (reservasiRows > 0) {
                        ResultSet reservasiKeys = pstmtReservasi.getGeneratedKeys();
                        if (reservasiKeys.next()) {
                            int idReservasi = reservasiKeys.getInt(1);
                            reservasiBaru.setIdReservasi(idReservasi);
                            reservasiList.add(reservasiBaru);
                        }
                        reservasiKeys.close();
                        
                        conn.commit();
                        JOptionPane.showMessageDialog(this, "Reservasi berhasil disimpan menggunakan model!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                        bersihkanForm();
                        loadJadwalDataWithModel();
                        
                        System.out.println("Reservasi tersimpan - ID: " + reservasiBaru.getIdReservasi() + 
                                         ", NIM: " + reservasiBaru.getNimMahasiswa() + 
                                         ", Keperluan: " + reservasiBaru.getKeperluan());
                    } else {
                        conn.rollback();
                        JOptionPane.showMessageDialog(this, "Gagal menyimpan reservasi!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    
                    pstmtReservasi.close();
                }
                generatedKeys.close();
            } else {
                conn.rollback();
                JOptionPane.showMessageDialog(this, "Gagal menyimpan jadwal!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            pstmtJadwal.close();
            
        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Error database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isMahasiswaExists(Connection conn, String nim) throws SQLException {
        String sql = "SELECT COUNT(*) FROM mahasiswa WHERE nim = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, nim);
        ResultSet rs = pstmt.executeQuery();
        
        boolean exists = false;
        if (rs.next()) {
            exists = rs.getInt(1) > 0;
        }
        
        rs.close();
        pstmt.close();
        return exists;
    }

    private void bersihkanForm() {
        cmbKelas.setSelectedIndex(0);
        txtNim.setText("");
        txtTanggal.setText("");
        txtJamMulai.setText("");
        txtJamSelesai.setText("");
        txtKeperluan.setText("");
        txtNim.requestFocus();
    }
    
    private void kembaliKeMenu() {
        this.setVisible(false);
        parentForm.showMainMenu();
    }
    
    public List<Reservasi> getReservasiList() {
        return new ArrayList<>(reservasiList);
    }
    
    public List<Reservasi> getReservasiByNim(String nim) {
        List<Reservasi> result = new ArrayList<>();
        for (Reservasi reservasi : reservasiList) {
            if (reservasi.getNimMahasiswa().equals(nim)) {
                result.add(reservasi);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ReservasiForm(null).setVisible(true);
            }
        });
    }
}