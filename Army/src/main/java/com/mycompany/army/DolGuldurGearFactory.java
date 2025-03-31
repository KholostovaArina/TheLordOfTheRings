package com.mycompany.army;

public class DolGuldurGearFactory implements OrcGearFactory {
    @Override
    public Weapon createWeapon() {
        return new Spear();
    }

    @Override
    public Armor createArmor() {
        return new ChainMail();
    }

    @Override
    public Banner createBanner() {
        return new BannerWithASpider();
    }
}