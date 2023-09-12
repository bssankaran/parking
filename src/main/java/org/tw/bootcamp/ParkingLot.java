package org.tw.bootcamp;

public class ParkingLot {
    private final int totalSpace;
    private int currentAvailable;

    public ParkingLot(int totalSpace) {
        this.totalSpace = totalSpace;
        currentAvailable = totalSpace;
    }

    public boolean park() {
        if (currentAvailable == 0) {
            return false;
        }
        currentAvailable--;
        return true;
    }
}
