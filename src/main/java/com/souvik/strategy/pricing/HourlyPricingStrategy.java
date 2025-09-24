package com.souvik.strategy.pricing;

import com.souvik.enums.SpotFeature;
import com.souvik.enums.SpotType;
import com.souvik.model.Ticket;
import com.souvik.model.spot.ParkingSpot;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

public class HourlyPricingStrategy implements PricingStrategy {
    private static final Map<SpotType, Double> BASE_RATES = Map.of(
            SpotType.TWO_WHEELER, 20.0,
            SpotType.FOUR_WHEELER, 50.0,
            SpotType.HEAVY_VEHICLE, 100.0
    );

    private static final Map<SpotFeature, Double> FEATURE_COSTS = Map.of(
            SpotFeature.EV_CHARGING, 20.0
    );

    @Override
    public double calculateFee(Ticket ticket) {
        LocalDateTime entryTime = ticket.getEntryTime();
        LocalDateTime exitTime = LocalDateTime.now();
        Duration duration = Duration.between(entryTime, exitTime);

        long hours = duration.toSeconds() / 3600;
        if (duration.toSeconds() % 3600 != 0 || hours == 0) {
            hours++;
        }

        ParkingSpot spot = ticket.getSpot();
        double totalHourlyRate = BASE_RATES.getOrDefault(spot.getSpotType(), 0.0);
        for (SpotFeature feature : spot.getFeatures()) {
            totalHourlyRate += FEATURE_COSTS.getOrDefault(feature, 0.0);
        }

        return hours * totalHourlyRate;
    }
}
