package com.souvik;

import com.souvik.logger.ParkingLotLogger;
import com.souvik.model.Floor;
import com.souvik.model.Ticket;
import com.souvik.model.gate.EntryGate;
import com.souvik.model.gate.ExitGate;
import com.souvik.model.spot.ParkingSpot;
import com.souvik.model.vehicle.Vehicle;
import com.souvik.service.AllocationService;
import com.souvik.service.BillingService;
import com.souvik.strategy.allocation.NearestAllocationStrategy;
import com.souvik.strategy.pricing.HourlyPricingStrategy;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private final AllocationService allocationService;
    private final BillingService billingService;
    private final ParkingLotLogger logger;

    private ParkingLot(List<Floor> floors, List<EntryGate> entryGates) {
        this.logger = ParkingLotLogger.getInstance();
        this.allocationService = new AllocationService(new NearestAllocationStrategy());
        this.billingService = new BillingService(new HourlyPricingStrategy());
        this.allocationService.initialize(floors, entryGates);
    }

    public Ticket enter(Vehicle vehicle, EntryGate entryGate) {
        logger.logVehicleEntry(vehicle, entryGate.getGateId());
        ParkingSpot spot = allocationService.allocateSpot(vehicle, entryGate);

        if (spot == null) {
            logger.logParkingFull();
            return null;
        }

        spot.parkVehicle(vehicle);
        Ticket ticket = new Ticket(vehicle, spot);
        logger.logSpotAllocation(spot);
        return ticket;
    }

    public double exit(Ticket ticket, ExitGate exitGate) {
        logger.logVehicleExit(ticket.getVehicle(), exitGate.getGateId());
        double fee = billingService.calculateFee(ticket);
        logger.logFee(fee);

        ParkingSpot spot = ticket.getSpot();
        spot.freeSpot();
        allocationService.freeSpot(spot);
        logger.logSpotFreed(spot);
        return fee;
    }

    public static class Builder {
        private final List<Floor> floors = new ArrayList<>();
        private final List<EntryGate> entryGates = new ArrayList<>();

        public Builder addFloor(Floor floor) {
            this.floors.add(floor);
            return this;
        }

        public Builder addEntryGate(EntryGate gate) {
            this.entryGates.add(gate);
            return this;
        }

        public ParkingLot build() {
            return new ParkingLot(this.floors, this.entryGates);
        }
    }
}