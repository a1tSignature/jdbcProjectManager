package repository.jdbc.employee;

import employee.EmployeeImpl;
import repository.jdbc.JdbcDataSourceInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class H2jdbcEmployeeEntityManager extends JdbcDataSourceInfo {

    public void create() throws SQLException {
        Statement statement = null;

        try (Connection connection = DriverManager.getConnection(url + dbName, user, password)) {
            Class.forName(driver);
            statement = connection.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS employee " +
                    "(id INTEGER NOT NULL AUTO_INCREMENT, " +
                    " name VARCHAR(255), " +
                    " surname VARCHAR(255), " +
                    " patronymic VARCHAR(255), " +
                    " PRIMARY KEY (id))";
            statement.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS position " +
                    "(id INTEGER NOT NULL AUTO_INCREMENT, " +
                    "position_name VARCHAR(60), " +
                    "PRIMARY KEY (id))";
            statement.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS rank " +
                    "(id INTEGER  NOT NULL AUTO_INCREMENT, " +
                    "rank_name VARCHAR(60), " +
                    "PRIMARY KEY (id))";
            statement.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS specification " +
                    "(id INTEGER NOT NULL AUTO_INCREMENT, " +
                    "specification_name VARCHAR(60), " +
                    "PRIMARY KEY (id))";
            statement.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS employee_position " +
                    "(employee_id INTEGER NOT NULL, " +
                    "position_id INTEGER NOT NULL, " +
                    "CONSTRAINT employee_id_fk " +
                    "FOREIGN KEY (employee_id) REFERENCES employee (id) ON DELETE CASCADE, " +
                    "CONSTRAINT position_id_fk " +
                    "FOREIGN KEY (position_id) REFERENCES position (id) ON DELETE CASCADE)";
            statement.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS employee_rank " +
                    "(employee_id INTEGER NOT NULL, " +
                    "rank_id INTEGER NOT NULL, " +
                    "CONSTRAINT employee_id_fk1 " +
                    "FOREIGN KEY (employee_id) REFERENCES employee (id) ON DELETE CASCADE , " +
                    "CONSTRAINT rank_id_fk " +
                    "FOREIGN KEY (rank_id) REFERENCES rank (id) ON DELETE CASCADE)";
            statement.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS employee_specification " +
                    "(employee_id INTEGER NOT NULL, " +
                    "specification_id INTEGER NOT NULL, " +
                    "CONSTRAINT employee_id_fk2 " +
                    "FOREIGN KEY (employee_id) REFERENCES employee (id) ON DELETE CASCADE , " +
                    "CONSTRAINT specification_id_fk " +
                    "FOREIGN KEY (specification_id) REFERENCES specification (id) ON DELETE CASCADE)";
            statement.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS project " +
                    "(id INTEGER NOT NULL AUTO_INCREMENT, " +
                    "title VARCHAR(100) NOT NULL UNIQUE, " +
                    "PRIMARY KEY(id))";
            statement.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS type " +
                    "(id INTEGER NOT NULL AUTO_INCREMENT, " +
                    "type_name VARCHAR(60) NOT NULL, " +
                    "PRIMARY KEY (id))";
            statement.executeUpdate(sql);

            sql ="CREATE TABLE IF NOT EXISTS project_type " +
                    "(project_id INTEGER NOT NULL, " +
                    "type_id INTEGER NOT NULL, " +
                    "CONSTRAINT project_id_fk " +
                    "FOREIGN KEY (project_id) REFERENCES project (id) ON DELETE CASCADE, " +
                    "CONSTRAINT type_id_fk " +
                    "FOREIGN KEY (type_id) REFERENCES type (id) ON DELETE CASCADE)";
            statement.executeUpdate(sql);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            assert statement != null;
            statement.close();
        }
    }

    public List<List<String>> getEmployees() {
        Statement statement = null;
        List<List<String>> ans = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url + dbName, user, password)) {
            Class.forName(driver);
            statement = connection.createStatement();

            String sql = "SELECT * FROM rank";
            ResultSet set = statement.executeQuery(sql);

            while (set.next()) {
                int id = set.getInt("id");
                String name = set.getString("rank_name");

                System.out.println("id: " + id);
                System.out.println("name: " + name);
            }


            sql = "SELECT DISTINCT name, surname, patronymic, pos.position_name, ra.rank_name, sp.specification_name FROM employee e " +
                    "INNER JOIN employee_position p ON e.id = p.employee_id " +
                    "INNER JOIN position pos ON p.position_id = pos.id " +
                    "INNER JOIN employee_rank r ON e.id = r.employee_id " +
                    "INNER JOIN rank ra ON r.rank_id = ra.id " +
                    "INNER JOIN employee_specification s ON e.id = s.employee_id " +
                    "INNER JOIN specification sp ON s.specification_id = sp.id";
            set = statement.executeQuery(sql);

            while (set.next()) {
                List<String> ls = new ArrayList<>();
                String name = set.getString("name");
                ls.add(name);
                String surname = set.getString("surname");
                ls.add(surname);
                String patronymic = set.getString("patronymic");
                ls.add(patronymic);
                String position = set.getString("position_name");
                ls.add(position);
                String specification = set.getString("specification_name");
                ls.add(specification);
                String rank = set.getString("rank_name");
                ls.add(rank);

                ans.add(ls);

                System.out.println("name: " + name);
                System.out.println("surname: " + surname);
                System.out.println("patronymic: " + patronymic);
                System.out.println("pos: " + position);
                System.out.println("rank: " + rank);
                System.out.println("spec: " + specification);
                System.out.println();

            }

        } catch (SQLException |
                ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                assert statement != null;
                statement.close();
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }

        return ans;

    }

    public void insertInitial() {
        Statement statement = null;

        try (Connection connection = DriverManager.getConnection(url + dbName, user, password)) {
            Class.forName(driver);
            statement = connection.createStatement();

            String sql = "INSERT INTO rank(rank_name) VALUES ('JUNIOR')";
            statement.executeUpdate(sql);
            sql = "INSERT INTO rank(rank_name) VALUES ('MIDDLE')";
            statement.executeUpdate(sql);
            sql = "INSERT INTO rank(rank_name) VALUES ('SENIOR')";
            statement.executeUpdate(sql);
            sql = "INSERT INTO rank(rank_name) VALUES ('TEAMLEAD')";
            statement.executeUpdate(sql);

            sql = "INSERT INTO position(position_name) VALUES ('BACKEND')";
            statement.executeUpdate(sql);
            sql = "INSERT INTO position(position_name) VALUES ('FRONTEND')";
            statement.executeUpdate(sql);
            sql = "INSERT INTO position(position_name) VALUES ('ANALYST')";
            statement.executeUpdate(sql);
            sql = "INSERT INTO position(position_name) VALUES ('TESTER')";
            statement.executeUpdate(sql);

            sql = "INSERT INTO specification(specification_name) VALUES ('ANDROID')";
            statement.executeUpdate(sql);
            sql = "INSERT INTO specification(specification_name) VALUES ('IOS')";
            statement.executeUpdate(sql);
            sql = "INSERT INTO specification(specification_name) VALUES ('JAVASERVER')";
            statement.executeUpdate(sql);
            sql = "INSERT INTO specification(specification_name) VALUES ('NONE')";
            statement.executeUpdate(sql);

            sql = "INSERT INTO type(type_name) VALUES ('FULLSTACK')";
            statement.executeUpdate(sql);
            sql = "INSERT INTO type(type_name) VALUES ('BACKEND_ONLY')";
            statement.executeUpdate(sql);
            sql = "INSERT INTO type(type_name) VALUES ('FRONTEND_ONLY')";
            statement.executeUpdate(sql);


        } catch (SQLException |
                ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            assert statement != null;
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void deleteAll() {

        Statement statement = null;

        try (Connection connection = DriverManager.getConnection(url + dbName, user, password)) {
            Class.forName(driver);
            statement = connection.createStatement();

            String sql = "DROP TABLE IF EXISTS employee CASCADE";
            statement.executeUpdate(sql);
            sql = "DROP TABLE IF EXISTS position CASCADE";
            statement.executeUpdate(sql);
            sql = "DROP TABLE IF EXISTS rank CASCADE";
            statement.executeUpdate(sql);
            sql = "DROP TABLE IF EXISTS specification CASCADE";
            statement.executeUpdate(sql);
            sql = "DROP TABLE IF EXISTS employee_position CASCADE";
            statement.executeUpdate(sql);
            sql = "DROP TABLE IF EXISTS employee_rank CASCADE";
            statement.executeUpdate(sql);
            sql = "DROP TABLE IF EXISTS employee_specification CASCADE";
            statement.executeUpdate(sql);
            sql = "DROP TABLE IF EXISTS project CASCADE ";
            statement.executeUpdate(sql);
            sql = "DROP TABLE IF EXISTS type CASCADE";
            statement.executeUpdate(sql);
            sql = "DROP TABLE IF EXISTS project_type CASCADE";
            statement.executeUpdate(sql);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            assert statement != null;
            try {
                statement.close();
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
    }

    public void deleteByNameAndSurname(String name, String surname) {
        PreparedStatement statement = null;

        try (Connection connection = DriverManager.getConnection(url + dbName, user, password)) {
            Class.forName(driver);

            String sql = "DELETE FROM employee WHERE name = ? and surname = ? ";
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.addBatch();
            statement.executeBatch();

            statement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            assert statement != null;
            try {
                statement.close();
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }

    }


    public void insertIntoEmployee(EmployeeImpl employee) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url + dbName, user, password);
        } catch (SQLException ex) {
            System.err.println("Cant establish connection in method insertIntoEmployee");
        }
        PreparedStatement statement = null;

        try {
            Class.forName(driver);

            assert connection != null;
            connection.setAutoCommit(false);

            String sql = "INSERT INTO employee(name, surname, patronymic) VALUES(?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getSurname());
            statement.setString(3, employee.getPatronymic());
            statement.addBatch();
            statement.executeBatch();

            sql = "SELECT employee.id FROM employee WHERE name = ? AND surname = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getSurname());

            ResultSet rs = statement.executeQuery();
            rs.next();
            int employeeId = rs.getInt("id");

            sql = "INSERT INTO employee_position(employee_id, position_id) VALUES (?, " +
                    "(SELECT position.id FROM position WHERE position_name = ?))";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, employeeId);
            statement.setString(2, employee.getPosition().getPositionType().name());
            statement.addBatch();
            statement.executeBatch();

            sql = "INSERT INTO employee_rank(employee_id, rank_id) VALUES (?, " +
                    "(SELECT rank.id FROM rank WHERE rank_name = ?))";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, employeeId);
            statement.setString(2, employee.getRank().name());
            statement.addBatch();
            statement.executeBatch();

            sql = "INSERT INTO employee_specification(employee_id, specification_id) VALUES (?, " +
                    "(SELECT specification.id FROM specification WHERE specification_name = ?))";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, employeeId);
            statement.setString(2, employee.getPosition().getSpecification().name());
            statement.addBatch();
            statement.executeBatch();

            connection.commit();

        } catch (SQLException |
                ClassNotFoundException e) {
            e.printStackTrace();
            try {
                assert connection != null;
                connection.rollback();
            } catch (SQLException sql) {
                System.err.println("Exception in transaction");
            }
        } finally {
            assert statement != null;
            try {
                connection.close();
                statement.close();
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
    }
}
