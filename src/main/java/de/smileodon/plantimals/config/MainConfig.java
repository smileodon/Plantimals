package de.smileodon.plantimals.config;

public class MainConfig {
    private boolean onlyActiveWhenSneaking = true;
    private int requiredMoistureLevel = 1;

    private int secondsTillPigSpawns = 300;
    private int secondsTillSheepSpawns = 300;
    private int secondsTillRabbitSpawns = 300;
    private int secondsTillChickenSpawns = 300;
    private int secondsTillCowSpawns = 300;


    public MainConfig() {
    }

    public boolean isOnlyActiveWhenSneaking() {
        return onlyActiveWhenSneaking;
    }

    public void setOnlyActiveWhenSneaking(boolean onlyActiveWhenSneaking) {
        this.onlyActiveWhenSneaking = onlyActiveWhenSneaking;
    }

    public int getSecondsTillPigSpawns() {
        return secondsTillPigSpawns;
    }

    public int getSecondsTillSheepSpawns() {
        return secondsTillSheepSpawns;
    }

    public int getSecondsTillRabbitSpawns() {
        return secondsTillRabbitSpawns;
    }

    public int getSecondsTillChickenSpawns() {
        return secondsTillChickenSpawns;
    }

    public int getSecondsTillCowSpawns() {
        return secondsTillCowSpawns;
    }

    public int getRequiredMoistureLevel() {
        return requiredMoistureLevel;
    }
}
