package de.smileodon.plantimals.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.UUID;

public class Plantimal {
    private final PlantimalType plantimalType;
    private final Location location;
    private final int tickToSpawn;

    private final UUID armorStandUUID;


    @JsonCreator
    public Plantimal(@JsonProperty("plantimalType") PlantimalType plantimalType, @JsonProperty("location") Location location, @JsonProperty("timeToSpawn") int tickToSpawn, @JsonProperty("armorStandUUID") UUID armorStandUUID) {
        this.plantimalType = plantimalType;
        this.location = location;
        this.tickToSpawn = tickToSpawn;
        this.armorStandUUID = armorStandUUID;
    }


    public Location getLocation() {
        return location;
    }

    public int getTickToSpawn() {
        return tickToSpawn;
    }

    public EntityType getEntityType() {
        return plantimalType.getEntityType();
    }

    public UUID getArmorStandUUID() {
        return armorStandUUID;
    }

    public void spawnEntity() {
        location.getWorld().spawnEntity(location, getEntityType());
    }

    public ArmorStand getArmorstand() {
        // Search for entities within the radius
        for (Entity entity : location.getWorld().getNearbyEntities(location, 1, 1, 1)) {
            // Check if the entity is wanted ArmorStand
            if (entity.getUniqueId().equals(armorStandUUID)) {
                return (ArmorStand) entity;
            }
        }
        System.out.println("Armorstand not found");
        return null;
    }
}
