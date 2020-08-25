package cc.oilslug.utils;

import com.comphenix.protocol.PacketType;

public class PacketUtil {

    public static boolean isFlyingPacket(PacketType packetType){
        return packetType == PacketType.Play.Client.FLYING || packetType == PacketType.Play.Client.POSITION
                || packetType == PacketType.Play.Client.POSITION_LOOK || packetType == PacketType.Play.Client.LOOK;
    }

}
