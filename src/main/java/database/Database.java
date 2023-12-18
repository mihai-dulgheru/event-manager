package database;

import enums.MetodaDePlata;
import enums.Moneda;
import enums.TipEveniment;
import mvc.model.*;

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
            Client[] clients = {
                    new Client("Nume Client 1", "Prenume Client 1", "CNP Client 1", "Adresa Client 1", "Email Client 1", "Telefon Client 1", "client1", "parola1"),
                    new Client("Nume Client 2", "Prenume Client 2", "CNP Client 2", "Adresa Client 2", "Email Client 2", "Telefon Client 2", "client2", "parola2"),
                    new Client("Nume Client 3", "Prenume Client 3", "CNP Client 3", "Adresa Client 3", "Email Client 3", "Telefon Client 3", "client3", "parola3"),
                    new Client("Nume Client 4", "Prenume Client 4", "CNP Client 4", "Adresa Client 4", "Email Client 4", "Telefon Client 4", "client4", "parola4"),
                    new Client("Nume Client 5", "Prenume Client 5", "CNP Client 5", "Adresa Client 5", "Email Client 5", "Telefon Client 5", "client5", "parola5"),};

            for (Client client : clients) {
                client.insert();
            }

            Contract[] contracts = {
                    new Contract(clients[0].getId(), 10_000d, Moneda.RON, "Observatii", MetodaDePlata.CARD),
                    new Contract(clients[1].getId(), 10_000d, Moneda.EUR, "Observatii 2", MetodaDePlata.CASH),
                    new Contract(clients[2].getId(), 10_000d, Moneda.USD, "Observatii 3", MetodaDePlata.TRANSFER_BANCAR),
                    new Contract(clients[3].getId(), 10_000d, Moneda.RON, "Observatii 4", MetodaDePlata.CARD),
                    new Contract(clients[4].getId(), 10_000d, Moneda.EUR, "Observatii 5", MetodaDePlata.CASH),};

            for (Contract contract : contracts) {
                contract.insert();
            }

            Locatie[] locations = {
                    new Locatie("Denumire locatie 1", 100),
                    new Locatie("Denumire locatie 2", 200),
                    new Locatie("Denumire locatie 3", 300),
                    new Locatie("Denumire locatie 4", 400),
                    new Locatie("Denumire locatie 5", 500),};

            for (Locatie locatie : locations) {
                locatie.insert();
            }

            Eveniment[] events = {
                    new Eveniment(contracts[0].getId(), locations[0].getIdLocatie(), TipEveniment.NUNTA, "2023-12-17", 50),
                    new Eveniment(contracts[1].getId(), locations[1].getIdLocatie(), TipEveniment.CONCERT, "2023-12-18", 150),
                    new Eveniment(contracts[2].getId(), locations[2].getIdLocatie(), TipEveniment.BOTEZ, "2023-12-19", 250),
                    new Eveniment(contracts[3].getId(), locations[3].getIdLocatie(), TipEveniment.EXPOZITIE, "2023-12-20", 350),
                    new Eveniment(contracts[4].getId(), locations[4].getIdLocatie(), TipEveniment.PETRECERE_ABSOLVIRE, "2023-12-21", 450),
            };

            for (Eveniment eveniment : events) {
                eveniment.insert();
            }

            Pachet[] packets = {
                    new Pachet(events[0].getIdEveniment(), "Nume Pachet 1", "Detalii Pachet 1"),
                    new Pachet(events[1].getIdEveniment(), "Nume Pachet 2", "Detalii Pachet 2"),
                    new Pachet(events[2].getIdEveniment(), "Nume Pachet 3", "Detalii Pachet 3"),
                    new Pachet(events[3].getIdEveniment(), "Nume Pachet 4", "Detalii Pachet 4"),
                    new Pachet(events[4].getIdEveniment(), "Nume Pachet 5", "Detalii Pachet 5"),};

            for (Pachet pachet : packets) {
                pachet.insert();
            }

            Serviciu[] servicii = {
                    new Serviciu(packets[0].getId(), "Nume Serviciu 1", 100.99f, 1f, "Detalii serviciu 1"),
                    new Serviciu(packets[1].getId(), "Nume Serviciu 2", 200.99f, 2f, "Detalii serviciu 2"),
                    new Serviciu(packets[2].getId(), "Nume Serviciu 3", 300.99f, 3f, "Detalii serviciu 3"),
                    new Serviciu(packets[3].getId(), "Nume Serviciu 4", 400.99f, 4f, "Detalii serviciu 4"),
                    new Serviciu(packets[4].getId(), "Nume Serviciu 5", 500.99f, 5f, "Detalii serviciu 5"),};

            for (Serviciu serviciu : servicii) {
                serviciu.insert();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
