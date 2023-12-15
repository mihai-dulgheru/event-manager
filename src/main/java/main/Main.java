package main;

import model.Database;
import model.Pachet;

import java.sql.SQLException;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
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

            Database.disconnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}