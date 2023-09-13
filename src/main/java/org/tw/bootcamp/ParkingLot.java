package org.tw.bootcamp;

import java.util.HashSet;
import java.util.Set;

public class ParkingLot {
    private final int totalSpace;
    private int currentAvailable;
    private final Set<String> vehicles = new HashSet<>();

    public ParkingLot(int totalSpace) {
        this.totalSpace = totalSpace;
        currentAvailable = totalSpace;
    }

    public boolean park(Parkable parkable) {
        if (currentAvailable == 0) {
            return false;
        }
        vehicles.add(parkable.registrationNumber());
        currentAvailable--;
        return true;
    }

    public boolean isParked(String number) {
        return vehicles.contains(number);
    }

    public void leave(String number) {
        if(!vehicles.contains(number)){
            throw new IllegalStateException("Vehicle does not exist.");
        }
        vehicles.remove(number);
        currentAvailable++;
    }
}
