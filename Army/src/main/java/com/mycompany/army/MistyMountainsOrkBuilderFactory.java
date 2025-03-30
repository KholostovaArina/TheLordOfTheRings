
package com.mycompany.army;

import java.util.Random;


public class MistyMountainsOrkBuilderFactory implements OrkBuilderFactory {
    private final OrcGearFactory gearFactory = new MistyMountainsGearFactory();

   @Override
    public OrkBuilder createOrkBuilder() {
        Random rand = new Random();
        return new OrkBuilder()
                .setWeapon(gearFactory.createWeapon())
                .setArmor(gearFactory.createArmor())
                .setBanner(gearFactory.createBanner())
                .setStrength(rand.nextInt(101))
                .setAgility(rand.nextInt(130)+1)
                .setIntelligence(rand.nextInt(30)+1)
                .setHealth(rand.nextInt(151)+50);
    }
}
