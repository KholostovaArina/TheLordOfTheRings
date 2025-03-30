
package com.mycompany.army;

import com.mycompany.army.Weapon;

public class Axe implements Weapon {
    private final String name;

    public Axe(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}