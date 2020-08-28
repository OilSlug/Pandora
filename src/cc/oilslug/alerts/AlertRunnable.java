package cc.oilslug.alerts;

import cc.oilslug.Pandora;
import cc.oilslug.alerts.api.Alert;
import cc.oilslug.api.event.impl.FlagEvent;
import cc.oilslug.check.api.CheckManager;
import cc.oilslug.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class AlertRunnable extends BukkitRunnable {

    @Override
    public void run() {
        if(Pandora.getInstance().getAlertManager().getAlerts().size() > 0) {
            ArrayList<Alert> alertsToRemove = new ArrayList<>();
            Pandora.getInstance().getAlertManager().getAlerts().forEach(alert -> {
                alertsToRemove.add(alert);
                FlagEvent flagEvent = new FlagEvent(alert.getCheckName(), alert.getPlayer(), alert.getVerbose());
                Pandora.getInstance().getEventManager().callEvent(flagEvent);
                if(flagEvent.isCanceled())return;
                if(alert.getVerbose() % alert.getMaxVerbose() == 0 && alert.getVerbose() > 0 && CheckManager.isPunishable(alert.getCheckName())){
                    Bukkit.broadcastMessage(ChatUtils.colour("&8[&dPandora&8] &d" + alert.getPlayer().getName() + " &7was removed from the server!"));
                }else {
                    String alertMessage = "&8[&dPandora&8] &d" + alert.getPlayer().getName() + " &7failed &d" + alert.getCheckName() + " &8[&7VL: &d" + alert.getVerbose() + "&8/&d" + alert.getMaxVerbose() + "&8]";
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        if (player.hasPermission("pandora.alerts")) {
                            player.sendMessage(ChatUtils.colour(alertMessage));
                        }
                    }
                    if(CheckManager.canLagBack(alert.getCheckName())){
                        //TODO LAG BACK
                    }
                }
            });
            for (Alert alert : alertsToRemove) {
                Pandora.getInstance().getAlertManager().getAlerts().remove(alert);
            }
        }
    }
}
