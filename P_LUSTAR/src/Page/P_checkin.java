/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Page;

import javax.swing.JOptionPane;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import javax.swing.JTextArea;
import java.util.Date;
import java.text.NumberFormat;
import java.util.Locale;
/**
 *
 * @author Lucky
 */
public class P_checkin extends javax.swing.JFrame {

    /**
     * Creates new form 
     */
    String id_karyawan;
    Connection Con = S_Connection.getConnection();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat dateDay = new SimpleDateFormat("dd");
    SimpleDateFormat dateMonth = new SimpleDateFormat("MM");
    SimpleDateFormat dateYear = new SimpleDateFormat("yyyy");
    
    public P_checkin(String id_karyawan) {
        this.id_karyawan = id_karyawan;
        initComponents();
        idTransaksi();
        setResizable(false);
    }
    
    public String getDay(){
//         masukan data
        String theMasuk = dateDay.format(dateMasuk.getDate());
        String theKeluar = dateDay.format(dateKeluar.getDate());
//         mengubah String to int
        int tempMasuk = Integer.parseInt(theMasuk);
        int tempKeluar = Integer.parseInt(theKeluar);

        int tempHasil = (tempKeluar - tempMasuk);
        return tempHasil + "";
    }
    
    public String ubahRupiah()
    {
        double a = getRoomPrice();
       
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        System.out.println(formatRupiah.format(a));
       
        //merubah mendari 10.000.000.000
        NumberFormat nf = NumberFormat.getNumberInstance(new Locale("in", "ID"));
        return nf.format(a) + " ";
    }
    
    public String getRoomData()
    {
        String id_kamar = cmbRoomType.getSelectedItem().toString();
        try{
            String sql = "SELECT harga_kmr FROM tb_kamar WHERE tipe_kmr='"+id_kamar+"'";
            Statement st = Con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {                
                return rs.getString(1);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return null;
    }
    
    public double getRoomPrice()
    {
        int hargaKamar = Integer.parseInt(getRoomData());
        int hari = Integer.parseInt(getDay());
        double temp = hargaKamar*hari;
        return temp;
    }
    
    
    public String idTransaksi(){
        String query = "SELECT id_transaksi FROM tb_checkin";
        String temp = "";
        try {
            Statement st = Con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                temp = rs.getString("id_transaksi");
            }
            System.out.println(temp);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
        }
        return temp;
    }
    
    public void masukanData()
    {
        String id_transaksi = idTransaksi();
        try{
            PreparedStatement ps = Con.prepareStatement("INSERT INTO `tb_checkin` (`id_transaksi`, `no_transaksi`, `nik_tamu`, `nama_tamu`, `status_tamu`, `alamat_tamu`, `hp_tamu`, `wn_tamu`, `jenkel_tamu`, `tipe_room`, `tgl_masuk`, `tgl_keluar`, `hari`, `jumlah_orang`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, null);
            ps.setString(2, "T-" + id_transaksi);
            ps.setString(3, txtNik.getText());
            ps.setString(4, txtNama.getText());
            ps.setString(5, cmbStatus.getSelectedItem().toString());
            ps.setString(6, txtAlamat.getText());
            ps.setString(7, txtNoHp.getText());
            ps.setString(8, cmbWarganegara.getSelectedItem().toString());
            ps.setString(9, jenisKelamin());
            ps.setString(10, cmbRoomType.getSelectedItem().toString());
            ps.setString(11, getDateTglMasuk());
            ps.setString(12, getDateTglKeluar());
            ps.setString(13, getDay());
            ps.setString(14, cmbAdult.getSelectedItem().toString() + " Adult, " + cmbChild.getSelectedItem().toString() + " Child");
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data tersimpan");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR masukandata(): " + e.getMessage());
        }
    }

    public String jenisKelamin()
    {
        if (jradioMale.isSelected()) {
            return "Laki-laki";
        }else if(jradioFemale.isSelected()){
            return "Perempuan";
        }else{
            return "Laki-laki";
        }
    }
    
    public void resetValue()
    {
        // Set default text
        txtNik.setText("");
        txtNama.setText("");
        txtAlamat.setText("");
        txtNoHp.setText("");

        // Set default cmb
        cmbStatus.setSelectedIndex(0);
        cmbWarganegara.setSelectedIndex(0);
        cmbRoomType.setSelectedIndex(0);
        

    }
    
    public String[] getRoomValue(){
        String kmr_kosong = null, kmr_isi = null;
        try{
            String query = "SELECT kmr_kosong, kmr_isi FROM tb_kamar WHERE `tipe_kmr`='"+ cmbRoomType.getSelectedItem().toString() +"'";
            Statement st = Con.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                kmr_kosong = rs.getString("kmr_kosong");
                kmr_isi = rs.getString("kmr_isi");
                System.out.println(kmr_kosong);
                System.out.println(kmr_isi);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "ERROR getRoomValue = " + e.getMessage());
        }
        String[] temp = {kmr_kosong, kmr_isi};
        return temp;
    }
    
