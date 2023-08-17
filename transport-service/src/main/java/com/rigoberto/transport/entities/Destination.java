package com.rigoberto.transport.entities;

public enum Destination {
    BURDEN,
    PASSAGE,
    WALK;

    public static Destination fromString(String x) {
        return switch (x) {
            case "BURDEN" -> BURDEN;
            case "PASSAGE" -> PASSAGE;
            default -> WALK;
        };
    }
}
