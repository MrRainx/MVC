/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.User;
import Views.Desktop;

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
    
    public void Init(){
        this.desktop.setVisible(true);
    }
    
    
    
    /*
        SUPPORT METHODS
    */
    
    
    
    
    
    /*
        EVENTS
    */
    
    
    
    
}
