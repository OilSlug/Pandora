package cc.oilslug;

import cc.oilslug.alerts.AlertRunnable;
import cc.oilslug.alerts.api.AlertManager;
import cc.oilslug.commands.PandoraCommand;
import cc.oilslug.data.DataManager;
import cc.oilslug.handlers.PacketEventHandler;
import cc.oilslug.profiler.ProfilerUpdater;
import cc.oilslug.profiler.ProfilerManager;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Pandora extends JavaPlugin {

    @Getter
    private static Pandora instance;
    @Getter
    private DataManager dataManager;
    @Getter
    private AlertManager alertManager;
    @Getter
    private BukkitRunnable runnable;

    @Getter
    private ProfilerManager profilerManager;

    @Override
    public void onEnable() {
        instance = this;
        dataManager = new DataManager();
        alertManager = new AlertManager();

        profilerManager = new ProfilerManager();
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new ProfilerUpdater(), 0, 20L);

        runnable = new AlertRunnable();
        new PacketEventHandler();
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, runnable, 0L, 1L);

        getCommand("pandora").setExecutor(new PandoraCommand());
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
    }
}
