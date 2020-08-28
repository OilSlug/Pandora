package cc.oilslug.check.impl.movement.fly;

import cc.oilslug.check.api.Check;
import cc.oilslug.check.api.CheckType;
import cc.oilslug.data.PlayerData;
import cc.oilslug.utils.MathUtils;
import com.comphenix.protocol.events.PacketEvent;

public class FlyA extends Check {

    public FlyA(String name, CheckType checkType, boolean punishable, boolean lagBack, int maxVl) {
        super(name, checkType, punishable, lagBack, maxVl);
    }

    @Override
    public void handle(PlayerData data, PacketEvent event) {
        if(data.getMovementProcessor().getLastGroundLocation() == null || data.getMovementProcessor().getTo() == null)return;
        double distanceXZ = MathUtils.getDistanceXZ(data.getMovementProcessor().getTo(), data.getMovementProcessor().getLastGroundLocation());
        double distanceY = MathUtils.getDistanceY(data.getMovementProcessor().getTo(), data.getMovementProcessor().getLastGroundLocation());
        if(data.getMovementProcessor().getVelocityTicks() > 0 || data.getMovementProcessor().getFlyingTicks() > 0)return;
        if(((distanceXZ > 5 && distanceY > -3) || distanceY > 1.3) && !data.getMovementProcessor().isOnGround()){
            if(preVl++ > 3) {
                flag(data);
            }
        }else preVl -= Math.max(0, preVl-1);

    }
}
