package cc.oilslug.api.event.impl;

import cc.oilslug.api.event.api.Event;
import lombok.Getter;
import org.bukkit.entity.Player;

@Getter
public class FlagEvent extends Event {

    private String checkName;
    private Player player;
    private int verbose;

    public FlagEvent(String checkName, Player player, int verbose) {
        this.checkName = checkName;
        this.player = player;
        this.verbose = verbose;
        eventCalledTime = System.currentTimeMillis();
        eventID = 1;
    }
}
