package org.tw.bootcamp;

import java.util.HashSet;
import java.util.Set;

public class ParkingLot {
    private final Set<Parkable> vehicles = new HashSet<>();

    private final NotificationSystem notificationSystem;

    private int currentAvailable;


    public ParkingLot(int totalSpace, NotificationSystem notificationSystem) {
        currentAvailable = totalSpace;
        this.notificationSystem = notificationSystem;
    }

    public void park(Parkable parkable) {
        if (currentAvailable == 0) {
            return;
        }
        vehicles.add(parkable);
        currentAvailable--;
        if (currentAvailable == 0) {
            notificationSystem.notify(EventType.PARKING_LOT_FULL);
        }
    }

    public boolean isParked(Parkable parkable) {
        return vehicles.contains(parkable);
    }

    public void leave(Parkable parkable) {
        if (!vehicles.contains(parkable)) {
            throw new IllegalStateException("Vehicle does not exist.");
        }
        vehicles.remove(parkable);

        boolean shouldNotify = currentAvailable == 0;

        currentAvailable++;

        if (shouldNotify) {
            notificationSystem.notify(EventType.PARKING_LOT_AVAILABLE);
        }
    }
}
