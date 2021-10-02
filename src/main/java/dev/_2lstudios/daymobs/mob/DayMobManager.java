package dev._2lstudios.daymobs.mob;

import java.util.Collection;
import java.util.HashSet;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public class DayMobManager {
    private final Collection<DayMob> dayMobs = new HashSet<>();

    private DayMob addMob(final EntityType entityType) {
        final DayMob dayMob = new DayMob(entityType);

        dayMobs.add(dayMob);

        return dayMob;
    };

    public DayMobManager() {
        addMob(EntityType.ZOMBIE).setHelmet(new ItemStack(Material.LEATHER_HELMET));
        addMob(EntityType.ZOMBIE).setHelmet(new ItemStack(Material.LEATHER_HELMET))
                .setHand(new ItemStack(Material.STICK));
        addMob(EntityType.ZOMBIE).setHelmet(new ItemStack(Material.LEATHER_HELMET))
                .setHand(new ItemStack(Material.WOOD_SWORD));
        addMob(EntityType.ZOMBIE).setHelmet(new ItemStack(Material.GOLD_HELMET))
                .setHand(new ItemStack(Material.GOLD_SWORD));
        addMob(EntityType.ZOMBIE).setHelmet(new ItemStack(Material.GOLD_HELMET))
                .setHand(new ItemStack(Material.GOLD_AXE));
    }

    public static <T> T random(final Collection<T> coll) {
        int num = (int) (Math.random() * coll.size());
        for (final T t : coll)
            if (--num < 0)
                return t;
        throw new AssertionError();
    }

    public DayMob getRandomDayMob() {
        return random(dayMobs);
    }
}
