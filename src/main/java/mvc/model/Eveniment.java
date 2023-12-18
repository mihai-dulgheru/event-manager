package mvc.model;

import abstractClasses.AbstractModel;
import database.Database;
import enums.TipEveniment;
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
public class Eveniment extends AbstractModel {
    static {
        try {
            Database.statement.executeUpdate("CREATE TABLE IF NOT EXISTS evenimente" +
                    "(" +
                    "    id_eveniment    VARCHAR(36) PRIMARY KEY," +
                    "    id_contract     VARCHAR(36)  NOT NULL," +
                    "    id_locatie      VARCHAR(36)  NOT NULL," +
                    "    tip_eveniment   VARCHAR(255) NOT NULL," +
                    "    data_eveniment  DATE NOT NULL," +
                    "    nr_participanti INTEGER DEFAULT 0," +
                    "    FOREIGN KEY (id_contract) REFERENCES contracte (id_contract)," +
                    "    FOREIGN KEY (id_locatie) REFERENCES locatii (id_locatie)" +
                    ")");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private final UUID id;
    private final UUID idContract;
    private final UUID idLocatie;
    private TipEveniment tipEveniment;
    private String dataEveniment;
    private Integer nrParticipanti;

    public Eveniment(UUID idContract, UUID idLocatie, TipEveniment tipEveniment, String dataEveniment, Integer nrParticipanti) {
        this.id = UUID.randomUUID();
        this.idContract = idContract;
        this.idLocatie = idLocatie;
        this.tipEveniment = tipEveniment;
        if (DateUtil.isValidDate(dataEveniment)) {
            this.dataEveniment = dataEveniment;
        } else {
            throw new IllegalArgumentException("Data evenimentului nu este valida!");
        }
        if (isValid(nrParticipanti)) {
            this.nrParticipanti = nrParticipanti;
        } else {
            throw new IllegalArgumentException("Numarul de participanti nu este valid!");
        }
    }

    private Eveniment(UUID id, UUID idContract, UUID idLocatie, TipEveniment tipEveniment,
                      String dataEveniment, Integer nrParticipanti) {
        this.id = id;
        this.idContract = idContract;
        this.idLocatie = idLocatie;
        this.tipEveniment = tipEveniment;
        this.dataEveniment = dataEveniment;
        if (isValid(nrParticipanti)) {
            this.nrParticipanti = nrParticipanti;
        } else {
            throw new IllegalArgumentException("Numarul de participanti nu este valid!");
        }
    }

    public static AbstractModel readOne(UUID id) throws SQLException {
        String selectString = "SELECT * FROM evenimente WHERE id_eveniment = ?";
        PreparedStatement selectPackage = Database.connection.prepareStatement(selectString);

        selectPackage.setString(1, id.toString());

        ResultSet rs = selectPackage.executeQuery();
        return load(rs);
    }

    public static List<AbstractModel> readMany() throws SQLException {
        ResultSet rs = Database.statement.executeQuery("SELECT * FROM evenimente");

        List<AbstractModel> packages = new ArrayList<>();
        while (rs.next()) {
            packages.add(load(rs));
        }

        return packages;
    }

    protected static AbstractModel load(ResultSet resultSet) throws SQLException {
        UUID idEveniment = UUID.fromString(resultSet.getString(1));
        UUID idContract = UUID.fromString(resultSet.getString(2));
        UUID idLocatie = UUID.fromString(resultSet.getString(3));
        TipEveniment tipEveniment = TipEveniment.valueOf(resultSet.getString(4));
        String dataEveniment = resultSet.getString(5);
        Integer nrParticipanti = resultSet.getInt(6);

        return new Eveniment(idEveniment, idContract, idLocatie, tipEveniment, dataEveniment, nrParticipanti);
    }

    private Boolean isValid(Integer nrParticipanti) {
        try {
            if (nrParticipanti < 0) {
                return false;
            }
            if (this.idLocatie.toString().isEmpty()) {
                return false;
            }
            Locatie locatie = (Locatie) Locatie.readOne(this.idLocatie);
            return nrParticipanti <= locatie.getCapacitate();
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public void insert() throws SQLException {
        String insertString = "INSERT INTO evenimente VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement insertEvent = Database.connection.prepareStatement(insertString);

        insertEvent.setString(1, this.id.toString());
        insertEvent.setString(2, this.idContract.toString());
        insertEvent.setString(3, this.idLocatie.toString());
        insertEvent.setString(4, this.tipEveniment.toString());
        insertEvent.setString(5, this.dataEveniment);
        insertEvent.setInt(6, this.nrParticipanti);

        insertEvent.executeUpdate();
        System.out.println("1 row affected");
    }

    @Override
    public void update() throws SQLException {
        String updateString = "UPDATE evenimente SET id_contract = ?, id_locatie = ?, tip_eveniment = ?, data_eveniment = ?, nr_participanti = ? WHERE id_eveniment = ?";
        PreparedStatement updateEvent = Database.connection.prepareStatement(updateString);

        updateEvent.setString(1, this.idContract.toString());
        updateEvent.setString(2, this.idLocatie.toString());
        updateEvent.setString(3, this.tipEveniment.toString());
        updateEvent.setString(4, this.dataEveniment);
        updateEvent.setString(5, this.id.toString());
        updateEvent.setInt(6, this.nrParticipanti);

        updateEvent.executeUpdate();
        System.out.println("1 row affected");
    }

    @Override
    public void delete() throws SQLException {
        String deleteString = "DELETE FROM evenimente WHERE id_eveniment = ?";
        PreparedStatement deleteEvent = Database.connection.prepareStatement(deleteString);

        deleteEvent.setString(1, this.id.toString());

        deleteEvent.executeUpdate();
        System.out.println("1 row affected");
    }

    public UUID getId() {
        return id;
    }

    public UUID getIdContract() {
        return idContract;
    }

    public UUID getIdLocatie() {
        return idLocatie;
    }

    public TipEveniment getTipEveniment() {
        return tipEveniment;
    }

    public void setTipEveniment(TipEveniment tipEveniment) {
        this.tipEveniment = tipEveniment;
    }

    public String getDataEveniment() {
        return dataEveniment;
    }

    public void setDataEveniment(String dataEveniment) {
        if (DateUtil.isValidDate(dataEveniment)) {
            this.dataEveniment = dataEveniment;
        } else {
            throw new IllegalArgumentException("Data evenimentului nu este valida!");
        }
    }

    public Integer getNrParticipanti() {
        return nrParticipanti;
    }

    public void setNrParticipanti(Integer nrParticipanti) {
        if (isValid(nrParticipanti)) {
            this.nrParticipanti = nrParticipanti;
        } else {
            throw new IllegalArgumentException("Numarul de participanti nu este valid!");
        }
    }

    @Override
    public String toString() {
        return "Eveniment{" +
                "id=" + id +
                ", idContract=" + idContract +
                ", idLocatie=" + idLocatie +
                ", tipEveniment=" + tipEveniment +
                ", dataEveniment='" + dataEveniment + '\'' +
                ", nrParticipanti=" + nrParticipanti +
                '}';
    }
}
