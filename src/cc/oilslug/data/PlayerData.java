package cc.oilslug.data;

import cc.oilslug.check.api.Check;
import cc.oilslug.check.api.CheckManager;
import cc.oilslug.data.processors.MovementProcessor;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

@Getter
public class PlayerData {

    private Player player;
    private MovementProcessor movementProcessor;

    private ArrayList<Check> loadedChecks, flaggedChecks;
    private HashMap<Check, Integer> verbose;

    public PlayerData(final Player player){
        this.player = player;
        this.loadedChecks = CheckManager.getChecks();
        this.movementProcessor = new MovementProcessor(player);
        verbose = new HashMap<>();
        flaggedChecks = new ArrayList<>();
    }

    public void addVerbose(Check check){
        if(!flaggedChecks.contains(check)){
            flaggedChecks.add(check);
        }
        if(verbose.containsKey(check)){
            verbose.put(check, verbose.get(check)+1);
        }else{
            verbose.put(check, 1);
        }
    }

}
