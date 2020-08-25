package cc.oilslug.profiler;

import cc.oilslug.Pandora;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class ProfilerUpdater extends BukkitRunnable {

    @Override
    public void run() {
        Pandora.getInstance().getProfilerManager().players = Bukkit.getOnlinePlayers().size();

        //Dividing by 1000000 because there is 1000000 nano seconds in a millisecond
        double average = (Pandora.getInstance().getProfilerManager().callAverageTime/Pandora.getInstance().getProfilerManager().callsPerSecond) / 1000000;

        Pandora.getInstance().getProfilerManager().callsTimePerPacket = average;
        Pandora.getInstance().getProfilerManager().lastcallsPerSecond = Pandora.getInstance().getProfilerManager().callsPerSecond;

        Pandora.getInstance().getProfilerManager().callsPerSecond = 0;
        Pandora.getInstance().getProfilerManager().callAverageTime = 0;
    }
}
