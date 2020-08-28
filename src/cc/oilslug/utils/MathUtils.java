package cc.oilslug.utils;

import org.bukkit.Location;

public class MathUtils {

    public static double getDistanceXZ(Location a, Location b){
        double X = Math.abs(a.getX()-b.getX());
        double Z = Math.abs(a.getZ()-b.getZ());
        return Math.sqrt(Math.pow(X, 2) + Math.pow(Z, 2));
    }

    public static double getDistanceYAbs(Location a, Location b){
        return Math.abs(a.getY()-b.getY());
    }

    public static double getDistanceY(Location a, Location b){
        return a.getY()-b.getY();
    }

}
