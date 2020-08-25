package cc.oilslug.check.api;

import cc.oilslug.check.impl.movement.speed.SpeedA;

import java.util.ArrayList;

public class CheckManager {

    public static ArrayList<Check> getChecks(){
        ArrayList<Check> checks = new ArrayList<>();

        checks.add(new SpeedA("SpeedA", CheckType.MOVEMENT, true, true, 10));

        return checks;
    }

}
