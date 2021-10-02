package dev._2lstudios.daymobs.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import dev._2lstudios.daymobs.DayMobs;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin (final PlayerJoinEvent event) {
        final String message = DayMobs.getInstance().getConfig().getString("messages.from-listener");
        event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
}
