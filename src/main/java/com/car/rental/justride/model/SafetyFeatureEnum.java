package com.car.rental.justride.model;

public enum SafetyFeatureEnum {
    AIRBAGS_FRONT_SIDE("Airbags (Front & Side)"),
    ANTI_LOCK_BRAKES("Anti-lock Brakes (ABS)"),
    STABILITY_CONTROL("Stability Control"),
    TRACTION_CONTROL("Traction Control"),
    LANE_DEPARTURE_WARNING("Lane Departure Warning"),
    COLLISION_MITIGATION_BRAKING("Collision Mitigation Braking"),
    BLIND_SPOT_MONITORING("Blind Spot Monitoring"),
    ADAPTIVE_CRUISE_CONTROL("Adaptive Cruise Control"),
    REAR_CROSS_TRAFFIC_ALERT("Rear Cross Traffic Alert"),
    FORWARD_COLLISION_WARNING("Forward Collision Warning"),
    AUTOMATIC_EMERGENCY_BRAKING("Automatic Emergency Braking"),
    LANE_KEEPING_ASSIST("Lane Keeping Assist");

    private final String displayName;

    SafetyFeatureEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}