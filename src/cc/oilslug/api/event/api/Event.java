package cc.oilslug.api.event.api;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Event {
    public int eventID;
    public long eventCalledTime;
    @Setter
    public boolean canceled;
}
