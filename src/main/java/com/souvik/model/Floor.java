package com.souvik.model;

import com.souvik.model.spot.ParkingSpot;

import java.util.List;

public class Floor {
    private final int floorNumber;
    private final List<ParkingSpot> allSpots;

    public Floor(int floorNumber, List<ParkingSpot> allSpots) {
        this.floorNumber = floorNumber;
        this.allSpots = allSpots;
    }

    public List<ParkingSpot> getAllSpots() {
        return allSpots;
    }
}
