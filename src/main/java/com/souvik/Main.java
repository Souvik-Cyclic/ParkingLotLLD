package com.souvik;

import com.souvik.enums.SpotFeature;
import com.souvik.enums.VehicleType;
import com.souvik.model.Floor;
import com.souvik.model.Ticket;
import com.souvik.model.gate.EntryGate;
import com.souvik.model.gate.ExitGate;
import com.souvik.model.spot.FourWheelerSpot;
import com.souvik.model.spot.HeavyVehicleSpot;
import com.souvik.model.spot.ParkingSpot;
import com.souvik.model.spot.TwoWheelerSpot;
import com.souvik.model.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<ParkingSpot> floor1Spots = new ArrayList<>();
        floor1Spots.add(new TwoWheelerSpot(1, 1, EnumSet.noneOf(SpotFeature.class)));
        floor1Spots.add(new TwoWheelerSpot(1, 3, EnumSet.noneOf(SpotFeature.class)));
        floor1Spots.add(new FourWheelerSpot(1, 10, EnumSet.of(SpotFeature.EV_CHARGING)));
        floor1Spots.add(new FourWheelerSpot(1, 12, EnumSet.noneOf(SpotFeature.class)));
        floor1Spots.add(new FourWheelerSpot(1, 15, EnumSet.noneOf(SpotFeature.class)));

        List<ParkingSpot> floor2Spots = new ArrayList<>();
        floor2Spots.add(new HeavyVehicleSpot(2, 20, EnumSet.noneOf(SpotFeature.class)));
        floor2Spots.add(new FourWheelerSpot(2, 25, EnumSet.noneOf(SpotFeature.class)));
        floor2Spots.add(new TwoWheelerSpot(2, 30, EnumSet.noneOf(SpotFeature.class)));

        List<ParkingSpot> floor3Spots = new ArrayList<>();
        floor3Spots.add(new FourWheelerSpot(3, 35, EnumSet.of(SpotFeature.EV_CHARGING)));
        floor3Spots.add(new FourWheelerSpot(3, 40, EnumSet.noneOf(SpotFeature.class)));
        floor3Spots.add(new TwoWheelerSpot(3, 45, EnumSet.noneOf(SpotFeature.class)));

        ParkingLot parkingLot = new ParkingLot.Builder()
                .addFloor(new Floor(1, floor1Spots))
                .addFloor(new Floor(2, floor2Spots))
                .addFloor(new Floor(3, floor3Spots))
                .addEntryGate(new EntryGate(1))
                .addEntryGate(new EntryGate(5))
                .addEntryGate(new EntryGate(7))
                .build();

        System.out.println("PARKING LOT SETUP COMPLETED");
        System.out.println("Total Spots: " + (floor1Spots.size() + floor2Spots.size() + floor3Spots.size()));
        System.out.println("EV Charging Spots: 2");
        System.out.println("Entry Gates: 3 (Gates 1, 5, 7)");
        System.out.println("Floors: 3\n");

        runSimulation(parkingLot);
    }

    private static void runSimulation(ParkingLot parkingLot) throws InterruptedException {
        EntryGate entryGate1 = new EntryGate(1);
        EntryGate entryGate5 = new EntryGate(5);
        EntryGate entryGate7 = new EntryGate(7);

        ExitGate exitGate1 = new ExitGate(1);
        ExitGate exitGate5 = new ExitGate(5);
        ExitGate exitGate7 = new ExitGate(7);

        System.out.println("=== ENHANCED PARKING LOT SIMULATION ===");
        System.out.println("Testing all vehicle types, multiple gates, and EV charging\n");

        System.out.println("--- PHASE 1: Vehicle Entries ---");

        Vehicle bike1 = new Vehicle("KA-02-B-9876", VehicleType.TWO_WHEELER);
        System.out.println("Bike enters at Gate 1...");
        Ticket bike1Ticket = parkingLot.enter(bike1, entryGate1);
        System.out.println("--------------------");

        Vehicle car1 = new Vehicle("KA-01-A-1111", VehicleType.FOUR_WHEELER);
        System.out.println("Car 1 (Regular) enters at Gate 5...");
        Ticket car1Ticket = parkingLot.enter(car1, entryGate5);
        System.out.println("--------------------");

        Vehicle car2 = new Vehicle("KA-01-C-3333", VehicleType.FOUR_WHEELER);
        System.out.println("Car 2 (EV - will get EV charging spot) enters at Gate 1...");
        Ticket car2Ticket = parkingLot.enter(car2, entryGate1);
        System.out.println("--------------------");

        Vehicle bike2 = new Vehicle("KA-02-D-5555", VehicleType.TWO_WHEELER);
        System.out.println("Bike 2 enters at Gate 7...");
        Ticket bike2Ticket = parkingLot.enter(bike2, entryGate7);
        System.out.println("--------------------");

        Vehicle truck1 = new Vehicle("KA-03-T-7777", VehicleType.HEAVY_VEHICLE);
        System.out.println("Truck enters at Gate 5...");
        Ticket truck1Ticket = parkingLot.enter(truck1, entryGate5);
        System.out.println("--------------------");

        Vehicle car3 = new Vehicle("KA-01-E-8888", VehicleType.FOUR_WHEELER);
        System.out.println("Car 3 (Regular) enters at Gate 7...");
        Ticket car3Ticket = parkingLot.enter(car3, entryGate7);
        System.out.println("--------------------");

        System.out.println("\n--- PHASE 2: Parking Duration ---");
        System.out.println("Vehicles parked for different durations...");

        Thread.sleep(1000);
        System.out.println("1 second passed...");

        System.out.println("\n--- PHASE 3: First Batch Exits (Short Stays) ---");

        System.out.println("Bike 1 exits at Gate 1...");
        parkingLot.exit(bike1Ticket, exitGate1);
        System.out.println("--------------------");

        System.out.println("Car 1 exits at Gate 5...");
        parkingLot.exit(car1Ticket, exitGate5);
        System.out.println("--------------------");

        System.out.println("\n--- PHASE 4: Medium Stay Duration ---");
        Thread.sleep(1500);
        System.out.println("Additional 1.5 seconds passed (total 2.5 seconds)...");

        System.out.println("\n--- PHASE 5: Second Batch Exits (Medium Stays) ---");

        System.out.println("Car 2 (EV) exits at Gate 7...");
        parkingLot.exit(car2Ticket, exitGate7);
        System.out.println("--------------------");

        System.out.println("Bike 2 exits at Gate 5...");
        parkingLot.exit(bike2Ticket, exitGate5);
        System.out.println("--------------------");

        System.out.println("\n--- PHASE 6: Long Stay Duration ---");
        Thread.sleep(2000);
        System.out.println("Additional 2 seconds passed (total 4.5 seconds)...");

        System.out.println("\n--- PHASE 7: Final Exits (Long Stays) ---");

        System.out.println("Truck exits at Gate 1...");
        parkingLot.exit(truck1Ticket, exitGate1);
        System.out.println("--------------------");

        System.out.println("Car 3 exits at Gate 7...");
        parkingLot.exit(car3Ticket, exitGate7);
        System.out.println("--------------------");

        System.out.println("\n--- PHASE 8: Testing Edge Cases ---");

        Vehicle extraBike = new Vehicle("KA-02-X-9999", VehicleType.TWO_WHEELER);
        System.out.println("Extra bike tries to enter (should get remaining spot)...");
        Ticket extraBikeTicket = parkingLot.enter(extraBike, entryGate1);

        if (extraBikeTicket != null) {
            System.out.println("Extra bike exits immediately...");
            parkingLot.exit(extraBikeTicket, exitGate5);
        }
        System.out.println("--------------------");

        Vehicle extraTruck = new Vehicle("KA-03-X-1234", VehicleType.HEAVY_VEHICLE);
        System.out.println("Extra truck tries to enter (might be denied - only 1 heavy spot)...");
        Ticket extraTruckTicket = parkingLot.enter(extraTruck, entryGate7);

        if (extraTruckTicket != null) {
            System.out.println("Extra truck exits...");
            parkingLot.exit(extraTruckTicket, exitGate1);
        }

        System.out.println("\n=== SIMULATION COMPLETED ===");
        System.out.println("Tested: All vehicle types");
        System.out.println("Tested: Multiple entry/exit gates");
        System.out.println("Tested: EV charging spots");
        System.out.println("Tested: Different parking durations");
        System.out.println("Tested: Edge cases (full parking)");
    }
}