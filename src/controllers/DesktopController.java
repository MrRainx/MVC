/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.Libraries.Effects;
import models.dao.PersonDAO;
import models.jdbc.PersonImp;
import models.jdbc.UserImp;
import models.dto.PersonDTO;
import models.dto.UserDTO;
import views.Desktop;
import views.Users.UserList;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 *
 * @author MrRainx
 */
public class DesktopController {

    private UserImp user;
    private Desktop desktop;

    public DesktopController(UserImp user, Desktop desktop) {
        this.user = user;
        this.desktop = desktop;
        InitEffects();
    }
    

    /*
        INIT
     */
    public void Init() {
        
        
        this.desktop.setVisible(true);
        this.desktop.setLocationRelativeTo(null);
        
        
        this.desktop.getBtnListaUsuarios().addMouseListener(new MouseAdapter() {
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
        
        
        
        this.desktop.getBtnIngresarUsuario().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnAgregarUsuarioOnMouseClicked(e);
            }
            
        });
        
    }
    
    
    private void InitEffects(){
        
        Color colorBase = desktop.getBtnPersons().getBackground();
        
        Effects.Hover(desktop.getBtnPersons(), Color.CYAN, colorBase);
        
        
    }
    

    /*
        SUPPORT METHODS
     */
    /*
        EVENTS
     */
    private void btnUserOnMouseClicked(MouseEvent e) {
        
        UserListController lista = new UserListController(this.desktop, new UserList(), new UserImp());
        
        lista.Init();
        
        
    }
    
    private void btnPersonOnMouseClicked(MouseEvent e){
        PersonListController.getInstance(desktop);
    }
    
    private void btnAgregarUsuarioOnMouseClicked(MouseEvent e){
        
        PersonDAO dao = new PersonImp();
        
    }
    
    
    

}
