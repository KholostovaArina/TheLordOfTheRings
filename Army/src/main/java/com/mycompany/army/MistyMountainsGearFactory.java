
package com.mycompany.army;

import com.mycompany.army.OrcGearFactory;
import com.mycompany.army.LeatherArmor;

public class MistyMountainsGearFactory implements OrcGearFactory {
    @Override
    public Weapon createWeapon() {
        return new Axe("Топор");
    }

    @Override
    public Armor createArmor() {
        return new LeatherArmor("Кожаная броня");
    }

    @Override
    public Banner createBanner() {
        return new BannerWithMoon("Знамя с Луной");
    }
}