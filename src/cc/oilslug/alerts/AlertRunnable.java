package cc.oilslug.alerts;

import cc.oilslug.Pandora;
import cc.oilslug.alerts.api.Alert;
import cc.oilslug.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class AlertRunnable extends BukkitRunnable {

    @Override
    public void run() {
        ArrayList<Alert> alertsToRemove = new ArrayList<>();
        Pandora.getInstance().getAlertManager().getAlerts().forEach(alert -> {
            alertsToRemove.add(alert);
            String alertMessage = "&8[&dPandora&8] &d" + alert.getPlayer().getName() + " &7failed &d" + alert.getCheckName() + " &8[&7VL: &d" + alert.getVerbose() + "&8/&d" + alert.getMaxVerbose() + "&8]";
            for(Player player : Bukkit.getOnlinePlayers()) {
                if (player.hasPermission("pandora.alerts")) {
                    player.sendMessage(ChatUtils.colour(alertMessage));
                }
            }
        });
        for(Alert alert : alertsToRemove){
            Pandora.getInstance().getAlertManager().getAlerts().remove(alert);
        }
    }
}
