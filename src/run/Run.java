package run;

import controllers.LoginController;
import models.jdbc.UserImp;
import views.Login;
import javax.swing.UIManager;

/**
 *
 * @author MrRainx
 */
public class Run {

    public static void main(String[] args) {

        try {

            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Run.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        /*
        
         */

        java.awt.EventQueue.invokeLater(() -> {
            
            
            LoginController login = new LoginController(new UserImp(), new Login());
            login.Init();
            

            /*
            DesktopController desktop = new DesktopController(new User(), new Desktop());
            
            desktop.Init();
             */
        });

    }

}
