package fr.velris.cashflow.managers;

import fr.velris.cashflow.CashFlow;
import fr.velris.cashflow.listeners.LPlayerJoin;
import org.bukkit.plugin.PluginManager;

public class MListeners {

    private final CashFlow plugin = CashFlow.getInstance();

    public void LoadListeners() {
        PluginManager pm = plugin.getServer().getPluginManager();

        pm.registerEvents(new LPlayerJoin(), plugin);
    }

}
