package cc.oilslug.data.processors;

import cc.oilslug.utils.BoundingBox;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.List;

@Getter
public class MovementProcessor {

    /**
     * TODO update velocity Ticks.
     * TODO update liquid Ticks.
     */

    public int groundTicks, serverGroundTicks, airTicks, serverAirTicks, velocityTicks, liquidTicks, iceTicks, slimeTicks, underBlockTicks, flyingTicks;
    private Location to, from, lastGroundLocation, lastServerGroundLocation;
    private boolean onGround, lastOnGround;
    private double deltaXZ, lastDeltaXZ;

    private Player player;

    public MovementProcessor(final Player player){
        this.player = player;
    }

    public void update(){

        /**
         * Getting every block that the player is colided with
         */

        final BoundingBox boundingBox = new BoundingBox(getPlayer().getLocation().getX(), getPlayer().getLocation().getY(), getPlayer().getLocation().getZ());
        boundingBox.expand(0.5, 0.07, 0.5).move(0.0, -0.55, 0.0);
        final World world = getPlayer().getWorld();
        String colided = "";
        List<Block> colidedBlocks = boundingBox.getColided(world);
        for(Block b : colidedBlocks){
            colided += b.getType().toString() + " ";
        }

        /**
         * Updating players data
         */

        lastOnGround = onGround;
        onGround = colidedBlocks.stream().noneMatch(block -> block.getType() == Material.AIR);

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

        if(colided.contains("ICE")){
            iceTicks = 20;
        }else{
            iceTicks -= iceTicks > 0 ? 1 : 0;
        }

        if(colided.contains("SLIME")){
            slimeTicks = 20;
        }else{
            slimeTicks -= slimeTicks > 0 ? 1 : 0;
        }

        if(onGround){
            groundTicks += groundTicks < 20 ? 1 : 0;
            airTicks = 0;
            lastGroundLocation = player.getLocation();
            liquidTicks = 0;

        }else{
            airTicks += airTicks < 20 ? 1 : 0;
            groundTicks = 0;
            iceTicks -= iceTicks > 0 ? 1 : 0;

        }

        if(player.isFlying()){
            flyingTicks = 20;
        }else flyingTicks -= Math.max(0, flyingTicks-1);

    }

}
