package fr.velris.cashflow.commands;

import fr.velris.cashflow.CashFlow;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TabCash implements TabCompleter {

    private final CashFlow plugin = CashFlow.getInstance();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        List<String> list = new ArrayList<>();

        if (args.length == 1) {
            if (sender.hasPermission("cash.balance")) {
                if (sender instanceof Player) {
                    list.add("balance");
                }
                for (Player player : Bukkit.getOnlinePlayers()) {
                    list.add(player.getName());
                }
            }
            if (sender.hasPermission("cash.reload")) {
                list.add("reload");
            }
            if (sender.hasPermission("cash.baltop")) {
                list.add("baltop");
            }
            if (sender.hasPermission("cash.pay")) {
                list.add("pay");
            }
            if (sender.hasPermission("cash.add")) {
                list.add("add");
            }
            if (sender.hasPermission("cash.remove")) {
                list.add("remove");
            }
            if (sender.hasPermission("cash.set")) {
                list.add("set");
            }
            list.add("help");
        }

        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("pay") || args[0].equalsIgnoreCase("add")
                || args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("set")
                    || args[0].equalsIgnoreCase("balance")) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    list.add(player.getName());
                }
            }
        }

        if (args.length == 3) {
            if (args[0].equalsIgnoreCase("pay") || args[0].equalsIgnoreCase("add")
                    || args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("set")) {
                list.add("50");
                list.add("100");
                list.add("500");
                list.add("1000");
                list.add("5000");
                list.add("10000");
                list.add("50000");
                list.add("100000");
                list.add("500000");
                list.add("1000000");
            }
        }

        return list;
    }
}
