
package com.mycompany.army;

import com.mycompany.army.*;
import com.mycompany.army.DolGuldurGearFactory;


public class DolGuldurOrkBuilderFactory implements OrkBuilderFactory {
    private final OrcGearFactory gearFactory = new DolGuldurGearFactory();

    @Override
    public OrkBuilder createOrkBuilder() {
        return new OrkBuilder()
                .setWeapon(gearFactory.createWeapon())
                .setArmor(gearFactory.createArmor())
                .setBanner(gearFactory.createBanner())
                .setStrength(80)
                .setAgility(40)
                .setIntelligence(30)
                .setHealth(150);
    }
}
