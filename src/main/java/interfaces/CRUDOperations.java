package interfaces;

import java.sql.SQLException;

public interface CRUDOperations {
    void insert() throws SQLException;

    void update() throws SQLException;

    void delete() throws SQLException;
}
