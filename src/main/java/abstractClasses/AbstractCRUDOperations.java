package abstractClasses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public abstract class AbstractCRUDOperations {
    public static AbstractCRUDOperations readOne(UUID id) throws SQLException {
        return null;
    }

    public static List<AbstractCRUDOperations> readMany() throws SQLException {
        return null;
    }

    protected static AbstractCRUDOperations load(ResultSet resultSet) throws SQLException {
        return null;
    }

    public abstract void insert() throws SQLException;

    public abstract void update() throws SQLException;

    public abstract void delete() throws SQLException;
}
