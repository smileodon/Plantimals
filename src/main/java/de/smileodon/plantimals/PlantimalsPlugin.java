package de.smileodon.plantimals;

import de.smileodon.plantimals.listener.PlayerClickListener;
import de.smileodon.plantimals.manager.PlantableSpawnManager;
import de.smileodon.plantimals.manager.PlantimalsManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class PlantimalsPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        PlantimalsManager.INSTANCE.loadConfigs(this);
        Bukkit.getPluginManager().registerEvents(new PlayerClickListener(this), this);
        PlantableSpawnManager.getInstance(this).startSpawnScheduler();
    }


}
