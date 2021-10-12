package db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.SQLException;

public class DataBaseConnection implements Closeable {
    private static final String JDBC_DRIVER = PropertiesLoader.getProperty("db.driver");
    private static final String url = PropertiesLoader.getProperty("db.url");
    private static final  String user = PropertiesLoader.getProperty("db.user");
    private static final  String password = PropertiesLoader.getProperty("db.password");

    private static DataBaseConnection dataBaseConnection;
    private Connection connection;
    private HikariDataSource ds;

    private DataBaseConnection() {
        ds = new HikariDataSource(initDataSource());
    }

    private HikariConfig initDataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(JDBC_DRIVER);
        config.setJdbcUrl(url);
        config.setPassword(password);
        config.setUsername(user);
        config.setMaximumPoolSize(10);
        config.setIdleTimeout(10000);
        config.setConnectionTimeout(10000);
        return config;
    }

    public  Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public static DataBaseConnection getInstance()  {
        if (dataBaseConnection == null) {
            dataBaseConnection = new DataBaseConnection();
        }
        return dataBaseConnection;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
