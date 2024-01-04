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
    private List<Serviciu> servicii;
    private String numePachet;
    private String detaliiPachet;
    private UUID idEveniment;

    private Pachet(UUID id, UUID idEveniment, String numePachet, String detaliiPachet) {
        this.id = id;
        this.idEveniment = idEveniment;
        this.numePachet = numePachet;
        this.detaliiPachet = detaliiPachet;
 //       TODO: citește serviciile din baza de date

        try {
            List<AbstractModel> serviciiDefault = Serviciu.readServiciiDefault();
            for(AbstractModel serviciu:serviciiDefault){
                this.servicii.add((Serviciu) serviciu);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Pachet(PachetBuilder pachetBuilder) {
        super();
        this.id = UUID.randomUUID();
        this.idEveniment = pachetBuilder.idEveniment;
        this.numePachet = pachetBuilder.numePachet;
        this.detaliiPachet = pachetBuilder.detaliiPachet;
        this.servicii = new ArrayList<>();
        this.servicii.addAll(pachetBuilder.serviciiDefault);
        this.servicii.addAll(pachetBuilder.serviciiSuplimentare);
    }

    public static AbstractModel readOne(UUID id) throws SQLException {
        String selectString = "SELECT * FROM pachete WHERE id_pachet = ?";
        PreparedStatement selectPackage = Database.connection.prepareStatement(selectString);

        selectPackage.setString(1, id.toString());

        ResultSet rs = selectPackage.executeQuery();
        return load(rs);
    }

    public static List<AbstractModel> readMany() throws SQLException {
        ResultSet rs = Database.statement.executeQuery("SELECT * FROM pachete");

        List<AbstractModel> packages = new ArrayList<>();
        while (rs.next()) {
            packages.add(load(rs));
        }

        return packages;
    }

    protected static AbstractModel load(ResultSet resultSet) throws SQLException {
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

    public List<Serviciu> getServicii() {
        return servicii;
    }

    public void setServicii(List<Serviciu> servicii) {
        this.servicii = servicii;
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

    public UUID getIdEveniment() {
        return idEveniment;
    }

    public void setIdEveniment(UUID idEveniment) {
        this.idEveniment = idEveniment;
    }

    @Override
    public String toString() {
        return "Pachet{" +
                "id=" + id +
                ", servicii=" + servicii +
                ", numePachet='" + numePachet + '\'' +
                ", detaliiPachet='" + detaliiPachet + '\'' +
                ", idEveniment=" + idEveniment +
                '}';
    }

    public static class PachetBuilder {
        private final UUID idEveniment;
        private final List<Serviciu> serviciiDefault = new ArrayList<>();
        private final List<Serviciu> serviciiSuplimentare = new ArrayList<>();
        private String numePachet;
        private String detaliiPachet;

        public PachetBuilder(UUID idEveniment) {
            this.idEveniment = idEveniment;
            try {
                List<AbstractModel> servicii = Serviciu.readMany();
                for (AbstractModel abstractModel : servicii) {
                    Serviciu serviciu = (Serviciu) abstractModel;
                    if (serviciu.getTipEveniment().equalsIgnoreCase("DEFAULT")) {
                        this.serviciiDefault.add(serviciu);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public PachetBuilder addServiciu(Serviciu serviciu) {
            this.serviciiSuplimentare.add(serviciu);
            return this;
        }

        public Pachet build() {
            try {
                Eveniment eveniment = (Eveniment) Eveniment.readOne(this.idEveniment);
                boolean isBasic = this.serviciiSuplimentare.isEmpty();
                this.numePachet = String.format("pachet_%s_%s", eveniment.getTipEveniment().toString().toLowerCase(), isBasic ? "basic" : "custom");
                if (isBasic) {
                    this.detaliiPachet = "";
                } else {
                    StringBuilder stringBuilder = new StringBuilder("Pachetul conține serviciile ");
                    float total = 0.0f;
                    for (Serviciu serviciu : this.serviciiDefault) {
                        stringBuilder.append(serviciu.getNumeServiciu()).append(", ");
                        total += serviciu.getCostServiciu();
                    }
                    for (Serviciu serviciu : this.serviciiSuplimentare) {
                        stringBuilder.append(serviciu.getNumeServiciu()).append(", ");
                        total += serviciu.getCostServiciu();
                    }
                    stringBuilder.append("în valoare de ").append(total).append(" RON.");
                    this.detaliiPachet = stringBuilder.toString();
                }
                return new Pachet(this);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
