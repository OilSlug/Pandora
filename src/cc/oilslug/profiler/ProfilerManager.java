package cc.oilslug.profiler;

import cc.oilslug.check.api.CheckManager;

public class ProfilerManager {

    public double ticksPerSecond, callsPerSecond, callAverageTime;
    public long callStart;
    public int checks, players;

    public double callsTimePerPacket, lastcallsPerSecond;


    public ProfilerManager(){
        checks = CheckManager.getChecks().size();
        players = 0;
    }

}
