package Controllers;

import Models.BD.UsuarioImp;
import Views.Desktop;
import Views.Users.AddUser;
import javax.swing.JLayeredPane;

/**
 *
 * @author MrRainx
 */
public class FormularioUserController {
    
    private Desktop desktop;
    private AddUser view;
    private UsuarioImp model;

    public FormularioUserController(Desktop desktop, AddUser view, UsuarioImp model) {
        this.desktop = desktop;
        this.view = view;
        this.model = model;
    }
    
    /*
        Inits
    */
    
    public void Init(){
        this.view.show();
        this.desktop.getBgDesktop().remove(this.view);
        this.desktop.getBgDesktop().add(this.view, JLayeredPane.MODAL_LAYER);
        
        
    }
    
    
    /*
        Metodos de Apoyo
    */
    
    
    /*
        Eventos
    */
    
}
