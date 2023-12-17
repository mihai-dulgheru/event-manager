package mvc.model;

import abstractClasses.ACRUDOperations;
import abstractClasses.AModel;
import database.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Active Record
 */
public class Locatie extends AModel {
    static {
        try {
            Database.statement.executeUpdate("CREATE TABLE IF NOT EXISTS locatii" +
                    "(" +
                    "    id_locatie VARCHAR(36) PRIMARY KEY," +
                    "    denumire   VARCHAR(255) NOT NULL," +
                    "    capacitate INTEGER NOT NULL" +
                    ")");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private final UUID idLocatie;
    private String denumire;
    private int capacitate;

    public Locatie(String denumire, int capacitate) {
        this.idLocatie = UUID.randomUUID();
        this.denumire = denumire;
        this.capacitate = capacitate;
    }

    private Locatie(UUID idLocatie, String denumire, int capacitate) {
        this.idLocatie = idLocatie;
        this.denumire = denumire;
        this.capacitate = capacitate;
    }

    public static ACRUDOperations readOne(UUID id) throws SQLException {
        String selectString = "SELECT * FROM locatii WHERE id_locatie = ?";
        PreparedStatement selectLocation = Database.connection.prepareStatement(selectString);

        selectLocation.setString(1, id.toString());

        ResultSet rs = selectLocation.executeQuery();
        return load(rs);
    }

    public static List<ACRUDOperations> readMany() throws SQLException {
        ResultSet rs = Database.statement.executeQuery("SELECT * FROM locatii");

        List<ACRUDOperations> locations = new ArrayList<>();
        while (rs.next()) {
            locations.add(load(rs));
        }

        return locations;
    }

    protected static ACRUDOperations load(ResultSet resultSet) throws SQLException {
        UUID idLocatie = UUID.fromString(resultSet.getString(1));
        String denumire = resultSet.getString(2);
        int capacitate = resultSet.getInt(3);

        return new Locatie(idLocatie, denumire, capacitate);
    }

    @Override
    public void insert() throws SQLException {
        String insertString = "INSERT INTO locatii VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = Database.connection.prepareStatement(insertString);

        preparedStatement.setString(1, idLocatie.toString());
        preparedStatement.setString(2, denumire);
        preparedStatement.setInt(3, capacitate);

        preparedStatement.executeUpdate();
        System.out.println("1 row(s) affected");
    }

    @Override
    public void update() throws SQLException {
        String updateString = "UPDATE locatii SET denumire = ?, capacitate = ? WHERE id_locatie = ?";
        PreparedStatement preparedStatement = Database.connection.prepareStatement(updateString);

        preparedStatement.setString(1, denumire);
        preparedStatement.setInt(2, capacitate);
        preparedStatement.setString(3, idLocatie.toString());

        preparedStatement.executeUpdate();
        System.out.println("1 row(s) affected");
    }

    @Override
    public void delete() throws SQLException {
        String deleteString = "DELETE FROM locatii WHERE id_locatie = ?";
        PreparedStatement preparedStatement = Database.connection.prepareStatement(deleteString);

        preparedStatement.setString(1, idLocatie.toString());

        preparedStatement.executeUpdate();
        System.out.println("1 row(s) affected");
    }

    public UUID getIdLocatie() {
        return idLocatie;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public int getCapacitate() {
        return capacitate;
    }

    public void setCapacitate(int capacitate) {
        this.capacitate = capacitate;
    }

    @Override
    public String toString() {
        return "Locatie{" + "idLocatie=" + idLocatie + ", denumire='" + denumire + '\'' + ", capacitate=" + capacitate + '}';
    }
}
