package mvc.model;

import abstractClasses.ACRUDOperations;
import abstractClasses.AModel;
import database.Database;
import enums.TipEveniment;

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
                    "    id_locatie     VARCHAR(36)  NOT NULL," +
                    "    tip_eveniment  VARCHAR(255) NOT NULL," +
                    "    data_eveniment DATE NOT NULL," +
                    "    FOREIGN KEY (id_contract) REFERENCES contracte (id_contract)," +
                    "    FOREIGN KEY (id_locatie) REFERENCES locatii (id_locatie)" +
                    ")");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private final UUID idEveniment;
    private final UUID idContract;
    private final UUID idLocatie;
    private TipEveniment tipEveniment;
    private String dataEveniment;

    public Eveniment(UUID idContract, UUID idLocatie, TipEveniment tipEveniment, String dataEveniment) {
        this.idEveniment = UUID.randomUUID();
        this.idContract = idContract;
        this.idLocatie = idLocatie;
        this.tipEveniment = tipEveniment;
        this.dataEveniment = dataEveniment;
    }

    private Eveniment(UUID idEveniment, UUID idContract, UUID idLocatie, TipEveniment tipEveniment, String dataEveniment) {
        this.idEveniment = idEveniment;
        this.idContract = idContract;
        this.idLocatie = idLocatie;
        this.tipEveniment = tipEveniment;
        this.dataEveniment = dataEveniment;
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
        UUID idLocatie = UUID.fromString(resultSet.getString(3));
        TipEveniment tipEveniment = TipEveniment.valueOf(resultSet.getString(4));
        String dataEveniment = resultSet.getString(5);

        return new Eveniment(idEveniment, idContract, idLocatie, tipEveniment, dataEveniment);
    }

    @Override
    public void insert() throws SQLException {
        String insertString = "INSERT INTO evenimente VALUES (?, ?, ?, ?, ?)";
        PreparedStatement insertEvent = Database.connection.prepareStatement(insertString);

        insertEvent.setString(1, this.idEveniment.toString());
        insertEvent.setString(2, this.idContract.toString());
        insertEvent.setString(3, this.idLocatie.toString());
        insertEvent.setString(4, this.tipEveniment.toString());
        insertEvent.setString(5, this.dataEveniment);

        insertEvent.executeUpdate();
        System.out.println("1 row(s) affected");
    }

    @Override
    public void update() throws SQLException {
        String updateString = "UPDATE evenimente SET id_contract = ?, tip_eveniment = ?, data_eveniment = ?, id_locatie = ? WHERE id_eveniment = ?";
        PreparedStatement updateEvent = Database.connection.prepareStatement(updateString);

        updateEvent.setString(1, this.idContract.toString());
        updateEvent.setString(2, this.tipEveniment.toString());
        updateEvent.setString(3, this.dataEveniment);
        updateEvent.setString(4, this.idLocatie.toString());
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

    public UUID getIdLocatie() {
        return idLocatie;
    }

    @Override
    public String toString() {
        return "Eveniment{" +
                "idEveniment=" + idEveniment +
                ", idContract=" + idContract +
                ", tipEveniment=" + tipEveniment +
                ", dataEveniment=" + dataEveniment +
                ", idLocatie='" + idLocatie + '\'' +
                '}';
    }
}
