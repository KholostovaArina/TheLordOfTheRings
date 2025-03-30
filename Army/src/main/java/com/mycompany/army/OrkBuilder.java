package com.mycompany.army;

public class OrkBuilder {
    private String name;
    private Weapon weapon;
    private Armor armor;
    private Banner banner;
    private int strength;
    private int agility;
    private int intelligence;
    private int health;

    public OrkBuilder setName(String name) {
        this.name = name;
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

    public int getIntelligence() {
        return intelligence;
    }

    public int getAgility() {
        return agility;
    }

    public Ork build() {
        return new Ork(name, weapon, armor, banner, strength, agility, intelligence, health);
    }
}