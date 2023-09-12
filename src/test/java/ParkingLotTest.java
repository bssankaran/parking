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
}
