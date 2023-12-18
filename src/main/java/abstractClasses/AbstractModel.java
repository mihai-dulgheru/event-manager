package abstractClasses;

import interfaces.CRUDOperations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public abstract class AbstractModel implements CRUDOperations {
    public static AbstractModel readOne(UUID id) throws SQLException {
        return null;
    }

    public static List<AbstractModel> readMany() throws SQLException {
        return null;
    }

    protected static AbstractModel load(ResultSet resultSet) throws SQLException {
        return null;
    }
}
