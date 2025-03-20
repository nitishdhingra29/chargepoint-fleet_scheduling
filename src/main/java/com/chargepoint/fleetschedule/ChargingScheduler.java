package com.chargepoint.fleetschedule;

import java.util.List;
import java.util.Map;

public interface ChargingScheduler {
    /**
     * Creates a charging schedule for the given trucks and chargers within the specified time limit.
     *
     * @param trucks List of trucks that need charging
     * @param chargers List of available chargers
     * @param timeLimit Time limit in hours
     * @return Map where key is charger ID and value is list of truck IDs to be charged on that charger
     */
    Map<String, List<String>> createSchedule(List<Truck> trucks, List<Charger> chargers, int timeLimit);
} 