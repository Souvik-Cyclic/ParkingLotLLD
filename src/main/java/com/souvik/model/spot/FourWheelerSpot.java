package com.souvik.model.spot;

import com.souvik.enums.SpotFeature;
import com.souvik.enums.SpotType;

import java.util.EnumSet;

public class FourWheelerSpot extends ParkingSpot{
    public FourWheelerSpot(
            int floorNumber,
            int spotNumber,
            EnumSet<SpotFeature> features
    ){
        super(floorNumber, spotNumber, SpotType.FOUR_WHEELER, features);
    }
}
