package Run;

import Controllers.FindController;
import Controllers.InsertController;
import Controllers.LoginController;
import Models.BD.PersonImp;
import Models.BD.UsersImp;
import Views.Desktop;
import Views.Find;
import Views.Insert;
import Views.Login;
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


            LoginController login = new LoginController(new UsersImp(), new Login());
            login.Init();

            
        });

    }

}
