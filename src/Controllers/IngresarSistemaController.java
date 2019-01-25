/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.BD.UsuarioImp;
import Models.User;
import Views.Desktop;
import Views.Ingresar;
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
public class IngresarSistemaController {
    
    private Ingresar view; //QUE VOY A MOSTRAR
    
    private UsuarioImp model; //CON QUE VOY A TRABAJAR

    
    public IngresarSistemaController(Ingresar view, UsuarioImp model) {
        this.view = view;
        this.model = model;
    }
    
    /*
        Inits
    */
    
    
    public void Init(){
        
        this.view.setVisible(true);
        this.view.setLocationRelativeTo(null);
        
        
        
        this.view.getBtnIngresar().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnIngresarOnMouseClicked(e);
            }
        });
       
        
        
        this.view.getTxtPassword().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                txtPasswordKeyReleesed(e);
            }
        });
        
        
    }
    
    
    
    
    
    /*
        Metodos de Apoyo
    */
    
    private void Login(){
      
        this.model.setUserName(this.view.getTxtUsername().getText());
        this.model.setPassword(this.view.getTxtPassword().getText());
        
        
        List<User>Lista = model.SelectOne();
        
        
        if (Lista.isEmpty()){
            JOptionPane.showMessageDialog(null, "Usuario o Contraseña Incorrectos");
        } else {
            this.model.setIdUser(  Lista.get(0).getIdUser()   );
            
            this.model.setName(   Lista.get(0).getName() );
            
            this.model.setPhoto(  Lista.get(0).getPhoto() );
            
            
            ImageIcon image = new ImageIcon(model.getPhoto());
            
            Icon icon = new ImageIcon(image.getImage().getScaledInstance(160, 90, Image.SCALE_DEFAULT));
            
            
            JOptionPane.showMessageDialog(null, this.model.getName(),
                    "HA INICIADO SESION COMO: ",
                    JOptionPane.OK_OPTION,
                    icon);
            
            
            
            DesktopController desktop = new DesktopController(this.model, new Desktop());
            desktop.Init();
            
            this.view.dispose();
            
        }
    }
    
    
    /*
        Procesadores de los Eventos
    */
    
    
    private void btnIngresarOnMouseClicked(MouseEvent e){
        Login();
    }
    
    
    private void txtPasswordKeyReleesed(KeyEvent evt){
        
        if (evt.getKeyCode() == 10){
            Login();
        }
        
    }
    
    
}
