package cc.oilslug.check.impl.movement.speed;

import cc.oilslug.check.api.Check;
import cc.oilslug.check.api.CheckType;
import cc.oilslug.data.PlayerData;
import com.comphenix.protocol.events.PacketEvent;

public class SpeedA extends Check {

    public SpeedA(String name, CheckType checkType, boolean punishable, boolean lagBack, int maxVl) {
        super(name, checkType, punishable, lagBack, maxVl);
    }

    @Override
    public void handle(PlayerData data, PacketEvent event) {
        if(data.getMovementProcessor().getVelocityTicks() > 0 || data.getMovementProcessor().getFlyingTicks() > 0)return;

        double motionXZ = data.getMovementProcessor().getDeltaXZ();
        
        double maxSpeed = data.getMovementProcessor().getServerGroundTicks() > 2 ? 0.288 : 0.34;

        maxSpeed += ((data.getPlayer().getWalkSpeed()-0.2) / 0.2) * 0.4f;
        //maxSpeed += data.getMovementProcessor().getVelocityTicks() > 0 ? 0.5 : 0;
        maxSpeed += data.getMovementProcessor().getIceTicks() > 0 ? 0.4 : 0;

        if(motionXZ > maxSpeed) {
            if (preVl++ > 5) {
                flag(data);
            }
        }else preVl = Math.max(0, preVl-1);
    }


}
