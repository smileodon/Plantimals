package config;

public class MainConfigFile {
    private boolean onlyActiveWhenSneaking = true;

    public MainConfigFile() {
    }

    public boolean isOnlyActiveWhenSneaking() {
        return onlyActiveWhenSneaking;
    }

    public void setOnlyActiveWhenSneaking(boolean onlyActiveWhenSneaking) {
        this.onlyActiveWhenSneaking = onlyActiveWhenSneaking;
    }
}
