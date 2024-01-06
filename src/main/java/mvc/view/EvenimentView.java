package mvc.view;

import abstractClasses.AbstractModel;
import interfaces.Printable;
import mvc.model.Eveniment;
import mvc.model.Locatie;

import java.sql.SQLException;

public class EvenimentView implements Printable {
    @Override
    public void print(AbstractModel model) {
        try {
            Eveniment eveniment = (Eveniment) model;
            AbstractModel abstractModelLocatie;
            abstractModelLocatie = Locatie.readDenumireLocatie(eveniment.getIdLocatie());
            Locatie locatie = (Locatie) abstractModelLocatie;
            System.out.println(eveniment.getTipEveniment() + " | " + eveniment.getDataEveniment() + " | " + locatie + " | " + eveniment.getNrParticipanti() + " participanti");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
