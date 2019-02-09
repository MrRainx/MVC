package Controllers;

import Controllers.Libraries.Effects;
import models.dao.PersonImp;
import Models.DTO.PersonDTO;
import Views.Desktop;
import Views.Persons.PersonList;
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
    private static DefaultTableModel ModelT;
    private List<PersonDTO> PersonList;

    private PersonListController(Desktop desktop, PersonList view, PersonImp person) {

        PersonListController.desktop = desktop;
        PersonListController.view = view;
        model = person;
    }


    /*
        INIT
     */
    public void Init() {

        view.setTitle("Person List");

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

        view.getBtnUpdate().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnUpdateOnMouseClicked(e);
            }
        });

        LoadTableAll();
        InitEffects();

        view.show();
        desktop.getBgDesktop().add(view, JLayeredPane.DEFAULT_LAYER);
    }

    private void InitEffects() {

        Color colorBtns = view.getBtnDelete().getBackground();
        Color enterColor = new Color(68, 98, 145);
        
        Effects.colorHover(view.getBtnNew(), enterColor , colorBtns);
        Effects.colorHover(view.getBtnEdit(), enterColor, colorBtns);
        Effects.colorHover(view.getBtnDelete(), enterColor, colorBtns);
        Effects.colorHover(view.getBtnUpdate(), enterColor, colorBtns);
        Effects.letterHover(view.getBtnDelete(), enterColor, colorBtns);
    }

    /*
        SUPPORT METHODS
     */
    private void LoadTableAll() {

        PersonList = model.SelectAll();

        PersonList.stream()
                .forEach(PersonListController::InsertRow);

        view.getLbState().setText(PersonList.size() + " rows");
    }

    private static void InsertRow(PersonDTO obj) {
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

        PersonList = model.SelectOne(Aguja);

        PersonList.stream()
                .forEach(PersonListController::InsertRow);

        view.getLbState().setText(PersonList.size() + " rows");

    }

    private void ResetTable() {
        
        for (int i = 0; i < ModelT.getDataVector().size() + 1; i++) {
            //ModelT.removeRow(0);
        }
        for (int i = 0; i < ModelT.getDataVector().size(); i++) {
            
            System.out.println(setPersonFromTable(i));
            
            //ModelT.removeRow(0);
        }

    }
    
    private PersonImp setPersonFromTable(int row) {

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

        PersonFormController insert = PersonFormController.getIntance(desktop);

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
                    setPersonFromTable(row),
                    "ARE YOU SURE TO DELETE THIS PERSON?",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    icon
            );

            if (option == 0) {

                String Aguja = setPersonFromTable(row).getIdPerson();

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

    private void btnUpdateOnMouseClicked(MouseEvent e) {
        ResetTable();
        //LoadTableAll();
        view.getTxtFind().setText("");
    }

    public static PersonListController getInstance(Desktop desktop) {

        if (INSTACE == null) {
            INSTACE = new PersonListController(desktop, new PersonList(), new PersonImp());
            INSTACE.Init();
        } else {

            try {
                view.show();
                PersonListController.desktop.getBgDesktop().add(view, JLayeredPane.MODAL_LAYER);

            } catch (IllegalArgumentException e) {
            }
        }
        return INSTACE;
    }
    
    
    
}
