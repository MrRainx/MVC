package Run;

import controller.LoginCTR;
import model.dao.UsersImp;
import view.Login;
import javax.swing.UIManager;

/**
 *
 * @author MrRainx
 */
public class Start {

    public static void main(String[] args) {

        try {

            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        /*
        
        /*
        
         */

        java.awt.EventQueue.invokeLater(() -> {
            
            
            LoginCTR login = new LoginCTR(new UsersImp(), new Login());
            login.Init();
            

            /*
            DesktopController desktop = new DesktopController(new User(), new Desktop());
            
            desktop.Init();
             */
        });

    }

}
