package com.rigoberto.transport.entities;

public enum TypeVehicle {
    LAND,
    AERIAL,
    MARITIME;

    public static TypeVehicle fromString(String x) {
        return switch (x) {
            case "MARITIME" -> MARITIME;
            case "AERIAL" -> AERIAL;
            default -> LAND;
        };
    }

}
