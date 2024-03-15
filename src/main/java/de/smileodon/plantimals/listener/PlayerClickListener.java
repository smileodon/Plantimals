package de.smileodon.plantimals.listener;

import de.smileodon.plantimals.PlantimalsPlugin;
import de.smileodon.plantimals.config.MainConfig;
import de.smileodon.plantimals.manager.PlantimalsManager;
import de.smileodon.plantimals.types.Plantimal;
import de.smileodon.plantimals.types.PlantimalType;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Farmland;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.LocalDateTime;
import java.util.Objects;

public class PlayerClickListener implements Listener {
    private final MainConfig config;
    private static PlantimalsPlugin plugin = null;

    public PlayerClickListener(PlantimalsPlugin plugin) {
        PlayerClickListener.plugin = plugin;
        this.config = PlantimalsManager.INSTANCE.getMainConfig();
    }

    @EventHandler
    public void onPlayerClick(PlayerInteractEvent event) {
        if (Objects.equals(event.getHand(), EquipmentSlot.HAND)) { // Check only main hand
            if (event.getAction().isRightClick()) {
                Player player = event.getPlayer();
                if (config.isOnlyActiveWhenSneaking() && !player.isSneaking()) {
                    return;
                }
                if (player.hasPermission("plantimals.use")) {
                    Block clickedBlock = event.getClickedBlock();
                    if (clickedBlock != null) {
                        if (clickedBlock.getType().equals(Material.FARMLAND)) {
                            BlockData blockData = clickedBlock.getBlockData();
                            Farmland farmland = (Farmland) blockData;
                            if (farmland.getMoisture() >= config.getRequiredMoistureLevel()) {
                                ItemStack itemStack = player.getInventory().getItemInMainHand();
                                Material material = itemStack.getType();
                                if (material == Material.PORKCHOP || material == Material.MUTTON || material == Material.RABBIT ||
                                        material == Material.CHICKEN || material == Material.BEEF) {
                                    event.setCancelled(true);
                                    System.out.println("Planting new Plantimal...");
                                    PlantimalType plantimalType = PlantimalType.fromMaterial(material);
                                    LocalDateTime timeToSpawn = addSecondsToTimestamp(LocalDateTime.now(), getSecondsTillSpawnFromConfig(plantimalType));
                                    Location location = clickedBlock.getLocation();
                                    Location plantimalSpawnLocation = new Location(location.getWorld(), location.getX(), location.getY() + 1, location.getZ());
                                    Location armorStandSpawnLocation = new Location(location.getWorld(), location.getX() + 0.5, location.getY() + 0.03, location.getZ());
                                    ArmorStand armorStand = spawnFlatItem(armorStandSpawnLocation, plantimalType.getMaterial());
                                    PlantimalsManager.INSTANCE.addPlantable(
                                            new Plantimal(plantimalType, plantimalSpawnLocation, timeToSpawn, armorStand)
                                    );
                                    // TODO spawn armor stand

                                    if (player.getGameMode() != GameMode.CREATIVE) {
                                        itemStack.setAmount(itemStack.getAmount() - 1);
                                        player.getInventory().setItemInMainHand(itemStack);
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }
    }

    private int getSecondsTillSpawnFromConfig(PlantimalType plantimalType) {
        switch (plantimalType) {
            case PIG -> {
                return config.getSecondsTillPigSpawns();
            }
            case SHEEP -> {
                return config.getSecondsTillSheepSpawns();
            }
            case RABBIT -> {
                return config.getSecondsTillRabbitSpawns();
            }
            case CHICKEN -> {
                return config.getSecondsTillChickenSpawns();
            }
            case COW -> {
                return config.getSecondsTillCowSpawns();
            }
            default -> {
                return 300;
            }
        }
    }

    private LocalDateTime addSecondsToTimestamp(LocalDateTime timestamp, long seconds) {
        return timestamp.plusSeconds(seconds);
    }

    public static ArmorStand spawnFlatItem(Location location, Material material) {
        World world = location.getWorld();
        if (world == null) return null; // Always check that the world is not null
        // Using this method to set invisibility faster
        return world.spawn(location, ArmorStand.class, armorStand -> {
            armorStand.setVisible(false); // Make the armor stand invisible
            armorStand.setHeadPose(armorStand.getHeadPose().setX(Math.toRadians(90))); // Rotate to make the item flat
            armorStand.setGravity(false); // Prevent the armor stand from falling
            armorStand.setCanPickupItems(false); // Prevent the armor stand from picking up items
            armorStand.setMarker(true); // Reduce the hitbox to virtually nothing
            armorStand.setBasePlate(false); // Remove the base plate to reduce visibility
            armorStand.setSmall(true); // Make the armor stand small

            // Delay setting the item by scheduling a task because otherwise the item is seeing rotating for a moment
            plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
                armorStand.getEquipment().setHelmet(new ItemStack(material, 1));
            }, 4L); // Delayed by 4 tick
            //armorStand.setHelmet(new ItemStack(material, 1)); // Set the item as the armor stand's head
        });
    }

}