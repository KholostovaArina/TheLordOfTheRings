
package com.mycompany.army;

import com.mycompany.army.Weapon;

public class Spear implements Weapon {
    private final String name;

    public Spear(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
