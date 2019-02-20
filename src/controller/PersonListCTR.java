package controller;

import controller.libraries.Effects;
import model.dao.PersonImp;
import model.dto.PersonDTO;
import view.Desktop;
import view.persons.PersonList;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLayeredPane;
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

    private DefaultTableModel ModelT;

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

        personList.forEach(obj -> {
            addRow(obj);
        });
    }

    private void addRow(PersonDTO obj) {
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
        
        model.setIdPerson((String)view.getTabPersons().getValueAt(row, 0));
        
        
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
        }

    }

    private void btnDeleteActionPerformance(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void btnUpdateActionPerformance(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
