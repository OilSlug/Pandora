package cc.oilslug.data.processors;

import cc.oilslug.utils.PlayerUtils;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@Getter
public class MovementProcessor {

    public int groundTicks, serverGroundTicks, airTicks, serverAirTicks, velocityTicks, liquidTicks, underBlockTicks, flyingTicks;
    private Location to, from, lastGroundLocation, lastServerGroundLocation;
    private boolean onGround, lastOnGround;
    private double deltaXZ, lastDeltaXZ;

    private Player player;

    public MovementProcessor(final Player player){
        this.player = player;
    }

    public void update(){

        lastOnGround = onGround;
        onGround = PlayerUtils.isOnGround(getPlayer());

        from = to;
        to = player.getLocation();

        lastDeltaXZ = deltaXZ;

        if(from != null){
            double X = Math.abs(to.getX() - from.getX());
            double Z = Math.abs(to.getZ() - from.getZ());
            deltaXZ = Math.sqrt((X * X) + (Z * Z));
        }

        if(getPlayer().isOnGround()){
            serverGroundTicks += serverGroundTicks < 20 ? 1 : 0;
            serverAirTicks = 0;
            lastServerGroundLocation = player.getLocation();
        }else{
            serverAirTicks += serverAirTicks < 20 ? 1 : 0;
            serverGroundTicks = 0;
        }

        if(onGround){
            groundTicks += groundTicks < 20 ? 1 : 0;
            airTicks = 0;
            lastGroundLocation = player.getLocation();
        }else{
            airTicks += airTicks < 20 ? 1 : 0;
            groundTicks = 0;
        }

    }

}
