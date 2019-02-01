package Controllers;

import Controllers.Libraries.Effects;
import Controllers.Libraries.Validators;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import javax.swing.JOptionPane;
import Models.BD.PersonImp;
import Views.Desktop;
import Views.Persons.PersonForm;
import javax.swing.JLayeredPane;

/**
 *
 * @author MrRainx
 */
public class PersonFormController {
    
    private Desktop desktop;
    private PersonForm view;
    private PersonImp model;
    
    
    private static PersonFormController INSTANCE;
    

    private String OldId;

    private PersonFormController(Desktop desktop, PersonForm view, PersonImp model) {
        this.desktop = desktop;
        this.view = view;
        this.model = model;
        
        InitEffects();
        
    }
    


    /*
        INIT 
     */
    public void Init() {
        
        this.view.show();
        this.desktop.getBgDesktop().add(this.view, JLayeredPane.MODAL_LAYER);
        
        this.view.getBtnInsert().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnInsertOnMaouseClicked(e);
            }
        });
        

    }

    public void InitInsert() {

        this.view.getBtnClear().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnClearOnMouseClicked(e);
            }

        });

    }

    public void InitEdit() {

        OldId = this.model.getIdPerson();
        this.view.getBtnInsert().setText("Edit");

        this.view.getTxtID().setText(model.getIdPerson());
        this.view.getTxtName().setText(model.getNames());
        this.view.getTxtLastName().setText(model.getLastNames());
        this.view.getjCalendar().setDate(Date.valueOf(model.getBirthdate()));
        this.view.getTxtPhone().setText(model.getPhone());
        this.view.getComboSex().setSelectedItem(model.getSex());
        this.view.getTxtSalary().setText(Double.toString(model.getSalary()));
        this.view.getTxtQuota().setText(Integer.toString(model.getQuota()));

    }

    private void InitEffects() {

        Effects.Hover(this.view.getBtnInsert(), new Color(68, 98, 145), this.view.getBtnInsert().getBackground());
        Effects.Hover(this.view.getBtnClear(), new Color(68, 98, 145), this.view.getBtnClear().getBackground());
        
    }

    /*
        SUPPORT METHODS
     */
    

    private LocalDate getDateFromTxt() {

        return LocalDate.of(
                this.view.getjCalendar().getCalendar().get(Calendar.YEAR),
                this.view.getjCalendar().getCalendar().get(Calendar.MONTH) + 1,
                this.view.getjCalendar().getCalendar().get(Calendar.DAY_OF_MONTH));
    }

    private void CreatePersonFromView() {
        model.setIdPerson(this.view.getTxtID().getText());
        model.setNames(this.view.getTxtName().getText());
        model.setLastNames(this.view.getTxtLastName().getText());
        model.setBirthdate(getDateFromTxt());
        model.setPhone(this.view.getTxtPhone().getText());
        model.setSex(this.view.getComboSex().getSelectedItem().toString());
        model.setSalary(Validators.getDoubleFromJFTXTfield(view.getTxtSalary().getText()));
        model.setQuota(Integer.parseInt(this.view.getTxtQuota().getText()));
    }

    /*
        EVENTS
     */
    private void btnInsertOnMaouseClicked(MouseEvent e) {

        if (view.getBtnInsert().getText().equals("Edit")) {
            
            CreatePersonFromView();
            
            model.Update(OldId);

            this.view.setVisible(false);

            JOptionPane.showMessageDialog(null, "PERSON HAS BEEN UPDATED");
            
        } else {
            
            CreatePersonFromView();

            model.Insert();

            this.view.setVisible(false);

            JOptionPane.showMessageDialog(null, "PERSON HAS BEEN ADDED");
            
            
            
        }

    }

    private void btnClearOnMouseClicked(MouseEvent e) {

    }
    
    
    //SINGLETON

    public static PersonFormController getIntance(Desktop desktop){
        
        if (INSTANCE == null){
            
            INSTANCE = new PersonFormController(desktop,new PersonForm(),new PersonImp());
        }
        
        return INSTANCE;
        
    }
    

}
