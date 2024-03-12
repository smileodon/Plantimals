import config.MainConfigFile;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import de.eldoria.eldoutilities.config.JacksonConfig;

public class PlantimalsPlugin extends JavaPlugin {
    private JacksonConfig<MainConfigFile> config;

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new PlayerClickListener(), this);
    }
}
