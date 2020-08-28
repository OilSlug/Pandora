package cc.oilslug.handlers;

import cc.oilslug.Pandora;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class BukkitEventHandler implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Pandora.getInstance().getDataManager().addData(event.getPlayer());
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        Pandora.getInstance().getDataManager().removeData(event.getPlayer());
    }

}
