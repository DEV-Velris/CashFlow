package fr.velris.cashflow.commands;

import fr.velris.cashflow.CashFlow;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.logging.Level;

public class CCash implements CommandExecutor {

    private final CashFlow plugin = CashFlow.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            if (sender instanceof Player) {

                if (sender.hasPermission("cash.balance")) {
                    sender.sendMessage(plugin.getData().PREFIX + plugin.getData().LANG_BALANCE_YOURSELF
                            .replace("%money", plugin.getCash().getUserCash(sender.getName())+""));
                } else {
                    sender.sendMessage(plugin.getData().PREFIX + plugin.getData().LANG_ERRORS_NO_PERMISSION);
                }
            } else {
                sender.sendMessage(plugin.getData().PREFIX + plugin.getData().LANG_ERRORS_NOT_A_PLAYER);
            }

            return true;

        } else if (args.length == 1) {

            if (args[0].equalsIgnoreCase("balance")) {

                if (sender instanceof Player) {
                    if (sender.hasPermission("cash.balance")) {
                        sender.sendMessage(plugin.getData().PREFIX + plugin.getData().LANG_BALANCE_YOURSELF
                                .replace("%money", plugin.getCash().getUserCash(sender.getName())+""));
                    } else {
                        sender.sendMessage(plugin.getData().PREFIX + plugin.getData().LANG_ERRORS_NO_PERMISSION);
                    }
                } else {
                    sender.sendMessage(plugin.getData().PREFIX + plugin.getData().LANG_ERRORS_NOT_A_PLAYER);
                }

                return true;

            } else if (args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("cash.reload")) {
                    try {
                        plugin.getFiles().configDocument.save();
                        plugin.getFiles().configDocument.reload();
                        plugin.getFiles().langDocument.save();
                        plugin.getFiles().langDocument.reload();

                        plugin.getData().LoadConfig();
                        plugin.getData().LoadMessages();
                        sender.sendMessage(plugin.getData().PREFIX + plugin.getData().LANG_RELOAD);
                    } catch (IOException exception) {
                        plugin.Log(Level.SEVERE, exception.getMessage());
                    }
                } else {
                    sender.sendMessage(plugin.getData().PREFIX + plugin.getData().LANG_ERRORS_NO_PERMISSION);
                }

                return true;

            } else if (args[0].equalsIgnoreCase("baltop")) {
                if (sender.hasPermission("cash.baltop")) {
                    //Todo Baltop
                } else {
                    sender.sendMessage(plugin.getData().PREFIX + plugin.getData().LANG_ERRORS_NO_PERMISSION);
                }

                return true;

            } else {
                if (plugin.getCash().hasAccount(args[0])) {
                    sender.sendMessage(plugin.getData().PREFIX + plugin.getData().LANG_BALANCE_OTHERS
                            .replace("%target%", args[0])
                            .replace("%money%", plugin.getCash().getUserCash(args[0])+""));
                } else {
                    sender.sendMessage(plugin.getData().PREFIX + plugin.getData().LANG_ERRORS_PLAYER_DONT_EXIST.replace("%player%", args[0]));
                }

                return true;

            }

        } else if (args.length == 3) {
            Player target = Bukkit.getPlayer(args[1]);

            if (args[0].equalsIgnoreCase("pay")) {

                if (sender instanceof Player) {

                    if (sender.hasPermission("cash.pay")) {

                        if (plugin.getCash().hasAccount(args[1])) {

                            if (plugin.isNumeric(args[2])) {
                                double pay = Double.parseDouble(args[2]);

                                if (plugin.getCash().hasEnoughCash(sender.getName(), pay)) {

                                    plugin.getCash().withdrawCash(sender.getName(), pay);
                                    sender.sendMessage(plugin.getData().PREFIX + plugin.getData().LANG_PAY_SENDER
                                            .replace("%amount%", pay+"")
                                            .replace("%target%", args[1]));
                                    plugin.getCash().depositCash(args[1], pay);
                                    if (target != null) {
                                        target.sendMessage(plugin.getData().PREFIX + plugin.getData().LANG_PAY_RECEIVER
                                                .replace("%amount%", pay+"")
                                                .replace("%sender%", sender.getName()));
                                    }

                                } else {
                                    sender.sendMessage(plugin.getData().PREFIX + plugin.getData().LANG_ERRORS_NO_ENOUGH_MONEY
                                            .replace("%currentmoney%", plugin.getCash().getUserCash(sender.getName())+"")
                                            .replace("%neededmoney%", pay+""));
                                }

                            } else {
                                sender.sendMessage(plugin.getData().PREFIX + plugin.getData().LANG_ERRORS_NOT_NUMERIC);
                            }

                        } else {
                            sender.sendMessage(plugin.getData().PREFIX + plugin.getData().LANG_ERRORS_PLAYER_DONT_EXIST
                                    .replace("%player%", args[1]));
                        }

                    } else {
                        sender.sendMessage(plugin.getData().PREFIX + plugin.getData().LANG_ERRORS_NO_PERMISSION);
                    }

                } else {
                    sender.sendMessage(plugin.getData().PREFIX + plugin.getData().LANG_ERRORS_NOT_A_PLAYER);
                }

                return true;

            } else if (args[0].equalsIgnoreCase("add")) {

                if (sender.hasPermission("cash.add")) {

                    if (plugin.getCash().hasAccount(args[1])) {

                        if (plugin.isNumeric(args[2])) {
                            double add = Double.parseDouble(args[2]);

                            plugin.getCash().depositCash(args[1], add);
                            sender.sendMessage(plugin.getData().PREFIX + plugin.getData().LANG_ADD_ADMIN
                                    .replace("%amount%", add+"")
                                    .replace("%target%", args[1]));
                            if (target != null) {
                                target.sendMessage(plugin.getData().PREFIX + plugin.getData().LANG_ADD_TARGET
                                        .replace("%admin%", sender.getName())
                                        .replace("%amount%", add+""));
                            }

                        } else {
                            sender.sendMessage(plugin.getData().PREFIX + plugin.getData().LANG_ERRORS_NOT_NUMERIC);
                        }

                    } else {
                        sender.sendMessage(plugin.getData().PREFIX + plugin.getData().LANG_ERRORS_PLAYER_DONT_EXIST
                                .replace("%player%", args[1]));
                    }

                } else {
                    sender.sendMessage(plugin.getData().PREFIX + plugin.getData().LANG_ERRORS_NO_PERMISSION);
                }

                return true;

            } else if (args[0].equalsIgnoreCase("remove")) {

                if (sender.hasPermission("cash.remove")) {

                    if (plugin.getCash().hasAccount(args[1])) {

                        if (plugin.isNumeric(args[2])) {
                            double remove = Double.parseDouble(args[2]);

                            plugin.getCash().withdrawCash(args[1], remove);
                            sender.sendMessage(plugin.getData().PREFIX + plugin.getData().LANG_REMOVE_ADMIN
                                    .replace("%amount%", remove+"")
                                    .replace("%target%", args[1]));
                            if (target != null) {
                                target.sendMessage(plugin.getData().PREFIX + plugin.getData().LANG_REMOVE_TARGET
                                        .replace("%admin%", sender.getName())
                                        .replace("%amount%", remove+""));
                            }

                        } else {
                            sender.sendMessage(plugin.getData().PREFIX + plugin.getData().LANG_ERRORS_NOT_NUMERIC);
                        }

                    } else {
                        sender.sendMessage(plugin.getData().PREFIX + plugin.getData().LANG_ERRORS_PLAYER_DONT_EXIST
                                .replace("%player%", args[1]));
                    }

                } else {
                    sender.sendMessage(plugin.getData().PREFIX + plugin.getData().LANG_ERRORS_NO_PERMISSION);
                }

                return true;

            } else if (args[0].equalsIgnoreCase("set")) {

                if (sender.hasPermission("cash.set")) {

                    if (plugin.getCash().hasAccount(args[1])) {

                        if (plugin.isNumeric(args[2])) {
                            double set = Double.parseDouble(args[2]);

                            if (plugin.getCash().setCash(args[1], set)) {
                                sender.sendMessage(plugin.getData().PREFIX + plugin.getData().LANG_SET_ADMIN
                                        .replace("%target%", args[1])
                                        .replace("%amount%", sender+""));
                                if (target != null) {
                                    target.sendMessage(plugin.getData().PREFIX + plugin.getData().LANG_SET_TARGET
                                            .replace("%amount%", sender+"")
                                            .replace("%admin%", sender.getName()));
                                }
                            } else {
                                sender.sendMessage(plugin.getData().PREFIX + plugin.getData().PREFIX + "§cError 1, ask on discord");
                            }

                        } else {
                            sender.sendMessage(plugin.getData().PREFIX + plugin.getData().LANG_ERRORS_NOT_NUMERIC);
                        }

                    } else {
                        sender.sendMessage(plugin.getData().PREFIX + plugin.getData().LANG_ERRORS_PLAYER_DONT_EXIST
                                .replace("%player%", args[1]));
                    }

                } else {
                    sender.sendMessage(plugin.getData().PREFIX + plugin.getData().LANG_ERRORS_NO_PERMISSION);
                }

            }

            return true;

        }

        return false;
    }
}
