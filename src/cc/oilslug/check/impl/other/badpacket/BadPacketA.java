package cc.oilslug.check.impl.other.badpacket;

import cc.oilslug.check.api.Check;
import cc.oilslug.check.api.CheckType;
import cc.oilslug.data.PlayerData;
import com.comphenix.protocol.events.PacketEvent;

public class BadPacketA extends Check {

    public BadPacketA(String name, CheckType checkType, boolean punishable, boolean lagBack, int maxVl) {
        super(name, checkType, punishable, lagBack, maxVl);
    }

    @Override
    public void handle(PlayerData data, PacketEvent event) {
        if(Math.abs(data.getPlayer().getLocation().getPitch()) > 90)flag(data);
    }
}
