package org.tw.bootcamp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class NotificationSystemTest {

    @Mock
    private NotificationListener listener1;
    @Mock
    private NotificationListener listener2;
    @Mock
    private EventType eventType;

    @Test
    void should_notify_listener_if_subscribed() {
        NotificationSystem notificationSystem = new NotificationSystem();
        notificationSystem.subscribe(listener1);
        notificationSystem.subscribe(listener2);

        notificationSystem.notify(eventType);

        Mockito.verify(listener1, times(1)).notify(eventType);
        Mockito.verify(listener2, times(1)).notify(eventType);
    }

    @Test
    void should_stop_notifying_when_listener_unsubscribed() {
        NotificationSystem notificationSystem = new NotificationSystem();
        notificationSystem.subscribe(listener1);
        notificationSystem.subscribe(listener2);
        notificationSystem.unsubscribe(listener1);

        notificationSystem.notify(eventType);

        Mockito.verify(listener1, times(0)).notify(any());
        Mockito.verify(listener2, times(1)).notify(eventType);
    }
}