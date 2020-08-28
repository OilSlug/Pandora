package cc.oilslug.commands;

import cc.oilslug.Pandora;
import cc.oilslug.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PandoraCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;

            if(args.length == 0){
                player.sendMessage("");
                player.sendMessage(ChatUtils.colour("&d/pandora performance &7- pulls pandora's performance."));
                player.sendMessage("");
            }else{
                if(args[0].equalsIgnoreCase("performance")){
                    player.sendMessage("");
                    player.sendMessage(ChatUtils.colour("&5&lPerformance"));
                    player.sendMessage("");
                    player.sendMessage(ChatUtils.colour("&5Packet Info&8:"));
                    player.sendMessage(ChatUtils.colour("&d- &7CallsPerPacketTime&8: &d" + Pandora.getInstance().getProfilerManager().callsTimePerPacket + "ms"));
                    player.sendMessage(ChatUtils.colour("&d- &7CallsPerPersonTime&8: &d" + (Pandora.getInstance().getProfilerManager().callsTimePerPacket/Pandora.getInstance().getProfilerManager().players) + "ms"));
                    player.sendMessage("");
                    player.sendMessage(ChatUtils.colour("&5Server Stats:"));
                    player.sendMessage(ChatUtils.colour("&d- &7Players&8: &d" + Pandora.getInstance().getProfilerManager().players));
                    player.sendMessage(ChatUtils.colour("&d- &7MaxPlayers&8: &d" + Bukkit.getServer().getMaxPlayers()));
                    player.sendMessage(ChatUtils.colour("&d- &7TPS&8: &dnot done"));
                    player.sendMessage("");
                }
            }

        }

        return true;
    }
}
