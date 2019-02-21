/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.users;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author MrRainx
 */
public class UserForm extends javax.swing.JInternalFrame {
    
    public UserForm() {
        initComponents();
    }

    public JPanel getBgContainer() {
        return bgContainer;
    }

    public JDialog getBgFile() {
        return bgFile;
    }

    public JButton getBtnFind() {
        return btnFind;
    }

    public JButton getBtnSave() {
        return btnSave;
    }

    public JFileChooser getFileFinder() {
        return fileFinder;
    }

    public JLabel getLbImage() {
        return lbImage;
    }

    public JTextField getTxtFilePath() {
        return txtFilePath;
    }

    public JTextField getTxtNames() {
        return txtNames;
    }

    public JTextField getTxtPassword() {
        return txtPassword;
    }

    public JTextField getTxtUserName() {
        return txtUserName;
    }
    
    

    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgFile = new javax.swing.JDialog();
        fileFinder = new javax.swing.JFileChooser();
        bgContainer = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        txtPassword = new javax.swing.JTextField();
        txtUserName = new javax.swing.JTextField();
        txtNames = new javax.swing.JTextField();
        txtFilePath = new javax.swing.JTextField();
        btnFind = new javax.swing.JButton();
        lbImage = new javax.swing.JLabel();

        bgFile.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        bgFile.setMinimumSize(new java.awt.Dimension(623, 397));
        bgFile.setSize(new java.awt.Dimension(623, 397));

        javax.swing.GroupLayout bgFileLayout = new javax.swing.GroupLayout(bgFile.getContentPane());
        bgFile.getContentPane().setLayout(bgFileLayout);
        bgFileLayout.setHorizontalGroup(
            bgFileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fileFinder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        bgFileLayout.setVerticalGroup(
            bgFileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fileFinder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setClosable(true);
        setIconifiable(true);

        bgContainer.setBackground(new java.awt.Color(51, 51, 51));
        bgContainer.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "User Form", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Names:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("UserName:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Password:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Photo:");

        btnSave.setBackground(new java.awt.Color(0, 153, 153));
        btnSave.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("Save");
        btnSave.setContentAreaFilled(false);
        btnSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSave.setOpaque(true);

        txtPassword.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtUserName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtNames.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtFilePath.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        btnFind.setBackground(new java.awt.Color(0, 153, 153));
        btnFind.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnFind.setForeground(new java.awt.Color(255, 255, 255));
        btnFind.setText("Find Image");
        btnFind.setContentAreaFilled(false);
        btnFind.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFind.setOpaque(true);

        lbImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        javax.swing.GroupLayout bgContainerLayout = new javax.swing.GroupLayout(bgContainer);
        bgContainer.setLayout(bgContainerLayout);
        bgContainerLayout.setHorizontalGroup(
            bgContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgContainerLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPassword))
                    .addGroup(bgContainerLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNames))
                    .addGroup(bgContainerLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtUserName))
                    .addGroup(bgContainerLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(bgContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bgContainerLayout.createSequentialGroup()
                                .addComponent(txtFilePath, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(bgContainerLayout.createSequentialGroup()
                                .addGroup(bgContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(163, 163, 163)))))
                .addContainerGap())
        );
        bgContainerLayout.setVerticalGroup(
            bgContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNames, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(bgContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(bgContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(bgContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFilePath, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lbImage, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bgContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bgContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bgContainer;
    private javax.swing.JDialog bgFile;
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnSave;
    private javax.swing.JFileChooser fileFinder;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lbImage;
    private javax.swing.JTextField txtFilePath;
    private javax.swing.JTextField txtNames;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
