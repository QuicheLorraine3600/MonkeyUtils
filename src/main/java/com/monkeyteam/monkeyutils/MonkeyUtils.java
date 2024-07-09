package com.monkeyteam.monkeyutils;

import com.monkeyteam.monkeyutils.modules.*;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class MonkeyUtils extends JavaPlugin {

    private static MonkeyUtils instance;

    FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        // Events
        getServer().getPluginManager().registerEvents(new CropsBreak(), this);
        getServer().getPluginManager().registerEvents(new Back(), this);
        getServer().getPluginManager().registerEvents(new MOTD(), this);
        getServer().getPluginManager().registerEvents(new Cat(), this);
        getServer().getPluginManager().registerEvents(new Whitelist(), this);

        // Commands
        this.getCommand("openinventory").setExecutor(new OpenInventory());
        this.getCommand("back").setExecutor(new Back());
        this.getCommand("home").setExecutor(new Home.HomeCommand());
        this.getCommand("sethome").setExecutor(new Home.SethomeCommand());
        this.getCommand("monkeyreload").setExecutor(new Reload());
        this.getCommand("whitelist").setExecutor(new Whitelist());

        Bukkit.getLogger().fine("Ready to monkeying !");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static MonkeyUtils getInstance() {
        return instance;
    }
}
