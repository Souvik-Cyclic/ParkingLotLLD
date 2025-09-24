package com.souvik.model.spot;

import com.souvik.enums.SpotFeature;
import com.souvik.enums.SpotType;

import java.util.EnumSet;

public class HeavyVehicleSpot extends ParkingSpot {
    public HeavyVehicleSpot(
            int floorNumber,
            int spotNumber,
            EnumSet<SpotFeature> features
    ) {
        super(floorNumber, spotNumber, SpotType.HEAVY_VEHICLE, features);
    }
}
