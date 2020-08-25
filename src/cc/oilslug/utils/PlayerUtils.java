package cc.oilslug.utils;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class PlayerUtils {

    public static boolean isOnGround(Player player) {
        final BoundingBox boundingBox = new BoundingBox(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
        boundingBox.expand(0.5, 0.07, 0.5).move(0.0, -0.55, 0.0);

        final World world = player.getWorld();

        final boolean touchingAir = boundingBox.checkBlocks(world, material -> material == Material.AIR);
        final boolean touchingIllegalBlock = boundingBox.checkBlocks(world, material -> material.toString().contains("LILY") || material == Material.BREWING_STAND || material.toString().contains("SKULL"));

        if (touchingAir && !touchingIllegalBlock) {
            return false;
        } else {
            return true;
        }
    }

}
