package dev._2lstudios.daymobs;

import org.bukkit.plugin.java.JavaPlugin;

import dev._2lstudios.daymobs.commands.ExampleCommand;
import dev._2lstudios.daymobs.listeners.PlayerJoinListener;
import dev._2lstudios.daymobs.tasks.ExampleTask;

public class DayMobs extends JavaPlugin {
    
    @Override
    public void onEnable () {
        // Save default config
        this.saveDefaultConfig();

        // Set static instance
        DayMobs.instance = this;

        // Register the example command
        this.getCommand("example").setExecutor(new ExampleCommand());
        
        // Register the example listener
        this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);

        // Register the example task
        final long taskRepeatEvery = this.getConfig().getInt("task-repeat-every") * 20L;
        this.getServer().getScheduler().runTaskTimer(this, new ExampleTask(), taskRepeatEvery, taskRepeatEvery);
    }

    private static DayMobs instance;

    public static DayMobs getInstance () {
        return DayMobs.instance;
    }
}