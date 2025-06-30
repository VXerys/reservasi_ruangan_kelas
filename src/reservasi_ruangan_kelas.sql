CREATE DATABASE IF NOT EXISTS reservasi_ruangan_kelas;
USE reservasi_ruangan_kelas;

-- Table: kelas
CREATE TABLE IF NOT EXISTS kelas (
    id_kelas INT AUTO_INCREMENT PRIMARY KEY,
    nama_kelas VARCHAR(10) NOT NULL UNIQUE,
    lantai INT NOT NULL,
    gedung VARCHAR(50) NOT NULL
);

-- Table: mahasiswa
CREATE TABLE IF NOT EXISTS mahasiswa (
    nim VARCHAR(15) PRIMARY KEY,
    nama VARCHAR(100) NOT NULL,
    jurusan VARCHAR(50) NOT NULL
);

-- Table: jadwal
CREATE TABLE IF NOT EXISTS jadwal (
    id_jadwal INT AUTO_INCREMENT PRIMARY KEY,
    id_kelas INT NOT NULL,
    tanggal DATE NOT NULL,
    jam_mulai TIME NOT NULL,
    jam_selesai TIME NOT NULL,
    FOREIGN KEY (id_kelas) REFERENCES kelas(id_kelas)
);

-- Table: reservasi
CREATE TABLE IF NOT EXISTS reservasi (
    id_reservasi INT AUTO_INCREMENT PRIMARY KEY,
    nim VARCHAR(15) NOT NULL,
    id_jadwal INT NOT NULL,
    keperluan VARCHAR(255) NOT NULL,
    FOREIGN KEY (nim) REFERENCES mahasiswa(nim),
    FOREIGN KEY (id_jadwal) REFERENCES jadwal(id_jadwal)
);

-- Insert initial data for 'kelas'
INSERT INTO kelas (nama_kelas, lantai, gedung) VALUES
('IF1A', 1, 'Gedung Informatika'),
('IF1B', 1, 'Gedung Informatika'),
('IF1C', 1, 'Gedung Informatika'),
('IF2A', 2, 'Gedung Informatika'),
('IF2B', 2, 'Gedung Informatika'),
('IF2C', 2, 'Gedung Informatika'),
('IF3A', 3, 'Gedung Informatika'),
('IF3B', 3, 'Gedung Informatika'),
('IF3C', 3, 'Gedung Informatika');

-- Insert initial data for 'mahasiswa' (example data)
INSERT INTO mahasiswa (nim, nama, jurusan) VALUES
('2022001', 'Budi Santoso', 'Informatika'),
('2022002', 'Siti Aminah', 'Sistem Informasi'),
('2022003', 'Joko Susilo', 'Teknik Komputer');

-- Insert initial data for 'jadwal' (example data - assuming id_kelas 1 is IF1A, 2 is IF1B, etc.)
-- You might need to adjust id_kelas based on actual auto-increment values after inserting 'kelas' data
INSERT INTO jadwal (id_kelas, tanggal, jam_mulai, jam_selesai) VALUES
(1, '2025-06-20', '09:00:00', '10:00:00'),
(2, '2025-06-20', '10:00:00', '11:00:00'),
(3, '2025-06-21', '13:00:00', '14:00:00');

-- Insert initial data for 'reservasi' (example data)
-- Assuming nim '2022001' and id_jadwal 1
INSERT INTO reservasi (nim, id_jadwal, keperluan) VALUES
('2022001', 1, 'Diskusi Kelompok PBO'),
('2022002', 2, 'Presentasi Tugas Akhir');


