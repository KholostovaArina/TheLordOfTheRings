package com.mycompany.army;

import com.github.javafaker.Faker;
import java.util.Random;
import org.apache.commons.lang3.RandomUtils;

public abstract class OrkBuilder {
    private final String[] suffixes = {"ur"," gash", "ug","bash","rud", "essa" , "zak"};
    protected final String name = (new Faker()).lordOfTheRings().character()+suffixes[(new Random()).nextInt(7)]; // Имя орка
    protected String role;
    protected Weapon weapon;
    protected Armor armor;
    protected Banner banner;
    protected int strength;
    protected int agility;
    protected int intelligence;
    protected int health;
    protected String additionalItem = null;

    protected final OrcGearFactory gearFactory;

    protected OrkBuilder(OrcGearFactory gearFactory) {
        this.gearFactory = gearFactory;
    }

    public OrkBuilder setAdditionalItem(String additionalItem) {
        this.additionalItem = additionalItem;
        return this;
    }
    
    public OrkBuilder setRole(String role) {
        this.role = role;
        return this;
    }

    public OrkBuilder setWeapon(Weapon weapon) {
        this.weapon = weapon;
        return this;
    }

    public OrkBuilder setArmor(Armor armor) {
        this.armor = armor;
        return this;
    }

    public OrkBuilder setBanner(Banner banner) {
        this.banner = banner;
        return this;
    }

    public OrkBuilder setStrength(int strength) {
        this.strength = strength;
        return this;
    }

    public OrkBuilder setAgility(int agility) {
        this.agility = agility;
        return this;
    }

    public OrkBuilder setIntelligence(int intelligence) {
        this.intelligence = intelligence;
        return this;
    }

    public OrkBuilder setHealth(int health) {
        this.health = health;
        return this;
    }

    public abstract Ork build();
}