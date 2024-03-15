package de.smileodon.plantimals.config;

import de.smileodon.plantimals.types.Plantimal;

import java.util.ArrayList;

public class PlantimalsConfig {
    private ArrayList<Plantimal> plantimals = new ArrayList<>();

    public ArrayList<Plantimal> getPlantimals() {
        return plantimals;
    }

    public void setPlantimals(ArrayList<Plantimal> plantimals) {
        this.plantimals = plantimals;
    }
}
