package de.smileodon.plantimals.config;

public class MainConfig {
    private boolean onlyActiveWhenSneaking = true;
    private int requiredMoistureLevel = 1;

    private int ticksTillPigSpawns = 1200;
    private int ticksTillSheepSpawns = 1200;
    private int ticksTillRabbitSpawns = 1200;
    private int ticksTillChickenSpawns = 1200;
    private int ticksTillCowSpawns = 1200;

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
