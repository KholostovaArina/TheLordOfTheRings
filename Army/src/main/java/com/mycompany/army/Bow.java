package com.mycompany.army;

public class Bow  implements Weapon {
    private final String name = "Лук";

    @Override
    public String getName() {
        return name;
    }
}