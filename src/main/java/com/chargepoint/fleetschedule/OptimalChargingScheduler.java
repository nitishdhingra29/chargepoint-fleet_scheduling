package com.chargepoint.fleetschedule;

import java.util.*;

public class OptimalChargingScheduler implements ChargingScheduler {
    @Override
    public Map<String, List<String>> createSchedule(List<Truck> trucks, List<Charger> chargers, int timeLimit) {
        Map<String, List<String>> schedule = new HashMap<>();
        for (Charger charger : chargers) {
            schedule.put(charger.getId(), new ArrayList<>());
        }

        if (trucks.isEmpty() || chargers.isEmpty() || timeLimit <= 0) {
            return schedule;
        }

        // Create PriorityQueue for trucks sorted by charging need (descending)
        PriorityQueue<Truck> truckQueue = new PriorityQueue<>((t1, t2) -> 
            Double.compare(t2.getRemainingChargeNeeded(), t1.getRemainingChargeNeeded()));
        truckQueue.addAll(trucks);

        // Create PriorityQueue for chargers sorted by charging rate (descending)
        PriorityQueue<Charger> chargerQueue = new PriorityQueue<>((c1, c2) -> 
            Double.compare(c2.getChargingRate(), c1.getChargingRate()));
        chargerQueue.addAll(chargers);

        // Track remaining time for each charger
        Map<String, Double> remainingTime = new HashMap<>();
        for (Charger charger : chargers) {
            remainingTime.put(charger.getId(), (double) timeLimit);
        }

        // Assign trucks to chargers
        while (!truckQueue.isEmpty() && !chargerQueue.isEmpty()) {
            Truck truck = truckQueue.poll();
            Charger bestCharger = null;
            double bestTime = Double.MAX_VALUE;

            // Find the best charger for this truck
            List<Charger> tempChargers = new ArrayList<>();
            while (!chargerQueue.isEmpty()) {
                Charger charger = chargerQueue.poll();
                tempChargers.add(charger);

                double chargingTime = truck.getTimeToFullCharge(charger.getChargingRate());
                if (chargingTime <= remainingTime.get(charger.getId()) && chargingTime < bestTime) {
                    bestTime = chargingTime;
                    bestCharger = charger;
                }
            }

            // Assign truck if a suitable charger was found
            if (bestCharger != null) {
                schedule.get(bestCharger.getId()).add(truck.getId());
                remainingTime.put(bestCharger.getId(), 
                    remainingTime.get(bestCharger.getId()) - bestTime);
            }

            // Return chargers to queue
            chargerQueue.addAll(tempChargers);
        }

        return schedule;
    }
} 