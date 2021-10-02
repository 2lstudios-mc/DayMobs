package dev._2lstudios.daymobs;

import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

import dev._2lstudios.daymobs.commands.DayMobsCommand;
import dev._2lstudios.daymobs.listeners.PlayerJoinListener;
import dev._2lstudios.daymobs.mob.DayMobManager;
import dev._2lstudios.daymobs.tasks.SpawnerTask;

public class DayMobs extends JavaPlugin {
    private static DayMobs instance;

    public static DayMobs getInstance () {
        return DayMobs.instance;
    }
    
    @Override
    public void onEnable () {
        final Server server = getServer();
        final DayMobManager dayMobManager = new DayMobManager();

        DayMobs.instance = this;

        getCommand("daymobs").setExecutor(new DayMobsCommand());
        
        server.getPluginManager().registerEvents(new PlayerJoinListener(), this);

        server.getScheduler().runTaskTimer(this, new SpawnerTask(server, dayMobManager), 600L, 600L);
    }
}