package di.com.a1tSign.projectManager.repository.jdbc.project;

import di.com.a1tSign.projectManager.project.Project;
import di.com.a1tSign.projectManager.repository.jdbc.info.JdbcDataSourceInfo;
import di.org.springframework.beans.factory.stereotype.Component;
import di.org.springframework.beans.factory.stereotype.priority.Primary;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
@Primary
public final class H2jdbcProjectEntityManagerImpl extends JdbcDataSourceInfo {
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

    public List<String> findByTitle(String title) {
        PreparedStatement statement = null;
        List<String> ans = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url + dbName, user, password)) {
            Class.forName(driver);

            String sql = "SELECT p.title, t.type_name FROM project p " +
                    "INNER JOIN project_type pr ON p.id = pr.project_id " +
                    "INNER JOIN type t ON pr.type_id = t.id " +
                    "WHERE p.title = ?";

            statement = connection.prepareStatement(sql);
            statement.setString(1, title);

            ResultSet rs = statement.executeQuery();
            rs.next();

            String title1 = rs.getString("title");
            ans.add(title1);
            String type = rs.getString("type_name");
            ans.add(type);

        } catch (ClassNotFoundException | SQLException e) {
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

    public void updateProject(String oldTitle, Project project) {
        PreparedStatement statement = null;

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url + dbName, user, password);
        } catch (SQLException ex) {
            System.err.println("Cant establish connection in method updateProject");
        }

        try  {
            Class.forName(driver);

            assert connection != null;
            connection.setAutoCommit(false);

            String sql = "SELECT p.id FROM project p WHERE p.title = ?";
            statement = connection.prepareStatement(sql);

            statement.setString(1, oldTitle);
            ResultSet rs = statement.executeQuery();
            rs.next();
            int projectId = rs.getInt("id");

            sql = "UPDATE project SET title = ? WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, project.getTitle());
            statement.setInt(2, projectId);
            statement.addBatch();
            statement.executeBatch();

            sql = "UPDATE project_type SET type_id = " +
                    "(SELECT type_id FROM type t WHERE t.type_name = ?) " +
                    "WHERE project_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, project.getProjectType().name());
            statement.setInt(2, projectId);
            statement.addBatch();
            statement.executeBatch();

            connection.commit();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                assert statement != null;
                statement.close();
                connection.close();
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }

    }
}
