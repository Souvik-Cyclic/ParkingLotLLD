package com.souvik.logger;

import com.souvik.model.spot.ParkingSpot;
import com.souvik.model.vehicle.Vehicle;

public class ParkingLotLogger {

    private ParkingLotLogger() {}

    private static class LoggerHolder {
        private static final ParkingLotLogger INSTANCE = new ParkingLotLogger();
    }

    public static ParkingLotLogger getInstance() {
        return LoggerHolder.INSTANCE;
    }

    public void logVehicleEntry(Vehicle vehicle, int gateId) {
        System.out.println("Vehicle " + vehicle.getLicensePlate() + " (" + vehicle.getType() + ") entering at Gate " + gateId);
    }

    public void logSpotAllocation(ParkingSpot spot) {
        String spotInfo = "Spot{floor=" + spot.getFloorNumber() + ", number=" + spot.getSpotNumber() + ", type=" + spot.getSpotType() + '}';
        System.out.println(">>> Allocated " + spotInfo);
    }

    public void logParkingFull() {
        System.out.println(">>> Entry DENIED. Parking lot is full for this vehicle type.");
    }

    public void logVehicleExit(Vehicle vehicle, int gateId) {
        System.out.println("\nVehicle " + vehicle.getLicensePlate() + " exiting at Gate " + gateId);
    }

    public void logFee(double fee) {
        System.out.println(">>> Parking Fee: ₹" + String.format("%.2f", fee));
    }

    public void logSpotFreed(ParkingSpot spot) {
        String spotInfo = "Spot{floor=" + spot.getFloorNumber() + ", number=" + spot.getSpotNumber() + ", type=" + spot.getSpotType() + '}';
        System.out.println(">>> " + spotInfo + " is now free.");
    }
}