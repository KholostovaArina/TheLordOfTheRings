
package com.mycompany.army;

import com.mycompany.army.OrcGearFactory;
import com.mycompany.army.SteelArmor;

public class MordorGearFactory implements OrcGearFactory {
    @Override
    public Weapon createWeapon() {
        return new HeavySword("Тяжелый меч");
    }

    @Override
    public Armor createArmor() {
        return new SteelArmor("Стальная броня");
    }

    @Override
    public Banner createBanner() {
        return new BannerWithRedEye("Знамя с Красным Оком");
    }
}