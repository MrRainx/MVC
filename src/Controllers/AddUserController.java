package Controllers;

import Models.BD.UsersImp;
import Views.Desktop;
import Views.Users.AddUser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
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
    public void Init() {
        this.view.setVisible(true);

        this.view.show();
        this.desktop.getBgDesktop().remove(view);
        this.desktop.getBgDesktop().add(view, JLayeredPane.MODAL_LAYER);
        this.view.setAlignmentX(100);

        this.view.getBtnAdd().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnAddOnMouseClicked(e);
            }

        });

        this.view.getBtnFind().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnFindOnMouseClicked(e);
            }

        });

        this.view.getFileFinder().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileChosseActionPerformance(e);
            }
        });

    }

    /*
        SUPPORT METHODS
     */
 /*
        EVENTS
     */
    private void btnAddOnMouseClicked(MouseEvent e) {

    }

    private void btnFindOnMouseClicked(MouseEvent e) {

        this.view.getBgFile().setVisible(true);
        this.view.getBgFile().setLocationRelativeTo(this.desktop);
        
    }

    private void FileChosseActionPerformance(ActionEvent evt) {

        JFileChooser findFile = (JFileChooser) evt.getSource();
        String command = evt.getActionCommand();
        if (command.equals(JFileChooser.APPROVE_SELECTION)) {

            FileInputStream input = null;
            try {
                
                URI uri = findFile.getSelectedFile().toURI();
                input = new FileInputStream(new File(uri));
                ImageIcon photo = new ImageIcon(ImageIO.read(input));
                input.close();
                this.view.getLbImage().setIcon(photo);
                
                
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    input.close();
                } catch (IOException ex) {
                    Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }else if (command.equals(JFileChooser.CANCEL_SELECTION)){
            this.view.getBgFile().dispose();
        }
    }

}
