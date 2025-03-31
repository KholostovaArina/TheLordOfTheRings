package com.mycompany.army;

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