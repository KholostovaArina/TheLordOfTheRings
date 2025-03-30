package com.mycompany.army;

import java.util.Random;

public class MordorOrkBuilderFactory implements OrkBuilderFactory {
    private final OrcGearFactory gearFactory = new MordorGearFactory();

    @Override
    public OrkBuilder createOrkBuilder() {
        Random rand = new Random();
        return new OrkBuilder()
                .setWeapon(gearFactory.createWeapon())
                .setArmor(gearFactory.createArmor())
                .setBanner(gearFactory.createBanner())
                .setStrength(rand.nextInt(131))
                .setAgility(rand.nextInt(70)+1)
                .setIntelligence(rand.nextInt(50)+1)
                .setHealth(rand.nextInt(151)+50);
    }
}

