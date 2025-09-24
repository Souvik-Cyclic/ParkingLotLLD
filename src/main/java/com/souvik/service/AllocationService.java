package com.souvik.service;

import com.souvik.enums.SpotType;
import com.souvik.enums.VehicleType;
import com.souvik.model.Floor;
import com.souvik.model.gate.EntryGate;
import com.souvik.model.spot.ParkingSpot;
import com.souvik.model.vehicle.Vehicle;
import com.souvik.strategy.allocation.AllocationStrategy;

import java.util.List;

public class AllocationService {
    private final AllocationStrategy allocationStrategy;

    public AllocationService(AllocationStrategy allocationStrategy) {
        this.allocationStrategy = allocationStrategy;
    }

    public void initialize(
            List<Floor> floors,
            List<EntryGate> entryGates
    ){
        allocationStrategy.initialize(floors, entryGates);
    }

    public ParkingSpot allocateSpot(
            Vehicle vehicle,
            EntryGate entryGate
    ){
        SpotType requiredSpotType = getRequiredSpotType(vehicle.getType());
        return allocationStrategy.findAndAllocateSpot(requiredSpotType, entryGate);
    }

    public void freeSpot(ParkingSpot spot) {
        allocationStrategy.freeSpot(spot);
    }

    private SpotType getRequiredSpotType(VehicleType vehicleType) {
        switch (vehicleType) {
            case TWO_WHEELER: return SpotType.TWO_WHEELER;
            case FOUR_WHEELER: return SpotType.FOUR_WHEELER;
            case HEAVY_VEHICLE: return SpotType.HEAVY_VEHICLE;
            default: throw new IllegalArgumentException("Unsupported vehicle type");
        }
    }
}
