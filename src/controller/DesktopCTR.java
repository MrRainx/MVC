/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.libraries.Effects;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import model.dao.PersonImp;
import model.dao.UsersImp;
import view.Desktop;
import view.persons.PersonList;

/**
 *
 * @author MrRainx
 */
public class DesktopCTR {

    private UsersImp user;
    private Desktop desktop;

    public DesktopCTR(UsersImp user, Desktop desktop) {
        this.user = user;
        this.desktop = desktop;
    }


    /*
        INIT
     */
    public void Init() {

        InitEvents();
        InitEffects();

        desktop.setVisible(true);
        desktop.setLocationRelativeTo(null);

    }

    private void InitEvents() {

        desktop.getBtnPersons().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnPersonsOnMouseClicked(e);
            }

        });

    }

    private void InitEffects() {

        Color colorBase = desktop.getBtnPersons().getBackground();

        Effects.colorHover(desktop.getBtnPersons(), Color.CYAN, colorBase);

    }


    /*
        SUPPORT METHODS
     */
 /*
        EVENTS
     */
    private void btnPersonsOnMouseClicked(MouseEvent e) {

        PersonListCTR person = new PersonListCTR(desktop, new PersonList(), new PersonImp());
        person.Init();
        
    }
}
