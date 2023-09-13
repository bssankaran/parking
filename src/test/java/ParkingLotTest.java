import org.junit.jupiter.api.Test;
import org.tw.bootcamp.ParkingLot;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    @Test
    public void should_park_when_space_available() {
        ParkingLot parkingLot = new ParkingLot(1);

        boolean isParked = parkingLot.park();

        assertTrue(isParked);
    }

    @Test
    public void should_not_park_when_space_not_available() {
        ParkingLot parkingLot = new ParkingLot(0);

        boolean isParked = parkingLot.park();

        assertFalse(isParked);
    }

    @Test
    void should_allow_to_park_2_cars_when_space_is_available(){
        ParkingLot parkingLot = new ParkingLot(2);

        boolean isParked1 = parkingLot.park();
        boolean isParked2 = parkingLot.park();

        assertTrue(isParked1);
        assertTrue(isParked2);
    }
}
