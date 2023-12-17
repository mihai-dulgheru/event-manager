package database;

import enums.MetodaDePlata;
import enums.Moneda;
import enums.TipEveniment;
import mvc.model.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

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

    public static void populate() {
        try {
            Client[] clients = {
                    new Client("Nume Client 1", "Prenume Client 1", "CNP Client 1", "Adresa Client 1", "Email Client 1", "Telefon Client 1", "client1", "parola1"),
                    new Client("Nume Client 2", "Prenume Client 2", "CNP Client 2", "Adresa Client 2", "Email Client 2", "Telefon Client 2", "client2", "parola2"),
                    new Client("Nume Client 3", "Prenume Client 3", "CNP Client 3", "Adresa Client 3", "Email Client 3", "Telefon Client 3", "client3", "parola3"),
                    new Client("Nume Client 4", "Prenume Client 4", "CNP Client 4", "Adresa Client 4", "Email Client 4", "Telefon Client 4", "client4", "parola4"),
                    new Client("Nume Client 5", "Prenume Client 5", "CNP Client 5", "Adresa Client 5", "Email Client 5", "Telefon Client 5", "client5", "parola5"),};

            Contract[] contracts = {
                    new Contract(clients[0].getId(), "2023-12-17", 10_000d, Moneda.RON, "Observatii", MetodaDePlata.CARD),
                    new Contract(clients[1].getId(), "2023-12-18", 10_000d, Moneda.EUR, "Observatii 2", MetodaDePlata.CASH),
                    new Contract(clients[2].getId(), "2023-12-19", 10_000d, Moneda.USD, "Observatii 3", MetodaDePlata.TRANSFER_BANCAR),
                    new Contract(clients[3].getId(), "2023-12-20", 10_000d, Moneda.RON, "Observatii 4", MetodaDePlata.CARD),
                    new Contract(clients[4].getId(), "2023-12-21", 10_000d, Moneda.EUR, "Observatii 5", MetodaDePlata.CASH),};

            Locatie[] locatii = {
                    new Locatie("Denumire locatie 1", 100),
                    new Locatie("Denumire locatie 2", 200),
                    new Locatie("Denumire locatie 3", 300),
                    new Locatie("Denumire locatie 4", 400),
                    new Locatie("Denumire locatie 5", 500),};

            Eveniment[] evenimente = {
                    new Eveniment(UUID.randomUUID(), locatii[0].getIdLocatie(), TipEveniment.NUNTA, "2023-12-17"),
                    new Eveniment(UUID.randomUUID(), locatii[1].getIdLocatie(), TipEveniment.CONCERT, "2023-12-18"),
                    new Eveniment(UUID.randomUUID(), locatii[2].getIdLocatie(), TipEveniment.BOTEZ, "2023-12-19"),
                    new Eveniment(UUID.randomUUID(), locatii[3].getIdLocatie(), TipEveniment.CONFERINTA, "2023-12-20"),
                    new Eveniment(UUID.randomUUID(), locatii[4].getIdLocatie(), TipEveniment.CUNUNIE, "2023-12-21"),};

            Pachet[] pachete = {
                    new Pachet(UUID.randomUUID(), "Nume Pachet 1", "Detalii Pachet 1"),
                    new Pachet(UUID.randomUUID(), "Nume Pachet 2", "Detalii Pachet 2"),
                    new Pachet(UUID.randomUUID(), "Nume Pachet 3", "Detalii Pachet 3"),
                    new Pachet(UUID.randomUUID(), "Nume Pachet 4", "Detalii Pachet 4"),
                    new Pachet(UUID.randomUUID(), "Nume Pachet 5", "Detalii Pachet 5"),};

            Serviciu[] servicii = {
                    new Serviciu(UUID.randomUUID(), "Nume Serviciu 1", 100.99f, 1f, "Detalii serviciu 1"),
                    new Serviciu(UUID.randomUUID(), "Nume Serviciu 2", 200.99f, 2f, "Detalii serviciu 2"),
                    new Serviciu(UUID.randomUUID(), "Nume Serviciu 3", 300.99f, 3f, "Detalii serviciu 3"),
                    new Serviciu(UUID.randomUUID(), "Nume Serviciu 4", 400.99f, 4f, "Detalii serviciu 4"),
                    new Serviciu(UUID.randomUUID(), "Nume Serviciu 5", 500.99f, 5f, "Detalii serviciu 5"),};

            for (Client client : clients) {
                client.insert();
            }

            for (Contract contract : contracts) {
                contract.insert();
            }

            for (Locatie locatie : locatii) {
                locatie.insert();
            }

            for (Eveniment eveniment : evenimente) {
                eveniment.insert();
            }

            for (Pachet pachet : pachete) {
                pachet.insert();
            }

            for (Serviciu serviciu : servicii) {
                serviciu.insert();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
