package controller;

import controller.libraries.Effects;
import model.dao.PersonImp;
import model.dto.PersonDTO;
import view.Desktop;
import view.persons.PersonList;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import view.persons.PersonForm;

/**
 *
 * @author MrRainx
 */
public class PersonListCTR {

    private Desktop desktop;
    private PersonList view;
    private PersonImp model;

    private static DefaultTableModel ModelT;

    private List<PersonDTO> personList;

    public PersonListCTR(Desktop desktop, PersonList view, PersonImp model) {
        this.desktop = desktop;
        this.view = view;
        this.model = model;
    }

    /*
        INIT
     */
    public void Init() {

        view.setTitle("Person List");

        ModelT = (DefaultTableModel) view.getTabPersons().getModel();

        InitEvents();
        InitEffects();

        loadTable();

        view.show();
        desktop.getBgDesktop().add(view);

        try {
            view.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(PersonListCTR.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void InitEvents() {

        view.getBtnNew().addActionListener(e -> btnNewActionPerformance(e));
        view.getBtnEdit().addActionListener(e -> btnEditActionPerformance(e));
        view.getBtnDelete().addActionListener(e -> btnDeleteActionPerformance(e));
        view.getBtnUpdate().addActionListener(e -> btnUpdateActionPerformance(e));

        view.getTxtFind().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                txtFindOnKeyReleased(e);
            }
        });
    }

    private void InitEffects() {

        Color colorBtns = view.getBtnDelete().getBackground();
        Color enterColor = new Color(68, 98, 145);

        Effects.colorHover(view.getBtnNew(), enterColor, colorBtns);
        Effects.colorHover(view.getBtnEdit(), enterColor, colorBtns);
        Effects.colorHover(view.getBtnDelete(), enterColor, colorBtns);
        Effects.colorHover(view.getBtnUpdate(), enterColor, colorBtns);
    }

    /*
        SUPPORT METHODS
     */
    private void loadTable() {

        personList = model.selectAll();

        //Aqui hago uso de Java Stream
        personList.forEach(PersonListCTR::addRow);
    }

    private void loadTableFilter(String Aguja) {

        personList = model.selectWhereNombreOrApellidoLike(Aguja);

        //Aqui hago uso de Java Stream
        personList.forEach(PersonListCTR::addRow);

    }

    private static void addRow(PersonDTO obj) {
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

    private void setObjectFromTable(int row) {

        model.setIdPerson((String) view.getTabPersons().getValueAt(row, 0));
        model.setNames((String) view.getTabPersons().getValueAt(row, 1));
        model.setLastNames((String) view.getTabPersons().getValueAt(row, 2));
        model.setBirthdate((LocalDate) view.getTabPersons().getValueAt(row, 3));
        model.setPhone((String) view.getTabPersons().getValueAt(row, 4));
        model.setSex((String) view.getTabPersons().getValueAt(row, 5));
        model.setSalary((Double) view.getTabPersons().getValueAt(row, 6));
        model.setQuota((Integer) view.getTabPersons().getValueAt(row, 7));

    }

    private void resetTable() {
        int rows = ModelT.getDataVector().size();

        if (rows > 0) {
            for (int i = 0; i < rows; i++) {
                ModelT.removeRow(0);
            }
        }
    }


    /*
        EVENT PROCESSORS
    
     */
    private void btnNewActionPerformance(ActionEvent e) {

        PersonFormCTR personForm = new PersonFormCTR(desktop, new PersonForm(), new PersonImp(), "New");

        personForm.Init();

    }

    private void btnEditActionPerformance(ActionEvent e) {

        int row = view.getTabPersons().getSelectedRow();

        if (row != -1) {

            setObjectFromTable(row);

            PersonFormCTR personForm = new PersonFormCTR(desktop, new PersonForm(), model, "Edit");

            personForm.Init();

        } else {
            JOptionPane.showMessageDialog(desktop, "Select an item from the table!!");
        }

    }

    private void btnDeleteActionPerformance(ActionEvent e) {
        int row = view.getTabPersons().getSelectedRow();

        if (row != -1) {
            ImageIcon icon = new ImageIcon("src\\view\\Static\\icons\\Denied_48px.png");

            setObjectFromTable(row);
            int option = JOptionPane.showConfirmDialog(null,
                    model,
                    "ARE YOU SURE TO DELETE THIS PERSON?",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    icon
            );

            if (option == 0) {

                model.delete(model.getIdPerson());

                JOptionPane.showMessageDialog(null, "PERSON HAS BEEN DELETED");

                resetTable();
                loadTable();

            } else {
                JOptionPane.showMessageDialog(null, "PERSON IS NOT DELETED");
            }
        } else {
            JOptionPane.showMessageDialog(desktop, "Select an item from the table!!");
        }
    }

    private void btnUpdateActionPerformance(ActionEvent e) {

        resetTable();
        loadTable();

    }

    private void txtFindOnKeyReleased(KeyEvent e) {
        resetTable();

        loadTableFilter(view.getTxtFind().getText());

    }
}
