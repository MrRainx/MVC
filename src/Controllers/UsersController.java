/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.BD.UsersImp;
import Views.Desktop;
import Views.Users.AddUser;
import Views.Users.UsersView;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLayeredPane;

/**
 *
 * @author MrRainx
 */
public class UsersController {

    private UsersImp user;
    private UsersView view;
    private Desktop desktop;

    public UsersController(UsersImp user, UsersView view, Desktop desktop) {
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
        this.desktop.getBgDesktop().remove(this.view);
        this.desktop.getBgDesktop().add(this.view, JLayeredPane.DEFAULT_LAYER);
        
        
        this.view.getBtnAddNewUser().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnAddUserOnMouseClicked(e);
            }
        });
        
    }

    /*
        SUPPORT METHODS
     */
 /*
        EVENTS
     */
    
    private void btnAddUserOnMouseClicked(MouseEvent e){
        this.view.setVisible(false);
        AddUserController addUser = new AddUserController(new UsersImp(), new AddUser(), this.desktop);
        this.view.setVisible(true);
        addUser.Init();
    }
    
}
