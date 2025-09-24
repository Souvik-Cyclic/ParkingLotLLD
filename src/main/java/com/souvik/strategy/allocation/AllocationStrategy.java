package com.souvik.strategy.allocation;

import com.souvik.enums.SpotType;
import com.souvik.model.Floor;
import com.souvik.model.gate.EntryGate;
import com.souvik.model.spot.ParkingSpot;

import java.util.List;

public interface AllocationStrategy {
    void initialize(List<Floor> floors, List<EntryGate> entryGates);

    ParkingSpot findAndAllocateSpot(SpotType spotType, EntryGate entryGate);

    void freeSpot(ParkingSpot spot);
}
