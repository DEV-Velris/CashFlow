package fr.velris.cashflow;

import fr.velris.cashflow.managers.MCommands;
import fr.velris.cashflow.managers.MDatabase;
import fr.velris.cashflow.managers.MFiles;
import fr.velris.cashflow.managers.MListeners;
import fr.velris.cashflow.utils.UCash;
import fr.velris.cashflow.utils.UData;
import fr.velris.cashflow.vault.VImplement;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class CashFlow extends JavaPlugin {

    private static CashFlow instance;
    boolean started;

    /*
    MANAGERS
     */
    private MFiles files;
    private MListeners listeners;
    private MCommands commands;
    private MDatabase database;

    /*
    UTILS
     */
    private UCash cash;
    private UData data;

    /*
    VAULT
     */
    private VImplement implement;

    @Override
    public void onEnable() {
        instance = this;

        //Files
        files = new MFiles();
        files.LoadFiles();
        data = new UData();
        data.LoadConfig();
        data.LoadMessages();

        if (!getServer().getPluginManager().isPluginEnabled("Vault") && data.VAULT_ENABLED) {
            Log(Level.SEVERE, "Vault plugin not found");
            Log(Level.SEVERE, "If you don't want to use Vault with CashFlow modify it in the configuration file !");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        /*
        MANAGERS
         */
        listeners = new MListeners();
        listeners.LoadListeners();
        commands = new MCommands();
        commands.LoadCommands();
        database = new MDatabase();
        database.LoadDatabase();

        /*
        UTILS
         */
        cash = new UCash();

        /*
        VAULT
         */
        if (data.VAULT_ENABLED) {
            implement = new VImplement();
            getServer().getServicesManager().register(Economy.class, implement, this, ServicePriority.Normal);
        }

        started = true;
    }

    @Override
    public void onDisable() {
        if (started) {
            database.disconnect();
        }
    }

    public static CashFlow getInstance() {
        return instance;
    }

    public void Log(Level level, String message) {
        getLogger().log(level, message);
    }

    public MFiles getFiles() {
        return files;
    }

    public MListeners getListeners() {
        return listeners;
    }

    public MCommands getCommands() {
        return commands;
    }

    public MDatabase getDatabase() {
        return database;
    }

    public UCash getCash() {
        return cash;
    }

    public UData getData() {
        return data;
    }

    public VImplement getImplement() {
        return implement;
    }

    public boolean isNumeric(String number) {
        try {
            double d = Double.parseDouble(number);
        } catch (NumberFormatException exception) {
            return false;
        }
        return true;
    }
}
