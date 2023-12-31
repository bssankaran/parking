package org.tw.bootcamp.parkinglot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.tw.bootcamp.parkinglot.EventType;
import org.tw.bootcamp.parkinglot.NotificationSystem;
import org.tw.bootcamp.parkinglot.Parkable;
import org.tw.bootcamp.parkinglot.ParkingLot;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class ParkingLotTest {
    @Mock
    private Parkable parkable1;
    @Mock
    private Parkable parkable2;
    @Mock
    NotificationSystem notificationSystem;

    @Test
    public void should_park_when_space_available() {
        ParkingLot parkingLot = new ParkingLot(1, notificationSystem);

        parkingLot.park(parkable1);

        assertTrue(parkingLot.isParked(parkable1));
    }

    @Test
    public void should_not_park_when_space_not_available() {
        ParkingLot parkingLot = new ParkingLot(0, notificationSystem);

        parkingLot.park(parkable1);

        assertFalse(parkingLot.isParked(parkable1));
    }

    @Test
    void should_allow_to_park_2_cars_when_space_is_available() {
        ParkingLot parkingLot = new ParkingLot(2, notificationSystem);

        parkingLot.park(parkable1);
        parkingLot.park(parkable2);

        assertTrue(parkingLot.isParked(parkable1));
        assertTrue(parkingLot.isParked(parkable2));
    }

    @Test
    void should_return_true_when_my_car_is_available_in_parking_lot() {
        ParkingLot parkingLot = new ParkingLot(1, notificationSystem);
        parkingLot.park(parkable1);

        assertTrue(parkingLot.isParked(parkable1));
    }

    @Test
    void should_return_false_when_my_car_is_not_available_in_parking_lot() {
        ParkingLot parkingLot = new ParkingLot(1, notificationSystem);

        assertFalse(parkingLot.isParked(parkable1));
    }

    @Test
    void should_return_true_when_car_successfully_unparked() {
        ParkingLot parkingLot = new ParkingLot(1, notificationSystem);
        parkingLot.park(parkable1);

        parkingLot.leave(parkable1);
        parkingLot.park(parkable2);

        assertTrue(parkingLot.isParked(parkable2));
    }

    @Test
    void should_throw_exception_when_trying_to_unpark_non_existing_car() {
        ParkingLot parkingLot = new ParkingLot(1, notificationSystem);

        assertThrows(IllegalStateException.class, () -> parkingLot.leave(parkable1));
    }

    @Test
    void should_notify_when_parking_full() {
        ParkingLot parkingLot = new ParkingLot(1, notificationSystem);

        parkingLot.park(parkable1);

        Mockito.verify(notificationSystem, times(1)).notify(EventType.PARKING_LOT_FULL);
    }

    @Test
    void should_not_notify_until_parking_not_full() {
        ParkingLot parkingLot = new ParkingLot(100, notificationSystem);

        for (int i = 0; i < 99; i++) {
            parkingLot.park(Mockito.mock(Parkable.class));
        }

        Mockito.verify(notificationSystem, times(0)).notify(any());
    }

    @Test
    void should_notify_when_parking_lot_becomes_available() {
        ParkingLot parkingLot = new ParkingLot(1, notificationSystem);
        parkingLot.park(parkable1);

        parkingLot.leave(parkable1);

        Mockito.verify(notificationSystem, times(1)).notify(EventType.PARKING_LOT_AVAILABLE);
    }
}
