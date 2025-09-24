package com.souvik.model.spot;

import com.souvik.enums.SpotFeature;
import com.souvik.enums.SpotType;
import com.souvik.model.vehicle.Vehicle;

import java.util.EnumSet;

public abstract class ParkingSpot {
    private final int floorNumber;
    private final int spotNumber;
    private final SpotType spotType;
    private Vehicle parkedVehicle;
    private final EnumSet<SpotFeature> features;

    public ParkingSpot(
            int floorNumber,
            int spotNumber,
            SpotType spotType,
            EnumSet<SpotFeature> features
    ){
        this.floorNumber = floorNumber;
        this.spotNumber = spotNumber;
        this.spotType = spotType;
        this.features = features;
        this.parkedVehicle = null;
    }

    public boolean isOccupied() {
        return parkedVehicle != null;
    }

    public void parkVehicle(Vehicle vehicle) {
        this.parkedVehicle = vehicle;
    }

    public void freeSpot() {
        this.parkedVehicle = null;
    }

    public EnumSet<SpotFeature> getFeatures() {
        return features;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public SpotType getSpotType() {
        return spotType;
    }

    @Override
    public String toString() {
        return "Spot{floor=" + floorNumber + ", number=" + spotNumber + ", type=" + spotType + '}';
    }
}
