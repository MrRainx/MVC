package Controllers;


import Models.BD.PersonImp;
import Models.BD.UsersImp;
import Models.User;
import Views.Desktop;
import Views.Insert;
import Views.Login;
import Views.Static.Effects;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author MrRainx
 */
public class LoginController {

    private UsersImp user;

    private Login login;

    public LoginController(UsersImp user, Login login) {
        this.user = user;
        this.login = login;
    }

    /*
        INITs
     */
    public void Init() {

        this.login.setVisible(true);
        this.login.setLocationRelativeTo(null);
        
        this.login.getBtnEnter().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnEnterOnMouseClicked(e);
            }
        });
        
        Effects.exit(this.login.getBtnClose());
        Effects.minimize(this.login.getBtnMinimize(), this.login);
    }

    /*
        SUPPORT METHODS
     */
    
 
    /*
        EVENTS
     */
    private void btnEnterOnMouseClicked(MouseEvent e) {
        
        user.setUserName(this.login.getTxtUsername().getText());
        
        user.setPassword(this.login.getTxtPassword().getText());
        
        List<User>UsersList = user.SelectOne();
        
        if (UsersList.isEmpty()){
            JOptionPane.showMessageDialog(null, "Incorrect User or Password");
            
            
        } else {
            
            
            
            user = (UsersImp) UsersList.get(0);
            
            JOptionPane.showMessageDialog(null, "YOU HAS BEEN LOGIN AS: "+user.getName());
            
            DesktopController desktop = new DesktopController(new User(), new Desktop());
            desktop.Init();
            
            
        }
        
        
        
    }

}
