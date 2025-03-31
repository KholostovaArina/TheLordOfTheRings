package com.mycompany.army;

import java.util.Random;

public class DolGuldurOrkBuilderFactory implements OrkBuilderFactory {
    private final OrcGearFactory gearFactory = new DolGuldurGearFactory();

    @Override
    public OrkBuilder createOrkBuilder() {
        Random rand = new Random();
        return new OrkBuilder()
                .setWeapon(gearFactory.createWeapon())
                .setArmor(gearFactory.createArmor())
                .setBanner(gearFactory.createBanner())
                .setStrength(rand.nextInt(101))
                .setAgility(rand.nextInt(100)+1)
                .setIntelligence(rand.nextInt(50)+1)
                .setHealth(rand.nextInt(151)+50);
    }
}
