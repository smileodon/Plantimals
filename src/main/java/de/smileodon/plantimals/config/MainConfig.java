package de.smileodon.plantimals.config;

public class MainConfig {
    private final boolean onlyActiveWhenSneaking = true;
    private final int requiredMoistureLevel = 1;

    private final int ticksTillPigSpawns = 1200;
    private final int ticksTillSheepSpawns = 1200;
    private final int ticksTillRabbitSpawns = 1200;
    private final int ticksTillChickenSpawns = 1200;
    private final int ticksTillCowSpawns = 1200;

    public MainConfig() {
    }

    public boolean isOnlyActiveWhenSneaking() {
        return onlyActiveWhenSneaking;
    }

    public int getTicksTillPigSpawns() {
        return ticksTillPigSpawns;
    }

    public int getTicksTillSheepSpawns() {
        return ticksTillSheepSpawns;
    }

    public int getTicksTillRabbitSpawns() {
        return ticksTillRabbitSpawns;
    }

    public int getTicksTillChickenSpawns() {
        return ticksTillChickenSpawns;
    }

    public int getTicksTillCowSpawns() {
        return ticksTillCowSpawns;
    }

    public int getRequiredMoistureLevel() {
        return requiredMoistureLevel;
    }

}
