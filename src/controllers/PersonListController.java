package controllers;

import controllers.Libraries.Effects;
import models.jdbc.PersonImp;
import models.dto.PersonDTO;
import views.Desktop;
import views.Persons.PersonList;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MrRainx
 */
public class PersonListController {

    private static Desktop desktop;
    private static PersonList view;
    private static PersonImp model;    
    
    
    private static PersonListController INSTACE;
    private DefaultTableModel ModelT;

    private PersonListController(Desktop desktop, PersonList view, PersonImp person) {

        PersonListController.desktop = desktop;
        PersonListController.view = view;
        model = person;
    }
    

    /*
        INIT
     */
    public void Init() {

        view.show();
        desktop.getBgDesktop().add(view, JLayeredPane.DEFAULT_LAYER);

        ModelT = (DefaultTableModel) view.getTabPersons().getModel();

        view.getTxtFind().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                TxtOnKeyRelessed(e);
            }
            
        });

        view.getBtnNew().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnNewOnMaouseClicked(e);
            }

        });

        view.getBtnEdit().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnEditMouseClicked(e);
            }

        });

        view.getBtnDelete().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnDeleteOnMouseClicked(e);
            }

        });

        LoadTableAll();
        InitEffects();

    }

    private void InitEffects() {

        Color colorBtns = view.getBtnDelete().getBackground();

        Effects.Hover(view.getBtnNew(), new Color(68, 98, 145), colorBtns);
        Effects.Hover(view.getBtnEdit(), new Color(68, 98, 145), colorBtns);
        Effects.Hover(view.getBtnDelete(), new Color(68, 98, 145), colorBtns);

    }

    /*
        SUPPORT METHODS
     */
    private void LoadTableAll() {

        List<PersonDTO> PersonList = model.SelectAll();

        PersonList.forEach((obj) -> {
            InsertRow(obj);

            view.getLbState().setText(PersonList.size() + " rows");

        });

    }

    private void InsertRow(PersonDTO obj) {
        ModelT.addRow(new Object[]{
            obj.getIdPerson(),
            obj.getNames(),
            obj.getLastNames(),
            obj.getBirthdate(),
            obj.getPhone(),
            obj.getSex(),
            obj.getSalary(),
            obj.getQuota()
        });

    }

    private void LoadOneToTable(String Aguja) {

        List<PersonDTO> PersonList = model.SelectOne(Aguja);
        PersonList.forEach((obj) -> {
            InsertRow(obj);
        });
        view.getLbState().setText(PersonList.size() + " rows");

    }
    
    private void ResetTable() {
        
        int a = view.getTabPersons().getRowCount() - 1;
        
        try {
            for (int i = a; i >= 0; i++) {
                ModelT.removeRow(view.getTabPersons().getRowCount() - 1);
            }

        } catch (ArrayIndexOutOfBoundsException e) {

        }

    }

    private PersonImp createPersonFromTable(int row) {

        return new PersonImp(
                (String) view.getTabPersons().getValueAt(row, 0),
                (String) view.getTabPersons().getValueAt(row, 1),
                (String) view.getTabPersons().getValueAt(row, 2),
                (LocalDate) view.getTabPersons().getValueAt(row, 3),
                (String) view.getTabPersons().getValueAt(row, 4),
                (String) view.getTabPersons().getValueAt(row, 5),
                (Double) view.getTabPersons().getValueAt(row, 6),
                (Integer) view.getTabPersons().getValueAt(row, 7)
        );
    }

    /*
        EVENTS
     */
    private void TxtOnKeyRelessed(KeyEvent e) {
        ResetTable();
        LoadOneToTable(view.getTxtFind().getText());

    }

    private void btnNewOnMaouseClicked(MouseEvent e) {
        
        PersonFormController insert = PersonFormController.getIntance(this.desktop);
        insert.Init();
        insert.InitInsert();
        
        view.setVisible(false);
    }

    private void btnEditMouseClicked(MouseEvent e) {

        int row = view.getTabPersons().getSelectedRow();

        if (row != -1) {

            view.dispose();
            /*
            PersonFormController update = new PersonFormController(this.desktop, new PersonForm(), createPersonFromTable(row));
            
            update.Init();
            
            update.InitEdit();
             */
        } else {
            JOptionPane.showMessageDialog(null, "SELECT ONE ROW");
        }

    }

    private void btnDeleteOnMouseClicked(MouseEvent e) {

        int row = view.getTabPersons().getSelectedRow();

        if (row != -1) {

            ImageIcon icon = new ImageIcon("src/Views/Static/icons/Denied_48px.png");

            int option = JOptionPane.showConfirmDialog(null,
                    createPersonFromTable(row),
                    "ARE YOU SURE TO DELETE THIS PERSON?",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    icon
            );

            if (option == 0) {

                String Aguja = createPersonFromTable(row).getIdPerson();

                model.Delete(Aguja);

                JOptionPane.showMessageDialog(null, "PERSON HAS BEEN DELETED");

                ResetTable();
                LoadTableAll();

            } else {
                JOptionPane.showMessageDialog(null, "PERSON IS NOT DELETED");
            }

        } else {
            JOptionPane.showMessageDialog(null, "SELEC ONE ROW");
        }

    }

    public static PersonListController getInstance(Desktop desktop) {

        if (INSTACE == null) {
            INSTACE = new PersonListController(desktop, new PersonList(), new PersonImp());
            INSTACE.Init();
        } else {
            
            try {
                view.show();
                PersonListController.desktop.getBgDesktop().add(view, JLayeredPane.MODAL_LAYER);
                INSTACE.ResetTable();
                INSTACE.LoadTableAll();
            } catch (IllegalArgumentException e) {
            }

        }
        return INSTACE;

    }
    

}
