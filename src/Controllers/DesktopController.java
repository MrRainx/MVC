/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Controllers.Libraries.Effects;
import models.interfaces.PersonDAO;
import models.dao.PersonImp;
import models.dao.UsersImp;
import Models.DTO.PersonDTO;
import Models.DTO.UserDTO;
import Views.Desktop;
import Views.Users.UserList;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 *
 * @author MrRainx
 */
public class DesktopController {

    private UsersImp user;
    private Desktop desktop;

    public DesktopController(UsersImp user, Desktop desktop) {
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
        
        UserListController lista = new UserListController(this.desktop, new UserList(), new UsersImp());
        
        lista.Init();
        
        
    }
    
    private void btnPersonOnMouseClicked(MouseEvent e){
        PersonListController.getInstance(desktop);
    }
    
    private void btnAgregarUsuarioOnMouseClicked(MouseEvent e){
        
        PersonDAO dao = new PersonImp();
        
    }
    
    
    

}
