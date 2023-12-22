package mvc.model;

import abstractClasses.AbstractModel;
import database.Database;
import enums.MetodaDePlata;
import enums.Moneda;
import util.DateUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Active Record
 */
public class Contract extends AbstractModel {
    static {
        try {
            Database.statement.executeUpdate("CREATE TABLE IF NOT EXISTS contracte" +
                    "(" +
                    "    id_contract     VARCHAR(36) PRIMARY KEY," +
                    "    id_client       VARCHAR(36)  NOT NULL," +
                    "    data_incheiere  VARCHAR(255) NOT NULL," +
                    "    cost_total      DOUBLE NOT NULL," +
                    "    moneda          VARCHAR(255) NOT NULL," +
                    "    observatii      VARCHAR(255) NOT NULL," +
                    "    metoda_de_plata VARCHAR(255) NOT NULL," +
                    "    FOREIGN KEY (id_client) REFERENCES clienti (id_client)" +
                    ")");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private final UUID id;
    private final UUID idClient;
    private String dataIncheiere;
    private Double costTotal;
    private Moneda moneda;
    private String observatii;
    private MetodaDePlata metodaDePlata;

    public Contract(UUID idClient) {
        this.id = UUID.randomUUID();
        this.idClient = idClient;
        this.dataIncheiere = DateUtil.today();
    }

    public Contract(UUID idClient, Double costTotal, Moneda moneda, String observatii, MetodaDePlata metodaDePlata) {
        this(idClient);
        this.costTotal = costTotal;
        this.moneda = moneda;
        this.observatii = observatii;
        this.metodaDePlata = metodaDePlata;
    }

    private Contract(UUID id, UUID idClient, String dataIncheiere, Double costTotal, Moneda moneda, String observatii, MetodaDePlata metodaDePlata) {
        this.id = id;
        this.idClient = idClient;
        this.dataIncheiere = dataIncheiere;
        this.costTotal = costTotal;
        this.moneda = moneda;
        this.observatii = observatii;
        this.metodaDePlata = metodaDePlata;
    }

    public static AbstractModel readOne(UUID id) throws SQLException {
        String selectString = "SELECT * FROM contracte WHERE id_contract = ?";
        PreparedStatement selectContract = Database.connection.prepareStatement(selectString);

        selectContract.setString(1, id.toString());

        ResultSet rs = selectContract.executeQuery();
        return load(rs);
    }

    public static List<AbstractModel> readMany() throws SQLException {
        ResultSet rs = Database.statement.executeQuery("SELECT * FROM contracte");

        List<AbstractModel> contracts = new ArrayList<>();
        while (rs.next()) {
            contracts.add(load(rs));
        }

        return contracts;
    }

    protected static AbstractModel load(ResultSet resultSet) throws SQLException {
        UUID id = UUID.fromString(resultSet.getString(1));
        UUID idClient = UUID.fromString(resultSet.getString(2));
        String dataIncheiere = resultSet.getString(3);
        Double costTotal = resultSet.getDouble(4);
        Moneda moneda = Moneda.valueOf(resultSet.getString(5));
        String observatii = resultSet.getString(6);
        MetodaDePlata metodaDePlata = MetodaDePlata.valueOf(resultSet.getString(7));

        return new Contract(id, idClient, dataIncheiere, costTotal, moneda, observatii, metodaDePlata);
    }

    @Override
    public void insert() throws SQLException {
        String insertString = "INSERT INTO contracte VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement insertContract = Database.connection.prepareStatement(insertString);

        insertContract.setString(1, this.id.toString());
        insertContract.setString(2, this.idClient.toString());
        insertContract.setString(3, this.dataIncheiere);
        insertContract.setDouble(4, this.costTotal);
        insertContract.setString(5, this.moneda.toString());
        insertContract.setString(6, this.observatii);
        insertContract.setString(7, this.metodaDePlata.toString());

        insertContract.executeUpdate();
        System.out.println("1 row affected");
    }

    @Override
    public void update() throws SQLException {
        String updateString = "UPDATE contracte SET id_client = ?, data_incheiere = ?, cost_total = ?, moneda = ?, observatii = ?, metoda_de_plata = ? WHERE id_contract = ?";
        PreparedStatement updateContract = Database.connection.prepareStatement(updateString);

        updateContract.setString(1, this.idClient.toString());
        updateContract.setString(2, this.dataIncheiere);
        updateContract.setDouble(3, this.costTotal);
        updateContract.setString(4, this.moneda.toString());
        updateContract.setString(5, this.observatii);
        updateContract.setString(6, this.metodaDePlata.toString());
        updateContract.setString(7, this.id.toString());

        updateContract.executeUpdate();
        System.out.println("1 row affected");
    }

    @Override
    public void delete() throws SQLException {
        String deleteString = "DELETE FROM contracte WHERE id_contract = ?";
        PreparedStatement deleteContract = Database.connection.prepareStatement(deleteString);

        deleteContract.setString(1, this.id.toString());

        deleteContract.executeUpdate();
        System.out.println("1 row affected");
    }

    public UUID getId() {
        return id;
    }

    public UUID getIdClient() {
        return idClient;
    }

    public String getDataIncheiere() {
        return dataIncheiere;
    }

    public void setDataIncheiere(String dataIncheiere) {
        this.dataIncheiere = dataIncheiere;
    }

    public Double getCostTotal() {
        return costTotal;
    }

    public void setCostTotal(Double costTotal) {
        this.costTotal = costTotal;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public String getObservatii() {
        return observatii;
    }

    public void setObservatii(String observatii) {
        this.observatii = observatii;
    }

    public MetodaDePlata getMetodaDePlata() {
        return metodaDePlata;
    }

    public void setMetodaDePlata(MetodaDePlata metodaDePlata) {
        this.metodaDePlata = metodaDePlata;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", idClient=" + idClient +
                ", dataIncheiere='" + dataIncheiere + '\'' +
                ", costTotal='" + costTotal + '\'' +
                ", moneda=" + moneda +
                ", observatii='" + observatii + '\'' +
                ", metodaDePlata=" + metodaDePlata +
                '}';
    }
}
