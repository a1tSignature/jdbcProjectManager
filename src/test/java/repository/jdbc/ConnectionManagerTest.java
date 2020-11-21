package repository.jdbc;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repository.jdbc.employee.H2jdbcEmployeeEntityManager;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConnectionManagerTest {

    private static Connection connection;

    @BeforeAll
    static void init() throws SQLException {
       // connection = getNewConnection();
    }

    @AfterAll
    static void close() throws SQLException {
        //connection.close();
    }


    @Test
    public void shouldGetJdbcConnection() throws SQLException {
            assertTrue(connection.isValid(1));
            assertFalse(connection.isClosed());
    }

    @Test
    public void testCreation() throws SQLException {
        H2jdbcEmployeeEntityManager create = new H2jdbcEmployeeEntityManager();

        create.create();
    }
}
