package cc.oilslug.data.processors;

import lombok.Getter;
import org.bukkit.entity.Player;

@Getter
public class CombatProcessor {

    /**
     * TODO Hook into packet listener
     */

    private Player player;
    public CombatProcessor(final Player player){
        this.player = player;
    }

    public void update(){

    }

}
