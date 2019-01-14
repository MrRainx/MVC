package Controllers;


import Models.BD.PersonImp;
import Models.BD.UsersImp;
import Models.User;
import Views.Insert;
import Views.Login;
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
            JOptionPane.showMessageDialog(null, UsersList.get(0));
            
            InsertController insert = new InsertController(new PersonImp(), new Insert());
            insert.Init();
            insert.InitInsert();
            
        }
        
        
        
    }

}
