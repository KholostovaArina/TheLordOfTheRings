package com.mycompany.army;

public class Ork {
    private String name;
    private String role;
    private Weapon weapon;
    private Armor armor;
    private Banner banner;
    private int strength;
    private int agility;
    private int intelligence;
    private int health;

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

//    @Override
//    public String toString() {
//        return "Ork{" +
//                "name='" + name + '\'' +
//                ", weapon=" + weapon.getName() +
//                ", armor=" + armor.getName() +
//                ", banner=" + banner.getName() +
//                ", strength=" + strength +
//                ", agility=" + agility +
//                ", intelligence=" + intelligence +
//                ", health=" + health +
//                '}';
//    }
}
