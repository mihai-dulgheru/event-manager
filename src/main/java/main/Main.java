package main;

import model.Database;
import model.Eveniment;
import model.Pachet;
import model.enums.TipEveniment;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws ParseException {
        try {
            Database.connect();

            Pachet pachet = new Pachet(UUID.randomUUID(), "Nume Pachet", "Detalii Pachet");
            pachet.insert();
            pachet.setDetaliiPachet("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas metus neque, sodales quis odio a, consectetur dignissim tortor. Vivamus et eros at erat viverra tincidunt ut eu dolor. Nam ultricies convallis nisi nec faucibus.");
            pachet.update();
            System.out.println(Pachet.readOne(pachet.getId()));
            System.out.println(Pachet.readMany());
            pachet.delete();

            pachet = new Pachet(UUID.randomUUID(), "Nume Pachet", "Detalii Pachet");
            pachet.insert();
            pachet.setDetaliiPachet("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas metus neque, sodales quis odio a, consectetur dignissim tortor. Vivamus et eros at erat viverra tincidunt ut eu dolor. Nam ultricies convallis nisi nec faucibus.");
            pachet.update();
            System.out.println(Pachet.readOne(pachet.getId()));
            System.out.println(Pachet.readMany());


            DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
            Eveniment eveniment = new Eveniment(UUID.randomUUID(), TipEveniment.NUNTA, new Date(df.parse("12-10-2025").getTime()), "locatie 1");
            eveniment.insert();
            eveniment.setLocatie("locatie 2");
            eveniment.update();
            System.out.println(Eveniment.readOne(eveniment.getidEveniment()));
            System.out.println(Eveniment.readMany());
            eveniment.delete();

            eveniment = new Eveniment(UUID.randomUUID(), TipEveniment.BOTEZ, new Date(df.parse("05-05-2024").getTime()), "locatie 3");
            eveniment.insert();
            eveniment.setLocatie("locatie 4");
            eveniment.update();
            System.out.println(Eveniment.readOne(eveniment.getidEveniment()));
            System.out.println(Eveniment.readMany());

            Database.disconnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}