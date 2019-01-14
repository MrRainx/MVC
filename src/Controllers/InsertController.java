package Controllers;

import Views.Static.Effects;
import Run.Validators;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import javax.swing.JOptionPane;
import Models.BD.PersonImp;
import Views.Find;
import Views.Insert;
import javax.swing.ImageIcon;

/**
 *
 * @author MrRainx
 */
public class InsertController {

    private PersonImp person;
    private Insert view;
    
    private String OldId;

    public InsertController(PersonImp person, Insert insert) {
        this.person = person;
        this.view = insert;
        InitEffects();
        
    }

    public InsertController() {
    }

    /*
        INIT 
     */
    
    public void Init(){
        

        
        this.view.getBtnInsert().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnInsertOnMaouseClicked(e);
            }
        });
        
        this.view.getValID().setIcon(new ImageIcon("src/Views/Static/icons/Box Important_20px.png"));
        
    }
    
    public void InitInsert() {
        this.view.setVisible(true);
        this.view.setLocationRelativeTo(null);

        this.view.getBtnClear().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnClearOnMouseClicked(e);
            }
            
        });

    }

    public void InitEdit() {
        
        OldId = this.person.getIdPerson();
        this.view.getBtnInsert().setText("Edit");

        this.view.getTxtID().setText(person.getIdPerson());
        this.view.getTxtName().setText(person.getNames());
        this.view.getTxtLastName().setText(person.getLastNames());
        this.view.getjCalendar().setDate(Date.valueOf(person.getBirthdate()));
        this.view.getTxtPhone().setText(person.getPhone());
        this.view.getTxtSex().setText(person.getSex());
        this.view.getTxtSalary().setText(Double.toString(person.getSalary()));
        this.view.getTxtCupo().setText(Integer.toString(person.getQuota()));

    }

    private void InitEffects() {
        
        Color exitColor = new Color(240, 240, 240);
        
        this.view.getBtnInsert().setBackground(exitColor);
        this.view.getBtnClear().setBackground(exitColor);
        Effects.colorChanger(this.view.getBtnInsert(), new Color(68, 98, 145), exitColor);
        Effects.colorChanger(this.view.getBtnClear(), new Color(68, 98, 145), exitColor);
        Effects.moveableFrame(this.view);
    }

    /*
        SUPPORT METHODS
     */
    private void TxtCedulaListener() {

        this.view.getTxtID().addCaretListener(e -> {

            if (this.view.getTxtID().getText().length() == 10) {

                if (!Validators.isCedula(this.view.getTxtID().getText())) {
                    JOptionPane.showMessageDialog(null, "CEDULA ERRONEA");
                }

            } else if (this.view.getTxtID().getText().length() > 10) {
                JOptionPane.showMessageDialog(null, "CEDULA ERRONEA");
            }

        });

    }

    private LocalDate getDateFromTxt() {

        return LocalDate.of(
                this.view.getjCalendar().getCalendar().get(Calendar.YEAR),
                this.view.getjCalendar().getCalendar().get(Calendar.MONTH) + 1,
                this.view.getjCalendar().getCalendar().get(Calendar.DAY_OF_MONTH));
    }

    /*
        EVENTS
     */
    private void btnInsertOnMaouseClicked(MouseEvent e) {

        if (view.getBtnInsert().getText().equals("Edit")) {
            person.setIdPerson(this.view.getTxtID().getText());
            person.setNames(this.view.getTxtName().getText());
            person.setLastNames(this.view.getTxtLastName().getText());
            person.setBirthdate(getDateFromTxt());
            person.setPhone(this.view.getTxtPhone().getText());
            person.setSex(this.view.getTxtSex().getText());
            person.setSalary(Double.parseDouble(this.view.getTxtSalary().getText()));
            person.setQuota(Integer.parseInt(this.view.getTxtCupo().getText()));
            
            
            person.Update(OldId);

            this.view.setVisible(false);

            JOptionPane.showMessageDialog(null, "PERSON HAS BEEN UPDATED");

            FindController find = new FindController(new PersonImp(), new Find());
            find.Init();

        } else {

            person.setIdPerson(this.view.getTxtID().getText());
            person.setNames(this.view.getTxtName().getText());
            person.setLastNames(this.view.getTxtLastName().getText());
            person.setBirthdate(getDateFromTxt());
            person.setPhone(this.view.getTxtPhone().getText());
            person.setSex(this.view.getTxtSex().getText());
            person.setSalary(Double.parseDouble(this.view.getTxtSalary().getText()));
            person.setQuota(Integer.parseInt(this.view.getTxtCupo().getText()));

            person.Insert();

            this.view.setVisible(false);

            JOptionPane.showMessageDialog(null, "PERSON HAS BEEN ADDED");

            FindController find = new FindController(new PersonImp(), new Find());
            find.Init();

        }

    }
    
    private void btnClearOnMouseClicked(MouseEvent e){
        
    }
    
}
