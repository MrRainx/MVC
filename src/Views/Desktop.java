package Views;

import Views.Users.UsersView;
import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

/**
 *
 * @author MrRainx
 */
public class Desktop extends javax.swing.JFrame {

    UsersView user = null;

    public Desktop() {
        initComponents();
    }

    public UsersView getUser() {
        return user;
    }

    public void setUser(UsersView user) {
        this.user = user;
    }

    public JMenuBar getNavBar() {
        return NavBar;
    }

    public void setNavBar(JMenuBar NavBar) {
        this.NavBar = NavBar;
    }

    public JDesktopPane getBgDesktop() {
        return bgDesktop;
    }

    public void setBgDesktop(JDesktopPane bgDesktop) {
        this.bgDesktop = bgDesktop;
    }

    public JMenu getBtnPersons() {
        return btnPersons;
    }

    public void setBtnPersons(JMenu btnPersons) {
        this.btnPersons = btnPersons;
    }

    public JMenu getBtnUser() {
        return btnUser;
    }

    public void setBtnUser(JMenu btnUser) {
        this.btnUser = btnUser;
    }

    public JMenu getBtnIngresarUsuario() {
        return btnIngresarUsuario;
    }

    public void setBtnIngresarUsuario(JMenu btnIngresarUsuario) {
        this.btnIngresarUsuario = btnIngresarUsuario;
    }
    
    
    
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgDesktop = new javax.swing.JDesktopPane();
        NavBar = new javax.swing.JMenuBar();
        btnPersons = new javax.swing.JMenu();
        btnUser = new javax.swing.JMenu();
        btnIngresarUsuario = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(960, 540));
        setMinimumSize(new java.awt.Dimension(960, 540));
        setSize(new java.awt.Dimension(960, 540));

        javax.swing.GroupLayout bgDesktopLayout = new javax.swing.GroupLayout(bgDesktop);
        bgDesktop.setLayout(bgDesktopLayout);
        bgDesktopLayout.setHorizontalGroup(
            bgDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 960, Short.MAX_VALUE)
        );
        bgDesktopLayout.setVerticalGroup(
            bgDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 519, Short.MAX_VALUE)
        );

        btnPersons.setText("Persons");
        NavBar.add(btnPersons);

        btnUser.setText("Users");
        NavBar.add(btnUser);

        btnIngresarUsuario.setText("Agregar Usuario");
        NavBar.add(btnIngresarUsuario);

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
    private javax.swing.JMenu btnIngresarUsuario;
    private javax.swing.JMenu btnPersons;
    private javax.swing.JMenu btnUser;
    // End of variables declaration//GEN-END:variables
}
