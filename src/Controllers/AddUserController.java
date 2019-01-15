package Controllers;

import Models.BD.UsersImp;
import Views.Desktop;
import Views.Users.AddUser;
import javax.swing.JLayeredPane;

/**
 *
 * @author MrRainx
 */
public class AddUserController {

    private UsersImp user;
    private AddUser view;
    private Desktop desktop;

    public AddUserController(UsersImp user, AddUser view, Desktop desktop) {
        this.user = user;
        this.view = view;
        this.desktop = desktop;
    }
    
    /*
        INITs
    */
    
    public void Init(){
        this.view.setVisible(true);
        
        this.view.show();
        this.desktop.getBgDesktop().remove(view);
        this.desktop.getBgDesktop().add(view, JLayeredPane.DEFAULT_LAYER);
        
        
        
    }
    
    /*
        SUPPORT METHODS
    */
    
    /*
        EVENTS
    */
    
}
