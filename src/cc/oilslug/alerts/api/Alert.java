package cc.oilslug.alerts.api;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

@Getter @Setter
public class Alert {

    private Player player;
    private String checkName;
    private int verbose, maxVerbose;
    private long timeStamp;

    public Alert(Player player, String checkName, int verbose, int maxVerbose) {
        this.player = player;
        this.checkName = checkName;
        this.verbose = verbose;
        this.maxVerbose = maxVerbose;
        timeStamp = System.currentTimeMillis();
    }
}
