package mvc.model;

import abstractClasses.AbstractCRUDOperations;
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
public class Pachet extends AbstractModel {
    static {
        try {
            Database.statement.executeUpdate("CREATE TABLE IF NOT EXISTS pachete" +
                    "(" +
                    "    id_pachet      VARCHAR(36) PRIMARY KEY," +
                    "    id_eveniment   VARCHAR(36)  NOT NULL," +
                    "    nume_pachet    VARCHAR(255) NOT NULL," +
                    "    detalii_pachet VARCHAR(255) NOT NULL," +
                    "    FOREIGN KEY (id_eveniment) REFERENCES evenimente (id_eveniment)" +
                    ")");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private final UUID id;
    private UUID idEveniment;
    private String numePachet;
    private String detaliiPachet;

    public Pachet(UUID idEveniment, String numePachet, String detaliiPachet) {
        this.id = UUID.randomUUID();
        this.idEveniment = idEveniment;
        this.numePachet = numePachet;
        this.detaliiPachet = detaliiPachet;
    }

    private Pachet(UUID id, UUID idEveniment, String numePachet, String detaliiPachet) {
        this.id = id;
        this.idEveniment = idEveniment;
        this.numePachet = numePachet;
        this.detaliiPachet = detaliiPachet;
    }

    public static AbstractCRUDOperations readOne(UUID id) throws SQLException {
        String selectString = "SELECT * FROM pachete WHERE id_pachet = ?";
        PreparedStatement selectPackage = Database.connection.prepareStatement(selectString);

        selectPackage.setString(1, id.toString());

        ResultSet rs = selectPackage.executeQuery();
        return load(rs);
    }

    public static List<AbstractCRUDOperations> readMany() throws SQLException {
        ResultSet rs = Database.statement.executeQuery("SELECT * FROM pachete");

        List<AbstractCRUDOperations> packages = new ArrayList<>();
        while (rs.next()) {
            packages.add(load(rs));
        }

        return packages;
    }

    protected static AbstractCRUDOperations load(ResultSet resultSet) throws SQLException {
        UUID id = UUID.fromString(resultSet.getString(1));
        UUID idEveniment = UUID.fromString(resultSet.getString(2));
        String numePachet = resultSet.getString(3);
        String detaliiPachet = resultSet.getString(4);

        return new Pachet(id, idEveniment, numePachet, detaliiPachet);
    }

    @Override
    public void insert() throws SQLException {
        String insertString = "INSERT INTO pachete VALUES (?, ?, ?, ?)";
        PreparedStatement insertPackage = Database.connection.prepareStatement(insertString);

        insertPackage.setString(1, this.id.toString());
        insertPackage.setString(2, this.idEveniment.toString());
        insertPackage.setString(3, this.numePachet);
        insertPackage.setString(4, this.detaliiPachet);

        insertPackage.executeUpdate();
        System.out.println("1 row affected");
    }

    @Override
    public void update() throws SQLException {
        String updateString = "UPDATE pachete SET id_eveniment = ?, nume_pachet = ?, detalii_pachet = ? WHERE id_pachet = ?";
        PreparedStatement updatePackage = Database.connection.prepareStatement(updateString);

        updatePackage.setString(1, this.idEveniment.toString());
        updatePackage.setString(2, this.numePachet);
        updatePackage.setString(3, this.detaliiPachet);
        updatePackage.setString(4, this.id.toString());

        updatePackage.executeUpdate();
        System.out.println("1 row affected");
    }

    @Override
    public void delete() throws SQLException {
        String deleteString = "DELETE FROM pachete WHERE id_pachet = ?";
        PreparedStatement deletePackage = Database.connection.prepareStatement(deleteString);

        deletePackage.setString(1, this.id.toString());

        deletePackage.executeUpdate();
        System.out.println("1 row affected");
    }

    public UUID getId() {
        return id;
    }

    public UUID getIdEveniment() {
        return idEveniment;
    }

    public void setIdEveniment(UUID idEveniment) {
        this.idEveniment = idEveniment;
    }

    public String getNumePachet() {
        return numePachet;
    }

    public void setNumePachet(String numePachet) {
        this.numePachet = numePachet;
    }

    public String getDetaliiPachet() {
        return detaliiPachet;
    }

    public void setDetaliiPachet(String detaliiPachet) {
        this.detaliiPachet = detaliiPachet;
    }

    @Override
    public String toString() {
        return "Pachet{" +
                "id=" + id +
                ", idEveniment=" + idEveniment +
                ", numePachet='" + numePachet + '\'' +
                ", detaliiPachet='" + detaliiPachet + '\'' +
                '}';
    }
}
