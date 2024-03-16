package de.smileodon.plantimals.types;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import java.time.LocalDateTime;
import java.util.UUID;

public class Plantimal {
    private final PlantimalType plantimalType;
    private final Location location;
    private final LocalDateTime timeToSpawn;

    private final UUID armorStandUUID;


    public Plantimal(PlantimalType plantimalType, Location location, LocalDateTime timeToSpawn, UUID armorStandUUID) {
        this.plantimalType = plantimalType;
        this.location = location;
        this.timeToSpawn = timeToSpawn;
        this.armorStandUUID = armorStandUUID;
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

    public UUID getArmorStandUUID() {
        return armorStandUUID;
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
