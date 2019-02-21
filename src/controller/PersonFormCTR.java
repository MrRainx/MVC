package controller;

import controller.libraries.Effects;
import controller.libraries.Validators;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.dao.PersonImp;
import view.Desktop;
import view.persons.PersonForm;
import javax.swing.JLayeredPane;

/**
 *
 * @author MrRainx
 */
public class PersonFormCTR {

    private Desktop desktop;
    private PersonForm view;
    private PersonImp model;

    private String Function;

    private String Pk;

    public PersonFormCTR(Desktop desktop, PersonForm view, PersonImp model, String Function) {
        this.desktop = desktop;
        this.view = view;
        this.model = model;
        this.Function = Function;
    }

    /*
        INIT 
     */
    public void Init() {

        if (Function.equals("Edit")) {
            InitEdit();
        }

        InitEvents();

        InitEffects();

        view.show();
        desktop.getBgDesktop().add(view);

        try {
            view.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(PersonFormCTR.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void InitEvents() {

        if (Function.equals("Edit")) {
            view.getBtnSave().addActionListener(e -> btnSaveActionPerformanceEdit(e));
        } else {
            view.getBtnSave().addActionListener(e -> btnSaveActionPerformanceNew(e));
        }

        view.getBtnClear().addActionListener(e -> btnClearActionPerformance(e));
    }

    private void InitEdit() {

        Pk = model.getIdPerson();

        view.getTxtID().setText(model.getIdPerson());
        view.getTxtName().setText(model.getNames());
        view.getTxtLastName().setText(model.getLastNames());
        view.getjCalendar().setDate(Date.valueOf(model.getBirthdate()));
        view.getTxtPhone().setText(model.getPhone());
        view.getComboSex().setSelectedItem(model.getSex());
        view.getTxtSalary().setText(Double.toString(model.getSalary()));
        view.getTxtQuota().setText(Integer.toString(model.getQuota()));

    }

    private void InitEffects() {

        Effects.colorHover(view.getBtnSave(), new Color(68, 98, 145), view.getBtnSave().getBackground());
        Effects.colorHover(view.getBtnClear(), new Color(68, 98, 145), view.getBtnClear().getBackground());

    }

    /*
        SUPPORT METHODS
     */
    private LocalDate getDateFromTxt() {

        return LocalDate.of(
                view.getjCalendar().getCalendar().get(Calendar.YEAR),
                view.getjCalendar().getCalendar().get(Calendar.MONTH) + 1,
                view.getjCalendar().getCalendar().get(Calendar.DAY_OF_MONTH));
    }

    private void setObjectFromForm() {
        
        model.setIdPerson(view.getTxtID().getText());
        model.setNames(view.getTxtName().getText());
        model.setLastNames(view.getTxtLastName().getText());
        model.setBirthdate(getDateFromTxt());
        model.setPhone(view.getTxtPhone().getText());
        model.setSex(view.getComboSex().getSelectedItem().toString());
        model.setSalary(Validators.getDoubleFromJFTXTfield(view.getTxtSalary().getText()));
        model.setQuota(Integer.parseInt(view.getTxtQuota().getText()));
    }

    /*
        EVENTS
     */
    private void btnSaveActionPerformanceEdit(ActionEvent e) {
        setObjectFromForm();

        if (model.update(Pk)) {

            view.dispose();

            JOptionPane.showMessageDialog(null, "PERSON HAS BEEN UPDATED");
        } else {
            JOptionPane.showMessageDialog(null, "THERE IS ALREADY AN ITEM WITH THAT PRIMARY KEY");
        }
    }

    private void btnSaveActionPerformanceNew(ActionEvent e) {
        setObjectFromForm();


        if (model.insert()) {

            view.dispose();

            JOptionPane.showMessageDialog(null, "PERSON HAS BEEN ADDED");
        } else {
            JOptionPane.showMessageDialog(null, "THERE IS ALREADY AN ITEM WITH THAT PRIMARY KEY");
        }

    }

    private void btnClearActionPerformance(ActionEvent e) {

    }

}
