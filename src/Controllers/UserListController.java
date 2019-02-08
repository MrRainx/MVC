package Controllers;

import Controllers.Libraries.ImgLib;
import models.dao.UsersImp;
import Models.DTO.UserDTO;
import Views.Desktop;
import Views.Users.UserList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MrRainx
 */
public class UserListController {

    private Desktop desktop; //DONDE SE VA A MOSTRAR

    private UserList view; // QUE VAMOS A MOSTRAR

    private UsersImp model; //CON QUE VAMOS A TRABAJAR

    private DefaultTableModel modeloTabla;

    private List<UserDTO> Lista = null;

    public UserListController(Desktop desktop, UserList view, UsersImp model) {
        this.desktop = desktop;
        this.view = view;
        this.model = model;
    }

    /*
        Inits
     */
    public void Init() {
        this.view.show();
        this.desktop.getBgDesktop().add(this.view, JLayeredPane.MODAL_LAYER);
        modeloTabla = (DefaultTableModel) this.view.getTabUsers().getModel();

        this.view.getTabUsers().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tabUsersOnMouseClicked(e);
            }
        });

        CargarLista();
    }

    /*
        Metodos de Apoyo
     */
    private void CargarLista() {
        Lista = null;
        Lista = model.SelectAll();

        for (UserDTO obj : Lista) {
            modeloTabla.addRow(new Object[]{
                obj.getIdUser(),
                obj.getUserName(),
                obj.getName()
            });
        }

    }

    /*
        Procesadores de los Eventos
     */
    private void tabUsersOnMouseClicked(MouseEvent e) {

        int fila = this.view.getTabUsers().getSelectedRow();

        try {
            int Pk = (Integer) this.view.getTabUsers().getValueAt(fila, 0);

            for (UserDTO obj : Lista) {

                if (obj.getIdUser() == Pk) {

                    this.view.getTxtIdUsuario().setText(obj.getIdUser() + "");
                    this.view.getTxtNames().setText(obj.getName());
                    this.view.getTxtUsername().setText(obj.getUserName());

                    ImageIcon image = new ImageIcon(obj.getPhoto());

                    ImgLib.SetImageInLabel(image, this.view.getLblPhoto());
                }

            }
        } catch (ArrayIndexOutOfBoundsException ex) {
        }

    }

}
