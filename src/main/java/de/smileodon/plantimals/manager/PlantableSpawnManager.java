package de.smileodon.plantimals.manager;

import de.smileodon.plantimals.PlantimalsPlugin;
import de.smileodon.plantimals.types.Plantimal;
import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.PriorityQueue;

public enum PlantableSpawnManager {
    INSTANCE;
    private PlantimalsPlugin plugin;

    public void setPlugin(PlantimalsPlugin plugin) {
        this.plugin = plugin;
    }

    public void startSpawnScheduler() {
        new BukkitRunnable() {
            @Override
            public void run() {
                checkAndSpawnPlantimals();
            }
        }.runTaskTimer(plugin, 0L, 1L);
    }

    private void checkAndSpawnPlantimals() {
        int currentTick = plugin.getServer().getCurrentTick();
        PriorityQueue<Plantimal> plantimals = PlantimalsManager.INSTANCE.getPlantimals();
        boolean somethingRemoved = false;
        while (!plantimals.isEmpty() && plantimals.peek().getTickToSpawn() <= currentTick) {
            Plantimal plantimal = plantimals.remove();
            plantimal.spawnEntity();
            // Remove ArmorStand
            ArmorStand armorStand = plantimal.getArmorstand();
            if (armorStand != null) {
                armorStand.remove();
            }
            somethingRemoved = true;
        }
        if (somethingRemoved) {
            PlantimalsManager.INSTANCE.savePlantimalsConfig();
        }
    }


}
