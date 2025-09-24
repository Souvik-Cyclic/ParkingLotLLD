package com.souvik.strategy.allocation;

import com.souvik.enums.SpotType;
import com.souvik.model.Floor;
import com.souvik.model.gate.EntryGate;
import com.souvik.model.spot.ParkingSpot;
import com.souvik.service.DistanceService;

import java.util.*;

public class NearestAllocationStrategy implements AllocationStrategy {
    private final DistanceService distanceService = new DistanceService();
    private final Map<Integer, Map<SpotType, TreeSet<ParkingSpot>>> availableSpotsByGate = new HashMap<>();

    @Override
    public void initialize(List<Floor> floors, List<EntryGate> entryGates) {
        for (EntryGate entryGate : entryGates) {
            Comparator<ParkingSpot> distanceComparator = Comparator
                    .comparingInt((ParkingSpot spot) -> distanceService.calculateDistance(entryGate, spot))
                    .thenComparingInt(ParkingSpot::getFloorNumber)
                    .thenComparingInt(ParkingSpot::getSpotNumber);

            Map<SpotType, TreeSet<ParkingSpot>> spotsForGate = new HashMap<>();
            for (SpotType type : SpotType.values()) {
                spotsForGate.put(type, new TreeSet<>(distanceComparator));
            }

            for (Floor floor : floors) {
                for (ParkingSpot spot : floor.getAllSpots()) {
                    spotsForGate.get(spot.getSpotType()).add(spot);
                }
            }
            availableSpotsByGate.put(entryGate.getGateId(), spotsForGate);
        }
    }

    @Override
    public ParkingSpot findAndAllocateSpot(SpotType spotType, EntryGate entryGate) {
        TreeSet<ParkingSpot> availableSpots = availableSpotsByGate.get(entryGate.getGateId()).get(spotType);
        if (availableSpots.isEmpty()) {
            return null;
        }
        return availableSpots.pollFirst();
    }

    @Override
    public void freeSpot(ParkingSpot spot) {
        for (Map<SpotType, TreeSet<ParkingSpot>> spotsForGate : availableSpotsByGate.values()) {
            spotsForGate.get(spot.getSpotType()).add(spot);
        }
    }
}
