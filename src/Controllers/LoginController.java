package Controllers;

import Models.BD.UsersImp;
import Models.DTO.UserDTO;
import Views.Desktop;
import Views.Login;
import Controllers.Libraries.Effects;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author MrRainx
 */
public class LoginController {

    private final UsersImp user;

    private final Login view;

    public LoginController(UsersImp user, Login login) {
        this.user = user;
        this.view = login;
    }

    /*
        INITs
     */
    public void Init() {

        this.view.setVisible(true);
        this.view.setLocationRelativeTo(null);

        this.view.getBtnEnter().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnEnterOnMouseClicked(e);
            }
        });

        this.view.getTxtPassword().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                txtPasswordOnKeyRelessed(e);
            }
        });

        Effects.Hover(this.view.getBtnEnter(), Color.orange, this.view.getBtnEnter().getBackground());

        Effects.exit(this.view.getBtnClose());
        Effects.minimize(this.view.getBtnMinimize(), this.view);
        Effects.moveableFrame(this.view);
        
    }

    /*
        SUPPORT METHODS
     */
    private void Login() {
        user.setUserName(this.view.getTxtUsername().getText());

        user.setPassword(this.view.getTxtPassword().getText());

        List<UserDTO> UsersList = user.SelectOne();

        if (UsersList.isEmpty()) {

            JOptionPane.showMessageDialog(null, "Incorrect User or Password");

        } else {

            user.setIdUser(UsersList.get(0).getIdUser());
            user.setName(UsersList.get(0).getName());
            user.setPhoto(UsersList.get(0).getPhoto());

            ImageIcon image = new ImageIcon(user.getPhoto());

            Icon icon = new ImageIcon(image.getImage().getScaledInstance(160, 90, Image.SCALE_DEFAULT));

            JOptionPane.showMessageDialog(null,
                    user.getName(),
                    "YOU HAS BEEN LOGIN AS: ",
                    JOptionPane.OK_OPTION,
                    icon
            );
            DesktopController desktop = new DesktopController(user, new Desktop());
            desktop.Init();
            this.view.setVisible(false);
        }
    }

    /*
        EVENTS
     */
    private void btnEnterOnMouseClicked(MouseEvent e) {
        Login();
    }

    private void txtPasswordOnKeyRelessed(KeyEvent e) {
        if (e.getKeyCode() == 10) {
            Login();
        }

    }

}
