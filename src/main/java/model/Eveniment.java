package model;

import main.abstractClasses.ACRUDOperations;
import main.abstractClasses.AModel;
import model.enums.TipEveniment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Active Record
 */
public class Eveniment extends AModel {

    static {
        try {
            Database.statement.executeUpdate("CREATE TABLE IF NOT EXISTS evenimente" +
                    "(" +
                    "    id_eveniment   VARCHAR(36) PRIMARY KEY," +
                    "    id_contract    VARCHAR(36)  NOT NULL," +
                    "    tip_eveniment  VARCHAR(255) NOT NULL," +
                    "    data_eveniment DATE NOT NULL," +
                    "    locatie        VARCHAR(255) NOT NULL," +
                    "    FOREIGN KEY (id_contract) REFERENCES contracte (id_contract)" +
                    ")");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private final UUID idEveniment;
    private final UUID idContract;
    private TipEveniment tipEveniment;
    private String dataEveniment;
    private String locatie;

    public Eveniment(UUID idContract, TipEveniment tipEveniment, String dataEveniment, String locatie) {
        this.idEveniment = UUID.randomUUID();
        this.idContract = idContract;
        this.tipEveniment = tipEveniment;
        this.dataEveniment = dataEveniment;
        this.locatie = locatie;
    }

    private Eveniment(UUID idEveniment, UUID idContract, TipEveniment tipEveniment, String dataEveniment, String locatie) {
        this.idEveniment = idEveniment;
        this.idContract = idContract;
        this.tipEveniment = tipEveniment;
        this.dataEveniment = dataEveniment;
        this.locatie = locatie;
    }

    public static ACRUDOperations readOne(UUID id) throws SQLException {
        String selectString = "SELECT * FROM evenimente WHERE id_eveniment = ?";
        PreparedStatement selectPackage = Database.connection.prepareStatement(selectString);

        selectPackage.setString(1, id.toString());

        ResultSet rs = selectPackage.executeQuery();
        return load(rs);
    }

    public static List<ACRUDOperations> readMany() throws SQLException {
        ResultSet rs = Database.statement.executeQuery("SELECT * FROM evenimente");

        List<ACRUDOperations> packages = new ArrayList<>();
        while (rs.next()) {
            packages.add(load(rs));
        }

        return packages;
    }

    protected static ACRUDOperations load(ResultSet resultSet) throws SQLException {
        UUID idEveniment = UUID.fromString(resultSet.getString(1));
        UUID idContract = UUID.fromString(resultSet.getString(2));
        TipEveniment tipEveniment = TipEveniment.valueOf(resultSet.getString(3));
        String dataEveniment = resultSet.getString(4);
        String locatie = resultSet.getString(5);

        return new Eveniment(idEveniment, idContract, tipEveniment, dataEveniment, locatie);
    }

    @Override
    public void insert() throws SQLException {
        String insertString = "INSERT INTO evenimente VALUES (?, ?, ?, ?, ?)";
        PreparedStatement insertEvent = Database.connection.prepareStatement(insertString);

        insertEvent.setString(1, this.idEveniment.toString());
        insertEvent.setString(2, this.idContract.toString());
        insertEvent.setString(3, this.tipEveniment.toString());
        insertEvent.setString(4, this.dataEveniment);
        insertEvent.setString(5, this.locatie);

        insertEvent.executeUpdate();
        System.out.println("1 row(s) affected");
    }

    @Override
    public void update() throws SQLException {
        String updateString = "UPDATE evenimente SET id_contract = ?, tip_eveniment = ?, data_eveniment = ?, locatie = ? WHERE id_eveniment = ?";
        PreparedStatement updateEvent = Database.connection.prepareStatement(updateString);

        updateEvent.setString(1, this.idContract.toString());
        updateEvent.setString(2, this.tipEveniment.toString());
        updateEvent.setString(3, this.dataEveniment);
        updateEvent.setString(4, this.locatie);
        updateEvent.setString(5, this.idEveniment.toString());

        updateEvent.executeUpdate();
        System.out.println("1 row(s) affected");
    }

    @Override
    public void delete() throws SQLException {
        String deleteString = "DELETE FROM evenimente WHERE id_eveniment = ?";
        PreparedStatement deleteEvent = Database.connection.prepareStatement(deleteString);

        deleteEvent.setString(1, this.idEveniment.toString());

        deleteEvent.executeUpdate();
        System.out.println("1 row(s) affected");
    }

    public UUID getidEveniment() {
        return idEveniment;
    }

    public UUID getidContract() {
        return idContract;
    }

    public TipEveniment gettipEveniment() {
        return tipEveniment;
    }

    public void settipEveniment(TipEveniment tipEveniment) {
        this.tipEveniment = tipEveniment;
    }

    public String getdataEveniment() {
        return dataEveniment;
    }

    public void setdataEveniment(String dataEveniment) {
        this.dataEveniment = dataEveniment;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }


    @Override
    public String toString() {
        return "Eveniment{" +
                "idEveniment=" + idEveniment +
                ", idContract=" + idContract +
                ", tipEveniment=" + tipEveniment +
                ", dataEveniment=" + dataEveniment +
                ", locatie='" + locatie + '\'' +
                '}';
    }
}
