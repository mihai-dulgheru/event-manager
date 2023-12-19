package main;

import abstractClasses.AbstractModel;
import classes.Application;
import database.Database;
import designPatterns.abstractFactory.*;
import mvc.model.Contract;
import mvc.model.Locatie;
import util.DateUtil;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        Application.start();
        try {
            Database.connect();

            List<AbstractModel> contracte = Contract.readMany();
            Contract contract = (Contract) contracte.getFirst();
            List<AbstractModel> locatii = Locatie.readMany();
            Locatie locatie = (Locatie) locatii.getFirst();

            EvenimentFactory factory;
            factory = new BotezFactory();
            factory = new ConcertFactory();
            factory = new NuntaFactory();

            EvenimentCuTematica evenimentCuTematica = factory.createEvenimentCuTematica(contract.getId(), locatie.getId(), DateUtil.today(), 100);
            EvenimentFaraTematica evenimentFaraTematica = factory.createEvenimentFaraTematica(contract.getId(), locatie.getId(), DateUtil.today(), 100);

            evenimentCuTematica.tiparesteInvitatie();
            evenimentFaraTematica.tiparesteInvitatie();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Database.disconnect();
        }
    }
}