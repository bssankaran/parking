package org.tw.bootcamp;

import java.util.*;

public class NotificationSystem {
    private final Set<NotificationListener> listeners = new HashSet<>();

    public void notify(EventType eventType) {
        listeners.forEach(listener -> listener.notify(eventType));
    }

    public void subscribe(NotificationListener notificationListener) {
        listeners.add(notificationListener);
    }

    public void unsubscribe(NotificationListener notificationListener) {
        listeners.remove(notificationListener);
    }
}
