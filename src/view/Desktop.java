package view;

import view.users.UserList;
import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

/**
 *
 * @author MrRainx
 */
public class Desktop extends javax.swing.JFrame {
    

    public Desktop() {
        initComponents();
    }
    

    public JMenuBar getNavBar() {
        return NavBar;
    }


    public JDesktopPane getBgDesktop() {
        return bgDesktop;
    }



    public JMenu getBtnPersons() {
        return btnPersons;
    }



    public JMenu getBtnListaUsuarios() {
        return btnUser;
    }



    
    
    
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgDesktop = new javax.swing.JDesktopPane();
        NavBar = new javax.swing.JMenuBar();
        btnPersons = new javax.swing.JMenu();
        btnUser = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(960, 540));
        setSize(new java.awt.Dimension(960, 540));

        bgDesktop.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout bgDesktopLayout = new javax.swing.GroupLayout(bgDesktop);
        bgDesktop.setLayout(bgDesktopLayout);
        bgDesktopLayout.setHorizontalGroup(
            bgDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 960, Short.MAX_VALUE)
        );
        bgDesktopLayout.setVerticalGroup(
            bgDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 514, Short.MAX_VALUE)
        );

        btnPersons.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnPersons.setText("Persons");
        btnPersons.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        NavBar.add(btnPersons);

        btnUser.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnUser.setText("Users");
        btnUser.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        NavBar.add(btnUser);

        setJMenuBar(NavBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bgDesktop)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bgDesktop, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar NavBar;
    private javax.swing.JDesktopPane bgDesktop;
    private javax.swing.JMenu btnPersons;
    private javax.swing.JMenu btnUser;
    // End of variables declaration//GEN-END:variables
}
