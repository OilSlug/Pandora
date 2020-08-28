package cc.oilslug.check.api;

import cc.oilslug.check.impl.movement.fly.FlyA;
import cc.oilslug.check.impl.movement.speed.SpeedA;
import cc.oilslug.check.impl.other.badpacket.BadPacketA;

import java.util.ArrayList;

public class CheckManager {

    public static ArrayList<Check> getChecks(){
        ArrayList<Check> checks = new ArrayList<>();

        checks.add(new SpeedA("SpeedA", CheckType.MOVEMENT, true, true, 10));

        checks.add(new FlyA("FlyA", CheckType.MOVEMENT, true, true, 10));

        checks.add(new BadPacketA("BadPacketA", CheckType.OTHER, true, false, 1));

        return checks;
    }

    public static boolean isPunishable(String checkName){
        for(Check check : getChecks()){
            if(check.getName().equals(checkName)){
                return check.isPunishable();
            }
        }
        return false;
    }

    public static boolean canLagBack(String checkName){
        for(Check check : getChecks()){
            if(check.getName().equals(checkName)){
                return check.isLagBack();
            }
        }
        return false;
    }

}