    public void updateRoom(){
        String[] kamar = getRoomValue();
        int kamar_kosong = Integer.parseInt(kamar[0]), kamar_isi = Integer.parseInt(kamar[1]);
        int temp;
        if (kamar_kosong <= 0) {
            JOptionPane.showMessageDialog(null, "Kamar habis");
        }else{
            String query = "UPDATE `tb_kamar` SET `kmr_kosong` =?, `kmr_isi` =? WHERE `tipe_kmr` = '"+ cmbRoomType.getSelectedItem().toString() +"'";
            try {
                PreparedStatement ps = Con.prepareStatement(query);
                ps.setString(1, (kamar_kosong - 1) + "");
                ps.setString(2, (kamar_isi + 1) + "");
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil di Update!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error updateRoom: " + e.getMessage());
            }
        }
    }
    

    
    public String getDateTglMasuk()
    {
        String theDate = dateFormat.format(dateMasuk.getDate());
        return theDate; 
    }
    
    public String getDateTglKeluar()
    {
        String theDate = dateFormat.format(dateKeluar.getDate());
        return theDate;
    }
    
    public String getDateInfo()
    {
        Date d = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String theDate = dateFormat.format(d);
        return theDate;
    }
    
    public String getTime()
    {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String theTime = timeFormat.format(cal.getTime());
        return theTime;
    }
    
    public void text_area()
    {
        String temp_cetak; 
        temp_cetak = "                               HOTEL RECEIPT";
        temp_cetak += "\n";
        temp_cetak += "\nInvoice No: " + txtNik.getText();
        temp_cetak += "\nGuest Name: " + txtNama.getText();
        temp_cetak += "\ncitizen: " + cmbWarganegara.getSelectedItem().toString();
        temp_cetak += "\nArr date: " + getDateInfo();
        temp_cetak += "\nArr time: " + getTime();
        temp_cetak += "\nCompany: HOTEL BRIGHT LUSTAR";
        temp_cetak += "\n=========================================";
        temp_cetak += "\n                     SELAMAT DATANG DI LUSTAR";
        temp_cetak += "\n=========================================";
        temp_cetak += "\nTgl. Masuk: " + getDateTglMasuk() + "                Tgl. Keluar: " + getDateTglKeluar();
        temp_cetak += "\n";
        temp_cetak += "\nRoom Type: " + cmbRoomType.getSelectedItem().toString();
        temp_cetak += "\n";
        temp_cetak += "\nNumber of People: " + cmbAdult.getSelectedItem().toString() + " Adult " + cmbChild.getSelectedItem().toString() + " Child";
        temp_cetak += "\n";
        temp_cetak += "\nNight: " + getDay();
        temp_cetak += "\n";
        temp_cetak += "\nPrice: Rp." + ubahRupiah();
        temp_cetak += "\n";
        temp_cetak += "\n";
        temp_cetak += "\n=========================================";
        temp_cetak += "\n     TERIMAKASIH TELAH BERKUNJUNG DI LUSTAR ";
        temp_cetak += "\n=========================================";
        txt_area.setText(temp_cetak);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panel_Default = new javax.swing.JPanel();
        panel_dataPemesan = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNik = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtAlamat = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jradioMale = new javax.swing.JRadioButton();
        jradioFemale = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        txtNoHp = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cmbStatus = new javax.swing.JComboBox<>();
        cmbWarganegara = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cmbRoomType = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        dateMasuk = new com.toedter.calendar.JDateChooser();
        dateKeluar = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        cmbAdult = new javax.swing.JComboBox<>();
        cmbChild = new javax.swing.JComboBox<>();
        btnCheckIn = new javax.swing.JPanel();
        txtCheckIn = new javax.swing.JLabel();
        btnReset = new javax.swing.JPanel();
        txtReset = new javax.swing.JLabel();
        P_receipt = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_area = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btn_back = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel_Default.setBackground(new java.awt.Color(5, 21, 63));

        panel_dataPemesan.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Data Pemesan");

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 3, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("NIK");

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 3, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Nama");

        txtAlamat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAlamatActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 3, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Alamat");

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 3, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Jenis Kelamin");

        buttonGroup1.add(jradioMale);
        jradioMale.setText("Laki-laki");

        buttonGroup1.add(jradioFemale);
        jradioFemale.setText("Perempuan");

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 3, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("No. Hp");

        txtNoHp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNoHpActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Trebuchet MS", 3, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Warganegara");

