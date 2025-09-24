package com.souvik.service;

import com.souvik.model.Ticket;
import com.souvik.strategy.pricing.PricingStrategy;

public class BillingService {
    private final PricingStrategy pricingStrategy;

    public BillingService(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public double calculateFee(Ticket ticket) {
        return pricingStrategy.calculateFee(ticket);
    }
}
