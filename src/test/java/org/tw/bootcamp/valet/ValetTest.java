package org.tw.bootcamp.valet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.tw.bootcamp.parkinglot.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ValetTest {
    private List<ParkingLot> parkingLots;
    @Mock
    ParkingLot parkingLot1;
    @Mock
    ParkingLot parkingLot2;
    @Mock
    private Parkable parkable1;
    @Mock
    private NotificationSystem notificationSystem1;
    @Mock
    private NotificationSystem notificationSystem2;

    @BeforeEach
    void init() {
        parkingLots = List.of(parkingLot1, parkingLot2);
    }

    @Test
    void should_park_in_first_parking_lot_when_two_parking_lots_are_available() {
        when(parkingLot1.getNotificationSystem()).thenReturn(notificationSystem1);
        when(parkingLot2.getNotificationSystem()).thenReturn(notificationSystem2);
        Valet valet = new Valet(parkingLots);

        valet.park(parkable1);

        Mockito.verify(parkingLot1, Mockito.times(1)).park(parkable1);
    }

    @Test
    void should_park_in_first_parking_lot_when_two_parking_lots_are_available_without_using_mockito() {
        List<Parkable> arguments3 = new ArrayList<>();
        List<Parkable> arguments4 = new ArrayList<>();
        Parkable parkable3 = new Parkable() {
        };
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot3 = new ParkingLot(1, new NotificationSystem()) {
            @Override
            public void park(Parkable parkable) {
                arguments3.add(parkable);
            }
        };
        ParkingLot parkingLot4 = new ParkingLot(1, new NotificationSystem()) {
            @Override
            public void park(Parkable parkable) {
                arguments4.add(parkable);
            }
        };
        parkingLotList.add(parkingLot3);
        parkingLotList.add(parkingLot4);
        Valet valet = new Valet(parkingLotList);

        valet.park(parkable3);

        assertEquals(parkable3, arguments3.get(0));
    }

    @Test
    void should_park_in_second_parking_lot_when_first_lot_is_not_available() {
        when(parkingLot1.getNotificationSystem()).thenReturn(notificationSystem1);
        when(parkingLot2.getNotificationSystem()).thenReturn(notificationSystem2);
        Valet valet = new Valet(parkingLots);
        ArgumentCaptor<NotificationListener> listenerArgumentCaptor = ArgumentCaptor.forClass(NotificationListener.class);
        Mockito.verify(notificationSystem1).subscribe(listenerArgumentCaptor.capture());
        NotificationListener notificationListener = listenerArgumentCaptor.getValue();
        notificationListener.notify(EventType.PARKING_LOT_FULL);

        valet.park(parkable1);

        Mockito.verify(parkingLot1, Mockito.times(0)).park(parkable1);
        Mockito.verify(parkingLot2, Mockito.times(1)).park(parkable1);
    }
}