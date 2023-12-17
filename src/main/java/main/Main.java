package main;

import model.*;
import model.enums.MetodaDePlata;
import model.enums.Moneda;
import model.enums.TipEveniment;

import java.sql.SQLException;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        try {
            Database.connect();

            // Test Pachet
            Pachet pachet = new Pachet(UUID.randomUUID(), "Nume Pachet", "Detalii Pachet");
            pachet.insert();
            pachet.setDetaliiPachet("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas metus neque, sodales quis odio a, consectetur dignissim tortor. Vivamus et eros at erat viverra tincidunt ut eu dolor. Nam ultricies convallis nisi nec faucibus.");
            pachet.update();
            System.out.println(Pachet.readOne(pachet.getId()));
            System.out.println(Pachet.readMany());
            pachet.delete();

            pachet.insert();
            pachet.setDetaliiPachet("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas metus neque, sodales quis odio a, consectetur dignissim tortor. Vivamus et eros at erat viverra tincidunt ut eu dolor. Nam ultricies convallis nisi nec faucibus.");
            pachet.update();
            System.out.println(Pachet.readOne(pachet.getId()));
            System.out.println(Pachet.readMany());

            // Test Client
            Client client = new Client("Nume Client", "Prenume Client", "CNP Client", "Adresa Client", "Email Client", "Telefon Client");
            client.insert();
            client.setNumeClient("Nume Client Modificat");
            client.update();
            System.out.println(Client.readOne(client.getId()));
            System.out.println(Client.readMany());
            client.delete();

            client.insert();
            client.setNumeClient("Nume Client Modificat");
            client.update();
            System.out.println(Client.readOne(client.getId()));
            System.out.println(Client.readMany());

            // Test Contract
            Contract contract = new Contract(client.getId(), "2023-12-17", 10_000d, Moneda.RON, "Observatii", MetodaDePlata.CARD);
            contract.insert();
            contract.setCostTotal(9_000d);
            contract.update();
            System.out.println(Contract.readOne(contract.getId()));
            System.out.println(Contract.readMany());
            contract.delete();

            contract.insert();
            contract.setCostTotal(9_000d);
            contract.update();
            System.out.println(Contract.readOne(contract.getId()));
            System.out.println(Contract.readMany());

            // Test Eveniment
            Eveniment eveniment = new Eveniment(UUID.randomUUID(), TipEveniment.NUNTA, "2023-12-17", "locatie 1");
            eveniment.insert();
            eveniment.setLocatie("locatie 2");
            eveniment.update();
            System.out.println(Eveniment.readOne(eveniment.getidEveniment()));
            System.out.println(Eveniment.readMany());
            eveniment.delete();

            eveniment.insert();
            eveniment.setLocatie("locatie 2");
            eveniment.update();
            System.out.println(Eveniment.readOne(eveniment.getidEveniment()));
            System.out.println(Eveniment.readMany());

            // Test Serviciu
            Serviciu serviciu = new Serviciu(UUID.randomUUID(), "Nume Serviciu", 120.5f, 2f, "Detalii serviciu");
            serviciu.insert();
            serviciu.setObservatii("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas metus neque, sodales quis odio a, consectetur dignissim tortor. Vivamus et eros at erat viverra tincidunt ut eu dolor. Nam ultricies convallis nisi nec faucibus.");
            serviciu.update();
            System.out.println(Serviciu.readOne(serviciu.getId()));
            System.out.println(Serviciu.readMany());
            serviciu.delete();

            serviciu.insert();
            serviciu.setDurata(2.5f);
            serviciu.update();
            System.out.println(Serviciu.readOne(serviciu.getId()));
            System.out.println(Serviciu.readMany());

            Database.disconnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}