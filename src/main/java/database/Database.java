package database;

import designPatterns.proxy.ClientProxy;
import designPatterns.proxy.IAccountCreation;
import enums.MetodaDePlata;
import enums.Moneda;
import enums.TipEveniment;
import exceptions.ClientAgeException;
import mvc.model.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public final class Database {
    public static Connection connection;
    public static Statement statement;

    private Database() {
    }

    public static void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // TODO: populează baza de date cu date plauzibile
    public static void populate() {
        try {
            Client[] clients = getClients();

            for (Client client : clients) {
                client.insert();
            }

            Contract[] contracts = {
                    new Contract(clients[0].getId(), 10_000d, Moneda.RON, "Observații 1", MetodaDePlata.CARD),
                    new Contract(clients[1].getId(), 11_000d, Moneda.EUR, "Observații 2", MetodaDePlata.CASH),
                    new Contract(clients[2].getId(), 12_000d, Moneda.USD, "Observații 3", MetodaDePlata.TRANSFER_BANCAR),
                    new Contract(clients[3].getId(), 13_000d, Moneda.RON, "Observații 4", MetodaDePlata.CARD),
                    new Contract(clients[4].getId(), 14_000d, Moneda.EUR, "Observații 5", MetodaDePlata.CASH),};

            for (Contract contract : contracts) {
                contract.insert();
            }

            Locatie[] locations = {
                    new Locatie("Denumire locație 1", 100),
                    new Locatie("Denumire locație 2", 200),
                    new Locatie("Denumire locație 3", 300),
                    new Locatie("Denumire locație 4", 400),
                    new Locatie("Denumire locație 5", 500),};

            for (Locatie locatie : locations) {
                locatie.insert();
            }

            Eveniment[] events = {
                    new Eveniment(contracts[0].getId(), locations[0].getId(), TipEveniment.NUNTA, "2023-12-17", 50),
                    new Eveniment(contracts[1].getId(), locations[1].getId(), TipEveniment.CONCERT, "2023-12-18", 150),
                    new Eveniment(contracts[2].getId(), locations[2].getId(), TipEveniment.BOTEZ, "2023-12-19", 250),
                    new Eveniment(contracts[3].getId(), locations[3].getId(), TipEveniment.EXPOZITIE, "2023-12-20", 350),
                    new Eveniment(contracts[4].getId(), locations[4].getId(), TipEveniment.PETRECERE_ABSOLVIRE, "2023-12-21", 450),
            };

            for (Eveniment eveniment : events) {
                eveniment.insert();
            }

            Serviciu[] servicii = {
                    new Serviciu("Nume serviciu de bază 1", 100f, 1f, "Detalii serviciu de bază 1", "DEFAULT"),
                    new Serviciu("Nume serviciu de bază 2", 200f, 2f, "Detalii serviciu de bază 2", "DEFAULT"),
                    new Serviciu("Nume serviciu 1", 100f, 1f, "Detalii serviciu 1", TipEveniment.BOTEZ.toString()),
                    new Serviciu("Nume serviciu 2", 200f, 2f, "Detalii serviciu 2", TipEveniment.CONCERT.toString()),
                    new Serviciu("Nume serviciu 3", 300f, 3f, "Detalii serviciu 3", TipEveniment.EXPOZITIE.toString()),
                    new Serviciu("Nume serviciu 4", 400f, 4f, "Detalii serviciu 4", TipEveniment.NUNTA.toString()),
                    new Serviciu("Nume serviciu 5", 500f, 5f, "Detalii serviciu 5", TipEveniment.PETRECERE_ABSOLVIRE.toString()),};

            for (Serviciu serviciu : servicii) {
                serviciu.insert();
            }

            Pachet[] packets = {
                    new Pachet.PachetBuilder(events[0].getId()).addServiciu(servicii[2]).build(),
                    new Pachet.PachetBuilder(events[1].getId()).addServiciu(servicii[3]).build(),
                    new Pachet.PachetBuilder(events[2].getId()).addServiciu(servicii[4]).build(),
                    new Pachet.PachetBuilder(events[3].getId()).addServiciu(servicii[5]).build(),
                    new Pachet.PachetBuilder(events[4].getId()).addServiciu(servicii[6]).build(),};

            for (Pachet pachet : packets) {
                pachet.insert();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Client[] getClients() throws ClientAgeException, NoSuchAlgorithmException, InvalidKeySpecException {
        IAccountCreation proxy = new ClientProxy();
        return new Client[]{
                proxy.createAccount("Nume client 1", "Prenume client 1", "5010101408138", "Adresa client 1", "Email client 1", "Telefon client 1", "client1", "parola1"),
                proxy.createAccount("Nume client 2", "Prenume client 2", "5020202406649", "Adresa client 2", "Email client 2", "Telefon client 2", "client2", "parola2"),
                proxy.createAccount("Nume client 3", "Prenume client 3", "5030303409446", "Adresa client 3", "Email client 3", "Telefon client 3", "client3", "parola3"),
                proxy.createAccount("Nume client 4", "Prenume client 4", "5040404405101", "Adresa client 4", "Email client 4", "Telefon client 4", "client4", "parola4"),
                proxy.createAccount("Nume client 5", "Prenume client 5", "5050505405093", "Adresa client 5", "Email client 5", "Telefon client 5", "client5", "parola5"),};
    }
}
