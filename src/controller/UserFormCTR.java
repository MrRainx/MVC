package controller;

import controller.libraries.Effects;
import controller.libraries.ImgLib;
import java.awt.Color;
import model.dao.UsersImp;
import view.Desktop;
import view.users.UserForm;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author MrRainx
 */
public class UserFormCTR {

    private Desktop desktop;
    private UserForm view;
    private UsersImp model;

    private String Function;

    private int Pk = 0;

    public UserFormCTR(Desktop desktop, UserForm view, UsersImp user, String Function) {
        this.desktop = desktop;
        this.view = view;
        this.model = user;
        this.Function = Function;
    }

    /*
        INITs
     */
    public void Init() {

        view.setVisible(true);

        InitEvents();
        InitEffects();

        view.show();
        desktop.getBgDesktop().add(view);

        try {
            view.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(UserFormCTR.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (Function.equals("Edit")) {
            setModelInForm();

            Pk = model.getIdUser();

        }

    }

    private void InitEvents() {
        if (Function.equals("New")) {
            view.getBtnSave().addActionListener(e -> btnAddActionPerformance(e));

        } else {
            view.getBtnSave().addActionListener(e -> btnEditActionPerformance(e));

        }

        view.getBtnFind().addActionListener(e -> btnFindOnActionPerformance(e));

        view.getFileFinder().addActionListener(e -> FileChosseActionPerformance(e));
    }

    private void InitEffects() {
        Color colorExit = view.getBtnSave().getBackground();
        Color colorEnter = new Color(68, 98, 145);

        Effects.colorHover(view.getBtnSave(), colorEnter, colorExit);
        Effects.colorHover(view.getBtnFind(), colorEnter, colorExit);
    }

    /*
        SUPPORT METHODS
     */
    private void setModelInForm() {

        view.getTxtUserName().setText(model.getUserName());
        view.getTxtNames().setText(model.getName());

        if (model.getPhoto() == null) {

            view.getLbImage().setIcon(null);

        } else {

            ImageIcon image = new ImageIcon(model.getPhoto());

            ImgLib.SetImageInLabel(image, view.getLbImage());

        }

    }

    private void setObjectFromForm() {
        model.setUserName(view.getTxtUserName().getText());
        model.setPassword(view.getTxtPassword().getText());
        model.setName(view.getTxtNames().getText());
    }

    /*
        EVENTS
     */
    private void btnAddActionPerformance(ActionEvent e) {

        setObjectFromForm();

        if (model.insert()) {
            JOptionPane.showMessageDialog(desktop, "USER HAS BEEN ADDED");
            view.dispose();
        } else {
            JOptionPane.showMessageDialog(desktop, "FILL THE FORM CORRECTLY");
        }

    }

    private void btnFindOnActionPerformance(ActionEvent e) {

        view.getBgFile().setVisible(true);
        view.getBgFile().setLocationRelativeTo(desktop);

    }

    private void FileChosseActionPerformance(ActionEvent evt) {

        JFileChooser findFile = (JFileChooser) evt.getSource();
        String command = evt.getActionCommand();
        if (command.equals(JFileChooser.APPROVE_SELECTION)) {

            ImageIcon image = ImgLib.JFCToImageIcon(findFile);

            if (image == null) {

                JOptionPane.showMessageDialog(desktop.getBgDesktop(), "NO ES UN ARCHIVO VALIDO");

            } else {

                ImgLib.SetImageInLabel(image, view.getLbImage());

                view.getBgFile().dispose();

                model.setPhoto(ImgLib.getImageFromJFC(findFile));

                view.getTxtFilePath().setText(ImgLib.getStringPathFromJFC(findFile));

            }

        } else if (command.equals(JFileChooser.CANCEL_SELECTION)) {
            view.getBgFile().dispose();
        }
    }

    private void btnEditActionPerformance(ActionEvent e) {

        setObjectFromForm();

        if (model.update(Pk)) {
            JOptionPane.showMessageDialog(desktop, "USER HAS BEEN UPDATED");
            view.dispose();
        } else {
            JOptionPane.showMessageDialog(desktop, "FILL THE FORM CORRECTLY");
        }

    }

}
