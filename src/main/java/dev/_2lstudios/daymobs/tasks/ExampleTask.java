package dev._2lstudios.daymobs.tasks;

import org.bukkit.Bukkit;

import dev._2lstudios.daymobs.DayMobs;

public class ExampleTask implements Runnable {
    @Override
    public void run() {
        final String message = DayMobs.getInstance().getConfig().getString("messages.from-task");
        Bukkit.getServer().broadcastMessage(message);
    }
}