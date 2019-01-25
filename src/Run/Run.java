package Run;

import Controllers.DesktopController;
import Controllers.IngresarSistemaController;
import Controllers.LoginController;
import Models.BD.UsersImp;
import Models.BD.UsuarioImp;
import Models.User;
import Views.Desktop;
import Views.Ingresar;
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

            IngresarSistemaController ingresar = new IngresarSistemaController(new Ingresar(), new UsuarioImp());
            
            ingresar.Init();
            
            

            /*
            DesktopController desktop = new DesktopController(new User(), new Desktop());
            
            desktop.Init();
             */
        });

    }

}
