package com.souvik.service;

import com.souvik.model.gate.EntryGate;
import com.souvik.model.spot.ParkingSpot;

public class DistanceService {
    public int calculateDistance(EntryGate gate, ParkingSpot spot) {
        int floorDistance = (spot.getFloorNumber() - 1) * 1000;
        int spotDistanceOnFloor = Math.abs(spot.getSpotNumber() - gate.getGateId() * 10);
        return floorDistance + spotDistanceOnFloor;
    }
}
