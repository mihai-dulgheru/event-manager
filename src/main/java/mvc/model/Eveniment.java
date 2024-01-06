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
                    "    tematica        VARCHAR(255)," +
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
    private String tematica;

    public Eveniment(UUID idContract, UUID idLocatie, TipEveniment tipEveniment, String dataEveniment, Integer nrParticipanti, String tematica) {
        this.id = UUID.randomUUID();
        this.idContract = idContract;
        this.idLocatie = idLocatie;
        this.tipEveniment = tipEveniment;
        if (DateUtil.isValidDate(dataEveniment)) {
            this.dataEveniment = dataEveniment;
        } else {
            throw new IllegalArgumentException("Data evenimentului nu este validă!");
        }
        if (isValid(nrParticipanti)) {
            this.nrParticipanti = nrParticipanti;
        } else {
            throw new IllegalArgumentException("Numărul de participanți nu este valid!");
        }
        this.tematica = tematica;
    }

    private Eveniment(UUID id, UUID idContract, UUID idLocatie, TipEveniment tipEveniment, String dataEveniment, Integer nrParticipanti, String tematica) {
        this.id = id;
        this.idContract = idContract;
        this.idLocatie = idLocatie;
        this.tipEveniment = tipEveniment;
        this.dataEveniment = dataEveniment;
        if (isValid(nrParticipanti)) {
            this.nrParticipanti = nrParticipanti;
        } else {
            throw new IllegalArgumentException("Numărul de participanți nu este valid!");
        }
        this.tematica = tematica;
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

    public static AbstractModel readEvenimenteByIdContract(UUID id_contract) throws SQLException {
        String selectString = "SELECT * FROM evenimente where id_contract = ?";
        PreparedStatement selectEveniment = Database.connection.prepareStatement(selectString);

        selectEveniment.setString(1, id_contract.toString());

        ResultSet rs = selectEveniment.executeQuery();
        return load(rs);
    }

    protected static AbstractModel load(ResultSet resultSet) throws SQLException {
        UUID idEveniment = UUID.fromString(resultSet.getString(1));
        UUID idContract = UUID.fromString(resultSet.getString(2));
        UUID idLocatie = UUID.fromString(resultSet.getString(3));
        TipEveniment tipEveniment = TipEveniment.valueOf(resultSet.getString(4));
        String dataEveniment = resultSet.getString(5);
        Integer nrParticipanti = resultSet.getInt(6);
        String tematica = resultSet.getString(7);

        return new Eveniment(idEveniment, idContract, idLocatie, tipEveniment, dataEveniment, nrParticipanti, tematica);
    }

    public static void updateOrInsert(Eveniment eveniment) {
        try {
            String selectString = "SELECT * FROM evenimente WHERE id_eveniment = ?";
            PreparedStatement selectEvent = Database.connection.prepareStatement(selectString);

            selectEvent.setString(1, eveniment.id.toString());

            ResultSet rs = selectEvent.executeQuery();
            if (rs.next()) {
                eveniment.update();
            } else {
                eveniment.insert();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
        String insertString = "INSERT INTO evenimente VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement insertEvent = Database.connection.prepareStatement(insertString);

        insertEvent.setString(1, this.id.toString());
        insertEvent.setString(2, this.idContract.toString());
        insertEvent.setString(3, this.idLocatie.toString());
        insertEvent.setString(4, this.tipEveniment.toString());
        insertEvent.setString(5, this.dataEveniment);
        insertEvent.setInt(6, this.nrParticipanti);
        insertEvent.setString(7, this.tematica);

        insertEvent.executeUpdate();
    }

    @Override
    public void update() throws SQLException {
        String updateString = "UPDATE evenimente SET id_contract = ?, id_locatie = ?, tip_eveniment = ?, data_eveniment = ?, nr_participanti = ?, tematica = ? WHERE id_eveniment = ?";
        PreparedStatement updateEvent = Database.connection.prepareStatement(updateString);

        updateEvent.setString(1, this.idContract.toString());
        updateEvent.setString(2, this.idLocatie.toString());
        updateEvent.setString(3, this.tipEveniment.toString());
        updateEvent.setString(4, this.dataEveniment);
        updateEvent.setInt(5, this.nrParticipanti);
        updateEvent.setString(6, this.tematica);
        updateEvent.setString(7, this.id.toString());

        updateEvent.executeUpdate();
    }

    @Override
    public void delete() throws SQLException {
        String deleteString = "DELETE FROM evenimente WHERE id_eveniment = ?";
        PreparedStatement deleteEvent = Database.connection.prepareStatement(deleteString);

        deleteEvent.setString(1, this.id.toString());

        deleteEvent.executeUpdate();
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
            throw new IllegalArgumentException("Data evenimentului nu este validă!");
        }
    }

    public Integer getNrParticipanti() {
        return nrParticipanti;
    }

    public void setNrParticipanti(Integer nrParticipanti) {
        if (isValid(nrParticipanti)) {
            this.nrParticipanti = nrParticipanti;
        } else {
            throw new IllegalArgumentException("Numărul de participanți nu este valid!");
        }
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
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
                ", tematica='" + tematica + '\'' +
                '}';
    }

    protected String getNumeLocatie() {
        try {
            Locatie locatie = (Locatie) Locatie.readOne(this.idLocatie);
            return locatie.getDenumire();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected String getDataLimitaConfirmare() {
        return DateUtil.subtractDays(this.dataEveniment, 30);
    }

    protected String getNumeClient() {
        try {
            Contract contract = (Contract) Contract.readOne(this.idContract);
            Client client = (Client) Client.readOne(contract.getIdClient());
            return client.getNumeClient() + " " + client.getPrenumeClient();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
