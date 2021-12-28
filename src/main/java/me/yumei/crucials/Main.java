package me.yumei.crucials;

import me.yumei.crucials.commands.CommandMsg;
import me.yumei.crucials.web.WebServer;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import me.yumei.crucials.commands.CommandFly;
import me.yumei.crucials.events.EventPlayerChat;

import java.io.File;
import java.io.IOException;

public class Main extends JavaPlugin {

    private File rolesConfigFile;

    @Override
    public void onEnable() {
        // Config
        createRolesConfig();
        // Commands
        this.getCommand("fly").setExecutor(new CommandFly());
        this.getCommand("msg").setExecutor(new CommandMsg());
        // Events
        Bukkit.getPluginManager().registerEvents(new EventPlayerChat(), this);
        // Web server
        try {
            WebServer.setupServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void createRolesConfig() {
        rolesConfigFile = new File(getDataFolder(), "roles.yml");
        if(!rolesConfigFile.exists()) {
            rolesConfigFile.getParentFile().mkdirs();
            saveResource("roles.yml", false);
        }
        Config.rolesConfig = new YamlConfiguration();
        try {
            Config.rolesConfig.load(rolesConfigFile);
        } catch (IOException | InvalidConfigurationException error) {
            error.printStackTrace();
        }
    }
}
