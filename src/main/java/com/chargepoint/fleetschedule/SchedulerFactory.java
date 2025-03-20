package com.chargepoint.fleetschedule;

public class SchedulerFactory {
    public static ChargingScheduler createScheduler(SchedulingStrategy strategy) {
        switch (strategy) {
            case OPTIMAL:
                return new OptimalChargingScheduler();
            default:
                throw new IllegalArgumentException("Unknown scheduling strategy: " + strategy);
        }
    }


} 