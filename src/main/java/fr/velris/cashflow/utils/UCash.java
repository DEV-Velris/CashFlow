package fr.velris.cashflow.utils;

import fr.velris.cashflow.CashFlow;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

public class UCash {

    private final CashFlow plugin = CashFlow.getInstance();

    public double getUserCash(String player) {
        double cash = 0.00;

        try (Connection connection = plugin.getDatabase().getConnection()) {

            String sql = "SELECT cash FROM Users WHERE username = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, player);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                cash = rs.getDouble("cash");
            }

        } catch (SQLException exception) {
            plugin.Log(Level.SEVERE, exception.getMessage());
        }

        return cash;
    }

    public boolean hasEnoughCash(String player, Double cash) {
        boolean hasEnough = false;

        try (Connection connection = plugin.getDatabase().getConnection()) {

            String sql = "SELECT cash FROM Users WHERE username = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, player);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                hasEnough = (rs.getDouble("cash") >= cash);
            }

        } catch (SQLException exception) {
            plugin.Log(Level.SEVERE, exception.getMessage());
        }

        return hasEnough;
    }

    public boolean withdrawCash(String player, Double cash) {
        boolean hasWork = false;

        try (Connection connection = plugin.getDatabase().getConnection()) {

            String sql = "UPDATE Users SET cash = cash - ? WHERE username = ?";

            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setDouble(1, cash);
                pstmt.setString(2, player);
                pstmt.executeUpdate();
                hasWork = true;
            }

        } catch (SQLException exception) {
            plugin.Log(Level.SEVERE, exception.getMessage());
        }
        return hasWork;
    }

    public boolean depositCash(String player, Double cash) {
        boolean hasWork = false;

        try (Connection connection = plugin.getDatabase().getConnection()) {

            String sql = "UPDATE Users SET cash = cash + ? WHERE username = ?";

            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setDouble(1, cash);
                pstmt.setString(2, player);
                pstmt.executeUpdate();
                hasWork = true;
            }

        } catch (SQLException exception) {
            plugin.Log(Level.SEVERE, exception.getMessage());
        }

        return hasWork;
    }

    public boolean hasAccount(String player) {
        try (Connection connection = plugin.getDatabase().getConnection()) {

            String sql = "SELECT username FROM Users WHERE username = ?";

            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, player);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }

        } catch (SQLException exception) {
            plugin.Log(Level.SEVERE, exception.getMessage());
        }

        return false;
    }

    public boolean setCash(String player, Double cash) {
        if (!plugin.isNumeric(String.valueOf(cash))) {
            return false;
        }

        try (Connection connection = plugin.getDatabase().getConnection()) {

            String sql = "UPDATE Users SET cash = ? WHERE username = ?";

            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

                pstmt.setDouble(1, cash);
                pstmt.setString(2, player);

                int updated = pstmt.executeUpdate();
                if (updated == 1) {
                    return true;
                } else {
                    return  false;
                }

            }

        } catch (SQLException exception) {
            plugin.Log(Level.SEVERE, exception.getMessage());
            return false;
        }

    }

    public void registerPlayer(String player) {

        try (Connection connection = plugin.getDatabase().getConnection()) {

            String sql = "INSERT INTO Users(username, cash) VALUES(?, 0.00)";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, player);
            stmt.executeUpdate();

        } catch (SQLException exception) {
            plugin.Log(Level.SEVERE, exception.getMessage());
        }

    }

}
