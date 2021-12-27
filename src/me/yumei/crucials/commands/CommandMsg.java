package me.yumei.crucials.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandMsg implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player) {
            if(strings.length < 2) {
                commandSender.sendMessage("§cCorrect usage: /msg [player] [message]");
            } else {
                Player player = Bukkit.getPlayer(strings[0]);
                StringBuilder message = new StringBuilder();
                if(player != null) {
                    if(player.getName() != commandSender.getName()) {
                        for (int i = 0; i < strings.length; i++) {
                            if(i > 0) {
                                message.append(strings[i]).append(" ");
                            }
                        }
                        player.sendMessage("§a" + commandSender.getName() + " -> me§f: " + message);
                        commandSender.sendMessage("§ame" + " -> " + player.getName() + "§f: " + message);
                    } else {
                        commandSender.sendMessage("§cYou can't send message to yourself.");
                    }
                } else {
                    commandSender.sendMessage("§cPlayer " + strings[0] + " is not online.");
                }
            }
        } else {
            commandSender.sendMessage("§cCan't send private message from console.");
        }
        return true;
    }
}
