package gui;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.User;

public class ChangePassword extends javax.swing.JFrame {
    private final int userId;
    
//    public ChangePassword() {
//        initComponents();
//    }
    
    public ChangePassword(int userId) {
        initComponents();
        this.userId = userId;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        changePassLabel = new javax.swing.JLabel();
        newPassLabel = new javax.swing.JLabel();
        newrPassLabel = new javax.swing.JLabel();
        ChangePassButton = new javax.swing.JButton();
        inputRNewPass = new javax.swing.JPasswordField();
        inputNewPass = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        changePassLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        changePassLabel.setText("Change Password");

        newPassLabel.setText("New Password");

        newrPassLabel.setText("Repeat New Password");

        ChangePassButton.setText("Change Password");
        ChangePassButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChangePassButtonActionPerformed(evt);
            }
        });

        inputRNewPass.setText("jPasswordField1");

        inputNewPass.setText("jPasswordField1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(newrPassLabel)
                            .addComponent(newPassLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(inputNewPass, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                            .addComponent(inputRNewPass)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(changePassLabel)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(ChangePassButton)
                .addGap(89, 89, 89))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(changePassLabel)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newPassLabel)
                    .addComponent(inputNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newrPassLabel)
                    .addComponent(inputRNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(ChangePassButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ChangePassButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChangePassButtonActionPerformed
        User user = new User();
        String newPass = String.valueOf(inputNewPass.getPassword());
        String newRPass = String.valueOf(inputRNewPass.getPassword());
        
        if(newPass.equals(newRPass)) {
            try {
                user.update(this.userId, newPass);
                JOptionPane.showMessageDialog(null, "Password berhasil diganti");
                this.dispose();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ulang password baru tidak sesuai");
            inputNewPass.setText("");
            inputRNewPass.setText("");
        }
        
    }//GEN-LAST:event_ChangePassButtonActionPerformed

//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(() -> {
//            new ChangePassword().setVisible(true);
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ChangePassButton;
    private javax.swing.JLabel changePassLabel;
    private javax.swing.JPasswordField inputNewPass;
    private javax.swing.JPasswordField inputRNewPass;
    private javax.swing.JLabel newPassLabel;
    private javax.swing.JLabel newrPassLabel;
    // End of variables declaration//GEN-END:variables
}
