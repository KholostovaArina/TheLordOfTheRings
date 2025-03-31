package com.mycompany.army;

import com.github.javafaker.Faker;

public class OrkBuilder {
    private final String name = (new Faker()).lordOfTheRings().character();//имена не оч стоит массив делать
    private String role;
    private Weapon weapon;
    private Armor armor;
    private Banner banner;
    private int strength;
    private int agility;
    private int intelligence;
    private int health; 
    private String additionalItem = null;
    
    public OrkBuilder setRole(String role) {
        this.role = role;
        return this;
    }
    
    public OrkBuilder setAdditionalItem(String additionalItem) {
        this.additionalItem = additionalItem;
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

    public String getAdditionalItem() {
        return additionalItem;
    }

    public Ork build() {
        return new Ork(name, role, weapon, armor, banner, strength, agility, intelligence, health, additionalItem);
    }
}
