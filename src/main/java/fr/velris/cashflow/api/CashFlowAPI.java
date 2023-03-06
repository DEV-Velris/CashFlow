package fr.velris.cashflow.api;

import fr.velris.cashflow.utils.UCash;

public class CashFlowAPI {

    private final UCash uCash;

    public CashFlowAPI() {
        uCash = new UCash();
    }

    /**
     * Get the money the player has
     *
     * @param playerName Name of the player
     * @return The money that the player has in his account.
     */
    public double getUserCash(String playerName) {
        return uCash.getUserCash(playerName);
    }

    /**
     * Checks if the player account has the amount - DO NOT USE NEGATIVE AMOUNTS.
     *
     * @param playerName Name of the player
     * @param amount Amount to check
     * @return True if the player has enough money otherwise False.
     */
    public boolean hasEnoughCash(String playerName, Double amount) {
        return uCash.hasEnoughCash(playerName, amount);
    }

    /**
     * Withdraw an amount from a player - DO NOT USE NEGATIVE AMOUNTS.
     *
     * @param playerName Name of the player
     * @param amount Amount to withdraw
     * @return True if the action has been executed without error otherwise False.
     */
    public boolean withdrawCash(String playerName, Double amount) {
        return uCash.withdrawCash(playerName, amount);
    }

    /**
     * Deposit an amount to a player - DO NOT USE NEGATIVE AMOUNTS.
     *
     * @param playerName Name of the player
     * @param amount Amount to deposit
     * @return True if the action has been executed without error otherwise False.
     */
    public boolean depositCash(String playerName, Double amount) {
        return uCash.depositCash(playerName, amount);
    }

    /**
     * SET an amount to a player - DO NOT USE NEGATIVE AMOUNTS.
     *
     * @param playerName Name if the player
     * @param amount Amount to be defined on his account
     * @return True if the action has been executed without error otherwise False.
     */
    public boolean setCash(String playerName, Double amount) {
        return uCash.setCash(playerName, amount);
    }

}
