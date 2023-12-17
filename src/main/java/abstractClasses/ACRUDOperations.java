package abstractClasses;

import jdk.jshell.spi.ExecutionControl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public abstract class ACRUDOperations {
    public static ACRUDOperations readOne(UUID id) throws ExecutionControl.NotImplementedException, SQLException {
        throw new ExecutionControl.NotImplementedException("Not implemented");
    }

    public static List<ACRUDOperations> readMany() throws ExecutionControl.NotImplementedException, SQLException {
        throw new ExecutionControl.NotImplementedException("Not implemented");
    }

    protected static ACRUDOperations load(ResultSet resultSet) throws ExecutionControl.NotImplementedException, SQLException {
        throw new ExecutionControl.NotImplementedException("Not implemented");
    }

    public abstract void insert() throws SQLException;

    public abstract void update() throws SQLException;

    public abstract void delete() throws SQLException;
}
