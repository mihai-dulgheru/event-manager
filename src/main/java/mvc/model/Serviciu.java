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
public class Serviciu extends AbstractModel {
    static {
        try {
            Database.statement.executeUpdate("CREATE TABLE IF NOT EXISTS servicii" +
                    "(" +
                    "    id_serviciu   VARCHAR(36) PRIMARY KEY," +
                    "    nume_serviciu VARCHAR(255) NOT NULL," +
                    "    cost_serviciu REAL(255) NOT NULL," +
                    "    durata        REAL(255)," +
                    "    observatii    VARCHAR(255) NOT NULL," +
                    "    tip_eveniment VARCHAR(255) NOT NULL" +
                    ")");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private final UUID id;
    private String numeServiciu;
    private Float costServiciu;
    private Float durata;
    private String observatii;
    private String tipEveniment;

    public Serviciu(String numeServiciu, Float costServiciu, Float durata, String observatii, String tipEveniment) {
        this.id = UUID.randomUUID();
        this.numeServiciu = numeServiciu;
        this.costServiciu = costServiciu;
        this.durata = durata;
        this.observatii = observatii;
        this.tipEveniment = tipEveniment;
    }

    private Serviciu(UUID id, String numeServiciu, Float costServiciu, Float durata, String observatii, String tipEveniment) {
        this.id = id;
        this.numeServiciu = numeServiciu;
        this.costServiciu = costServiciu;
        this.durata = durata;
        this.observatii = observatii;
        this.tipEveniment = tipEveniment;
    }

    public static AbstractModel readOne(UUID id) throws SQLException {
        String selectString = "SELECT * FROM servicii WHERE id_serviciu = ?";
        PreparedStatement selectService = Database.connection.prepareStatement(selectString);

        selectService.setString(1, id.toString());

        ResultSet rs = selectService.executeQuery();
        return load(rs);
    }

    public static List<AbstractModel> readServiciiDefault() throws SQLException {
        String selectString = "SELECT * FROM servicii WHERE tip_eveniment = ?";
        PreparedStatement selectService = Database.connection.prepareStatement(selectString);

        selectService.setString(1, "DEFAULT");
        ResultSet rs = selectService.executeQuery();
        List<AbstractModel> services = new ArrayList<>();
        while (rs.next()) {
            services.add(load(rs));
        }

        return services;
    }

    public static List<AbstractModel> readMany() throws SQLException {
        ResultSet rs = Database.statement.executeQuery("SELECT * FROM servicii");

        List<AbstractModel> services = new ArrayList<>();
        while (rs.next()) {
            services.add(load(rs));
        }

        return services;
    }

    protected static AbstractModel load(ResultSet resultSet) throws SQLException {
        UUID id = UUID.fromString(resultSet.getString(1));
        String numeServiciu = resultSet.getString(2);
        Float costServiciu = resultSet.getFloat(3);
        Float durata = resultSet.getFloat(4);
        String observatii = resultSet.getString(5);
        String tipEveniment = resultSet.getString(6);


        return new Serviciu(id, numeServiciu, costServiciu, durata, observatii, tipEveniment);
    }

    @Override
    public void insert() throws SQLException {
        String insertString = "INSERT INTO servicii VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement insertService = Database.connection.prepareStatement(insertString);

        insertService.setString(1, this.id.toString());
        insertService.setString(2, this.numeServiciu);
        insertService.setFloat(3, this.costServiciu);
        if (this.durata != null) {
            insertService.setFloat(4, this.durata);
        } else {
            insertService.setObject(4, null);
        }
        insertService.setString(5, this.observatii);
        insertService.setString(6, this.tipEveniment);

        insertService.executeUpdate();
        System.out.println("1 row affected");
    }

    @Override
    public void update() throws SQLException {
        String updateString = "UPDATE servicii SET nume_serviciu = ?, cost_serviciu = ?, durata = ?, observatii = ?, tip_eveniment = ? WHERE id_serviciu = ?";
        PreparedStatement updateService = Database.connection.prepareStatement(updateString);

        updateService.setString(1, this.numeServiciu);
        updateService.setFloat(2, this.costServiciu);

        if (this.durata != null) {
            updateService.setFloat(3, this.durata);
        } else {
            updateService.setObject(3, null);
        }

        updateService.setString(4, this.observatii);
        updateService.setString(5, this.tipEveniment);
        updateService.setString(6, this.id.toString());

        updateService.executeUpdate();
        System.out.println("1 row affected");
    }


    @Override
    public void delete() throws SQLException {
        String deleteString = "DELETE FROM servicii WHERE id_serviciu = ?";
        PreparedStatement deleteService = Database.connection.prepareStatement(deleteString);

        deleteService.setString(1, this.id.toString());

        deleteService.executeUpdate();
        System.out.println("1 row affected");
    }

    public UUID getId() {
        return id;
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

    public String getTipEveniment() {
        return tipEveniment;
    }

    public void setTipEveniment(String tipEveniment) {
        this.tipEveniment = tipEveniment;
    }

    @Override
    public String toString() {
        return "Serviciu{" +
                "id=" + id +
                ", numeServiciu='" + numeServiciu + '\'' +
                ", costServiciu=" + costServiciu +
                ", durata=" + durata +
                ", observatii='" + observatii + '\'' +
                ", tipEveniment='" + tipEveniment + '\'' +
                '}';
    }

    public String print() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.numeServiciu);
        stringBuilder.append(": ");
        stringBuilder.append(this.costServiciu);
        stringBuilder.append(" RON | ");
        stringBuilder.append(this.durata);
        stringBuilder.append("h");
        if (this.observatii != null && !this.observatii.isEmpty() && !this.observatii.isBlank()) {
            stringBuilder.append(" (");
            stringBuilder.append(this.observatii);
            stringBuilder.append(")");
        }
        return stringBuilder.toString();
    }
}