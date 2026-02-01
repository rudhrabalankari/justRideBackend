package com.car.rental.justride.model;

public enum CarFeatureEnum {

    AIR_CONDITIONING("Air Conditioning"),
    BLUETOOTH_CONNECTIVITY("Bluetooth Connectivity"),
    BACKUP_CAMERA("Backup Camera"),
    APPLE_CARPLAY_ANDROID_AUTO("Apple CarPlay & Android Auto"),
    CRUISE_CONTROL("Cruise Control"),
    KEYLESS_ENTRY("Keyless Entry"),
    POWER_WINDOWS_LOCKS("Power Windows & Locks"),
    USB_PORTS("USB Ports");

    private final String displayName;

    CarFeatureEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
