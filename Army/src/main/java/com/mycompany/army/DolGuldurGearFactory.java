
package com.mycompany.army;

import com.mycompany.army.OrcGearFactory;

public class DolGuldurGearFactory implements OrcGearFactory {
    @Override
    public Weapon createWeapon() {
        return new Spear("Копье");
    }

    @Override
    public Armor createArmor() {
        return new ChainMail("Кольчуга");
    }

    @Override
    public Banner createBanner() {
        return new BannerWithASpider("Знамя с пауком");
    }
}