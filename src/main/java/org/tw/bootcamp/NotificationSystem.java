package org.tw.bootcamp;

import java.util.*;

public class NotificationSystem {
    private final Set<NotificationListener> listeners = new HashSet<>();

    public void notifyParkingLotFull() {
        listeners.forEach(NotificationListener::notifyParkingLotFull);
    }

    public void subscribe(NotificationListener notificationListener) {
        listeners.add(notificationListener);
    }

    public void unsubscribe(NotificationListener notificationListener) {
        listeners.remove(notificationListener);
    }
}
