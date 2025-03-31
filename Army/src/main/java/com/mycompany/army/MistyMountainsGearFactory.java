package com.mycompany.army;

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