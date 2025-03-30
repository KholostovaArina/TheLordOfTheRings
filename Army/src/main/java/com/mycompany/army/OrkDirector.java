
package com.mycompany.army;


public class OrkDirector {
    private OrkBuilder builder;

    public OrkDirector(OrkBuilder builder) {
        this.builder = builder;
    }

    public Ork createBasicOrk() {
        return builder.setName("Базовый орк").build();
    }

    public Ork createLeaderOrk() {
        return builder.setName("Командир")
                .setIntelligence(builder.getIntelligence() + 20)
                .build();
    }

    public Ork createScoutOrk() {
        return builder.setName("Разведчик")
                .setAgility(builder.getAgility() + 20)
                .build();
    }
}
