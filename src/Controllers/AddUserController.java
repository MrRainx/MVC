package Controllers;

import Controllers.Libraries.ImgLib;
import Models.BD.UsersImp;
import Views.Desktop;
import Views.Users.AddUser;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

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

        this.view.getFileFinder().addActionListener((ActionEvent e) -> {
            FileChosseActionPerformance(e);
        });

    }

    /*
        SUPPORT METHODS
     */
     /*
        EVENTS
     */
    private void btnAddOnMouseClicked(MouseEvent e) {
        
        
        this.user.setUserName(this.view.getTxtUserName().getText());
        this.user.setPassword(this.view.getTxtPassword().getText());
        this.user.setName(this.view.getTxtNames().getText());
        //Photo has been added in JFC
        this.user.Insert();
        
        
    }

    private void btnFindOnMouseClicked(MouseEvent e) {

        this.view.getBgFile().setVisible(true);
        this.view.getBgFile().setLocationRelativeTo(this.desktop);

    }

    private void FileChosseActionPerformance(ActionEvent evt) {

        JFileChooser findFile = (JFileChooser) evt.getSource();
        String command = evt.getActionCommand();
        if (command.equals(JFileChooser.APPROVE_SELECTION)) {

            ImageIcon image = ImgLib.JFCToImageIcon(findFile);

            if (image == null) {
                JOptionPane.showMessageDialog(this.desktop.getBgDesktop(), "NO ES UN ARCHIVO VALIDO");
            } else {

                ImgLib.SetImageInLabel(image, this.view.getLbImage());

                this.view.getBgFile().dispose();

                this.user.setPhoto(ImgLib.getImageFromFileInputStrem(ImgLib.getImgFileFromJFC(findFile)));
                
                this.view.getTxtFilePath().setText(ImgLib.getStringPathFromJFC(findFile));
                
                
            }

        } else if (command.equals(JFileChooser.CANCEL_SELECTION)) {
            this.view.getBgFile().dispose();
        }
    }

}