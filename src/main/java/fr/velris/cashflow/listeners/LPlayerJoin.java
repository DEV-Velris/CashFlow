package fr.velris.cashflow.listeners;

import fr.velris.cashflow.CashFlow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class LPlayerJoin implements Listener {

    private final CashFlow plugin = CashFlow.getInstance();

    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (!plugin.getCash().hasAccount(player.getName())) {
            plugin.getCash().registerPlayer(player.getName());
        }

    }

}
