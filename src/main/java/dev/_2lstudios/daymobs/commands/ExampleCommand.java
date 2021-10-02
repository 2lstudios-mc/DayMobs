package dev._2lstudios.daymobs.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import dev._2lstudios.daymobs.DayMobs;

public class ExampleCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        final String message = DayMobs.getInstance().getConfig().getString("messages.from-command");
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        return true;
    }
}
