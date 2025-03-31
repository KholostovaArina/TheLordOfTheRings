package com.mycompany.army;

import java.util.Random;

public class MordorOrkBuilder extends OrkBuilder {

    public MordorOrkBuilder(OrcGearFactory gearFactory) {
        super(gearFactory);
    }

    @Override
    public Ork build() {
        Random rand = new Random();
        return new Ork(
                name,
                role,
                weapon != null ? weapon : gearFactory.createWeapon(),
                gearFactory.createArmor(),
                gearFactory.createBanner(),
                rand.nextInt(130)+1,
                rand.nextInt(70) + 1,
                rand.nextInt(50) + 1,
                rand.nextInt(196) + 5,
                additionalItem
        );
    }
}