package cc.oilslug.handlers;

import cc.oilslug.Pandora;
import cc.oilslug.data.PlayerData;
import cc.oilslug.utils.PacketUtil;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.Bukkit;

public class PacketEventHandler {

    public PacketEventHandler(){
        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(Pandora.getInstance(),
                PacketType.Play.Client.FLYING, PacketType.Play.Client.POSITION, PacketType.Play.Client.POSITION_LOOK, PacketType.Play.Client.LOOK,
                PacketType.Play.Client.USE_ENTITY, PacketType.Play.Client.ARM_ANIMATION, PacketType.Play.Client.BLOCK_DIG, PacketType.Play.Client.BLOCK_PLACE,
                PacketType.Play.Client.ABILITIES, PacketType.Play.Client.CLIENT_COMMAND, PacketType.Play.Client.CUSTOM_PAYLOAD) {
            @Override
            public void onPacketReceiving(PacketEvent event) {

                PlayerData data = Pandora.getInstance().getDataManager().getPlayerData(event.getPlayer());
                if(data == null){
                    Pandora.getInstance().getDataManager().addData(event.getPlayer());
                    return;
                }

                if(PacketUtil.isFlyingPacket(event.getPacketType())){
                    data.getMovementProcessor().update();
                }

                Pandora.getInstance().getProfilerManager().callStart = System.nanoTime();
                data.getLoadedChecks().forEach(check -> check.handle(data, event));
                Pandora.getInstance().getProfilerManager().callsPerSecond++;

                double time = System.nanoTime()-Pandora.getInstance().getProfilerManager().callStart;
                Pandora.getInstance().getProfilerManager().callAverageTime += time;
            }

            @Override
            public void onPacketSending(PacketEvent event) {

            }
        });
    }

}
