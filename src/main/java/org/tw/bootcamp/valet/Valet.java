package org.tw.bootcamp.valet;

import org.tw.bootcamp.parkinglot.EventType;
import org.tw.bootcamp.parkinglot.Parkable;
import org.tw.bootcamp.parkinglot.ParkingLot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Valet {
    private final List<ParkingLot> parkingLots;
    private final Map<ParkingLot, Boolean> availabilityMap = new HashMap<>();

    public Valet(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
        makeAllAvailable(parkingLots);
        registerListeners(parkingLots);
    }

    private void makeAllAvailable(List<ParkingLot> parkingLots) {
        parkingLots.forEach(parkingLot->availabilityMap.put(parkingLot, true));
    }

    private void registerListeners(List<ParkingLot> parkingLots) {
        parkingLots.forEach(parkingLot -> parkingLot.getNotificationSystem().subscribe((eventType) -> {
            if (eventType == EventType.PARKING_LOT_AVAILABLE) {
                availabilityMap.put(parkingLot, true);
            }
            availabilityMap.put(parkingLot, false);
        }));
    }

    public void park(Parkable parkable) {
        getFirstAvailableParkingLot().get().park(parkable);
    }

    private Optional<ParkingLot> getFirstAvailableParkingLot() {
        return parkingLots.stream().filter(parkingLot -> availabilityMap.get(parkingLot)).findFirst();
    }
}
