package fr.velris.cashflow.vault;

import fr.velris.cashflow.CashFlow;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.List;

public class VImplement implements Economy {

    private final CashFlow plugin = CashFlow.getInstance();

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return "CashFlow";
    }

    @Override
    public boolean hasBankSupport() {
        return false;
    }

    @Override
    public int fractionalDigits() {
        return 2;
    }

    @Override
    public String format(double amount) {
        return String.format("%.2f", amount);
    }

    @Override
    public String currencyNamePlural() {
        return "coins";
    }

    @Override
    public String currencyNameSingular() {
        return "coin";
    }

    @Override
    public boolean hasAccount(String playerName) {
        return Bukkit.getOfflinePlayer(playerName).hasPlayedBefore();
    }

    @Override
    public boolean hasAccount(OfflinePlayer player) {
        return player.hasPlayedBefore();
    }

    @Override
    public boolean hasAccount(String playerName, String worldName) {
        return Bukkit.getOfflinePlayer(playerName).hasPlayedBefore();
    }

    @Override
    public boolean hasAccount(OfflinePlayer player, String worldName) {
        return player.hasPlayedBefore();
    }

    @Override
    public double getBalance(String playerName) {
        return plugin.getCash().getUserCash(playerName);
    }

    @Override
    public double getBalance(OfflinePlayer player) {
        return plugin.getCash().getUserCash(player.getName());
    }

    @Override
    public double getBalance(String playerName, String world) {
        return plugin.getCash().getUserCash(playerName);
    }

    @Override
    public double getBalance(OfflinePlayer player, String world) {
        return plugin.getCash().getUserCash(player.getName());
    }

    @Override
    public boolean has(String playerName, double amount) {
        return plugin.getCash().hasEnoughCash(playerName, amount);
    }

    @Override
    public boolean has(OfflinePlayer player, double amount) {
        return plugin.getCash().hasEnoughCash(player.getName(), amount);
    }

    @Override
    public boolean has(String playerName, String worldName, double amount) {
        return plugin.getCash().hasEnoughCash(playerName, amount);
    }

    @Override
    public boolean has(OfflinePlayer player, String worldName, double amount) {
        return plugin.getCash().hasEnoughCash(player.getName(), amount);
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, double amount) {
        if (plugin.getCash().withdrawCash(playerName, amount)) {
            return new EconomyResponse(amount, getBalance(playerName), EconomyResponse.ResponseType.SUCCESS, "");
        }
        return new EconomyResponse(amount, getBalance(playerName), EconomyResponse.ResponseType.FAILURE, "");
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer player, double amount) {
        if (plugin.getCash().withdrawCash(player.getName(), amount)) {
            return new EconomyResponse(amount, getBalance(player.getName()), EconomyResponse.ResponseType.SUCCESS, "");
        }
        return new EconomyResponse(amount, getBalance(player.getName()), EconomyResponse.ResponseType.FAILURE, "");
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, String worldName, double amount) {
        if (plugin.getCash().withdrawCash(playerName, amount)) {
            return new EconomyResponse(amount, getBalance(playerName), EconomyResponse.ResponseType.SUCCESS, "");
        }
        return new EconomyResponse(amount, getBalance(playerName), EconomyResponse.ResponseType.FAILURE, "");
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer player, String worldName, double amount) {
        if (plugin.getCash().withdrawCash(player.getName(), amount)) {
            return new EconomyResponse(amount, getBalance(player.getName()), EconomyResponse.ResponseType.SUCCESS, "");
        }
        return new EconomyResponse(amount, getBalance(player.getName()), EconomyResponse.ResponseType.FAILURE, "");
    }

    @Override
    public EconomyResponse depositPlayer(String playerName, double amount) {
        if (plugin.getCash().depositCash(playerName, amount)) {
            return new EconomyResponse(amount, getBalance(playerName), EconomyResponse.ResponseType.SUCCESS, "");
        } else {
            return new EconomyResponse(amount, getBalance(playerName), EconomyResponse.ResponseType.FAILURE, "");
        }
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer player, double amount) {
        if (plugin.getCash().depositCash(player.getName(), amount)) {
            return new EconomyResponse(amount, getBalance(player.getName()), EconomyResponse.ResponseType.SUCCESS, "");
        } else {
            return new EconomyResponse(amount, getBalance(player.getName()), EconomyResponse.ResponseType.FAILURE, "");
        }
    }

    @Override
    public EconomyResponse depositPlayer(String playerName, String worldName, double amount) {
        if (plugin.getCash().depositCash(playerName, amount)) {
            return new EconomyResponse(amount, getBalance(playerName), EconomyResponse.ResponseType.SUCCESS, "");
        } else {
            return new EconomyResponse(amount, getBalance(playerName), EconomyResponse.ResponseType.FAILURE, "");
        }
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer player, String worldName, double amount) {
        if (plugin.getCash().depositCash(player.getName(), amount)) {
            return new EconomyResponse(amount, getBalance(player.getName()), EconomyResponse.ResponseType.SUCCESS, "");
        } else {
            return new EconomyResponse(amount, getBalance(player.getName()), EconomyResponse.ResponseType.FAILURE, "");
        }
    }

    @Override
    public EconomyResponse createBank(String name, String player) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Not Implemented !");
    }

    @Override
    public EconomyResponse createBank(String name, OfflinePlayer player) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Not Implemented !");
    }

    @Override
    public EconomyResponse deleteBank(String name) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Not Implemented !");
    }

    @Override
    public EconomyResponse bankBalance(String name) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Not Implemented !");
    }

    @Override
    public EconomyResponse bankHas(String name, double amount) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Not Implemented !");
    }

    @Override
    public EconomyResponse bankWithdraw(String name, double amount) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Not Implemented !");
    }

    @Override
    public EconomyResponse bankDeposit(String name, double amount) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Not Implemented !");
    }

    @Override
    public EconomyResponse isBankOwner(String name, String playerName) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Not Implemented !");
    }

    @Override
    public EconomyResponse isBankOwner(String name, OfflinePlayer player) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Not Implemented !");
    }

    @Override
    public EconomyResponse isBankMember(String name, String playerName) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Not Implemented !");
    }

    @Override
    public EconomyResponse isBankMember(String name, OfflinePlayer player) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Not Implemented !");
    }

    @Override
    public List<String> getBanks() {
        return null;
    }

    @Override
    public boolean createPlayerAccount(String playerName) {
        return false;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer player) {
        return false;
    }

    @Override
    public boolean createPlayerAccount(String playerName, String worldName) {
        return false;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer player, String worldName) {
        return false;
    }
}
