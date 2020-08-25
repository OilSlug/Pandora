package cc.oilslug.data;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class DataManager {

    @Getter
    private ArrayList<PlayerData> dataList;

    public DataManager(){
        dataList = new ArrayList<>();
    }

    public void addData(Player player){
        if(getPlayerData(player) == null)dataList.add(new PlayerData(player));
    }

    public PlayerData getPlayerData(Player player){
        for(PlayerData data : dataList){
            if(data.getPlayer().getUniqueId() == player.getUniqueId()){
                return data;
            }
        }
        return null;
    }

    public void removeData(Player player){
        PlayerData dataToRemove = null;
        for(PlayerData data : dataList){
            if(data.getPlayer().getUniqueId() == player.getUniqueId()){
                dataToRemove = data;
            }
        }
        if(dataToRemove != null){
            dataList.remove(dataToRemove);
        }
    }


}
