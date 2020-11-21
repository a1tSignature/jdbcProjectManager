package repository.jdbc;

import lombok.Getter;

import java.util.ResourceBundle;

@Getter
public abstract class JdbcDataSourceInfo {

    private static ResourceBundle resource = ResourceBundle.getBundle("application");

    protected final String url = resource.getString("db.url");
    protected final String user = resource.getString("db.user");
    protected final String password = resource.getString("db.password");
    protected final String dbName = resource.getString("db.database");
    protected final String driver = resource.getString("db.driver");
}
