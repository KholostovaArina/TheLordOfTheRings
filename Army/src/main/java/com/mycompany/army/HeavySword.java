package com.mycompany.army;

public class HeavySword implements Weapon {
    private final String name = "Тяжёлый меч";

    @Override
    public String getName() {
        return name;
    }
}
