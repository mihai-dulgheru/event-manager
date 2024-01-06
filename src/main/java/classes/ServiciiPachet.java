package classes;

import database.Database;

import java.sql.SQLException;
import java.util.UUID;

/**
 * Active Record
 */
public class ServiciiPachet {
    static {
        try {
            Database.statement.executeUpdate("CREATE TABLE IF NOT EXISTS servicii_pachet" +
                    "(" +
                    "    id          VARCHAR(36) PRIMARY KEY," +
                    "    id_pachet   VARCHAR(36) NOT NULL," +
                    "    id_serviciu VARCHAR(36) NOT NULL" +
                    ")");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private final UUID id = UUID.randomUUID();
    private UUID idPachet;
    private UUID idServiciu;

    public ServiciiPachet(UUID idPachet, UUID idServiciu) {
        this.idPachet = idPachet;
        this.idServiciu = idServiciu;
    }

    public static void delete(UUID idPachet) {
        try {
            String deleteString = "DELETE FROM servicii_pachet WHERE id_pachet = ?";
            var deletePackage = Database.connection.prepareStatement(deleteString);
            deletePackage.setString(1, idPachet.toString());
            deletePackage.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void save() {
        try {
            String insertString = "INSERT INTO servicii_pachet (id, id_pachet, id_serviciu) VALUES (?, ?, ?)";
            var insertPackage = Database.connection.prepareStatement(insertString);
            insertPackage.setString(1, id.toString());
            insertPackage.setString(2, idPachet.toString());
            insertPackage.setString(3, idServiciu.toString());
            insertPackage.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public UUID getIdPachet() {
        return idPachet;
    }

    public void setIdPachet(UUID idPachet) {
        this.idPachet = idPachet;
    }

    public UUID getIdServiciu() {
        return idServiciu;
    }

    public void setIdServiciu(UUID idServiciu) {
        this.idServiciu = idServiciu;
    }

    @Override
    public String toString() {
        return "ServiciiPachet{" +
                "idPachet=" + idPachet +
                ", idServiciu=" + idServiciu +
                '}';
    }
}
