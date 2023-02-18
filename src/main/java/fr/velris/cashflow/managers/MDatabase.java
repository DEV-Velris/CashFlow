package fr.velris.cashflow.managers;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import fr.velris.cashflow.CashFlow;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

public class MDatabase {

    private final CashFlow plugin = CashFlow.getInstance();

    private HikariDataSource dataSource;

    public void LoadDatabase() {
        createDatabase();

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:sqlite:"+plugin.getDataFolder()+"/cashflow.db");
        config.setMinimumIdle(1);
        config.setMaximumPoolSize(1);
        config.setConnectionTimeout(0);
        dataSource = new HikariDataSource(config);

        try (Connection conn = getConnection()) {
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS Users(id INTEGER PRIMARY KEY, username TEXT NOT NULL, cash NUMERIC(10,2) NOT NULL)";
            stmt.execute(sql);
            plugin.Log(Level.INFO, "Database loaded !");
        } catch (SQLException exception) {
            plugin.Log(Level.SEVERE, exception.getMessage());
        }

    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException exception) {
            plugin.Log(Level.SEVERE, exception.getMessage());
        }

        return connection;
    }

    public void disconnect() {
        if (!dataSource.isClosed()) {
            dataSource.close();
        }
    }

    public void createDatabase() {
        File pluginFolder = new File("plugins/CashFlow");
        File dbFile = new File(pluginFolder, "cashflow.db");

        if (!pluginFolder.exists()) {
            pluginFolder.mkdirs();
        }

        if (!dbFile.exists()) {
            try {
                dbFile.createNewFile();
            } catch (IOException exception) {
                plugin.Log(Level.SEVERE, exception.getMessage());
            }
        }

    }

}
