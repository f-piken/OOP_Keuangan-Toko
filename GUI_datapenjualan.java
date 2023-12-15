/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package project_akhir_prak;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author ASUS
 */
public class GUI_datapenjualan extends javax.swing.JFrame {

    /**
     * Creates new form GUI_datapenjualan
     */
    String kode1,nama1,jumlah1;
    public GUI_datapenjualan() {
        initComponents();
        tampil();
        tampil_kode();
    }
    public Connection conn;
    public void clear() {
        kode.setSelectedIndex(0);
        jumlah.setText("");
    }
        public void koneksi() throws SQLException {
        try {
            conn = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/toko_2218138?user=root&password=");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GUI_barang.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            Logger.getLogger(GUI_barang.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception es) {
            Logger.getLogger(GUI_barang.class.getName()).log(Level.SEVERE, null, es);
        }
    }
    public void tampil() {
        DefaultTableModel tabelhead = new DefaultTableModel();
        tabelhead.addColumn("KODE");
        tabelhead.addColumn("NAMA");
        tabelhead.addColumn("JENIS");
        tabelhead.addColumn("MEREK");
        tabelhead.addColumn("HARGA");
        tabelhead.addColumn("JULAH");
        tabelhead.addColumn("POTONGAN");
        tabelhead.addColumn("TOTAL");
        try {
            koneksi();
            String sql = "SELECT * FROM tb_penjualan";
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery(sql);
            while (res.next()) {
                tabelhead.addRow(new Object[]{res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6),res.getString(7),res.getString(8),res.getString(9),});
            }
            jTable1.setModel(tabelhead);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "BELUM TERKONEKSI");
        }
    }
    public void tampil_kode() {
        try {
            koneksi();
            String sql = "SELECT kode FROM tb_barang order by kode asc";
            Statement stt = conn.createStatement();
            ResultSet res = stt.executeQuery(sql);
            while (res.next()) {
                Object[] ob = new Object[3];
                ob[0] = res.getString(1);
                kode.addItem((String) ob[0]);
            }
            res.close();
            stt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void refresh() {
        new GUI_datapenjualan().setVisible(true);
        this.setVisible(false);
    }
    public void ambil(){
        try {
            if (kode.getSelectedIndex() == 0) {
                nama.setText("");
            } else {
                koneksi();
                // Menggunakan PreparedStatement untuk menghindari SQL Injection
                String query = "SELECT nama FROM tb_barang WHERE kode = ?";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, (String) kode.getSelectedItem());

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    // Ambil nilai kolom "nama" dari hasil query
                    String nam = rs.getString("nama");
                    nama.setText(nam);
                } else {
                    // Jika tidak ada data yang cocok dengan kode yang dipilih
                    nama.setText("Data tidak ditemukan");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan dalam mengambil data dari database");
        }
    }
    
    public void insert() {
    String Kode = (String) kode.getSelectedItem();
    String Nama = "";
    String Jenis = "";
    String Merk = "";
    int Harga = 0;

    try {
        koneksi();

        // Mengambil nilai dari tb_barang menggunakan PreparedStatement
        String query = "SELECT nama, jenis, merk, harga FROM tb_barang WHERE kode = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, Kode);
        ResultSet rs = ps.executeQuery();

        // Memeriksa apakah ada hasil dari query
        if (rs.next()) {
            Nama = rs.getString("nama");
            Jenis = rs.getString("jenis");
            Merk = rs.getString("merk");
            Harga = rs.getInt("harga");
        }

        if (Jenis.equalsIgnoreCase("Makanan")||Integer.parseInt(jumlah.getText())>4) {
            makanan obj = new makanan();
            obj.harga = Harga;
            obj.jumlah = Integer.parseInt(jumlah.getText());

            // Menyisipkan data ke tb_penjualan menggunakan PreparedStatement
            String insertQuery = "INSERT INTO tb_penjualan (kode, nama, jenis, merk, harga, jumlah, potong, total) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement insertPs = conn.prepareStatement(insertQuery);
            insertPs.setString(1, Kode);
            insertPs.setString(2, Nama);
            insertPs.setString(3, Jenis);
            insertPs.setString(4, Merk);
            insertPs.setInt(5, Harga);
            insertPs.setInt(6, Integer.parseInt(jumlah.getText()));
            insertPs.setInt(7, obj.diskon);
            insertPs.setInt(8, obj.hittod());

            insertPs.executeUpdate();
            insertPs.close();

            JOptionPane.showMessageDialog(null, "Berhasil Memasukan Data Barang!\n" + Nama);
            refresh();
        } else if (Jenis.equalsIgnoreCase("Minuman")||Integer.parseInt(jumlah.getText())>6) {
            minuman obj = new minuman();
            obj.harga = Harga;
            obj.jumlah = Integer.parseInt(jumlah.getText());

            // Menyisipkan data ke tb_penjualan menggunakan PreparedStatement
            String insertQuery = "INSERT INTO tb_penjualan (kode, nama, jenis, merk, harga, jumlah, potong, total) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement insertPs = conn.prepareStatement(insertQuery);
            insertPs.setString(1, Kode);
            insertPs.setString(2, Nama);
            insertPs.setString(3, Jenis);
            insertPs.setString(4, Merk);
            insertPs.setInt(5, Harga);
            insertPs.setInt(6, Integer.parseInt(jumlah.getText()));
            insertPs.setInt(7, obj.diskon);
            insertPs.setInt(8, obj.hittod());

            insertPs.executeUpdate();
            insertPs.close();

            JOptionPane.showMessageDialog(null, "Berhasil Memasukan Data Barang!\n" + Nama);
            refresh();
        }else if (Jenis.equalsIgnoreCase("Grosir")||Integer.parseInt(jumlah.getText())==12) {
            grosir obj = new grosir();
            obj.harga = Harga;
            obj.jumlah = Integer.parseInt(jumlah.getText());

            // Menyisipkan data ke tb_penjualan menggunakan PreparedStatement
            String insertQuery = "INSERT INTO tb_penjualan (kode, nama, jenis, merk, harga, jumlah, potong, total) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement insertPs = conn.prepareStatement(insertQuery);
            insertPs.setString(1, Kode);
            insertPs.setString(2, Nama);
            insertPs.setString(3, Jenis);
            insertPs.setString(4, Merk);
            insertPs.setInt(5, Harga);
            insertPs.setInt(6, Integer.parseInt(jumlah.getText()));
            insertPs.setInt(7, obj.diskon(10));
            insertPs.setInt(8, obj.hittod());

            insertPs.executeUpdate();
            insertPs.close();

            JOptionPane.showMessageDialog(null, "Berhasil Memasukan Data Barang!\n" + Nama);
            refresh();
        }else{
            toko obj = new toko();
            obj.harga = Harga;
            obj.jumlah = Integer.parseInt(jumlah.getText());
            // Menyisipkan data ke tb_penjualan menggunakan PreparedStatement
            String insertQuery = "INSERT INTO tb_penjualan (kode, nama, jenis, merk, harga, jumlah, potong, total) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement insertPs = conn.prepareStatement(insertQuery);
            insertPs.setString(1, Kode);
            insertPs.setString(2, Nama);
            insertPs.setString(3, Jenis);
            insertPs.setString(4, Merk);
            insertPs.setInt(5, Harga);
            insertPs.setInt(6, Integer.parseInt(jumlah.getText()));
            insertPs.setInt(7, 0);
            insertPs.setInt(8, obj.hittod());

            insertPs.executeUpdate();
            insertPs.close();

            JOptionPane.showMessageDialog(null, "Berhasil Memasukan Data Barang!\n" + Nama);
            refresh();
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Terjadi Kesalahan Input!");
        e.printStackTrace(); // Ini akan mencetak detail kesalahan ke konsol
    }
}

    public void update() {
    String Kode = (String) kode.getSelectedItem();
    String Nama = "";
    String Jenis = "";
    String Merk = "";
    int Harga = 0;

    try {
        koneksi();

        // Fetching values from tb_barang using PreparedStatement
        String query = "SELECT nama, jenis, merk, harga FROM tb_barang WHERE kode = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, Kode);
        ResultSet rs = ps.executeQuery();

        // Checking if there are results from the query
        if (rs.next()) {
            Nama = rs.getString("nama");
            Jenis = rs.getString("jenis");
            Merk = rs.getString("merk");
            Harga = rs.getInt("harga");
        }

        // Updating the record based on certain conditions
        if (Jenis.equalsIgnoreCase("Makanan") || Integer.parseInt(jumlah.getText()) > 4) {
            // Assuming you want to update the record based on specific criteria
            makanan obj = new makanan();
            obj.harga = Harga;
            obj.jumlah = Integer.parseInt(jumlah.getText());
            String updateQuery = "UPDATE tb_penjualan SET jumlah = ?, total = ? WHERE kode = ?";
            PreparedStatement updatePs = conn.prepareStatement(updateQuery);
            updatePs.setInt(1, Integer.parseInt(jumlah.getText()));
            updatePs.setInt(2, obj.hittod());
            updatePs.setString(3, Kode);

            updatePs.executeUpdate();
            updatePs.close();

            JOptionPane.showMessageDialog(null, "Berhasil Memperbarui Data Barang!\n" + Nama);
            refresh();
        } else if (Jenis.equalsIgnoreCase("Minuman") || Integer.parseInt(jumlah.getText()) > 6) {
            minuman obj = new minuman();
            obj.harga = Harga;
            obj.jumlah = Integer.parseInt(jumlah.getText());
            String updateQuery = "UPDATE tb_penjualan SET jumlah = ?, total = ? WHERE kode = ?";
            PreparedStatement updatePs = conn.prepareStatement(updateQuery);
            updatePs.setInt(1, Integer.parseInt(jumlah.getText()));
            updatePs.setInt(2, obj.hittod());
            updatePs.setString(3, Kode);

            updatePs.executeUpdate();
            updatePs.close();

            JOptionPane.showMessageDialog(null, "Berhasil Memperbarui Data Barang!\n" + Nama);
            refresh();
        } else if (Jenis.equalsIgnoreCase("Grosir") || Integer.parseInt(jumlah.getText()) == 12) {
            grosir obj = new grosir();
            obj.harga = Harga;
            obj.jumlah = Integer.parseInt(jumlah.getText());
            String updateQuery = "UPDATE tb_penjualan SET jumlah = ?, total = ? WHERE kode = ?";
            PreparedStatement updatePs = conn.prepareStatement(updateQuery);
            updatePs.setInt(1, Integer.parseInt(jumlah.getText()));
            updatePs.setInt(2, obj.hittod());
            updatePs.setString(3, Kode);

            updatePs.executeUpdate();
            updatePs.close();

            JOptionPane.showMessageDialog(null, "Berhasil Memperbarui Data Barang!\n" + Nama);
            refresh();
        } else {
            toko obj = new toko();
            obj.harga = Harga;
            obj.jumlah = Integer.parseInt(jumlah.getText());
            String updateQuery = "UPDATE tb_penjualan SET jumlah = ?, total = ? WHERE kode = ?";
            PreparedStatement updatePs = conn.prepareStatement(updateQuery);
            updatePs.setInt(1, Integer.parseInt(jumlah.getText()));
            updatePs.setInt(2, obj.hittod());
            updatePs.setString(3, Kode);

            updatePs.executeUpdate();
            updatePs.close();

            JOptionPane.showMessageDialog(null, "Berhasil Memperbarui Data Barang!\n" + Nama);
            refresh();
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Terjadi Kesalahan Input!");
    }
}

    public void delete() {
        int ok = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin akan menghapus data ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            try {
                String sql = "DELETE FROM tb_penjualan WHERE kode='" + kode.getSelectedItem()+ "'";
                java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil di hapus");
//                batal();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data gagal di hapus");
            }
        }
        refresh();
    }

    public void itempilih() {
        nama.setText(nama1);
        jumlah.setText(jumlah1);
        kode.setSelectedItem(kode1);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jumlah = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        batal = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        simpan = new javax.swing.JButton();
        hapus = new javax.swing.JButton();
        keluar = new javax.swing.JButton();
        kode = new javax.swing.JComboBox<>();
        brg = new javax.swing.JButton();
        nama = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        update = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel6.setText("Data Penjualan");

        jLabel1.setText("KODE");

        jumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlahActionPerformed(evt);
            }
        });

        jLabel7.setText("JUMLAH");

        batal.setText("Batal");
        batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batalActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Kode", "Nama", "Jenis", "Merek", "Harga", "Jumlah", "Potongan", "Total"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        simpan.setText("Simpan");
        simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanActionPerformed(evt);
            }
        });

        hapus.setText("Hapus");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });

        keluar.setText("Keluar");
        keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keluarActionPerformed(evt);
            }
        });

        kode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--KODE--" }));
        kode.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                kodeItemStateChanged(evt);
            }
        });
        kode.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                kodePropertyChange(evt);
            }
        });

        brg.setText("Data Barang");
        brg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brgActionPerformed(evt);
            }
        });

        nama.setEditable(false);
        nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namaActionPerformed(evt);
            }
        });

        jLabel8.setText("NAMA");

        update.setText("Ubah");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel8))
                                        .addGap(61, 61, 61)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jumlah, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                                            .addComponent(kode, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(nama))))
                                .addGap(28, 28, 28))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(simpan)
                                .addGap(28, 28, 28)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(update)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(batal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(hapus)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(keluar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(brg))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 826, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(432, 432, 432)
                        .addComponent(jLabel6)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(kode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(simpan)
                        .addGap(131, 131, 131))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(brg)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(update)
                        .addComponent(batal)
                        .addComponent(hapus)
                        .addComponent(keluar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jumlahActionPerformed

    private void batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_batalActionPerformed

    private void keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keluarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_keluarActionPerformed

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
        // TODO add your handling code here:
        insert();
    }//GEN-LAST:event_simpanActionPerformed

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_hapusActionPerformed

    private void brgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brgActionPerformed
        // TODO add your handling code here:
        GUI_barang obj = new GUI_barang();
        obj.show();
    }//GEN-LAST:event_brgActionPerformed

    private void namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namaActionPerformed

    private void kodePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_kodePropertyChange
        // TODO add your handling code here:
        
    }//GEN-LAST:event_kodePropertyChange

    private void kodeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_kodeItemStateChanged
        // TODO add your handling code here:
        ambil();
    }//GEN-LAST:event_kodeItemStateChanged

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_updateActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int tabel = jTable1.getSelectedRow();
        kode1 = jTable1.getValueAt(tabel, 0).toString();
        nama1 = jTable1.getValueAt(tabel, 1).toString();
        jumlah1 = jTable1.getValueAt(tabel, 5).toString();
        itempilih();
    }//GEN-LAST:event_jTable1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI_datapenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_datapenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_datapenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_datapenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_datapenjualan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton batal;
    private javax.swing.JButton brg;
    private javax.swing.JButton hapus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jumlah;
    private javax.swing.JButton keluar;
    private javax.swing.JComboBox<String> kode;
    private javax.swing.JTextField nama;
    private javax.swing.JButton simpan;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
