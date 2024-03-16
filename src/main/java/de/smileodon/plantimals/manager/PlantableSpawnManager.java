package de.smileodon.plantimals.manager;

import de.smileodon.plantimals.PlantimalsPlugin;
import de.smileodon.plantimals.types.Plantimal;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.NavigableMap;

public class PlantableSpawnManager {
    private volatile static PlantableSpawnManager instance;
    private final PlantimalsPlugin plugin;

    private PlantableSpawnManager(PlantimalsPlugin plugin) {
        this.plugin = plugin;
    }

    // Static method to get the instance of the class (Singleton) with thread-safe double-checked locking
    public static PlantableSpawnManager getInstance(PlantimalsPlugin plugin) {
        if (instance == null) {
            synchronized (PlantableSpawnManager.class) {
                if (instance == null) {
                    instance = new PlantableSpawnManager(plugin);
                }
            }
        }
        return instance;
    }

    public void startSpawnScheduler() {
        // Schedule this to run asynchronously every minute
        new BukkitRunnable() {
            @Override
            public void run() {
                checkAndSpawnPlantimals();
            }
        }.runTaskTimerAsynchronously(plugin, 0L, 20L * 60); // 20 ticks * 60 seconds = 1 minute
    }

    private void checkAndSpawnPlantimals() {
        LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        // Use PlantableManager to get plantimal due to spawn this minute
        NavigableMap<LocalDateTime, Plantimal> duePlantimals = PlantimalsManager.INSTANCE.getPlantimalsThatSpawnThisMinute(now);
        duePlantimals.forEach((time, plantimal) -> {
            new BukkitRunnable() {
                @Override
                public void run() {
                    // This code runs on the main thread
                    spawnEntity(plantimal.getLocation(), plantimal.getEntityType());
                    // Remove ArmorStand
                    if (plantimal.getArmorStand() != null) {
                        plantimal.getArmorStand().remove();
                    }
                    // Remove plantimal after spawning to ensure thread-safe operation,
                    // it's executed on the main thread to ensure consistency with Bukkit's single-thread model
                    PlantimalsManager.INSTANCE.removePlantable(plantimal);
                }
            }.runTask(plugin);
        });
    }

    private void spawnEntity(Location location, EntityType entityType) {
        // Ensure this method is called on the main thread
        if (!Bukkit.isPrimaryThread()) {
            throw new IllegalStateException("spawnEntity must be called from the main thread");
        }
        location.getWorld().spawnEntity(location, entityType);
    }
}
