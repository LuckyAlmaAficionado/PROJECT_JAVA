/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Page;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Lucky
 */
public class P_menu extends javax.swing.JFrame {

    
    Connection conn = S_Connection.getConnection();
    PreparedStatement ps;
    ResultSet rs;
    String id_karyawan;
    
    public P_menu(String id_karyawan) {
        this.id_karyawan = id_karyawan;
        initComponents();
        showBiodata();
        setResizable(false);
        btn_home.setBackground(Color.white);
        txt_home.setForeground(new Color(5, 21, 63));
        setting();
    }
    
    public void setting(){
        dashboard();
        Room();
        getTypeRoom();
        hiddenAccount();
        
    }

    
    private void getTypeRoom(){
        int a = 0, b = 0, c = 0;
        String[] arrType = new String[5];
        String[] arrRoom = new String[5];
        try{
            String query = "SELECT tipe_kmr, kmr_kosong FROM tb_kamar";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                arrType[a] = rs.getString("tipe_kmr");
                arrRoom[a] = rs.getString("kmr_kosong");
                a++;
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR getTypeRoom() = " + e.getMessage());
        }

        txtType1.setText(arrType[0]);
        txtType2.setText(arrType[1]);
        txtType3.setText(arrType[2]);
        txtType4.setText(arrType[3]);
        txtType5.setText(arrType[4]);
        
        txtRoom1.setText(arrRoom[0]);
        txtRoom2.setText(arrRoom[1]);
        txtRoom3.setText(arrRoom[2]);
        txtRoom4.setText(arrRoom[3]);
        txtRoom5.setText(arrRoom[4]);
    }
        
        
    private void showBiodata(){
        String sql = "SELECT * FROM tb_karyawan WHERE id_karyawan='"+this.id_karyawan+"'";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                name_profile.setText(rs.getString("nama_depan").toUpperCase());
            }
        } catch (Exception e) {
            System.out.println("Error ShowBiodata() : " + e.getMessage());
        }
    }
    
    public int[] Room(){
        int kmr_isi = 0, kmr_kosong = 0;
        String query = "SELECT * FROM tb_kamar";
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {                
                kmr_kosong += Integer.parseInt(rs.getString("kmr_kosong"));
                kmr_isi += Integer.parseInt(rs.getString("kmr_isi"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error arr room = " + e.getMessage());
        }
        
        int arr[] = {kmr_kosong, kmr_isi};
        return arr;
    }
    
    public void dashboard(){
        int[] kamar=Room();
        txtAvailableRoom.setText(kamar[0] + "");
        txtNotAvailableRoom.setText(kamar[1] + "");
    }
    
    public void hiddenAccount(){
        // text
        txtNamaLengkap.setVisible(false);
        txtTTL.setVisible(false);
        txtAgama.setVisible(false);
        txtAlamat.setVisible(false);
        txtRTRW.setVisible(false);
        txtKelurahan.setVisible(false);
        txtKecamatan.setVisible(false);
        txtEmail.setVisible(false);
        txtNoHp.setVisible(false);
        txtSocialMedia.setVisible(false);
        
        // button
        btnSimpanData.setVisible(false);
        
        // combo box
        cmbJenKel.setVisible(false);
        cmbStatusPerkawinan.setVisible(false);
        cmbGoldar.setVisible(false);
    }
    
    public void setAccountActive(){
        // text
        txtNamaLengkap.setVisible(true);
        txtTTL.setVisible(true);
        txtAgama.setVisible(true);
        txtAlamat.setVisible(true);
        txtRTRW.setVisible(true);
        txtKelurahan.setVisible(true);
        txtKecamatan.setVisible(true);
        txtEmail.setVisible(true);
        txtNoHp.setVisible(true);
        txtSocialMedia.setVisible(true);
        
        // button
        btnSimpanData.setVisible(true);
        
        // combo box
        cmbJenKel.setVisible(true);
        cmbStatusPerkawinan.setVisible(true);
        cmbGoldar.setVisible(true);
    }
    
    public void updateDataAccount(){
        String query = "UPDATE `tb_karyawan` SET `nama_lengkap`=?, `ttl_karyawan` =? , `jenkel_karyawan` =?, `agama_karyawan` =?, `status_karyawan` =?, `goldar_karyawan` =?, `alamat_karyawan` =?, `rtrw_karyawan` =?, `keldesa_karyawan` =?, `kecamatan_karyawan` =?, `email_karyawan` =?, `no_karyawan` =?, `medsos_karyawan` =? WHERE `tb_karyawan`.`id_karyawan` = '"+ this.id_karyawan +"'";
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, txtNamaLengkap.getText());
            ps.setString(2, txtTTL.getText());
            ps.setString(3, cmbJenKel.getSelectedItem().toString());
            ps.setString(4, txtAgama.getText());
            ps.setString(5, cmbStatusPerkawinan.getSelectedItem().toString());
            ps.setString(6, cmbGoldar.getSelectedItem().toString());
            ps.setString(7, txtAlamat.getText());
            ps.setString(8, txtRTRW.getText());
            ps.setString(9, txtKelurahan.getText());
            ps.setString(10, txtKecamatan.getText());
            ps.setString(11, txtEmail.getText());
            ps.setString(12, txtNoHp.getText());
            ps.setString(13, txtSocialMedia.getText());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil di update");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data tidak berhasil di update");
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        p_menu = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btn_account = new javax.swing.JPanel();
        txt_account = new javax.swing.JLabel();
        pic_profile = new javax.swing.JLabel();
        name_profile = new javax.swing.JLabel();
        btn_dashboard = new javax.swing.JPanel();
        txt_dashboard = new javax.swing.JLabel();
        btn_home = new javax.swing.JPanel();
        txt_home = new javax.swing.JLabel();
        title_profile = new javax.swing.JLabel();
        btn_logout = new javax.swing.JPanel();
        txt_account1 = new javax.swing.JLabel();
        p_home = new javax.swing.JPanel();
        btn_checkin = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btn_absent = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        btn_absent1 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        btn_reservation1 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        p_dashboard = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        p_available = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        txtAvailableRoom = new javax.swing.JLabel();
        p_available1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        txtNotAvailableRoom = new javax.swing.JLabel();
        p_available2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        txtReservedRoom = new javax.swing.JLabel();
        p_available3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        txtType2 = new javax.swing.JLabel();
        txtRoom2 = new javax.swing.JLabel();
        p_available4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        txtType3 = new javax.swing.JLabel();
        txtRoom3 = new javax.swing.JLabel();
        p_available5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        txtType1 = new javax.swing.JLabel();
        txtRoom1 = new javax.swing.JLabel();
        p_available6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        txtType4 = new javax.swing.JLabel();
        txtRoom4 = new javax.swing.JLabel();
        p_available7 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        txtType5 = new javax.swing.JLabel();
        txtRoom5 = new javax.swing.JLabel();
        p_account = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtTTL = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtAgama = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        cmbStatusPerkawinan = new javax.swing.JComboBox<>();
        cmbJenKel = new javax.swing.JComboBox<>();
        cmbGoldar = new javax.swing.JComboBox<>();
        txtNamaLengkap = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        txtAlamat = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        txtRTRW = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        txtKelurahan = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        txtKecamatan = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        btnSimpanData = new javax.swing.JToggleButton();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        txtNoHp = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        txtSocialMedia = new javax.swing.JTextField();
        p_checkin = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        m_standart = new javax.swing.JPanel();
        txt_standart = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        m_deluxe = new javax.swing.JPanel();
        txt_deluxe = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        m_suit = new javax.swing.JPanel();
        txt_suit = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        m_psuit = new javax.swing.JPanel();
        txt_psuit = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        p_menu.setBackground(new java.awt.Color(5, 21, 63));
        p_menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/icons8-hotel-64.png"))); // NOI18N
        p_menu.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 540, 140, 70));

        jLabel2.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("LUSTAR");
        p_menu.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 590, 140, 50));

        btn_account.setBackground(new java.awt.Color(5, 21, 63));
        btn_account.setForeground(new java.awt.Color(255, 255, 255));
        btn_account.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_account.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_accountMouseClicked(evt);
            }
        });

        txt_account.setBackground(new java.awt.Color(255, 255, 255));
        txt_account.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        txt_account.setForeground(new java.awt.Color(255, 255, 255));
        txt_account.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/icons8_test_account_30px_1.png"))); // NOI18N
        txt_account.setText("Account");

        javax.swing.GroupLayout btn_accountLayout = new javax.swing.GroupLayout(btn_account);
        btn_account.setLayout(btn_accountLayout);
        btn_accountLayout.setHorizontalGroup(
            btn_accountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_accountLayout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(txt_account)
                .addContainerGap(127, Short.MAX_VALUE))
        );
        btn_accountLayout.setVerticalGroup(
            btn_accountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txt_account, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        p_menu.add(btn_account, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 330, 50));

        pic_profile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/icons8_Male_User_100px.png"))); // NOI18N
        p_menu.add(pic_profile, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 100, 100));

        name_profile.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
        name_profile.setForeground(new java.awt.Color(255, 255, 255));
        name_profile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        name_profile.setText("Employeed Name");
        p_menu.add(name_profile, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 330, 40));

        btn_dashboard.setBackground(new java.awt.Color(5, 21, 63));
        btn_dashboard.setForeground(new java.awt.Color(255, 255, 255));
        btn_dashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_dashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_dashboardMouseClicked(evt);
            }
        });

        txt_dashboard.setBackground(new java.awt.Color(255, 255, 255));
        txt_dashboard.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        txt_dashboard.setForeground(new java.awt.Color(255, 255, 255));
        txt_dashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/icons8_dashboard_layout_30px_2.png"))); // NOI18N
        txt_dashboard.setText("Dashboard");

        javax.swing.GroupLayout btn_dashboardLayout = new javax.swing.GroupLayout(btn_dashboard);
        btn_dashboard.setLayout(btn_dashboardLayout);
        btn_dashboardLayout.setHorizontalGroup(
            btn_dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_dashboardLayout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(txt_dashboard)
                .addContainerGap(102, Short.MAX_VALUE))
        );
        btn_dashboardLayout.setVerticalGroup(
            btn_dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txt_dashboard, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        p_menu.add(btn_dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 330, 50));

        btn_home.setBackground(new java.awt.Color(5, 21, 63));
        btn_home.setForeground(new java.awt.Color(255, 255, 255));
        btn_home.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_homeMouseClicked(evt);
            }
        });

        txt_home.setBackground(new java.awt.Color(255, 255, 255));
        txt_home.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        txt_home.setForeground(new java.awt.Color(255, 255, 255));
        txt_home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/icons8_home_30px_4.png"))); // NOI18N
        txt_home.setText("Home");

        javax.swing.GroupLayout btn_homeLayout = new javax.swing.GroupLayout(btn_home);
        btn_home.setLayout(btn_homeLayout);
        btn_homeLayout.setHorizontalGroup(
            btn_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_homeLayout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(txt_home)
                .addContainerGap(154, Short.MAX_VALUE))
        );
        btn_homeLayout.setVerticalGroup(
            btn_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txt_home, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        p_menu.add(btn_home, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 330, 50));

        title_profile.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        title_profile.setForeground(new java.awt.Color(255, 255, 255));
        title_profile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title_profile.setText("Employeed Title");
        p_menu.add(title_profile, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 330, 40));

        btn_logout.setBackground(new java.awt.Color(5, 21, 63));
        btn_logout.setForeground(new java.awt.Color(255, 255, 255));
        btn_logout.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_logoutMouseClicked(evt);
            }
        });

        txt_account1.setBackground(new java.awt.Color(255, 255, 255));
        txt_account1.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        txt_account1.setForeground(new java.awt.Color(255, 255, 255));
        txt_account1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/icons8_shutdown_30px.png"))); // NOI18N
        txt_account1.setText("Logout");

        javax.swing.GroupLayout btn_logoutLayout = new javax.swing.GroupLayout(btn_logout);
        btn_logout.setLayout(btn_logoutLayout);
        btn_logoutLayout.setHorizontalGroup(
            btn_logoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_logoutLayout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(txt_account1)
                .addContainerGap(142, Short.MAX_VALUE))
        );
        btn_logoutLayout.setVerticalGroup(
            btn_logoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txt_account1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        p_menu.add(btn_logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 330, 50));

        getContentPane().add(p_menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 330, 680));

        p_home.setBackground(new java.awt.Color(255, 255, 255));

        btn_checkin.setBackground(new java.awt.Color(5, 21, 63));
        btn_checkin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_checkinMouseClicked(evt);
            }
        });

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/icons8-hotel-check-in-100.png"))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Check-In");

        javax.swing.GroupLayout btn_checkinLayout = new javax.swing.GroupLayout(btn_checkin);
        btn_checkin.setLayout(btn_checkinLayout);
        btn_checkinLayout.setHorizontalGroup(
            btn_checkinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_checkinLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(btn_checkinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(99, Short.MAX_VALUE))
        );
        btn_checkinLayout.setVerticalGroup(
            btn_checkinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_checkinLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        btn_absent.setBackground(new java.awt.Color(5, 21, 63));
        btn_absent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_absentMouseClicked(evt);
            }
        });

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/icons8_hotel_check_out_100px.png"))); // NOI18N

        jLabel16.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Check-Out");

        javax.swing.GroupLayout btn_absentLayout = new javax.swing.GroupLayout(btn_absent);
        btn_absent.setLayout(btn_absentLayout);
        btn_absentLayout.setHorizontalGroup(
            btn_absentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_absentLayout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addGroup(btn_absentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(104, Short.MAX_VALUE))
        );
        btn_absentLayout.setVerticalGroup(
            btn_absentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_absentLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        jLabel17.setFont(new java.awt.Font("Trebuchet MS", 1, 48)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("HOME PAGE");

        btn_absent1.setBackground(new java.awt.Color(5, 21, 63));
        btn_absent1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_absent1MouseClicked(evt);
            }
        });

        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/icons8_attendance_100px.png"))); // NOI18N

        jLabel31.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("Absent");

        javax.swing.GroupLayout btn_absent1Layout = new javax.swing.GroupLayout(btn_absent1);
        btn_absent1.setLayout(btn_absent1Layout);
        btn_absent1Layout.setHorizontalGroup(
            btn_absent1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_absent1Layout.createSequentialGroup()
                .addGroup(btn_absent1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(btn_absent1Layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(jLabel30))
                    .addGroup(btn_absent1Layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(jLabel31)))
                .addContainerGap(102, Short.MAX_VALUE))
        );
        btn_absent1Layout.setVerticalGroup(
            btn_absent1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_absent1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
        );

        btn_reservation1.setBackground(new java.awt.Color(5, 21, 63));
        btn_reservation1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_reservation1MouseClicked(evt);
            }
        });

        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/icons8-list-100.png"))); // NOI18N

        jLabel33.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("Data Guest");

        javax.swing.GroupLayout btn_reservation1Layout = new javax.swing.GroupLayout(btn_reservation1);
        btn_reservation1.setLayout(btn_reservation1Layout);
        btn_reservation1Layout.setHorizontalGroup(
            btn_reservation1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_reservation1Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addGroup(btn_reservation1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))
                .addContainerGap(107, Short.MAX_VALUE))
        );
        btn_reservation1Layout.setVerticalGroup(
            btn_reservation1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_reservation1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout p_homeLayout = new javax.swing.GroupLayout(p_home);
        p_home.setLayout(p_homeLayout);
        p_homeLayout.setHorizontalGroup(
            p_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_homeLayout.createSequentialGroup()
                .addGroup(p_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p_homeLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 788, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p_homeLayout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(p_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btn_checkin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_reservation1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(p_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(p_homeLayout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(btn_absent1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p_homeLayout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(btn_absent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        p_homeLayout.setVerticalGroup(
            p_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_homeLayout.createSequentialGroup()
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(p_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_checkin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_absent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(p_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_reservation1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_absent1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(p_home, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 0, 800, 680));

        p_dashboard.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 3, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Dashboard");

        p_available.setBackground(new java.awt.Color(5, 21, 63));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel19.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(5, 21, 63));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Availble room");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        txtAvailableRoom.setFont(new java.awt.Font("Trebuchet MS", 1, 48)); // NOI18N
        txtAvailableRoom.setForeground(new java.awt.Color(255, 255, 255));
        txtAvailableRoom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtAvailableRoom.setText("NUMBER");

        javax.swing.GroupLayout p_availableLayout = new javax.swing.GroupLayout(p_available);
        p_available.setLayout(p_availableLayout);
        p_availableLayout.setHorizontalGroup(
            p_availableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(p_availableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtAvailableRoom, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                .addContainerGap())
        );
        p_availableLayout.setVerticalGroup(
            p_availableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_availableLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAvailableRoom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        p_available1.setBackground(new java.awt.Color(5, 21, 63));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jLabel24.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(5, 21, 63));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Room not available");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        txtNotAvailableRoom.setFont(new java.awt.Font("Trebuchet MS", 1, 48)); // NOI18N
        txtNotAvailableRoom.setForeground(new java.awt.Color(255, 255, 255));
        txtNotAvailableRoom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtNotAvailableRoom.setText("NUMBER");

        javax.swing.GroupLayout p_available1Layout = new javax.swing.GroupLayout(p_available1);
        p_available1.setLayout(p_available1Layout);
        p_available1Layout.setHorizontalGroup(
            p_available1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(p_available1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtNotAvailableRoom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        p_available1Layout.setVerticalGroup(
            p_available1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_available1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNotAvailableRoom, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                .addContainerGap())
        );

        p_available2.setBackground(new java.awt.Color(5, 21, 63));

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        jLabel25.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(5, 21, 63));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Reserved room");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        txtReservedRoom.setFont(new java.awt.Font("Trebuchet MS", 1, 48)); // NOI18N
        txtReservedRoom.setForeground(new java.awt.Color(255, 255, 255));
        txtReservedRoom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtReservedRoom.setText("NUMBER");

        javax.swing.GroupLayout p_available2Layout = new javax.swing.GroupLayout(p_available2);
        p_available2.setLayout(p_available2Layout);
        p_available2Layout.setHorizontalGroup(
            p_available2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(p_available2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtReservedRoom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        p_available2Layout.setVerticalGroup(
            p_available2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_available2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtReservedRoom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        p_available3.setBackground(new java.awt.Color(5, 21, 63));

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        txtType2.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        txtType2.setForeground(new java.awt.Color(5, 21, 63));
        txtType2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtType2.setText("Room not available");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtType2, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtType2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        txtRoom2.setFont(new java.awt.Font("Trebuchet MS", 1, 48)); // NOI18N
        txtRoom2.setForeground(new java.awt.Color(255, 255, 255));
        txtRoom2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRoom2.setText("NUMBER");

        javax.swing.GroupLayout p_available3Layout = new javax.swing.GroupLayout(p_available3);
        p_available3.setLayout(p_available3Layout);
        p_available3Layout.setHorizontalGroup(
            p_available3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(p_available3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtRoom2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        p_available3Layout.setVerticalGroup(
            p_available3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_available3Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRoom2, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                .addContainerGap())
        );

        p_available4.setBackground(new java.awt.Color(5, 21, 63));

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));

        txtType3.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        txtType3.setForeground(new java.awt.Color(5, 21, 63));
        txtType3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtType3.setText("Reserved room");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtType3, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtType3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        txtRoom3.setFont(new java.awt.Font("Trebuchet MS", 1, 48)); // NOI18N
        txtRoom3.setForeground(new java.awt.Color(255, 255, 255));
        txtRoom3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRoom3.setText("NUMBER");

        javax.swing.GroupLayout p_available4Layout = new javax.swing.GroupLayout(p_available4);
        p_available4.setLayout(p_available4Layout);
        p_available4Layout.setHorizontalGroup(
            p_available4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(p_available4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtRoom3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        p_available4Layout.setVerticalGroup(
            p_available4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_available4Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRoom3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        p_available5.setBackground(new java.awt.Color(5, 21, 63));

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));

        txtType1.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        txtType1.setForeground(new java.awt.Color(5, 21, 63));
        txtType1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtType1.setText("Availble room");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtType1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtType1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        txtRoom1.setFont(new java.awt.Font("Trebuchet MS", 1, 48)); // NOI18N
        txtRoom1.setForeground(new java.awt.Color(255, 255, 255));
        txtRoom1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRoom1.setText("NUMBER");

        javax.swing.GroupLayout p_available5Layout = new javax.swing.GroupLayout(p_available5);
        p_available5.setLayout(p_available5Layout);
        p_available5Layout.setHorizontalGroup(
            p_available5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(p_available5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtRoom1, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                .addContainerGap())
        );
        p_available5Layout.setVerticalGroup(
            p_available5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_available5Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRoom1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        p_available6.setBackground(new java.awt.Color(5, 21, 63));

        jPanel8.setBackground(new java.awt.Color(204, 204, 204));

        txtType4.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        txtType4.setForeground(new java.awt.Color(5, 21, 63));
        txtType4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtType4.setText("Availble room");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtType4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtType4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        txtRoom4.setFont(new java.awt.Font("Trebuchet MS", 1, 48)); // NOI18N
        txtRoom4.setForeground(new java.awt.Color(255, 255, 255));
        txtRoom4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRoom4.setText("NUMBER");

        javax.swing.GroupLayout p_available6Layout = new javax.swing.GroupLayout(p_available6);
        p_available6.setLayout(p_available6Layout);
        p_available6Layout.setHorizontalGroup(
            p_available6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(p_available6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtRoom4, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                .addContainerGap())
        );
        p_available6Layout.setVerticalGroup(
            p_available6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_available6Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRoom4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        p_available7.setBackground(new java.awt.Color(5, 21, 63));

        jPanel9.setBackground(new java.awt.Color(204, 204, 204));

        txtType5.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        txtType5.setForeground(new java.awt.Color(5, 21, 63));
        txtType5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtType5.setText("Room not available");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtType5, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtType5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        txtRoom5.setFont(new java.awt.Font("Trebuchet MS", 1, 48)); // NOI18N
        txtRoom5.setForeground(new java.awt.Color(255, 255, 255));
        txtRoom5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRoom5.setText("NUMBER");

        javax.swing.GroupLayout p_available7Layout = new javax.swing.GroupLayout(p_available7);
        p_available7.setLayout(p_available7Layout);
        p_available7Layout.setHorizontalGroup(
            p_available7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(p_available7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtRoom5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        p_available7Layout.setVerticalGroup(
            p_available7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_available7Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRoom5, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout p_dashboardLayout = new javax.swing.GroupLayout(p_dashboard);
        p_dashboard.setLayout(p_dashboardLayout);
        p_dashboardLayout.setHorizontalGroup(
            p_dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p_dashboardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p_dashboardLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(p_dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p_dashboardLayout.createSequentialGroup()
                        .addComponent(p_available, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(p_available1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(p_available2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p_dashboardLayout.createSequentialGroup()
                        .addComponent(p_available5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(p_available3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(p_available4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p_dashboardLayout.createSequentialGroup()
                        .addComponent(p_available6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(p_available7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(125, 125, 125)))
                .addGap(54, 54, 54))
        );
        p_dashboardLayout.setVerticalGroup(
            p_dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_dashboardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(50, 50, 50)
                .addGroup(p_dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(p_available1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(p_available2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(p_available, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(p_dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(p_available3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(p_available4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(p_available5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(p_dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(p_available7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(p_available6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(90, Short.MAX_VALUE))
        );

        getContentPane().add(p_dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 0, 800, 680));

        p_account.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 3, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Panel Account");

        jPanel1.setBackground(new java.awt.Color(13, 21, 63));

        jLabel26.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Nama Lengkap");

        jLabel28.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("TTL");

        jLabel34.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Jenis Kelamin");

        jLabel27.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Agama");

        txtAgama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAgamaActionPerformed(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("Status Perkawinan");

        jLabel44.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("Golongan Darah");

        cmbStatusPerkawinan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Belum Menikah", "Sudah Menikah", "Janda", "Duda" }));

        cmbJenKel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laki - laki", "Perempuan" }));

        cmbGoldar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "AB", "O" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTTL)
                    .addComponent(cmbJenKel, 0, 300, Short.MAX_VALUE)
                    .addComponent(txtNamaLengkap))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAgama)
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbStatusPerkawinan, 0, 300, Short.MAX_VALUE)
                    .addComponent(cmbGoldar, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(78, 78, 78))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAgama, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel43)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbStatusPerkawinan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel44)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbGoldar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNamaLengkap, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTTL, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbJenKel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34))
        );

        jPanel10.setBackground(new java.awt.Color(13, 21, 63));

        jLabel42.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText("RT/RW");

        jLabel41.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("Alamat");

        txtRTRW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRTRWActionPerformed(evt);
            }
        });

        jLabel45.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("Kel/Desa");

        jLabel46.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("Kecamatan");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtRTRW)
                    .addComponent(txtAlamat)
                    .addComponent(txtKelurahan)
                    .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                    .addComponent(txtKecamatan))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel41)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel42)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRTRW, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel45)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtKelurahan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel46)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtKecamatan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(13, 21, 63));

        btnSimpanData.setFont(new java.awt.Font("Trebuchet MS", 3, 36)); // NOI18N
        btnSimpanData.setText("SIMPAN DATA");
        btnSimpanData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanDataActionPerformed(evt);
            }
        });

        jLabel47.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setText("Email");

        jLabel48.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setText("No. Hp");

        jLabel49.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setText("Social Media (Instagram,Twitter,etc)");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnSimpanData, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtNoHp, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtSocialMedia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)))
                .addContainerGap(8, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel47)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel48)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNoHp, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel49)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSocialMedia, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnSimpanData)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout p_accountLayout = new javax.swing.GroupLayout(p_account);
        p_account.setLayout(p_accountLayout);
        p_accountLayout.setHorizontalGroup(
            p_accountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_accountLayout.createSequentialGroup()
                .addGroup(p_accountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p_accountLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 788, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p_accountLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(p_accountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(p_accountLayout.createSequentialGroup()
                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        p_accountLayout.setVerticalGroup(
            p_accountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p_accountLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(p_accountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p_accountLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p_accountLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        getContentPane().add(p_account, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 0, 800, 680));

        p_checkin.setBackground(new java.awt.Color(255, 255, 255));
        p_checkin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Trebuchet MS", 3, 36)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Panel Check-in");
        p_checkin.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 10, 800, 100));

        txt_standart.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        txt_standart.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_standart.setText("STANDART");

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/deluxe (1).png"))); // NOI18N
        jLabel20.setText("jLabel20");

        javax.swing.GroupLayout m_standartLayout = new javax.swing.GroupLayout(m_standart);
        m_standart.setLayout(m_standartLayout);
        m_standartLayout.setHorizontalGroup(
            m_standartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_standartLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(m_standartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 288, Short.MAX_VALUE)
                    .addComponent(txt_standart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        m_standartLayout.setVerticalGroup(
            m_standartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, m_standartLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_standart, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7))
        );

        p_checkin.add(m_standart, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, 300, 200));

        txt_deluxe.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        txt_deluxe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_deluxe.setText("DELUXE");

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/deluxe (1).png"))); // NOI18N
        jLabel21.setText("jLabel20");

        javax.swing.GroupLayout m_deluxeLayout = new javax.swing.GroupLayout(m_deluxe);
        m_deluxe.setLayout(m_deluxeLayout);
        m_deluxeLayout.setHorizontalGroup(
            m_deluxeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_deluxeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(m_deluxeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 288, Short.MAX_VALUE)
                    .addComponent(txt_deluxe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        m_deluxeLayout.setVerticalGroup(
            m_deluxeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, m_deluxeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_deluxe, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        p_checkin.add(m_deluxe, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 160, 300, 200));

        txt_suit.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        txt_suit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_suit.setText("SUIT");

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/deluxe (1).png"))); // NOI18N
        jLabel22.setText("jLabel20");

        javax.swing.GroupLayout m_suitLayout = new javax.swing.GroupLayout(m_suit);
        m_suit.setLayout(m_suitLayout);
        m_suitLayout.setHorizontalGroup(
            m_suitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_suitLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(m_suitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 288, Short.MAX_VALUE)
                    .addComponent(txt_suit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        m_suitLayout.setVerticalGroup(
            m_suitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, m_suitLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_suit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        p_checkin.add(m_suit, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 400, 300, 200));

        txt_psuit.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        txt_psuit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_psuit.setText("PRESIDENT SUIT");

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/deluxe (1).png"))); // NOI18N
        jLabel23.setText("jLabel20");

        javax.swing.GroupLayout m_psuitLayout = new javax.swing.GroupLayout(m_psuit);
        m_psuit.setLayout(m_psuitLayout);
        m_psuitLayout.setHorizontalGroup(
            m_psuitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_psuitLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(m_psuitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 288, Short.MAX_VALUE)
                    .addComponent(txt_psuit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        m_psuitLayout.setVerticalGroup(
            m_psuitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, m_psuitLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_psuit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        p_checkin.add(m_psuit, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 400, 300, 200));

        getContentPane().add(p_checkin, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 0, 800, 680));

        jMenu1.setText("Menu");

        jMenuItem1.setText("Check-In");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Check-Out");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Data Guest");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setText("Absent");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_colorTransition(int baris){
        switch(baris)
        {
            case 1:
                // animation
                btn_home.setBackground(Color.white);
                txt_home.setForeground(new Color(5, 21, 63));
                btn_dashboard.setBackground(new Color(5, 21, 63));
                txt_dashboard.setForeground(Color.white);
                btn_account.setBackground(new Color(5, 21, 63));
                txt_account.setForeground(Color.white);
                // show panel
                p_home.setVisible(true);
                p_dashboard.setVisible(false);
                p_account.setVisible(false);
                break;
            case 2:
                // animation
                btn_home.setBackground(new Color(5, 21, 63));
                txt_home.setForeground(Color.white);
                btn_dashboard.setBackground(Color.white);
                txt_dashboard.setForeground(new Color(5, 21, 63));
                btn_account.setBackground(new Color(5, 21, 63));
                txt_account.setForeground(Color.white);
                // show panel
                p_home.setVisible(false);
                p_dashboard.setVisible(true);
                p_account.setVisible(false);
                break;
            case 3:
                // animation
                btn_home.setBackground(new Color(5, 21, 63));
                txt_home.setForeground(Color.white);
                btn_dashboard.setBackground(new Color(5, 21, 63));
                txt_dashboard.setForeground(Color.white);
                btn_account.setBackground(Color.white);
                txt_account.setForeground(new Color(5, 21, 63));
                // show panel
                p_home.setVisible(false);
                p_dashboard.setVisible(false);
                p_account.setVisible(true);
                break;
            default:
                break;
        }
    }
    
    private void btn_accountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_accountMouseClicked
        // TODO add your handling code here:
        btn_colorTransition(3);
        setAccountActive();
    }//GEN-LAST:event_btn_accountMouseClicked

    private void btn_dashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dashboardMouseClicked
        // TODO add your handling code here:
        btn_colorTransition(2);
    }//GEN-LAST:event_btn_dashboardMouseClicked

    private void btn_homeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_homeMouseClicked
        // TODO add your handling code here:
        btn_colorTransition(1);
    }//GEN-LAST:event_btn_homeMouseClicked

    private void btn_logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_logoutMouseClicked
        P_login logout = new P_login();
        logout.setVisible(true);
        dispose();
    }//GEN-LAST:event_btn_logoutMouseClicked

    private void btn_checkinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_checkinMouseClicked
        // TODO add your handling code here:
        P_checkin a = new P_checkin(this.id_karyawan);
        a.setVisible(true);
        dispose();
    }//GEN-LAST:event_btn_checkinMouseClicked

    private void btn_absent1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_absent1MouseClicked
        // TODO add your handling code here:
        P_absen a = new P_absen(id_karyawan);
        a.setVisible(true);
        dispose();
    }//GEN-LAST:event_btn_absent1MouseClicked

    private void txtAgamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAgamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAgamaActionPerformed

    private void btnSimpanDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanDataActionPerformed
        // TODO add your handling code here:
        updateDataAccount();
    }//GEN-LAST:event_btnSimpanDataActionPerformed

    private void txtRTRWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRTRWActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRTRWActionPerformed

    private void btn_absentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_absentMouseClicked
        // TODO add your handling code here:
        P_checkout a = new P_checkout(id_karyawan);
        a.setVisible(true);
        dispose();
    }//GEN-LAST:event_btn_absentMouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        P_checkin a  = new P_checkin(id_karyawan);
        a.setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void btn_reservation1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_reservation1MouseClicked
        // TODO add your handling code here:
        P_guest a = new P_guest(id_karyawan);
        a.setVisible(true);
        dispose();
    }//GEN-LAST:event_btn_reservation1MouseClicked

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        P_checkout a  = new P_checkout(id_karyawan);
        a.setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        P_guest a  = new P_guest(id_karyawan);
        a.setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        P_absen a  = new P_absen(id_karyawan);
        a.setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

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
            java.util.logging.Logger.getLogger(P_menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(P_menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(P_menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(P_menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new P_menu().setVisible(true);
                P_login redirect = new P_login();
                redirect.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnSimpanData;
    private javax.swing.JPanel btn_absent;
    private javax.swing.JPanel btn_absent1;
    private javax.swing.JPanel btn_account;
    private javax.swing.JPanel btn_checkin;
    private javax.swing.JPanel btn_dashboard;
    private javax.swing.JPanel btn_home;
    private javax.swing.JPanel btn_logout;
    private javax.swing.JPanel btn_reservation1;
    private javax.swing.JComboBox<String> cmbGoldar;
    private javax.swing.JComboBox<String> cmbJenKel;
    private javax.swing.JComboBox<String> cmbStatusPerkawinan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel m_deluxe;
    private javax.swing.JPanel m_psuit;
    private javax.swing.JPanel m_standart;
    private javax.swing.JPanel m_suit;
    private javax.swing.JLabel name_profile;
    private javax.swing.JPanel p_account;
    private javax.swing.JPanel p_available;
    private javax.swing.JPanel p_available1;
    private javax.swing.JPanel p_available2;
    private javax.swing.JPanel p_available3;
    private javax.swing.JPanel p_available4;
    private javax.swing.JPanel p_available5;
    private javax.swing.JPanel p_available6;
    private javax.swing.JPanel p_available7;
    private javax.swing.JPanel p_checkin;
    private javax.swing.JPanel p_dashboard;
    private javax.swing.JPanel p_home;
    private javax.swing.JPanel p_menu;
    private javax.swing.JLabel pic_profile;
    private javax.swing.JLabel title_profile;
    private javax.swing.JTextField txtAgama;
    private javax.swing.JTextField txtAlamat;
    private javax.swing.JLabel txtAvailableRoom;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtKecamatan;
    private javax.swing.JTextField txtKelurahan;
    private javax.swing.JTextField txtNamaLengkap;
    private javax.swing.JTextField txtNoHp;
    private javax.swing.JLabel txtNotAvailableRoom;
    private javax.swing.JTextField txtRTRW;
    private javax.swing.JLabel txtReservedRoom;
    private javax.swing.JLabel txtRoom1;
    private javax.swing.JLabel txtRoom2;
    private javax.swing.JLabel txtRoom3;
    private javax.swing.JLabel txtRoom4;
    private javax.swing.JLabel txtRoom5;
    private javax.swing.JTextField txtSocialMedia;
    private javax.swing.JTextField txtTTL;
    private javax.swing.JLabel txtType1;
    private javax.swing.JLabel txtType2;
    private javax.swing.JLabel txtType3;
    private javax.swing.JLabel txtType4;
    private javax.swing.JLabel txtType5;
    private javax.swing.JLabel txt_account;
    private javax.swing.JLabel txt_account1;
    private javax.swing.JLabel txt_dashboard;
    private javax.swing.JLabel txt_deluxe;
    private javax.swing.JLabel txt_home;
    private javax.swing.JLabel txt_psuit;
    private javax.swing.JLabel txt_standart;
    private javax.swing.JLabel txt_suit;
    // End of variables declaration//GEN-END:variables
}
