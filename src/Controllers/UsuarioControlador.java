package Controllers;

import Models.BD.UsuarioImp;
import Views.Desktop;
import Views.Users.AddUser;
import javax.swing.JLayeredPane;

/**
 *
 * @author MrRainx
 */
public class UsuarioControlador {
    
    private Desktop desktop;//DONDE SE VA A VISUALIZAR
    
    private AddUser view;//QUE SE VA A VISUALIZAR
    
    private UsuarioImp model; //CON LO QUE VAMOS A TRABAJAR

    public UsuarioControlador(Desktop desktop, AddUser view, UsuarioImp model) {
        this.desktop = desktop;
        this.view = view;
        this.model = model;
    }
    
    
    /*
        INITs
    */
    
    public void Init(){
        this.view.show();
        this.desktop.getBgDesktop().add(view, JLayeredPane.MODAL_LAYER);
        this.view.setTitle("Insertar Usuario en la Base de Datos");
        
        
    }
    
    
    /*
        Metodos de Apoyo
    */
    
    
    /*
        Eventos
    */
    
}
