package Controllers;

import Controllers.Libraries.ImgLib;
import Models.BD.UsuarioImp;
import Models.User;
import Views.Desktop;
import Views.Users.UsersView;
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
public class ListaUsuariosController {

    private Desktop desktop; //DONDE SE VA A MOSTRAR

    private UsersView view; // QUE VAMOS A MOSTRAR

    private UsuarioImp model; //CON QUE VAMOS A TRABAJAR

    private DefaultTableModel modeloTabla;

    private List<User> Lista = null;

    public ListaUsuariosController(Desktop desktop, UsersView view, UsuarioImp model) {
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

        for (User obj : Lista) {
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

            for (User obj : Lista) {

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
