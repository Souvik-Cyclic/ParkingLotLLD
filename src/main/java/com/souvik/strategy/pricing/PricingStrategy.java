package com.souvik.strategy.pricing;

import com.souvik.model.Ticket;

public interface PricingStrategy {
    double calculateFee(Ticket ticket);
}