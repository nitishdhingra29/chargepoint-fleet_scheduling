package com.chargepoint.fleetschedule;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class FleetChargingDemo {
    public static void main(String[] args) {
        List<Truck> trucks = Arrays.asList(
                new Truck.Builder().id("1").batteryCapacity(200.0).currentChargeLevel(100.0).build(),
                new Truck.Builder().id("2").batteryCapacity(300.0).currentChargeLevel(200.0).build()
        );

        List<Charger> chargers = Arrays.asList(
                new Charger.Builder().id("1").chargingRate(200.0).build(),  // 200 kW
                new Charger.Builder().id("2").chargingRate(300.0).build()   // 300 kW
        );





        ChargingScheduler scheduler = SchedulerFactory.createScheduler(SchedulingStrategy.OPTIMAL);
        Map<String, List<String>> schedule = scheduler.createSchedule(trucks, chargers, 1); // 8 hours time limit

        // Print the schedule
        System.out.println("Charging Schedule:");
        schedule.forEach((chargerId, truckIds) -> {
            System.out.printf("Charger %s: %s%n", chargerId, String.join(", ", truckIds));
        });
    }
} 