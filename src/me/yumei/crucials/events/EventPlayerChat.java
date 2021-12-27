package me.yumei.crucials.events;

import me.yumei.crucials.Config;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class EventPlayerChat implements Listener {
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        event.setCancelled(true);

        boolean sentMessage = false;

        ConfigurationSection rolesSection = Config.rolesConfig.getConfigurationSection("roles");
        for (String key : rolesSection.getKeys(false)) {
            if(player.hasPermission(rolesSection.getString(key + ".permission"))) {
                Bukkit.broadcastMessage(rolesSection.getString(key + ".chatformat")
                        .replace("%player%", player.getName())
                        .replace("%message%", event.getMessage()));
                sentMessage = true;
                break;
            }
        }
        if(!sentMessage) {
            Bukkit.broadcastMessage(Config.rolesConfig.getString("chatformat")
                    .replace("%player%", player.getName())
                    .replace("%message%", event.getMessage()));
        }
    }
}
