package Controllers;

import Views.Static.Effects;
import java.awt.Color;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Models.BD.PersonImp;
import Models.Person;
import Views.Persons.Find;
import Views.Persons.Insert;

/**
 *
 * @author MrRainx
 */
public class FindController {

    private PersonImp person;
    private Find find;

    private DefaultTableModel ModelT;

    public FindController(PersonImp person, Find fin) {
        this.person = person;
        this.find = fin;
    }

    public FindController() {
    }

    /*
        INIT
     */
    public void Init() {

        this.find.setVisible(true);

        ModelT = (DefaultTableModel) this.find.getTabPersons().getModel();
        
        this.find.getTxtFind().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                TxtOnKeyRelessed(e);
            }

        });

        this.find.getBtnNew().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnNewOnMaouseClicked(e);
            }

        });

        this.find.getBtnEdit().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnEditMouseClicked(e);
            }

        });

        this.find.getBtnDelete().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnDeleteOnMouseClicked(e);
            }

        });

        LoadTableAll();
        InitEffects();

    }

    private void InitEffects() {

        Color exitColor = new Color(240, 240, 240);

        this.find.getBtnNew().setBackground(exitColor);
        this.find.getBtnEdit().setBackground(exitColor);
        this.find.getBtnDelete().setBackground(exitColor);

        Effects.colorChanger(this.find.getBtnNew(), new Color(68, 98, 145), exitColor);
        Effects.colorChanger(this.find.getBtnEdit(), new Color(68, 98, 145), exitColor);
        Effects.colorChanger(this.find.getBtnDelete(), new Color(68, 98, 145), exitColor);


    }

    /*
        SUPPORT METHODS
     */
    private void LoadTableAll() {

        List<Person> PersonList = person.SelectAll();

        PersonList.forEach((obj) -> {
            InsertRow(obj);

            this.find.getLbState().setText(PersonList.size() + " rows");

        });

    }

    private void InsertRow(Person obj) {
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

        List<Person> PersonList = person.SelectOne(Aguja);
        PersonList.forEach((obj) -> {
            InsertRow(obj);
        });
        this.find.getLbState().setText(PersonList.size() + " rows");

    }

    private void ResetTable() {

        int a = this.find.getTabPersons().getRowCount() - 1;

        try {
            for (int i = a; i >= 0; i++) {
                ModelT.removeRow(this.find.getTabPersons().getRowCount() - 1);
            }

        } catch (ArrayIndexOutOfBoundsException e) {

        }

    }

    private PersonImp createPersonFromTable(int row) {

        return new PersonImp(
                (String) this.find.getTabPersons().getValueAt(row, 0),
                (String) this.find.getTabPersons().getValueAt(row, 1),
                (String) this.find.getTabPersons().getValueAt(row, 2),
                (LocalDate) this.find.getTabPersons().getValueAt(row, 3),
                (String) this.find.getTabPersons().getValueAt(row, 4),
                (String) this.find.getTabPersons().getValueAt(row, 5),
                (Double) this.find.getTabPersons().getValueAt(row, 6),
                (Integer) this.find.getTabPersons().getValueAt(row, 7)
        );

    }

    /*
        EVENTS
     */
    private void TxtOnKeyRelessed(KeyEvent e) {
        ResetTable();
        LoadOneToTable(this.find.getTxtFind().getText());

    }

    private void btnNewOnMaouseClicked(MouseEvent e) {
        this.find.setVisible(false);

        InsertController insert = new InsertController(new PersonImp(), new Insert());
        insert.Init();
        insert.InitInsert();
    }

    private void btnEditMouseClicked(MouseEvent e) {

        int row = this.find.getTabPersons().getSelectedRow();

        if (row != -1) {

            this.find.dispose();

            InsertController update = new InsertController(createPersonFromTable(row), new Insert());
            
            update.Init();
            
            update.InitEdit();

        } else {
            JOptionPane.showMessageDialog(null, "SELECT ONE ROW");
        }

    }

    private void btnDeleteOnMouseClicked(MouseEvent e) {

        int row = this.find.getTabPersons().getSelectedRow();

        if (row != -1) {

            ImageIcon icon = new ImageIcon("src/Run/Static/icons/Denied_48px.png");

            int option = JOptionPane.showConfirmDialog(null,
                    createPersonFromTable(row),
                    "ARE YOU SURE TO DELETE THIS PERSON?",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    icon
            );

            if (option == 0) {
                
                String Aguja = createPersonFromTable(row).getIdPerson();
                
                person.Delete(Aguja);

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

}
