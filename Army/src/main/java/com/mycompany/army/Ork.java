package com.mycompany.army;

public class Ork {
    private final String name;
    private final String role;
    private final Weapon weapon;
    private final Armor armor;
    private final Banner banner;
    private final int strength;
    private final int agility;
    private final int intelligence;
    private final int health;

    public Ork(String name, String role, Weapon weapon, Armor armor, Banner banner, int strength, int agility, int intelligence, int health) {
        this.name = name;
        this.role = role;
        this.weapon = weapon;
        this.armor = armor;
        this.banner = banner;
        this.strength = strength;
        this.agility = agility;
        this.intelligence = intelligence;
        this.health = health;
    }

    public String getName() { return name; }
    public String getRole() { return role; }
    public Weapon getWeapon() { return weapon; }
    public Armor getArmor() { return armor; }
    public Banner getBanner() { return banner; }
    public int getStrength() { return strength; }
    public int getAgility() { return agility; }
    public int getIntelligence() { return intelligence; }
    public int getHealth() { return health; }
}
