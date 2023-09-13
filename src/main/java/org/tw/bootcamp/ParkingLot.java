package org.tw.bootcamp;

import java.util.HashSet;
import java.util.Set;

public class ParkingLot {
    private int currentAvailable;
    private final Set<Parkable> vehicles = new HashSet<>();

    public ParkingLot(int totalSpace) {
        currentAvailable = totalSpace;
    }

    public void park(Parkable parkable) {
        if (currentAvailable == 0) {
            return;
        }
        vehicles.add(parkable);
        currentAvailable--;
    }

    public boolean isParked(Parkable parkable) {
        return vehicles.contains(parkable);
    }

    public void leave(Parkable parkable) {
        if (!vehicles.contains(parkable)) {
            throw new IllegalStateException("Vehicle does not exist.");
        }
        vehicles.remove(parkable);
        currentAvailable++;
    }
}
