package com.chargepoint.fleetschedule;

public class Truck {
    private final String id;
    private final double batteryCapacity; // in kilowatt hours
    private final double currentChargeLevel; // in kilowatt hours
    private final double remainingChargeNeeded;

    private Truck(Builder builder) {
        this.id = builder.id;
        this.batteryCapacity = builder.batteryCapacity;
        this.currentChargeLevel = builder.currentChargeLevel;
        this.remainingChargeNeeded = batteryCapacity - currentChargeLevel;
    }

    public String getId() {
        return id;
    }

    public double getBatteryCapacity() {
        return batteryCapacity;
    }

    public double getCurrentChargeLevel() {
        return currentChargeLevel;
    }

    public double getRemainingChargeNeeded() {
        return remainingChargeNeeded;
    }

    public double getTimeToFullCharge(double chargingRate) {
        return remainingChargeNeeded / chargingRate;
    }

    public static class Builder {
        private String id;
        private double batteryCapacity;
        private double currentChargeLevel;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder batteryCapacity(double batteryCapacity) {
            this.batteryCapacity = batteryCapacity;
            return this;
        }

        public Builder currentChargeLevel(double currentChargeLevel) {
            this.currentChargeLevel = currentChargeLevel;
            return this;
        }

        public Truck build() {
            return new Truck(this);
        }
    }
} 