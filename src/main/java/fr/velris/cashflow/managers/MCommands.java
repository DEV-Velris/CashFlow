package fr.velris.cashflow.managers;

import fr.velris.cashflow.CashFlow;
import fr.velris.cashflow.commands.CCash;
import fr.velris.cashflow.commands.TabCash;

import java.util.Objects;

public class MCommands {

    private final CashFlow plugin = CashFlow.getInstance();

    public void LoadCommands() {
        //Registering /cash command
        Objects.requireNonNull(plugin.getCommand("cash")).setExecutor(new CCash());
        Objects.requireNonNull(plugin.getCommand("cash")).setTabCompleter(new TabCash());
    }

}
