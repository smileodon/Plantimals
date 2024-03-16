package de.smileodon.plantimals.manager;

import de.eldoria.eldoutilities.config.ConfigKey;
import de.eldoria.eldoutilities.config.JacksonConfig;
import de.smileodon.plantimals.PlantimalsPlugin;
import de.smileodon.plantimals.config.MainConfig;
import de.smileodon.plantimals.config.PlantimalsConfig;
import de.smileodon.plantimals.types.Plantimal;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public enum PlantimalsManager {
    INSTANCE;


    private PriorityQueue<Plantimal> plantimals = new PriorityQueue<>(Comparator.comparingInt(Plantimal::getTickToSpawn));

    private PlantimalsConfig plantimalsConfig;
    private MainConfig mainConfig;

    private JacksonConfig<MainConfig> jacksonConfig;
    private ConfigKey plantimalsConfigKey;

    public void loadConfigs(PlantimalsPlugin plugin) {

        JacksonConfig<MainConfig> config = new JacksonConfig<>(plugin, ConfigKey.defaultConfig(MainConfig.class, MainConfig::new));


        var plantimalsConfigConfigKey = ConfigKey.of("second", Path.of("plantimals.yml"), PlantimalsConfig.class, PlantimalsConfig::new);
        PlantimalsConfig plantimalsConfig = config.secondary(plantimalsConfigConfigKey);

        config.save();
        config.save(plantimalsConfigConfigKey);


        this.jacksonConfig = config;
        this.plantimalsConfig = plantimalsConfig;
        this.mainConfig = jacksonConfig.main();
        this.plantimalsConfigKey = plantimalsConfigConfigKey;
        loadDataFromConfig();
    }

    private void loadDataFromConfig() {
        plantimals.addAll(plantimalsConfig.getPlantimals());
    }

    public void addPlantable(Plantimal plantimal) {
        plantimals.add(plantimal);
        savePlantimalsConfig();
    }


    public void savePlantimalsConfig() {
        plantimalsConfig.setPlantimals(new ArrayList<>(plantimals));
        jacksonConfig.save(plantimalsConfigKey);
        System.out.println("Saving config");
    }

    public MainConfig getMainConfig() {
        return mainConfig;
    }

    public PriorityQueue<Plantimal> getPlantimals() {
        return plantimals;
    }
}
