package com.mycompany.army;

public class MordorGearFactory implements OrcGearFactory {
    @Override
    public Weapon createWeapon() {
        return new HeavySword();
    }

    @Override
    public Armor createArmor() {
        return new SteelArmor();
    }

    @Override
    public Banner createBanner() {
        return new BannerWithRedEye();
    }
}