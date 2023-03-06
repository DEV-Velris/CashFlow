package fr.velris.cashflow.utils;

import dev.dejvokep.boostedyaml.YamlDocument;
import fr.velris.cashflow.CashFlow;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class UData {

    private final CashFlow plugin = CashFlow.getInstance();

    /*
    CONFIG
     */
    public String PREFIX;
    public Boolean VAULT_ENABLED;

    /*
    LANG
     */
    //Errors
    public String LANG_ERRORS_NOT_A_PLAYER;
    public String LANG_ERRORS_NO_PERMISSION;
    public String LANG_ERRORS_PLAYER_DONT_EXIST;
    public String LANG_ERRORS_NOT_NUMERIC;
    public String LANG_ERRORS_NO_ENOUGH_MONEY;
    //Messages
    public String LANG_BALANCE_YOURSELF;
    public String LANG_BALANCE_OTHERS;
    public String LANG_PAY_SENDER;
    public String LANG_PAY_RECEIVER;
    public String LANG_ADD_ADMIN;
    public String LANG_ADD_TARGET;
    public String LANG_REMOVE_ADMIN;
    public String LANG_REMOVE_TARGET;
    public String LANG_SET_ADMIN;
    public String LANG_SET_TARGET;
    //Others
    public String LANG_RELOAD;
    //Help
    public List<String> LANG_PLAYER_HELP = new ArrayList<>();
    public List<String> LANG_ADMIN_HELP = new ArrayList<>();

    public void LoadConfig() {
        YamlDocument config = plugin.getFiles().configDocument;

        PREFIX = config.getString("prefix").replace("&", "§");
        VAULT_ENABLED = config.getBoolean("vault.enable");

    }

    public void LoadMessages() {
        YamlDocument lang = plugin.getFiles().langDocument;

        LANG_ERRORS_NOT_A_PLAYER = lang.getString("errors.not-a-player").replace("&", "§");
        LANG_ERRORS_NO_PERMISSION = lang.getString("errors.no-permission").replace("&", "§");
        LANG_ERRORS_PLAYER_DONT_EXIST = lang.getString("errors.player-dont-exist").replace("&", "§");
        LANG_ERRORS_NOT_NUMERIC = lang.getString("errors.not-numeric").replace("&", "§");
        LANG_ERRORS_NO_ENOUGH_MONEY = lang.getString("errors.no-enough-money").replace("&", "§");

        LANG_BALANCE_YOURSELF = lang.getString("messages.balance.yourself").replace("&", "§");
        LANG_BALANCE_OTHERS = lang.getString("messages.balance.others").replace("&", "§");
        LANG_PAY_SENDER = lang.getString("messages.pay.sender").replace("&", "§");
        LANG_PAY_RECEIVER = lang.getString("messages.pay.receiver").replace("&", "§");
        LANG_ADD_ADMIN = lang.getString("messages.add.admin").replace("&", "§");
        LANG_ADD_TARGET = lang.getString("messages.add.target").replace("&", "§");
        LANG_REMOVE_ADMIN = lang.getString("messages.remove.admin").replace("&", "§");
        LANG_REMOVE_TARGET = lang.getString("messages.remove.target").replace("&", "§");
        LANG_SET_ADMIN = lang.getString("messages.set.admin").replace("&", "§");
        LANG_SET_TARGET = lang.getString("messages.set.target").replace("&", "§");

        LANG_RELOAD = lang.getString("messages.reload").replace("&", "§");

        LANG_PLAYER_HELP = lang.getStringList("help.player");
        for (int i = 0; i < LANG_PLAYER_HELP.size(); i++) {
            String str = LANG_PLAYER_HELP.get(i);
            str = str.replace("&", "§");
            LANG_PLAYER_HELP.set(i, str);
        }

        LANG_ADMIN_HELP = lang.getStringList("help.admin");
        for (int i = 0; i < LANG_ADMIN_HELP.size(); i++) {
            String str = LANG_ADMIN_HELP.get(i);
            str = str.replace("&", "§");
            LANG_ADMIN_HELP.set(i, str);
        }
    }

}
