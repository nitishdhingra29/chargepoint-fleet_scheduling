package com.chargepoint.fleetschedule;

public class Charger {
    private final String id;
    private final double chargingRate; // in kilowatts

    private Charger(Builder builder) {
        this.id = builder.id;
        this.chargingRate = builder.chargingRate;
    }

    public String getId() {
        return id;
    }

    public double getChargingRate() {
        return chargingRate;
    }

    public static class Builder {
        private String id;
        private double chargingRate;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder chargingRate(double chargingRate) {
            this.chargingRate = chargingRate;
            return this;
        }

        public Charger build() {
            return new Charger(this);
        }
    }
} 