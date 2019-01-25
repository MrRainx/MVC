package Controllers;

import Controllers.Libraries.ImgLib;
import Models.BD.UsuarioImp;
import Views.Desktop;
import Views.Users.AddUser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

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
        
        this.view.getBtnFind().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnFindOnMouseClicked(e);
            }
        });
       
        
        this.view.getFileFinder().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooserActionPerformance(e);
            }
        });
        
        
        this.view.getBtnAdd().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnAddUserOnMouseClicked(e);
            }
            
        });
        
        
    }
    
    
    /*
        Metodos de Apoyo
    */
    
    
    /*
       Procesadores de Eventos
    */
    
    
    private void btnFindOnMouseClicked(MouseEvent e){
        this.view.getBgFile().setVisible(true);
        this.view.getBgFile().setLocationRelativeTo(null);
    }
    
    private void fileChooserActionPerformance(ActionEvent evt){
        
        JFileChooser archivo = (JFileChooser) evt.getSource();
        
        String comando = evt.getActionCommand();
        
        if (comando.equals(JFileChooser.APPROVE_SELECTION)){
            
            ImageIcon imagen = ImgLib.JFCToImageIcon(archivo);
            
            if (imagen == null){
                JOptionPane.showMessageDialog(null, "SELECCIONE UN ARCHIVO VALIDO");
            } else {
                
                ImgLib.SetImageInLabel(imagen, this.view.getLbImage());
                
                this.view.getTxtFilePath().setText(ImgLib.getStringPathFromJFC(archivo));
                
                this.model.setPhoto(ImgLib.getImageFromFileInputStrem(ImgLib.getImgFileFromJFC(archivo)));
                
                this.view.getBgFile().dispose();
                
            }
            
            
        } else if (comando.equals(JFileChooser.CANCEL_SELECTION)){
            this.view.getBgFile().dispose();
        }
        
    }
    
    private void btnAddUserOnMouseClicked(MouseEvent e){
        
        this.model.setName(this.view.getTxtNames().getText());
        this.model.setUserName(this.view.getTxtUserName().getText());
        this.model.setPassword(this.view.getTxtPassword().getText());
        
        
        if (this.model.insertar() == true){
            JOptionPane.showMessageDialog(null, "EL Usuario "+model.getName()+" \n Se Agrego Correctamente");
            this.view.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Revise La Informacion Ingresada!!!");
        }
        
        
        
    }
    
    
}
