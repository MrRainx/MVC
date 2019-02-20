package controller;

import model.dao.UsersImp;
import model.dto.UserDTO;
import view.Desktop;
import view.Login;
import controller.libraries.Effects;
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
public class LoginCTR {

    private final UsersImp user;

    private final Login view;

    public LoginCTR(UsersImp user, Login login) {
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

        Effects.colorHover(this.view.getBtnEnter(), Color.orange, this.view.getBtnEnter().getBackground());

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

        List<UserDTO> UsersList = user.selectWhereUsernameAndPassword();

        if (UsersList.isEmpty()) {

            JOptionPane.showMessageDialog(null, "Incorrect User or Password");

        } else {

            user.setIdUser(UsersList.get(0).getIdUser());
            user.setName(UsersList.get(0).getName());
            ImageIcon image = null;
            Icon icon = null;

            if (UsersList.get(0).getPhoto() != null) {
                user.setPhoto(UsersList.get(0).getPhoto());
                image = new ImageIcon(user.getPhoto());
                icon = new ImageIcon(image.getImage().getScaledInstance(160, 90, Image.SCALE_DEFAULT));
            }

            JOptionPane.showMessageDialog(null,
                    user.getName(),
                    "YOU HAS BEEN LOGIN AS: ",
                    JOptionPane.OK_OPTION,
                    icon
            );
            DesktopCTR desktop = new DesktopCTR(user, new Desktop());
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
