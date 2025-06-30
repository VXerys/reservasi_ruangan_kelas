# 🏫 Sistem Reservasi Ruangan Kelas Universitas

Sebuah aplikasi desktop berbasis Java untuk mengelola reservasi ruangan kelas di lingkungan universitas, dikembangkan sebagai proyek akhir mata kuliah Pemrograman Berorientasi Objek (PBO).

## 📚 Daftar Isi

- [📖 Latar Belakang Masalah](#-latar-belakang-masalah)
- [🛠️ Metode Pengembangan Aplikasi](#%EF%B8%8F-metode-pengembangan-aplikasi)
- [🖥️ Hasil Implementasi](#%EF%B8%8F-hasil-implementasi)
- [📊 Kesimpulan dan Evaluasi](#-kesimpulan-dan-evaluasi)
- [📦 Fitur Aplikasi](#-fitur-aplikasi)
- [🧱 Struktur Database](#-struktur-database)
- [⚙️ Teknologi dan Tools yang Digunakan](#%EF%B8%8F-teknologi-dan-tools-yang-digunakan)
- [🏗️ Konsep OOP yang Diterapkan](#%EF%B8%8F-konsep-oop-yang-diterapkan)
- [📹 Dokumentasi Video](#-dokumentasi-video)

---

## 📖 Latar Belakang Masalah

Proses reservasi ruangan kelas di lingkungan universitas seringkali masih dilakukan secara manual, baik melalui pencatatan fisik maupun komunikasi langsung antar staf dan mahasiswa. Pendekatan ini rentan terhadap berbagai masalah seperti tabrakan jadwal, informasi yang tidak akurat, kesulitan dalam melacak ketersediaan ruangan, serta memakan waktu dan sumber daya yang tidak efisien. Mahasiswa kesulitan mengetahui ruangan mana yang kosong pada waktu tertentu, dan staf administrasi kewalahan dalam mengelola permintaan reservasi yang masuk secara bersamaan. 

Untuk mengatasi tantangan ini, diperlukan sebuah solusi digital yang terintegrasi dan efisien. Aplikasi desktop berbasis Java ini hadir sebagai jawaban untuk memodernisasi sistem reservasi ruangan kelas, menyediakan platform yang intuitif bagi mahasiswa untuk melakukan reservasi dan bagi staf untuk memantau penggunaan ruangan secara real-time. Dengan demikian, diharapkan efisiensi operasional dapat meningkat, kesalahan dapat diminimalisir, dan ketersediaan ruangan dapat dimanfaatkan secara optimal.

[🔝 Kembali ke atas](#📚-daftar-isi)

---

## 🛠️ Metode Pengembangan Aplikasi

Aplikasi **Sistem Reservasi Ruangan Kelas Universitas** ini dikembangkan dengan pendekatan modular dan berorientasi objek, memastikan kode yang bersih, mudah dipelihara, dan dapat diperluas. Berikut adalah rincian metode dan teknologi yang digunakan:

### Teknologi Inti
- **Java SE**: Digunakan sebagai bahasa pemrograman utama untuk membangun logika bisnis dan antarmuka pengguna.
- **Java Swing**: Dipilih untuk pengembangan Graphical User Interface (GUI) aplikasi desktop. Swing memungkinkan pembuatan antarmuka yang kaya fitur dan interaktif tanpa ketergantungan pada GUI Builder eksternal (seperti NetBeans GUI Builder), sehingga memberikan kontrol penuh atas kode UI.
- **MySQL**: Berfungsi sebagai sistem manajemen database relasional (RDBMS) untuk menyimpan semua data terkait kelas, mahasiswa, jadwal, dan reservasi. MySQL dipilih karena performanya yang handal dan kompatibilitas yang luas.
- **JDBC (Java Database Connectivity)**: Merupakan API standar Java yang digunakan untuk menghubungkan aplikasi Java dengan database MySQL. JDBC memungkinkan eksekusi query SQL dan pengelolaan transaksi database secara efisien.
- **XAMPP**: Digunakan sebagai lingkungan pengembangan lokal yang menyediakan Apache, MySQL, PHP, dan Perl dalam satu paket. XAMPP mempermudah setup server database MySQL untuk keperluan pengembangan dan pengujian.
- **Visual Studio Code (VS Code)**: Digunakan sebagai Integrated Development Environment (IDE) utama. VS Code menyediakan fitur-fitur yang mendukung pengembangan Java, termasuk debugging, autocompletion, dan integrasi terminal.

### Struktur Folder Proyek
Struktur folder proyek dirancang untuk memisahkan logika aplikasi, model data, dan dependensi eksternal, mengikuti praktik terbaik dalam pengembangan Java:

```
project-folder/
├── src/
│   └── com/
│       └── universitas/
│           └── reservasi/
│               ├── DatabaseConnection.java
│               ├── Kelas.java
│               ├── Mahasiswa.java
│               ├── Jadwal.java
│               ├── Reservasi.java
│               ├── MainMenuForm.java
│               ├── MahasiswaForm.java
│               ├── ReservasiForm.java
│               └── DataViewForm.java
├── lib/
│   └── mysql-connector-j-9.3.0.jar  (atau versi terbaru)
├── README.md
├── reservasi_ruangan_kelas.sql
├── PANDUAN_LENGKAP.md
└── URUTAN_PENGERJAAN.md
```

- `src/`: Berisi semua kode sumber Java.
- `com.universitas.reservasi/`: Paket utama aplikasi yang mengelompokkan semua kelas Java.
- `lib/`: Direktori untuk menyimpan library eksternal, seperti MySQL Connector/J JAR file.
- `README.md`: File dokumentasi proyek ini.
- `reservasi_ruangan_kelas.sql`: Script SQL untuk membuat database dan mengisi data awal.
- `PANDUAN_LENGKAP.md`: Panduan komprehensif untuk instalasi, penggunaan, dan troubleshooting.
- `URUTAN_PENGERJAAN.md`: Panduan step-by-step proses pengembangan.

### Pendekatan Modular OOP
Setiap entitas utama dalam sistem (Kelas, Mahasiswa, Jadwal, Reservasi) direpresentasikan sebagai kelas model terpisah, yang mengimplementasikan prinsip Encapsulation dengan atribut private dan metode getter/setter. Logika koneksi database diisolasi dalam kelas `DatabaseConnection`, memungkinkan reusabilitas dan pemeliharaan yang mudah. Antarmuka pengguna (GUI) juga dipecah menjadi beberapa kelas `JFrame` terpisah (`MainMenuForm`, `MahasiswaForm`, `ReservasiForm`, `DataViewForm`), yang masing-masing bertanggung jawab atas fungsionalitas spesifik, sehingga meningkatkan modularitas dan keterbacaan kode.

[🔝 Kembali ke atas](#📚-daftar-isi)

---

## 🖥️ Hasil Implementasi

Aplikasi **Sistem Reservasi Ruangan Kelas Universitas** telah berhasil diimplementasikan dengan fitur-fitur inti yang mendukung pengelolaan reservasi ruangan secara digital. Berikut adalah deskripsi fitur yang telah dibangun:

### Antarmuka Pengguna (GUI)
Antarmuka pengguna dibangun menggunakan Java Swing, dirancang agar sederhana dan intuitif, memungkinkan pengguna untuk berinteraksi dengan sistem secara efisien. Setiap form memiliki layout yang jelas dengan komponen standar seperti `JTextField`, `JButton`, `JComboBox`, dan `JTable`.

- **Main Menu (`MainMenuForm`)**:
  Menyediakan navigasi utama ke berbagai modul aplikasi (Manajemen Mahasiswa, Reservasi Ruangan, Tampilan Data).
  ![Screenshot Main Menu](placeholder_main_menu.png)
  *(Placeholder: Tambahkan screenshot Main Menu di sini)*

- **Form Input Mahasiswa (`MahasiswaForm`)**:
  Memungkinkan staf atau mahasiswa untuk memasukkan data mahasiswa baru (NIM, Nama, Jurusan) ke dalam database.
  ![Screenshot Form Mahasiswa](placeholder_mahasiswa_form.png)
  *(Placeholder: Tambahkan screenshot Form Input Mahasiswa di sini)*

- **Form Reservasi Ruangan (`ReservasiForm`)**:
  Merupakan modul utama untuk melakukan reservasi. Pengguna dapat memilih kelas, memasukkan NIM, tanggal, jam mulai dan selesai, serta keperluan reservasi. Form ini juga menampilkan jadwal ruangan yang tersedia dan yang sudah direservasi dalam bentuk tabel.
  ![Screenshot Form Reservasi](placeholder_reservasi_form.png)
  *(Placeholder: Tambahkan screenshot Form Reservasi di sini)*

- **Tampilan Data (`DataViewForm`)**:
  Menyajikan semua data yang tersimpan dalam database melalui tabbed pane, memisahkan tampilan data mahasiswa, kelas, jadwal, dan reservasi untuk kemudahan monitoring.
  ![Screenshot Data View](placeholder_data_view.png)
  *(Placeholder: Tambahkan screenshot Tampilan Data di sini)*

### Struktur Database
Database dirancang dengan 4 tabel utama yang saling berelasi untuk memastikan integritas data dan efisiensi query. Relasi antar tabel mencerminkan keterkaitan logis antara entitas dalam sistem reservasi.

- **Diagram ERD atau Struktur Tabel**:
  ![Struktur Database](placeholder_database_erd.png)
  *(Placeholder: Tambahkan gambar ERD atau diagram struktur tabel database di sini)*

[🔝 Kembali ke atas](#📚-daftar-isi)

---

## 📊 Kesimpulan dan Evaluasi

Proyek **Sistem Reservasi Ruangan Kelas Universitas** ini berhasil mengimplementasikan sebuah aplikasi desktop fungsional yang memenuhi kebutuhan dasar pengelolaan reservasi ruangan. Keberhasilan utama proyek ini terletak pada integrasi yang mulus antara antarmuka pengguna Java Swing dengan database MySQL melalui JDBC, serta penerapan prinsip-prinsip Pemrograman Berorientasi Objek (OOP) secara konsisten.

### Evaluasi Implementasi OOP
- **Encapsulation**: Diterapkan dengan baik pada semua kelas model (`Kelas`, `Mahasiswa`, `Jadwal`, `Reservasi`) melalui penggunaan atribut `private` dan metode `public` getter/setter. Ini memastikan data terlindungi dan diakses melalui antarmuka yang terkontrol.
- **Abstraction**: Terlihat dari pemisahan tanggung jawab antara lapisan GUI, logika bisnis, dan akses database. Kelas `DatabaseConnection` mengabstraksi detail koneksi database, memungkinkan komponen lain berinterinteraksi tanpa perlu mengetahui implementasi internalnya.
- **Modularity**: Aplikasi dibagi menjadi modul-modul yang lebih kecil dan independen (misalnya, setiap form GUI adalah kelas terpisah, setiap entitas data memiliki kelas modelnya sendiri). Ini mempermudah pengembangan, pengujian, dan pemeliharaan.
- **Code Reusability**: Contoh paling jelas adalah kelas `DatabaseConnection` yang digunakan di seluruh aplikasi untuk mendapatkan koneksi database, menghindari duplikasi kode dan memastikan konsistensi koneksi.
- **Efisiensi Program**: Aplikasi berjalan dengan responsif untuk operasi dasar seperti insert dan read data. Penggunaan `PreparedStatement` membantu mencegah SQL Injection dan meningkatkan performa query.

### Potensi Pengembangan ke Depan
Meski fungsional, aplikasi ini memiliki banyak ruang untuk pengembangan lebih lanjut guna meningkatkan fungsionalitas, keamanan, dan pengalaman pengguna:

1.  **Sistem Autentikasi Pengguna**: Implementasi fitur login untuk membedakan hak akses antara mahasiswa, staf, dan administrator.
2.  **Manajemen Data Lengkap (CRUD)**: Penambahan fungsionalitas *Update* dan *Delete* untuk semua entitas (Mahasiswa, Kelas, Jadwal, Reservasi).
3.  **Validasi Input Lanjutan**: Peningkatan validasi pada input pengguna, seperti format tanggal/waktu yang lebih ketat, pengecekan ketersediaan ruangan sebelum reservasi, dan penanganan error yang lebih *graceful*.
4.  **Fitur Pencarian dan Filter**: Penambahan kemampuan pencarian dan filter pada tabel data untuk mempermudah navigasi data dalam jumlah besar.
5.  **Laporan dan Ekspor Data**: Implementasi fitur untuk menghasilkan laporan reservasi dalam format PDF atau Excel.
6.  **Notifikasi Konflik Jadwal**: Sistem peringatan otomatis jika ada upaya reservasi yang bertabrakan dengan jadwal yang sudah ada.
7.  **Integrasi Kalender**: Penggunaan komponen kalender (misalnya JCalendar) untuk pemilihan tanggal yang lebih user-friendly.
8.  **Tampilan Jadwal Grafis**: Visualisasi jadwal ruangan dalam bentuk kalender atau Gantt chart untuk overview yang lebih baik.

Secara keseluruhan, proyek ini berhasil menunjukkan pemahaman yang kuat tentang konsep PBO dan kemampuan untuk membangun aplikasi desktop yang terintegrasi dengan database, menjadikannya fondasi yang kokoh untuk pengembangan sistem yang lebih kompleks di masa mendatang.

[🔝 Kembali ke atas](#📚-daftar-isi)

---

## 📦 Fitur Aplikasi

Aplikasi **Sistem Reservasi Ruangan Kelas Universitas** menyediakan fitur-fitur utama berikut:

-   **Manajemen Data Mahasiswa**: Memungkinkan penambahan data mahasiswa baru (NIM, Nama, Jurusan) ke dalam sistem.
-   **Reservasi Ruangan Kelas**: Mahasiswa dapat melakukan reservasi ruangan kelas untuk keperluan tertentu pada tanggal dan waktu yang spesifik.
-   **Pilihan Kelas Dinamis**: Daftar kelas yang tersedia dimuat secara dinamis dari database ke dalam `JComboBox`.
-   **Input Tanggal dan Waktu**: Pengguna dapat memasukkan tanggal reservasi serta jam mulai dan jam selesai.
-   **Pencatatan Keperluan Reservasi**: Detail keperluan penggunaan ruangan dapat dicatat saat melakukan reservasi.
-   **Tampilan Jadwal dan Reservasi**: Menampilkan daftar jadwal ruangan, termasuk status ketersediaan (Tersedia/Direservasi) dan detail reservasi yang sudah ada, dalam format `JTable`.
-   **Tampilan Data Komprehensif**: Menyediakan tampilan terpisah untuk semua data (Mahasiswa, Kelas, Jadwal, Reservasi) menggunakan `JTabbedPane`.
-   **Navigasi GUI Intuitif**: Antarmuka pengguna yang sederhana dan mudah digunakan dengan tombol navigasi yang jelas.
-   **Koneksi Database JDBC**: Aplikasi terhubung langsung ke database MySQL menggunakan JDBC untuk operasi data.
-   **Pembersihan Form Otomatis**: Form input akan dibersihkan setelah data berhasil disimpan untuk kemudahan input berikutnya.
-   **Refresh Data**: Tombol refresh untuk memperbarui tampilan data pada tabel setelah ada perubahan.

[🔝 Kembali ke atas](#📚-daftar-isi)

---

## 🧱 Struktur Database

Sistem ini menggunakan database MySQL dengan empat tabel utama yang saling berelasi untuk menyimpan dan mengelola semua informasi terkait reservasi ruangan kelas. Berikut adalah detail struktur masing-masing tabel dan relasinya:

### 1. `kelas`
Menyimpan informasi dasar mengenai ruangan kelas yang tersedia.
-   `id_kelas` (INT, Primary Key, AUTO_INCREMENT)
-   `nama_kelas` (VARCHAR(10), NOT NULL, UNIQUE) - Contoh: IF1A, IF2B
-   `lantai` (INT, NOT NULL) - Lantai ruangan kelas
-   `gedung` (VARCHAR(50), NOT NULL) - Nama gedung tempat kelas berada

### 2. `mahasiswa`
Menyimpan data mahasiswa yang akan melakukan reservasi.
-   `nim` (VARCHAR(15), Primary Key) - Nomor Induk Mahasiswa
-   `nama` (VARCHAR(100), NOT NULL) - Nama lengkap mahasiswa
-   `jurusan` (VARCHAR(50), NOT NULL) - Jurusan mahasiswa

### 3. `jadwal`
Menyimpan detail jadwal penggunaan ruangan, yang bisa berupa jadwal mata kuliah atau jadwal yang dibuat melalui reservasi.
-   `id_jadwal` (INT, Primary Key, AUTO_INCREMENT)
-   `id_kelas` (INT, Foreign Key) - Merujuk ke `id_kelas` di tabel `kelas`
-   `tanggal` (DATE, NOT NULL) - Tanggal penggunaan ruangan
-   `jam_mulai` (TIME, NOT NULL) - Waktu mulai penggunaan
-   `jam_selesai` (TIME, NOT NULL) - Waktu selesai penggunaan

### 4. `reservasi`
Menyimpan detail reservasi yang dilakukan oleh mahasiswa.
-   `id_reservasi` (INT, Primary Key, AUTO_INCREMENT)
-   `nim` (VARCHAR(15), Foreign Key) - Merujuk ke `nim` di tabel `mahasiswa`
-   `id_jadwal` (INT, Foreign Key) - Merujuk ke `id_jadwal` di tabel `jadwal`
-   `keperluan` (VARCHAR(255), NOT NULL) - Deskripsi keperluan reservasi

### Relasi Antar Tabel
-   `jadwal.id_kelas` ➡️ `kelas.id_kelas` (One-to-Many: Satu kelas dapat memiliki banyak jadwal)
-   `reservasi.nim` ➡️ `mahasiswa.nim` (Many-to-One: Banyak reservasi dapat dilakukan oleh satu mahasiswa)
-   `reservasi.id_jadwal` ➡️ `jadwal.id_jadwal` (Many-to-One: Banyak reservasi dapat merujuk ke satu jadwal, namun dalam konteks ini, satu jadwal idealnya hanya untuk satu reservasi pada waktu yang sama. Implementasi memastikan jadwal baru dibuat untuk setiap reservasi unik.)

[🔝 Kembali ke atas](#📚-daftar-isi)

---

## ⚙️ Teknologi dan Tools yang Digunakan

Berikut adalah daftar lengkap teknologi dan tools yang digunakan dalam pengembangan proyek ini:

-   **Bahasa Pemrograman**: Java SE Development Kit (JDK)
-   **Antarmuka Pengguna (GUI)**: Java Swing (bagian dari Java SE)
-   **Sistem Manajemen Database**: MySQL
-   **Konektivitas Database**: JDBC (Java Database Connectivity) API
-   **Driver JDBC**: MySQL Connector/J (versi 9.3.0 atau terbaru)
-   **Lingkungan Pengembangan Lokal**: XAMPP (untuk MySQL server dan phpMyAdmin)
-   **Integrated Development Environment (IDE)**: Visual Studio Code
-   **Sistem Operasi Pengembangan**: Platform Independent (diuji pada lingkungan Linux/Windows)

[🔝 Kembali ke atas](#📚-daftar-isi)

---

## 🏗️ Konsep OOP yang Diterapkan

Proyek ini secara fundamental dibangun di atas prinsip-prinsip Pemrograman Berorientasi Objek (OOP) untuk memastikan struktur kode yang terorganisir, mudah dipelihara, dan dapat diperluas. Berikut adalah konsep-konsep OOP utama yang diterapkan:

1.  **Encapsulation (Enkapsulasi)**
    -   **Penerapan**: Semua kelas model (`Kelas.java`, `Mahasiswa.java`, `Jadwal.java`, `Reservasi.java`) memiliki atribut (variabel instance) yang dideklarasikan sebagai `private`. Akses dan modifikasi terhadap atribut ini hanya dapat dilakukan melalui metode `public` getter (pengambil nilai) dan setter (pengatur nilai).
    -   **Manfaat**: Melindungi integritas data dengan mencegah akses langsung dan modifikasi yang tidak sah dari luar kelas. Ini juga memungkinkan perubahan internal pada implementasi kelas tanpa memengaruhi kode yang menggunakannya.

2.  **Abstraction (Abstraksi)**
    -   **Penerapan**: Detail implementasi yang kompleks disembunyikan dari pengguna kelas. Contoh paling jelas adalah kelas `DatabaseConnection.java`. Kelas ini menyediakan metode `getConnection()` yang mengembalikan objek `Connection` tanpa mengekspos detail tentang bagaimana koneksi tersebut dibuat (misalnya, driver JDBC yang digunakan, URL database, username, password).
    -   **Manfaat**: Menyederhanakan penggunaan API bagi pengembang lain. Pengguna kelas `DatabaseConnection` hanya perlu tahu cara mendapatkan koneksi, bukan bagaimana koneksi itu dibangun, sehingga mengurangi kompleksitas dan fokus pada fungsionalitas inti.

3.  **Modularity (Modularitas)**
    -   **Penerapan**: Aplikasi dipecah menjadi unit-unit fungsional yang lebih kecil dan independen, yang masing-masing diimplementasikan sebagai kelas terpisah. Misalnya, ada kelas khusus untuk setiap entitas data (`Kelas`, `Mahasiswa`), kelas untuk koneksi database (`DatabaseConnection`), dan kelas terpisah untuk setiap bagian antarmuka pengguna (`MainMenuForm`, `MahasiswaForm`, `ReservasiForm`, `DataViewForm`).
    -   **Manfaat**: Mempermudah pengembangan secara paralel, pengujian, dan *debugging*. Setiap modul dapat dikembangkan dan diuji secara terpisah sebelum diintegrasikan ke dalam sistem yang lebih besar. Ini juga meningkatkan keterbacaan kode dan pemeliharaan jangka panjang.

4.  **Code Reusability (Reusabilitas Kode)**
    -   **Penerapan**: Kelas `DatabaseConnection` adalah contoh utama dari reusabilitas kode. Objek `Connection` yang diperoleh dari kelas ini digunakan di berbagai bagian aplikasi (misalnya, di `MahasiswaForm`, `ReservasiForm`, dan `DataViewForm`) untuk melakukan operasi database.
    -   **Manfaat**: Mengurangi duplikasi kode, mempercepat pengembangan, dan memastikan konsistensi. Jika ada perubahan pada detail koneksi database, hanya kelas `DatabaseConnection` yang perlu dimodifikasi, bukan setiap bagian kode yang berinteraksi dengan database.

5.  **Inheritance (Pewarisan)**
    -   **Penerapan**: Meskipun tidak ada hierarki kelas model yang kompleks dalam proyek ini, konsep pewarisan secara implisit digunakan melalui penggunaan kelas-kelas dari Java Swing. Misalnya, semua kelas form GUI (`MainMenuForm`, `MahasiswaForm`, `ReservasiForm`, `DataViewForm`) mewarisi dari kelas `javax.swing.JFrame`, yang menyediakan fungsionalitas dasar untuk jendela aplikasi.
    -   **Manfaat**: Memungkinkan kelas-kelas spesifik untuk mendapatkan fungsionalitas umum dari kelas induk, mengurangi kebutuhan untuk menulis ulang kode dasar dan mempromosikan struktur yang terorganisir.

Penerapan konsep-konsep OOP ini menjadikan **Sistem Reservasi Ruangan Kelas Universitas** sebagai contoh proyek yang baik untuk memahami bagaimana prinsip-prinsip desain berorientasi objek dapat diterapkan dalam pengembangan aplikasi dunia nyata.

[🔝 Kembali ke atas](#📚-daftar-isi)

---

## 📹 Dokumentasi Video

Untuk pemahaman yang lebih mendalam mengenai proyek ini, berikut adalah tautan ke dokumentasi video yang mencakup presentasi aplikasi dan proses pengembangan kode.

-   **🎥 Video Presentasi Proyek (YouTube)**
    [Link Video Presentasi Anda di YouTube](https://www.youtube.com/watch?v=YOUR_VIDEO_ID)
    *(Catatan: Video presentasi berdurasi minimal 10 menit dan wajib menampilkan wajah (on-cam).)*

-   **💾 Video Proses Pembuatan Kode (Google Drive)**
    [Link Video Proses Pembuatan Kode Anda di Google Drive](https://drive.google.com/file/d/YOUR_FILE_ID/view)
    *(Catatan: Video proses pembuatan kode juga wajib menampilkan wajah (on-cam).)*

[🔝 Kembali ke atas](#📚-daftar-isi)


