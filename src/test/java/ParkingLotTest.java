import org.junit.jupiter.api.Test;
import org.tw.bootcamp.ParkingLot;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    @Test
    public void should_park_when_space_available() {
        ParkingLot parkingLot = new ParkingLot(1);

        boolean isParked = parkingLot.park(()->"1");

        assertTrue(isParked);
    }

    @Test
    public void should_not_park_when_space_not_available() {
        ParkingLot parkingLot = new ParkingLot(0);

        boolean isParked = parkingLot.park(()->"1");

        assertFalse(isParked);
    }

    @Test
    void should_allow_to_park_2_cars_when_space_is_available(){
        ParkingLot parkingLot = new ParkingLot(2);

        boolean isParked1 = parkingLot.park(()->"1");
        boolean isParked2 = parkingLot.park(()->"2");

        assertTrue(isParked1);
        assertTrue(isParked2);
    }

    @Test
    void should_return_true_when_my_car_is_available_in_parking_lot(){
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(()->"1");

        boolean isParked = parkingLot.isParked("1");

        assertTrue(isParked);
    }

    @Test
    void should_return_false_when_my_car_is_not_available_in_parking_lot(){
        ParkingLot parkingLot = new ParkingLot(1);

        boolean isParked = parkingLot.isParked("1");

        assertFalse(isParked);
    }

    @Test
    void should_return_true_when_car_successfully_unparked(){
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(()->"1");

        parkingLot.leave("1");

        assertTrue(parkingLot.park(()-> "2"));
    }

    @Test
    void should_throw_exception_when_trying_to_unpark_non_existing_car(){
        ParkingLot parkingLot = new ParkingLot(1);

        assertThrows(IllegalStateException.class, ()-> parkingLot.leave("1"));


    }
}
