package cc.oilslug.check.api;

import cc.oilslug.Pandora;
import cc.oilslug.alerts.api.Alert;
import cc.oilslug.data.PlayerData;
import cc.oilslug.utils.ChatUtils;
import com.comphenix.protocol.events.PacketEvent;
import lombok.Getter;

@Getter
public class Check {

    private String name;
    private CheckType checkType;
    private boolean punishable, lagBack;
    protected double preVl;
    private int maxVl;

    public Check(String name, CheckType checkType, boolean punishable, boolean lagBack, int maxVl) {
        this.name = name;
        this.checkType = checkType;
        this.punishable = punishable;
        this.lagBack = lagBack;
        this.maxVl = maxVl;
    }

    public void flag(PlayerData data){
        data.addVerbose(this);
        Alert alert = new Alert(data.getPlayer(), name, data.getVerbose().get(this), maxVl);
        Pandora.getInstance().getAlertManager().addAlert(alert);
    }

    public void handle(final PlayerData data, final PacketEvent event){ }

    public void debug(PlayerData data, String message){
        data.getPlayer().sendMessage(ChatUtils.colour("&8[&dDebug&8] &7" + message));
    }
}
