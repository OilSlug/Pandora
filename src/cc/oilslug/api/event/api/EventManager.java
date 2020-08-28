package cc.oilslug.api.event.api;

import java.util.ArrayList;

public class EventManager {

    private ArrayList<EventListener> registeredClasses;

    public EventManager(){
        registeredClasses = new ArrayList<>();
    }

    public ArrayList<EventListener> getRegisteredClasses() {
        return registeredClasses;
    }

    public void registerListener(EventListener eventListener){
        if(!registeredClasses.contains(eventListener)) registeredClasses.add(eventListener);
    }

    public void callEvent(Event event){
        registeredClasses.parallelStream().forEach(eventListener -> eventListener.handleEvent(event));
    }

}