        jLabel15.setFont(new java.awt.Font("Trebuchet MS", 3, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Status");

        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Belum Menikah", "Sudah Menikah" }));

        cmbWarganegara.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "WNA", "WNI" }));

        javax.swing.GroupLayout panel_dataPemesanLayout = new javax.swing.GroupLayout(panel_dataPemesan);
        panel_dataPemesan.setLayout(panel_dataPemesanLayout);
        panel_dataPemesanLayout.setHorizontalGroup(
            panel_dataPemesanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_dataPemesanLayout.createSequentialGroup()
                .addGroup(panel_dataPemesanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_dataPemesanLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_dataPemesanLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(txtNik, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_dataPemesanLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_dataPemesanLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jradioMale, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jradioFemale, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_dataPemesanLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(panel_dataPemesanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53)
                        .addGroup(panel_dataPemesanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panel_dataPemesanLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(panel_dataPemesanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_dataPemesanLayout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53))
                            .addGroup(panel_dataPemesanLayout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)))
                        .addGroup(panel_dataPemesanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cmbWarganegara, 0, 280, Short.MAX_VALUE)
                            .addComponent(txtNoHp))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panel_dataPemesanLayout.setVerticalGroup(
            panel_dataPemesanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_dataPemesanLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panel_dataPemesanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNik, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel_dataPemesanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel_dataPemesanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel_dataPemesanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel_dataPemesanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNoHp, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel_dataPemesanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbWarganegara, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel_dataPemesanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jradioMale, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jradioFemale, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 3, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Data Kamar");

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 3, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Room Type");

        cmbRoomType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Regular Room", "Superior Room", "Deluxe Room", "Family Room", "President Suit Room", " ", " " }));

        jLabel17.setFont(new java.awt.Font("Trebuchet MS", 3, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Tgl. Masuk");

        jLabel18.setFont(new java.awt.Font("Trebuchet MS", 3, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Tgl. Keluar");

        dateMasuk.setDateFormatString("d MMM y");

        dateKeluar.setDateFormatString("d MMM y");

        jLabel12.setFont(new java.awt.Font("Trebuchet MS", 3, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Jumlah orang");

        cmbAdult.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- ADULT -", "1", "2", "3", "4", "5" }));

        cmbChild.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- CHILD -", "0", "1", "2", "3", "4", "5" }));

        btnCheckIn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCheckInMouseClicked(evt);
            }
        });

        txtCheckIn.setFont(new java.awt.Font("Trebuchet MS", 3, 18)); // NOI18N
        txtCheckIn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtCheckIn.setText("Check-In");
        txtCheckIn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCheckInMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btnCheckInLayout = new javax.swing.GroupLayout(btnCheckIn);
        btnCheckIn.setLayout(btnCheckInLayout);
        btnCheckInLayout.setHorizontalGroup(
            btnCheckInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtCheckIn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
        );
        btnCheckInLayout.setVerticalGroup(
            btnCheckInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtCheckIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        txtReset.setFont(new java.awt.Font("Trebuchet MS", 3, 18)); // NOI18N
        txtReset.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtReset.setText("Reset");
        txtReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtResetMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btnResetLayout = new javax.swing.GroupLayout(btnReset);
        btnReset.setLayout(btnResetLayout);
        btnResetLayout.setHorizontalGroup(
            btnResetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtReset, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
        );
        btnResetLayout.setVerticalGroup(
            btnResetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtReset, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(cmbRoomType, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel18)
                                .addComponent(jLabel17))
                            .addGap(32, 32, 32)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(dateMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(dateKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addGap(23, 23, 23)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(btnCheckIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(12, 12, 12)
                                    .addComponent(cmbAdult, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(cmbChild, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel5))
                    .addComponent(cmbRoomType, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(cmbChild, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbAdult, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCheckIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );

        cmbRoomType.getAccessibleContext().setAccessibleName("");
        dateMasuk.getAccessibleContext().setAccessibleName("");
        dateKeluar.getAccessibleContext().setAccessibleName("");
        cmbAdult.getAccessibleContext().setAccessibleName("");

        P_receipt.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 3, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Receipt");

        txt_area.setColumns(20);
        txt_area.setRows(5);
        jScrollPane1.setViewportView(txt_area);

        javax.swing.GroupLayout P_receiptLayout = new javax.swing.GroupLayout(P_receipt);
        P_receipt.setLayout(P_receiptLayout);
        P_receiptLayout.setHorizontalGroup(
            P_receiptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_receiptLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(P_receiptLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        P_receiptLayout.setVerticalGroup(
            P_receiptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_receiptLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/icons8-hotel-64.png"))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("LUSTAR");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btn_back.setBackground(new java.awt.Color(255, 255, 255));
        btn_back.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        btn_back.setForeground(new java.awt.Color(6, 21, 63));
        btn_back.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_back.setText("BACK");
        btn_back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_backMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_back, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_back, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout panel_DefaultLayout = new javax.swing.GroupLayout(panel_Default);
        panel_Default.setLayout(panel_DefaultLayout);
        panel_DefaultLayout.setHorizontalGroup(
            panel_DefaultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_DefaultLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panel_DefaultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panel_DefaultLayout.createSequentialGroup()
                        .addComponent(panel_dataPemesan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(P_receipt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_DefaultLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_DefaultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        panel_DefaultLayout.setVerticalGroup(
            panel_DefaultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_DefaultLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panel_DefaultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(P_receipt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_DefaultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(panel_dataPemesan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(panel_DefaultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_DefaultLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_DefaultLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_Default, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_Default, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtAlamatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAlamatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAlamatActionPerformed

    private void txtNoHpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNoHpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNoHpActionPerformed

    private void txtCheckInMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCheckInMouseClicked
        masukanData();
        idTransaksi();
        text_area();
        updateRoom();
    }//GEN-LAST:event_txtCheckInMouseClicked

    private void txtResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtResetMouseClicked
        resetValue();
    }//GEN-LAST:event_txtResetMouseClicked

    private void btnCheckInMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCheckInMouseClicked
//        text_area();

    }//GEN-LAST:event_btnCheckInMouseClicked

    private void btn_backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_backMouseClicked
        // TODO add your handling code here:
        P_menu a = new P_menu(this.id_karyawan);
        a.setVisible(true);
        dispose();
    }//GEN-LAST:event_btn_backMouseClicked

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
            java.util.logging.Logger.getLogger(P_checkin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(P_checkin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(P_checkin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(P_checkin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new P_checkin().setVisible(true);
                P_login redirect = new P_login();
                redirect.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel P_receipt;
    private javax.swing.JPanel btnCheckIn;
    private javax.swing.JPanel btnReset;
    private javax.swing.JLabel btn_back;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cmbAdult;
    private javax.swing.JComboBox<String> cmbChild;
    private javax.swing.JComboBox<String> cmbRoomType;
    private javax.swing.JComboBox<String> cmbStatus;
    private javax.swing.JComboBox<String> cmbWarganegara;
    private com.toedter.calendar.JDateChooser dateKeluar;
    private com.toedter.calendar.JDateChooser dateMasuk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton jradioFemale;
    private javax.swing.JRadioButton jradioMale;
    private javax.swing.JPanel panel_Default;
    private javax.swing.JPanel panel_dataPemesan;
    private javax.swing.JTextField txtAlamat;
    private javax.swing.JLabel txtCheckIn;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNik;
    private javax.swing.JTextField txtNoHp;
    private javax.swing.JLabel txtReset;
    private javax.swing.JTextArea txt_area;
    // End of variables declaration//GEN-END:variables
}
