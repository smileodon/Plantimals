package de.smileodon.plantimals.types;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

public enum PlantimalType {
    PIG,
    SHEEP,
    RABBIT,
    CHICKEN,
    COW;

    public static PlantimalType fromMaterial(Material material) {
        return switch (material) {
            case PORKCHOP -> PIG;
            case MUTTON -> SHEEP;
            case RABBIT -> RABBIT;
            case CHICKEN -> CHICKEN;
            case BEEF -> COW;
            default -> throw new IllegalArgumentException("Unexpected Material: " + material.name());
        };
    }



    public EntityType getEntityType() {
        return switch (this) {
            case PIG -> EntityType.PIG;
            case SHEEP -> EntityType.SHEEP;
            case RABBIT -> EntityType.RABBIT;
            case CHICKEN -> EntityType.CHICKEN;
            case COW -> EntityType.COW;
            default -> throw new IllegalArgumentException("Unknown PlantableType: " + this);
        };
    }

    public Material getMaterial() {
        return switch (this) {
            case PIG -> Material.PORKCHOP;
            case SHEEP -> Material.MUTTON;
            case RABBIT -> Material.RABBIT;
            case CHICKEN -> Material.CHICKEN;
            case COW -> Material.BEEF;
            default -> throw new IllegalArgumentException("Unknown PlantableType: " + this);
        };
    }
}
