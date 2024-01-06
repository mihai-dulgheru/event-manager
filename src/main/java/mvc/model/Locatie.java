package mvc.model;

import abstractClasses.AbstractModel;
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
public class Locatie extends AbstractModel {
    static {
        try {
            Database.statement.executeUpdate("CREATE TABLE IF NOT EXISTS locatii" +
                    "(" +
                    "    id_locatie VARCHAR(36) PRIMARY KEY," +
                    "    denumire   VARCHAR(255) NOT NULL," +
                    "    capacitate INTEGER NOT NULL," +
                    "    cost_locatie INTEGER NOT NULL" +
                    ")");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private final UUID id;
    private String denumire;
    private int capacitate;
    private int cost;

    public Locatie(String denumire, int capacitate, int cost) {
        this.id = UUID.randomUUID();
        this.denumire = denumire;
        this.capacitate = capacitate;
        this.cost = cost;
    }

    private Locatie(UUID id, String denumire, int capacitate, int cost) {
        this.id = id;
        this.denumire = denumire;
        this.capacitate = capacitate;
        this.cost = cost;
    }

    public static AbstractModel readOne(UUID id) throws SQLException {
        String selectString = "SELECT * FROM locatii WHERE id_locatie = ?";
        PreparedStatement selectLocation = Database.connection.prepareStatement(selectString);

        selectLocation.setString(1, id.toString());

        ResultSet rs = selectLocation.executeQuery();
        return load(rs);
    }

    public static List<AbstractModel> readMany() throws SQLException {
        ResultSet rs = Database.statement.executeQuery("SELECT * FROM locatii");

        List<AbstractModel> locations = new ArrayList<>();
        while (rs.next()) {
            locations.add(load(rs));
        }

        return locations;
    }

    public static AbstractModel readDenumireLocatie(UUID id_locatie) throws SQLException {
        String selectString = "SELECT * FROM locatii where id_locatie = ?";
        PreparedStatement selectLocatie = Database.connection.prepareStatement(selectString);

        selectLocatie.setString(1, id_locatie.toString());

        ResultSet rs = selectLocatie.executeQuery();
        return load(rs);
    }

    protected static AbstractModel load(ResultSet resultSet) throws SQLException {
        UUID idLocatie = UUID.fromString(resultSet.getString(1));
        String denumire = resultSet.getString(2);
        int capacitate = resultSet.getInt(3);
        int cost = resultSet.getInt(4);

        return new Locatie(idLocatie, denumire, capacitate, cost);
    }

    public static List<Locatie> findByDataEveniment(String dataEveniment) throws SQLException {
        String selectString = "SELECT * FROM locatii WHERE id_locatie NOT IN (SELECT id_locatie FROM evenimente WHERE data_eveniment = ?)";
        PreparedStatement preparedStatement = Database.connection.prepareStatement(selectString);

        preparedStatement.setString(1, dataEveniment);

        ResultSet rs = preparedStatement.executeQuery();
        List<Locatie> locatii = new ArrayList<>();
        while (rs.next()) {
            UUID idLocatie = UUID.fromString(rs.getString(1));
            String denumire = rs.getString(2);
            int capacitate = rs.getInt(3);
            int cost = rs.getInt(4);

            locatii.add(new Locatie(idLocatie, denumire, capacitate, cost));
        }

        return locatii;
    }

    @Override
    public void insert() throws SQLException {
        String insertString = "INSERT INTO locatii VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = Database.connection.prepareStatement(insertString);

        preparedStatement.setString(1, id.toString());
        preparedStatement.setString(2, denumire);
        preparedStatement.setInt(3, capacitate);
        preparedStatement.setInt(4, cost);

        preparedStatement.executeUpdate();
    }

    @Override
    public void update() throws SQLException {
        String updateString = "UPDATE locatii SET denumire = ?, capacitate = ?, cost = ? WHERE id_locatie = ?";
        PreparedStatement preparedStatement = Database.connection.prepareStatement(updateString);

        preparedStatement.setString(1, denumire);
        preparedStatement.setInt(2, capacitate);
        preparedStatement.setInt(3, cost);
        preparedStatement.setString(4, id.toString());

        preparedStatement.executeUpdate();
    }

    @Override
    public void delete() throws SQLException {
        String deleteString = "DELETE FROM locatii WHERE id_locatie = ?";
        PreparedStatement preparedStatement = Database.connection.prepareStatement(deleteString);

        preparedStatement.setString(1, id.toString());

        preparedStatement.executeUpdate();
    }

    public UUID getId() {
        return id;
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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Locatie{" +
                "id=" + id +
                ", denumire='" + denumire + '\'' +
                ", capacitate=" + capacitate +
                ", cost=" + cost +
                '}';
    }
}
