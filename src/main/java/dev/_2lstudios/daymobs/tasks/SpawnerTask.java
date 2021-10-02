package dev._2lstudios.daymobs.tasks;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import dev._2lstudios.daymobs.mob.DayMob;
import dev._2lstudios.daymobs.mob.DayMobManager;

public class SpawnerTask implements Runnable {
    private final static int MIN_SPAWN_DISTANCE = 16;
    private final static int MAX_SPAWN_DISTANCE = 48;
    
    private final Server server;
    private final DayMobManager dayMobManager;

    public SpawnerTask(final Server server, final DayMobManager dayMobManager) {
        this.server = server;
        this.dayMobManager = dayMobManager;
    }

    private boolean isDay(final long time) {
        return time >= 0 && time <= 12000;
    }

    private double getRandom(final double min, final double max) {
        return Math.floor(Math.random() * (max - min + 1) + min);
    }

    private double getRandomRadians() {
        return getRandom(0, 6.3);
    }

    private int getRandomDistance() {
        final int randomDistance = (int) getRandom(MIN_SPAWN_DISTANCE, MAX_SPAWN_DISTANCE);

        return new Random().nextBoolean() ? randomDistance : -randomDistance;
    }

    private boolean isSafe(final Location location) {
        final World world = location.getWorld();
        final int x = (int) location.getX();
        final int y = (int) location.getY();
        final int z = (int) location.getZ();

        return world.getBlockAt(x, y - 1, z).getType() == Material.GRASS && !world.getBlockAt(x, y, z).getType().isSolid() && !world.getBlockAt(x, y + 1, z).getType().isSolid();
    }

    private Location getSafeLocation(final Location location) {
        if (isSafe(location)) {
            return location;
        }

        for (int y = -5; y < 5; y++) {
            final Location locationClone = location.clone().add(0, y, 0);

            if (isSafe(locationClone)) {
                return locationClone;
            }
        }

        return null;
    }

    private Entity spawnRandomMob(final Location location) {
        final Location safeLocation = getSafeLocation(location);

        if (safeLocation != null) {
            final int distance = getRandomDistance();
            final double radians = getRandomRadians();
            final int x = (int) (location.getX() + Math.cos(radians) * distance);
            final int y = (int) location.getY();
            final int z = (int) (location.getZ() + Math.sin(radians) * distance);
            final DayMob dayMob = dayMobManager.getRandomDayMob();
            final Entity entity = location.getWorld().spawnEntity(new Location(location.getWorld(), x, y, z), dayMob.getEntityType());

            dayMob.apply(entity);

            return entity;
        }

        return null;
    }

    @Override
    public void run() {
        for (final World world : server.getWorlds()) {
            if (world.getEnvironment() == Environment.NORMAL) {
                if (isDay(world.getTime())) {
                    for (final Player player : world.getPlayers()) {
                        spawnRandomMob(player.getLocation());
                    }
                }
            }
        }
    }
}