import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.tw.bootcamp.Parkable;
import org.tw.bootcamp.ParkingLot;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ParkingLotTest {
    @Mock
    private Parkable parkable1;
    @Mock
    private Parkable parkable2;

    @Test
    public void should_park_when_space_available() {
        ParkingLot parkingLot = new ParkingLot(1);

        parkingLot.park(parkable1);

        assertTrue(parkingLot.isParked(parkable1));
    }

    @Test
    public void should_not_park_when_space_not_available() {
        ParkingLot parkingLot = new ParkingLot(0);

        parkingLot.park(parkable1);

        assertFalse(parkingLot.isParked(parkable1));
    }

    @Test
    void should_allow_to_park_2_cars_when_space_is_available() {
        ParkingLot parkingLot = new ParkingLot(2);

        parkingLot.park(parkable1);
        parkingLot.park(parkable2);

        assertTrue(parkingLot.isParked(parkable1));
        assertTrue(parkingLot.isParked(parkable2));
    }

    @Test
    void should_return_true_when_my_car_is_available_in_parking_lot() {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(parkable1);

        assertTrue(parkingLot.isParked(parkable1));
    }

    @Test
    void should_return_false_when_my_car_is_not_available_in_parking_lot() {
        ParkingLot parkingLot = new ParkingLot(1);

        assertFalse(parkingLot.isParked(parkable1));
    }

    @Test
    void should_return_true_when_car_successfully_unparked() {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(parkable1);

        parkingLot.leave(parkable1);
        parkingLot.park(parkable2);

        assertTrue(parkingLot.isParked(parkable2));
    }

    @Test
    void should_throw_exception_when_trying_to_unpark_non_existing_car() {
        ParkingLot parkingLot = new ParkingLot(1);

        assertThrows(IllegalStateException.class, () -> parkingLot.leave(parkable1));
    }
}
