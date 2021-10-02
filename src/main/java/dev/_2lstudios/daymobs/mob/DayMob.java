package dev._2lstudios.daymobs.mob;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

public class DayMob {
    private final EntityType entityType;
    private String customName = null;
    private ItemStack helmet = null;
    private ItemStack chestplate = null;
    private ItemStack leggings = null;
    private ItemStack boots = null;
    private ItemStack hand = null;

    public DayMob(final EntityType entityType) {
        this.entityType = entityType;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public DayMob setCustomName(final String customName) {
        this.customName = customName;

        return this;
    }

    public DayMob setHelmet(final ItemStack helmet) {
        this.helmet = helmet;

        return this;
    }

    public DayMob setChestplate(final ItemStack chestplate) {
        this.chestplate = chestplate;

        return this;
    }

    public DayMob setLeggings(final ItemStack leggings) {
        this.leggings = leggings;

        return this;
    }

    public DayMob setBoots(final ItemStack boots) {
        this.boots = boots;

        return this;
    }

    public DayMob setHand(final ItemStack hand) {
        this.hand = hand;

        return this;
    }

    public void applyEquipment(final LivingEntity livingEntity) {
        final EntityEquipment equipment = livingEntity.getEquipment();

        equipment.setHelmet(helmet);
        equipment.setChestplate(chestplate);
        equipment.setLeggings(leggings);
        equipment.setBoots(boots);
        equipment.setItemInHand(hand);
    }

    public void applyCustomName(final LivingEntity livingEntity) {
        livingEntity.setCustomName(customName);
    }

    public void apply(final LivingEntity livingEntity) {
        applyCustomName(livingEntity);
        applyEquipment(livingEntity);
    }

    public void apply(final Entity entity) {
        if (entity instanceof LivingEntity) {
            apply((LivingEntity) entity);
        }
    }
}
