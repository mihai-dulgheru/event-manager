package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Active Record
 */
public class Serviciu extends CRUDOperations {
    static {
        try {
            Database.statement.executeUpdate("CREATE TABLE IF NOT EXISTS servicii" +
                    "(" +
                    "    id_serviciu      VARCHAR(36) PRIMARY KEY," +
                    "    id_pachet   VARCHAR(36)  NOT NULL," +
                    "    nume_serviciu    VARCHAR(255) NOT NULL," +
                    "    cost_serviciu REAL(255) NOT NULL," +
                    "    durata REAL(255)," +
                    "    observatii VARCHAR(255) NOT NULL," +
                    "    FOREIGN KEY (id_pachet) REFERENCES pachete (id_pachet)" +
                    ")");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private final UUID id;
    private final UUID idPachet;
    private String numeServiciu;
    private Float costServiciu;
    private Float durata;
    private String observatii;

    public Serviciu(UUID idPachet, String numeServiciu, Float costServiciu, Float durata, String observatii) {
        this.id = UUID.randomUUID();
        this.idPachet = idPachet;
        this.numeServiciu = numeServiciu;
        this.costServiciu = costServiciu;
        this.durata = durata;
        this.observatii = observatii;
    }

    public Serviciu(UUID idPachet, String numeServiciu, Float costServiciu, String observatii) {
        this.id = UUID.randomUUID();
        this.idPachet = idPachet;
        this.numeServiciu = numeServiciu;
        this.costServiciu = costServiciu;
        this.durata = null;
        this.observatii = observatii;
    }

    private Serviciu(UUID id, UUID idPachet, String numeServiciu, Float costServiciu, Float durata, String observatii) {
        this.id = id;
        this.idPachet = idPachet;
        this.numeServiciu = numeServiciu;
        this.costServiciu = costServiciu;
        this.durata = durata;
        this.observatii = observatii;
    }

    public static CRUDOperations readOne(UUID id) throws SQLException {
        String selectString = "SELECT * FROM servicii WHERE id_serviciu = ?";
        PreparedStatement selectService = Database.connection.prepareStatement(selectString);

        selectService.setString(1, id.toString());

        ResultSet rs = selectService.executeQuery();
        return load(rs);
    }

    public static List<CRUDOperations> readMany() throws SQLException {
        ResultSet rs = Database.statement.executeQuery("SELECT * FROM servicii");

        List<CRUDOperations> services = new ArrayList<>();
        while (rs.next()) {
            services.add(load(rs));
        }

        return services;
    }

    protected static CRUDOperations load(ResultSet resultSet) throws SQLException {
        UUID id = UUID.fromString(resultSet.getString(1));
        UUID idPachet = UUID.fromString(resultSet.getString(2));
        String numeServiciu = resultSet.getString(3);
        Float costServiciu = resultSet.getFloat(4);
        Float durata = resultSet.getFloat(5);
        String observatii = resultSet.getString(6);


        return new Serviciu(id, idPachet, numeServiciu, costServiciu, durata, observatii);
    }

    @Override
    public void insert() throws SQLException {
        String insertString = "INSERT INTO servicii VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement insertService = Database.connection.prepareStatement(insertString);

        insertService.setString(1, this.id.toString());
        insertService.setString(2, this.idPachet.toString());
        insertService.setString(3, this.numeServiciu);
        insertService.setFloat(4, this.costServiciu);
        if (this.durata != null) {
            insertService.setFloat(5, this.durata);
        } else {
            insertService.setObject(5, null);
        }
        insertService.setString(6, this.observatii);

        insertService.executeUpdate();
        System.out.println("1 row(s) affected");
    }

    @Override
    public void update() throws SQLException {
        String updateString = "UPDATE servicii SET id_pachet = ?, nume_serviciu = ?, cost_serviciu = ?, durata = ?, observatii = ? WHERE id_serviciu = ?";
        PreparedStatement updateService = Database.connection.prepareStatement(updateString);

        updateService.setString(1, this.idPachet.toString());
        updateService.setString(2, this.numeServiciu);
        updateService.setFloat(3, this.costServiciu);

        if (this.durata != null) {
            updateService.setFloat(4, this.durata);
        } else {
            updateService.setObject(4, null);
        }

        updateService.setString(5, this.observatii);
        updateService.setString(6, this.id.toString());

        updateService.executeUpdate();
        System.out.println("1 row(s) affected");
    }


    @Override
    public void delete() throws SQLException {
        String deleteString = "DELETE FROM servicii WHERE id_serviciu = ?";
        PreparedStatement deleteService = Database.connection.prepareStatement(deleteString);

        deleteService.setString(1, this.id.toString());

        deleteService.executeUpdate();
        System.out.println("1 row(s) affected");
    }

    public Float simplifyNullable(Float nullableFloat) {
        return nullableFloat;
    }

    public UUID getId() {
        return id;
    }

    public UUID getIdPachet() {
        return idPachet;
    }

    public String getNumeServiciu() {
        return numeServiciu;
    }

    public void setNumeServiciu(String numeServiciu) {
        this.numeServiciu = numeServiciu;
    }

    public Float getCostServiciu() {
        return costServiciu;
    }

    public void setCostServiciu(Float costServiciu) {
        this.costServiciu = costServiciu;
    }

    public Float getDurata() {
        return durata;
    }

    public void setDurata(Float durata) {
        this.durata = durata;
    }

    public String getObservatii() {
        return observatii;
    }

    public void setObservatii(String observatii) {
        this.observatii = observatii;
    }

    @Override
    public String toString() {
        return "Serviciu{" + "id=" + id + ", idPachet=" + idPachet + ", numerServiciu='" + numeServiciu + '\'' + ", costServiciu='" + costServiciu + '\'' + ", durata='" + durata + '\'' + ", observatii='" + observatii + '\'' + '}';
    }
}