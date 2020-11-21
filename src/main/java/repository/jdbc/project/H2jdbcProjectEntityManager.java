package repository.jdbc.project;

import project.Project;
import repository.jdbc.JdbcDataSourceInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class H2jdbcProjectEntityManager extends JdbcDataSourceInfo {
    public void deleteByTitle(String title) {
        PreparedStatement statement = null;

        try (Connection connection = DriverManager.getConnection(url + dbName, user, password)) {
            Class.forName(driver);

            String sql = "DELETE FROM project WHERE title = ? ";
            statement = connection.prepareStatement(sql);
            statement.setString(1, title);
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

    public List<List<String>>  getProjects() {
        Statement statement = null;
        List<List<String>> ans = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url + dbName, user, password)) {
            Class.forName(driver);
            statement = connection.createStatement();

            String sql = "SELECT p.title, t.type_name FROM project p " +
                    "INNER JOIN project_type pr ON p.id = pr.project_id " +
                    "INNER JOIN type t ON pr.type_id = t.id";
            ResultSet set = statement.executeQuery(sql);

            while (set.next()) {
                List<String> ls = new ArrayList<>();
                String title = set.getString("title");
                ls.add(title);
                String type = set.getString("type_name");
                ls.add(type);

                System.out.println("title: " + title);
                System.out.println("type: " + type);

                ans.add(ls);
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

    public void insertIntoProject(Project project) {
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

            String sql = "INSERT INTO project(title) VALUES(?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, project.getTitle());
            statement.addBatch();
            statement.executeBatch();

            sql = "INSERT INTO project_type(project_id, type_id) VALUES " +
                    "((SELECT project.id FROM project WHERE title = ?), " +
                    "SELECT type.id FROM type WHERE type_name = ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, project.getTitle());
            statement.setString(2, project.getProjectType().name());
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