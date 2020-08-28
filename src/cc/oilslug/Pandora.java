package cc.oilslug;

import cc.oilslug.alerts.AlertRunnable;
import cc.oilslug.alerts.api.AlertManager;
import cc.oilslug.api.event.api.EventManager;
import cc.oilslug.commands.PandoraCommand;
import cc.oilslug.data.DataManager;
import cc.oilslug.handlers.BukkitEventHandler;
import cc.oilslug.handlers.PacketEventHandler;
import cc.oilslug.profiler.ProfilerUpdater;
import cc.oilslug.profiler.ProfilerManager;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

@Getter
public class Pandora extends JavaPlugin {

    private static Pandora instance;
    private DataManager dataManager;
    private AlertManager alertManager;
    private BukkitRunnable runnable;
    private ProfilerManager profilerManager;
    private EventManager eventManager;

    @Override
    public void onEnable() {
        instance = this;
        dataManager = new DataManager();
        alertManager = new AlertManager();
        eventManager = new EventManager();

        profilerManager = new ProfilerManager();
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new ProfilerUpdater(), 0, 20L);

        runnable = new AlertRunnable();
        new PacketEventHandler();
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, runnable, 0L, 1L);

        Bukkit.getPluginManager().registerEvents(new BukkitEventHandler(), this);

        getCommand("pandora").setExecutor(new PandoraCommand());
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
    }

    public static Pandora getInstance() {
        return instance;
    }
}
