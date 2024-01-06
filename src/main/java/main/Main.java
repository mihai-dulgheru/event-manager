package main;

import classes.Application;
import database.Database;
import designPatterns.abstractFactory.BotezFactory;
import designPatterns.abstractFactory.EvenimentCuTematica;
import designPatterns.abstractFactory.EvenimentFactory;
import mvc.model.Contract;
import mvc.model.Locatie;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
//        Database.populate();
//        Application.start();
        Database.connect();
        EvenimentFactory factory = new BotezFactory();
        try {
            Contract contract = (Contract) Contract.readMany().get(0);
            Locatie locatie = (Locatie) Locatie.readMany().get(0);
            EvenimentCuTematica evenimentCuTematica = factory.createEvenimentCuTematica(contract.getId(), locatie.getId(), "2024-06-01", 100, "Tematica");
            evenimentCuTematica.tiparesteInvitatie();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}