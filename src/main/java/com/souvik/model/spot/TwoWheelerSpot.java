package com.souvik.model.spot;

import com.souvik.enums.SpotFeature;
import com.souvik.enums.SpotType;

import java.util.EnumSet;

public class TwoWheelerSpot extends ParkingSpot{
    public TwoWheelerSpot(
            int floorNumber,
            int spotNumber,
            EnumSet<SpotFeature> features
    ){
        super(floorNumber, spotNumber, SpotType.TWO_WHEELER, features);
    }
}
