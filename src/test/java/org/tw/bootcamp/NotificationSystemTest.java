package org.tw.bootcamp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class NotificationSystemTest {

    @Mock
    private NotificationListener listener1;
    @Mock
    private NotificationListener listener2;

    @Test
    void should_notify_listener_if_subscribed() {
        NotificationSystem notificationSystem = new NotificationSystem();
        notificationSystem.subscribe(listener1);
        notificationSystem.subscribe(listener2);

        notificationSystem.notifyParkingLotFull();

        Mockito.verify(listener1, times(1)).notifyParkingLotFull();
        Mockito.verify(listener2, times(1)).notifyParkingLotFull();
    }

    @Test
    void should_stop_notifying_when_listener_unsubscribed() {
        NotificationSystem notificationSystem = new NotificationSystem();
        notificationSystem.subscribe(listener1);
        notificationSystem.subscribe(listener2);
        notificationSystem.unsubscribe(listener1);

        notificationSystem.notifyParkingLotFull();

        Mockito.verify(listener1, times(0)).notifyParkingLotFull();
        Mockito.verify(listener2, times(1)).notifyParkingLotFull();
    }
}