package de.smileodon.plantimals.types;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

import java.time.LocalDateTime;

public class Plantimal {
    private final PlantimalType plantimalType;
    private final Location location;
    private final LocalDateTime timeToSpawn;

    private final ArmorStand armorStand;


    public Plantimal(PlantimalType plantimalType, Location location, LocalDateTime timeToSpawn, ArmorStand armorStand) {
        this.plantimalType = plantimalType;
        this.location = location;
        this.timeToSpawn = timeToSpawn;
        this.armorStand = armorStand;
    }


    public Location getLocation() {
        return location;
    }

    public LocalDateTime getTimeToSpawn() {
        return timeToSpawn;
    }

    public EntityType getEntityType() {
        return plantimalType.getEntityType();
    }

    public ArmorStand getArmorStand() {
        return armorStand;
    }

    @Override
    public String toString() {
        return "Plantimal{" +
                "plantimalType=" + plantimalType +
                ", location=" + location +
                ", timeToSpawn=" + timeToSpawn +
                '}';
    }
}
