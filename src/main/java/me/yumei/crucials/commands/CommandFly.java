package me.yumei.crucials.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandFly implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings.length > 0) {
            SetFlying(commandSender, strings[0], true);
        } else {
            if (commandSender instanceof Player) {
                SetFlying(commandSender, commandSender.getName(), false);
            } else {
                commandSender.sendMessage("§cYou can't use that command from console. Use /fly [player]");
            }
        }
        return true;
    }
    public void SetFlying(CommandSender commandSender, String playerName, boolean returnName) {
        Player player = Bukkit.getPlayer(playerName);
        if(commandSender.hasPermission("crucials.fly")) {
            if(player != null) {
                if (player.getAllowFlight()) {
                    player.setAllowFlight(false);
                    if(returnName) {
                        commandSender.sendMessage("§aDisabled flying for " + playerName + ".");
                        player.sendMessage("§aFlying is now disabled.");
                    } else {
                        commandSender.sendMessage("§aFlying is now disabled.");
                    }
                } else {
                    player.setAllowFlight(true);
                    if(returnName) {
                        commandSender.sendMessage("§aEnabled flying for " + playerName + ".");
                        player.sendMessage("§aFlying is now enabled.");
                    } else {
                        commandSender.sendMessage("§aFlying is now enabled.");
                    }
                }
            } else {
                commandSender.sendMessage("§cPlayer " + playerName + " is not online.");
            }
        } else {
            commandSender.sendMessage("§cYou're not permitted to use this command.");
        }
    }
}
