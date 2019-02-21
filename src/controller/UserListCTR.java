package controller;

import controller.libraries.Effects;
import controller.libraries.ImgLib;
import java.awt.Color;
import java.awt.event.ActionEvent;
import model.dao.UsersImp;
import model.dto.UserDTO;
import view.Desktop;
import view.users.UserList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import view.users.UserForm;

/**
 *
 * @author MrRainx
 */
public class UserListCTR {

    private Desktop desktop; //DONDE SE VA A MOSTRAR

    private UserList view; // QUE VAMOS A MOSTRAR

    private UsersImp model; //CON QUE VAMOS A TRABAJAR

    private static DefaultTableModel tableModel;

    private List<UserDTO> Lista = null;

    public UserListCTR(Desktop desktop, UserList view, UsersImp model) {
        this.desktop = desktop;
        this.view = view;
        this.model = model;
    }

    /*
        Inits
     */
    public void Init() {
        tableModel = (DefaultTableModel) view.getTabUsers().getModel();

        InitEvents();
        InitEffects();

        loadTable();

        view.show();
        desktop.getBgDesktop().add(view);

        try {
            view.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(UserListCTR.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void InitEvents() {
        this.view.getTabUsers().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tabUsersOnMouseClicked(e);
            }
        });

        view.getBtnFind().addActionListener(e -> btnFindActionPerformance(e));
        view.getBtnUpdate().addActionListener(e -> btnUpdateActionPerformance(e));
        view.getBtnEdit().addActionListener(e -> btnEditActionPerformance(e));
        view.getBtnDelete().addActionListener(e -> btnDeleteActionPerformance(e));
        view.getBtnNew().addActionListener(e -> btnNewActionPerformance(e));

    }

    private void InitEffects() {

        Color colorExit = view.getBtnUpdate().getBackground();
        Color colorEnter = new Color(68, 98, 145);

        Effects.colorHover(view.getBtnNew(), colorEnter, colorExit);
        Effects.colorHover(view.getBtnEdit(), colorEnter, colorExit);
        Effects.colorHover(view.getBtnDelete(), colorEnter, colorExit);
        Effects.colorHover(view.getBtnUpdate(), colorEnter, colorExit);
        Effects.colorHover(view.getBtnFind(), colorEnter, colorExit);
    }

    /*
        Metodos de Apoyo
     */
    private void loadTable() {
        Lista = null;
        Lista = model.selectAll();

        Lista.forEach(UserListCTR::insertRow);

    }

    private static void insertRow(UserDTO obj) {
        tableModel.addRow(new Object[]{
            obj.getIdUser(),
            obj.getUserName(),
            obj.getName()
        });
    }

    private void loadTableFilter(String Aguja) {

        Lista = null;
        Lista = model.selectWhereUsernameLIKE(Aguja);

        Lista.forEach(UserListCTR::insertRow);

    }

    private void resetTable() {

        int rows = tableModel.getDataVector().size();

        if (rows > 0) {
            for (int i = 0; i < rows; i++) {
                tableModel.removeRow(0);
            }
        }

    }

    private void resetForm() {
        view.getTxtIdUsuario().setText("");
        view.getTxtNames().setText("");
        view.getTxtUsername().setText("");
        view.getLblPhoto().setIcon(null);
    }
    

    /*
        Procesadores de los Eventos
     */
    private void tabUsersOnMouseClicked(MouseEvent e) {

        int fila = view.getTabUsers().getSelectedRow();

        try {
            int Pk = (Integer) view.getTabUsers().getValueAt(fila, 0);

            for (UserDTO obj : Lista) {

                if (obj.getIdUser() == Pk) {

                    view.getTxtIdUsuario().setText(obj.getIdUser() + "");
                    
                    model.setIdUser(obj.getIdUser());
                    
                    view.getTxtNames().setText(obj.getName());
                    
                    model.setName(obj.getName());
                    
                    view.getTxtUsername().setText(obj.getUserName());
                    
                    model.setUserName(obj.getUserName());
                    
                    model.setPassword(obj.getPassword());
                    

                    if (obj.getPhoto() != null) {

                        ImageIcon image = new ImageIcon(obj.getPhoto());

                        ImgLib.SetImageInLabel(image, view.getLblPhoto());
                        
                        model.setPhoto(obj.getPhoto());
                        

                    } else {
                        view.getLblPhoto().setIcon(null);
                        model.setPhoto(null);
                    }

                }

            }
        } catch (ArrayIndexOutOfBoundsException ex) {
        }

    }

    private void btnFindActionPerformance(ActionEvent e) {
        resetTable();
        loadTableFilter(view.getTxtUsername().getText());

    }

    private void btnUpdateActionPerformance(ActionEvent e) {

        resetTable();
        loadTable();

    }
    

    private void btnEditActionPerformance(ActionEvent e) {
        int row = view.getTabUsers().getSelectedRow();
        if (row != -1) {
            
            UserFormCTR user = new UserFormCTR(desktop, new UserForm(), model, "Edit");
            
            user.Init();
            
            
        } else {
            JOptionPane.showMessageDialog(desktop, "SELECT A ROW!!");
        }
    }

    private void btnDeleteActionPerformance(ActionEvent e) {

        int row = view.getTabUsers().getSelectedRow();

        if (row != -1) {

            int Pk = (Integer) view.getTabUsers().getValueAt(row, 0);

            String User = (String) view.getTabUsers().getValueAt(row, 1);

            ImageIcon icon = new ImageIcon("src\\view\\Static\\icons\\Denied_48px.png");

            int option = JOptionPane.showConfirmDialog(null,
                    User,
                    "ARE YOU SURE TO DELETE THIS USER?",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    icon
            );

            if (option == 0) {

                model.delete(Pk);

                JOptionPane.showMessageDialog(null, "USER HAS BEEN DELETED");

                resetTable();
                loadTable();
                resetForm();

            } else {
                JOptionPane.showMessageDialog(null, "USER IS NOT DELETED");
            }

        } else {
            JOptionPane.showMessageDialog(desktop, "SELECT A ROW!!");
        }

    }

    private void btnNewActionPerformance(ActionEvent e) {

        UserFormCTR userForm = new UserFormCTR(desktop, new UserForm(), new UsersImp(), "New");
        userForm.Init();

    }

}
