package com.mycompany.army;

public class OrkDirector {
    private final OrkBuilder builder;

    public OrkDirector(OrkBuilder builder) {
        this.builder = builder;
    }

    public Ork createBasicOrk() {
        return builder.setRole("Базовый орк").build();
    }

   public Ork createLeaderOrk() {
        return builder
                .setRole("Командир")
                .setAdditionalItem("Знамя и горн")
                .build();
    }

    public Ork createScoutOrk() {
        return builder
                .setRole("Разведчик")
                .setWeapon(new Bow())
                .build();
    }
}
