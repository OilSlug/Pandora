package cc.oilslug.alerts.api;

import lombok.Getter;

import java.util.ArrayList;

public class AlertManager {

    @Getter
    private ArrayList<Alert> alerts;

    public AlertManager(){
        alerts = new ArrayList<>();
    }

    public void addAlert(Alert alert){
        alerts.add(alert);
    }

}
