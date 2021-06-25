package gui;

import db.DBConnection;
import java.awt.HeadlessException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.User;

public final class Booking extends javax.swing.JFrame {
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private final int userId;
    
    public Booking(int userId) throws SQLException {
        this.userId = userId;
        this.con = DBConnection.getConnection();
        initComponents();
        getTableData();
        clearFields();
    }
    
    //untuk update data dalam tabel
    private void getTableData(){
    DefaultTableModel model = (DefaultTableModel) sewaTable.getModel();
    model.getDataVector().removeAllElements();
    model.fireTableDataChanged();
        try {
            Statement stmt = con.createStatement();
            String sql = "select * from booking inner join user on booking.user_id=user.user_id";
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                Object[] o = new Object[7];
                o[0] = rs.getString("booking_id");
                o[1] = rs.getString("name");
                o[2] = rs.getString("notelp");
                o[3] = rs.getString("date");
                o[4] = rs.getString("jamAwal");
                o[5] = rs.getString("jamAkhir");
                o[6] = rs.getString("username");
                model.addRow(o);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }       
    }
    
    //fungsi pengecekan jam
    private boolean isTabrakan(LocalTime nTime, LocalTime jamAwal, LocalTime jamAkhir) {
        if(nTime.isAfter(jamAwal) && nTime.isBefore(jamAkhir)){
        return true;
        } 
        return false;
    }
    //mengecek jam apabila sama dengan data yg sudah ada
    private boolean checkJam(String jamAwal, String jamAkhir, String date) throws ParseException{
        ArrayList<String> tgl = new ArrayList<>();
        ArrayList<LocalTime> awal = new ArrayList<>();
        ArrayList<LocalTime> akhir = new ArrayList<>();
        String sq = "SELECT `jamAwal`, `jamAkhir`, `date` FROM `booking`";
        try{
            PreparedStatement pst = con.prepareStatement(sq);
            ResultSet rst = pst.executeQuery();
                while (rst.next()) {
                awal.add(rst.getTime("jamAwal").toLocalTime());
                akhir.add(rst.getTime("jamAkhir").toLocalTime());
                tgl.add(rst.getString("date"));
                }
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        LocalTime x = LocalTime.parse(jamAwal);
        LocalTime y = LocalTime.parse(jamAkhir);
        int i = 0;
        while (i<awal.size()){
            if(date.equals(tgl.get(i))) {
                //cek apakah jam awal dan jam akhir yang di input sama dengan data yang sudah ada di database
                if (x.equals(awal.get(i)) || y.equals(akhir.get(i))) {
                    return true;
                }
                else if (isTabrakan(x, awal.get(i),akhir.get(i))) {
                    return true;
                }
                else if (isTabrakan(y, awal.get(i),akhir.get(i))){
                    return true;
                }
            } i++;
        } return false;
    }
    
    //mengecek jam apabila jam awal lebih besar daripada jam akhir
    private boolean checkJamAkhir(String a, String b) throws ParseException{
        LocalTime x = LocalTime.parse(a);
        LocalTime y = LocalTime.parse(b);
        return x.compareTo(y)==1;
    }
    
    //menghapus fields
    private void clearFields(){
        getName.setText(null);
        getNumber.setText(null);
        getDate.setDate(null);
        getJamAwal.setSelectedIndex(0);
        getJamAkhir.setSelectedIndex(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        notelpLabel = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();
        SaveButton = new javax.swing.JButton();
        DeleteButton = new javax.swing.JButton();
        getDate = new com.toedter.calendar.JDateChooser();
        getName = new javax.swing.JTextField();
        getNumber = new javax.swing.JTextField();
        getJamAwal = new javax.swing.JComboBox<>();
        sdLabel = new javax.swing.JLabel();
        getJamAkhir = new javax.swing.JComboBox<>();
        EditButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        sewaTable = new javax.swing.JTable();
        ResetButton = new javax.swing.JButton();
        logOutButton = new javax.swing.JButton();
        changePasswordButton = new javax.swing.JButton();
        deleteAccButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        titleLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        titleLabel.setText("Booking Lapangan");

        nameLabel.setText("Nama");

        notelpLabel.setText("No. Telp");

        dateLabel.setText("Tanggal Pinjam");

        timeLabel.setText("Waktu");

        SaveButton.setText("Save");
        SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });

        DeleteButton.setText("Delete");
        DeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteButtonActionPerformed(evt);
            }
        });

        getDate.setDateFormatString("yyyy-MM-dd");

        getJamAwal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", " " }));
        getJamAwal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getJamAwalActionPerformed(evt);
            }
        });

        sdLabel.setText("s.d.");

        getJamAkhir.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", " " }));
        getJamAkhir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getJamAkhirActionPerformed(evt);
            }
        });

        EditButton.setText("Edit");
        EditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditButtonActionPerformed(evt);
            }
        });

        sewaTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Booking", "Nama", "No. Telp", "Tanggal Pinjam", "Jam Mulai", "Jam Akhir", "Pegawai"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        sewaTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sewaTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(sewaTable);
        if (sewaTable.getColumnModel().getColumnCount() > 0) {
            sewaTable.getColumnModel().getColumn(3).setResizable(false);
            sewaTable.getColumnModel().getColumn(5).setResizable(false);
        }

        ResetButton.setText("Reset");
        ResetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetButtonActionPerformed(evt);
            }
        });

        logOutButton.setText("Log Out");
        logOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutButtonActionPerformed(evt);
            }
        });

        changePasswordButton.setText("Change Password");
        changePasswordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changePasswordButtonActionPerformed(evt);
            }
        });

        deleteAccButton.setText("Delete Account");
        deleteAccButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteAccButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nameLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(SaveButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(notelpLabel, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(timeLabel, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ResetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(EditButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(DeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(getName)
                            .addComponent(getNumber)
                            .addComponent(getDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(getJamAwal, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(sdLabel)
                                .addGap(12, 12, 12)
                                .addComponent(getJamAkhir, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(24, 24, 24))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(titleLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(logOutButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(changePasswordButton, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(deleteAccButton)
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(logOutButton)
                            .addComponent(changePasswordButton)
                            .addComponent(deleteAccButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(titleLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nameLabel)
                            .addComponent(getName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(notelpLabel)
                            .addComponent(getNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(getDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(timeLabel)
                            .addComponent(getJamAwal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sdLabel)
                            .addComponent(getJamAkhir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(95, 95, 95)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SaveButton)
                            .addComponent(ResetButton)
                            .addComponent(EditButton)
                            .addComponent(DeleteButton)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void getJamAwalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getJamAwalActionPerformed
    }//GEN-LAST:event_getJamAwalActionPerformed

    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButtonActionPerformed
        //aksi save button untuk menyimpan data
        User user  = new User();
        String query = "insert into booking (user_id,name,notelp,date,jamAwal,jamAkhir) values (?,?,?,?,?,?)";
        try{
            ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setString(2, getName.getText());
            ps.setString(3, getNumber.getText());
            String Date = ((JTextField)getDate.getDateEditor().getUiComponent()).getText();
            ps.setString(4, Date);
            String jamAwal = (getJamAwal.getSelectedItem().toString());
            ps.setString(5, jamAwal);
            String jamAkhir = getJamAkhir.getSelectedItem().toString();
            if(!checkJamAkhir(jamAkhir, jamAwal)){
                JOptionPane.showMessageDialog(null, "Tidak dapat input jam sama dengan atau akhir kurang dari jam waktu mulai");
                ps.close();
            } else if(checkJam(jamAwal, jamAkhir, Date) == true){
                JOptionPane.showMessageDialog(null, "Waktu sudah dibooking");
                ps.close();
            } else{
                ps.setString(6, jamAkhir);
            }
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
        }
        catch(HeadlessException | SQLException | ParseException e){
            System.out.println(e);
        }
        getTableData();
    }//GEN-LAST:event_SaveButtonActionPerformed

    private void DeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteButtonActionPerformed
        //untuk menghapus data
        int row = sewaTable.getSelectedRow();
        String cell = sewaTable.getModel().getValueAt(row, 0).toString();
        String sql2 = ("delete from booking where booking_id = ?");
        try{
            ps = con.prepareStatement(sql2);
            ps.setString(1, cell);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"Booking berhasil dihapus.");
        } catch(HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
        getTableData();
    }//GEN-LAST:event_DeleteButtonActionPerformed

    private void EditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditButtonActionPerformed
        //untuk mengeddit data
        User user = new User();
        int row = sewaTable.getSelectedRow();
        String cell = sewaTable.getModel().getValueAt(row, 0).toString();
        String sql3 = "update booking set user_id=?, name=?, notelp=?, date=?, jamAwal=?, jamAkhir=? where booking_id=?";
        try{
            ps= con.prepareStatement(sql3);
            ps.setInt(1, userId);
            ps.setString(2, getName.getText());
            ps.setString(3, getNumber.getText());
            String Date = ((JTextField)getDate.getDateEditor().getUiComponent()).getText();
            ps.setString(4, Date);
            String jamAwal = (getJamAwal.getSelectedItem().toString());
            ps.setString(5, jamAwal);
            String jamAkhir = getJamAkhir.getSelectedItem().toString();
            if(!checkJamAkhir(jamAkhir, jamAwal)){
                JOptionPane.showMessageDialog(null, "Tidak dapat input jam akhir sama dengan atau kurang dari jam waktu mulai");
                ps.close();
            } else if(checkJam(jamAwal, jamAkhir, Date) == true){
                JOptionPane.showMessageDialog(null, "Waktu sudah dibooking");
                ps.close();
            } else{
                ps.setString(6, jamAkhir);
            }
            ps.setString(7, cell);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data berhasil di edit");
        }
        catch(SQLException e){
            System.out.println(e);
        } 
        catch (ParseException ex) {
            System.out.println(ex);
        }
        getTableData();              
    }//GEN-LAST:event_EditButtonActionPerformed

    private void ResetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetButtonActionPerformed
        //untuk mereset fields
        clearFields();
        getTableData();
    }//GEN-LAST:event_ResetButtonActionPerformed

    private void sewaTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sewaTableMouseClicked
        //untuk menampilkan data tabel yang di klik ke fields
        int row = sewaTable.getSelectedRow();
        String selection = sewaTable.getModel().getValueAt(row,0).toString();
        String sql4 = "select * from booking where booking_id =?";
        try{
            PreparedStatement p = con.prepareStatement(sql4);
            p.setString(1, selection);
            ResultSet r = p.executeQuery();
            if(r.next()){
                getName.setText(r.getString("name"));
                getNumber.setText(r.getString("notelp")); 
                getDate.setDate(r.getDate("date"));
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                Date a = r.getTime("jamAwal");
                Date b = r.getTime("jamAkhir");
                String begin = sdf.format(a);
                String last = sdf.format(b);
                getJamAwal.setSelectedItem(begin);
                getJamAkhir.setSelectedItem(last);
            }
        } 
        catch(SQLException e) {
            System.out.println(e);
        }
        
    }//GEN-LAST:event_sewaTableMouseClicked

    private void getJamAkhirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getJamAkhirActionPerformed
    }//GEN-LAST:event_getJamAkhirActionPerformed

    private void logOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutButtonActionPerformed
        //keluar dari booking dan kembail ke login page
        this.dispose();
        new Login().setVisible(true);
    }//GEN-LAST:event_logOutButtonActionPerformed

    private void changePasswordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changePasswordButtonActionPerformed
       //memanggil ChangePassword untuk mengubah password
        new ChangePassword(this.userId).setVisible(true);
    }//GEN-LAST:event_changePasswordButtonActionPerformed

    private void deleteAccButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteAccButtonActionPerformed
        //menghapus akun yang sedang ter-login
        User user = new User();
        try {
            user.delete(userId);
            this.dispose();
            JOptionPane.showMessageDialog(null, "akun berhasil dihapus, kembali ke login page.");
            new Login().setVisible(true);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }//GEN-LAST:event_deleteAccButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DeleteButton;
    private javax.swing.JButton EditButton;
    private javax.swing.JButton ResetButton;
    private javax.swing.JButton SaveButton;
    private javax.swing.JButton changePasswordButton;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JButton deleteAccButton;
    private com.toedter.calendar.JDateChooser getDate;
    private javax.swing.JComboBox<String> getJamAkhir;
    private javax.swing.JComboBox<String> getJamAwal;
    private javax.swing.JTextField getName;
    private javax.swing.JTextField getNumber;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton logOutButton;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel notelpLabel;
    private javax.swing.JLabel sdLabel;
    private javax.swing.JTable sewaTable;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
} 
