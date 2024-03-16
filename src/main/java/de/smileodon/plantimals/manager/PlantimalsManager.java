package de.smileodon.plantimals.manager;

import de.eldoria.eldoutilities.config.ConfigKey;
import de.eldoria.eldoutilities.config.JacksonConfig;
import de.smileodon.plantimals.PlantimalsPlugin;
import de.smileodon.plantimals.config.MainConfig;
import de.smileodon.plantimals.config.PlantimalsConfig;
import de.smileodon.plantimals.types.Plantimal;
import org.bukkit.Location;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public enum PlantimalsManager {
    INSTANCE;

    private final Map<Location, Plantimal> plantimalsByLocation = new HashMap<>();
    private final NavigableMap<LocalDateTime, Plantimal> plantimalsByTime = new TreeMap<>();

    private final ArrayList<Plantimal> plantimalsAsList = new ArrayList<>();

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
        plantimalsAsList.clear();
        System.out.println(plantimalsConfig);
        plantimalsAsList.addAll(plantimalsConfig.getPlantimals());
        setPlantableMaps(plantimalsConfig.getPlantimals());
    }

    private Map<Location, Plantimal> convertPlantableListToMapByLocation(ArrayList<Plantimal> plantables) {
        return plantables.stream()
                .collect(Collectors.toMap(Plantimal::getLocation, plantable -> plantable, (existing, replacement) -> existing, HashMap::new));
    }

    private NavigableMap<LocalDateTime, Plantimal> convertPlantableListToMapByTime(ArrayList<Plantimal> plantables) {
        return plantables.stream()
                .collect(Collectors.toMap(Plantimal::getTimeToSpawn, plantable -> plantable, (existing, replacement) -> existing, TreeMap::new));
    }

    public void setPlantableMaps(ArrayList<Plantimal> plantablesAsList) {
        // Clear existing entries
        plantimalsByLocation.clear();
        plantimalsByTime.clear();
        // Convert lists to maps and populate the final maps
        Map<Location, Plantimal> locationMap = convertPlantableListToMapByLocation(plantablesAsList);
        NavigableMap<LocalDateTime, Plantimal> timeMap = convertPlantableListToMapByTime(plantablesAsList);

        plantimalsByLocation.putAll(locationMap);
        plantimalsByTime.putAll(timeMap);
    }

    public void addPlantable(Plantimal plantimal) {
        System.out.println("Adding Plantimal: \n" + plantimal);
        plantimalsByLocation.put(plantimal.getLocation(), plantimal);
        plantimalsByTime.put(plantimal.getTimeToSpawn(), plantimal);
        plantimalsAsList.add(plantimal);
        savePlantimalsConfig();
    }

    public Plantimal getPlantableByLocation(Location location) {
        return plantimalsByLocation.get(location);
    }

    public Plantimal getPlantableByTime(LocalDateTime localDateTime) {
        return plantimalsByTime.get(localDateTime);
    }

    public boolean locationContainsPlantable(Location location) {
        return plantimalsByLocation.containsKey(location);
    }

    public NavigableMap<LocalDateTime, Plantimal> getPlantimalsThatSpawnThisMinute(LocalDateTime now) {
        System.out.println("List of plantimals: " + plantimalsByTime.size());
        long toleranceSeconds = 60; // 1-minute tolerance
        // Define the search range based on tolerance
        LocalDateTime lowerBound = now.minusSeconds(toleranceSeconds);
        LocalDateTime upperBound = now.plusSeconds(toleranceSeconds);
        return plantimalsByTime.subMap(lowerBound, true, upperBound, true);
    }

    public void removePlantable(Plantimal plantable) {
        plantimalsByLocation.remove(plantable.getLocation());
        plantimalsByTime.remove(plantable.getTimeToSpawn());
        plantimalsAsList.remove(plantable);
        savePlantimalsConfig();
    }

    // TODO: Fix bug with timestamp
    private void savePlantimalsConfig() {
        /*
        jacksonConfig.main().setPlantimals(plantimalsAsList);
        jacksonConfig.save();
        System.out.println("Saving config");

         */
    }

    public MainConfig getMainConfig() {
        return mainConfig;
    }


}
