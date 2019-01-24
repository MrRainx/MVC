/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.BD.PersonImp;
import Models.BD.UsersImp;
import Models.User;
import Views.Desktop;
import Views.Persons.Find;
import Views.Users.UsersView;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author MrRainx
 */
public class DesktopController {

    private User user;
    private Desktop desktop;

    public DesktopController(User user, Desktop desktop) {
        this.user = user;
        this.desktop = desktop;
    }

    public DesktopController() {
    }

    /*
        INIT
     */
    public void Init() {
        this.desktop.setVisible(true);
        this.desktop.setLocationRelativeTo(null);
        this.desktop.getBtnUser().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnUserOnMouseClicked(e);
            }
        });
        
        this.desktop.getBtnPersons().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnPersonOnMouseClicked(e);
            }
            
        });
    }

    /*
        SUPPORT METHODS
     */
 /*
        EVENTS
     */
    private void btnUserOnMouseClicked(MouseEvent e) {
        
        UsersController userList = new UsersController(new UsersImp(), new UsersView(), this.desktop);
        userList.Init();
        
    }
    
    private void btnPersonOnMouseClicked(MouseEvent e){
        FindController fin = new FindController(new PersonImp(), new Find(), this.desktop);
        
        fin.Init();
                
    }

}
