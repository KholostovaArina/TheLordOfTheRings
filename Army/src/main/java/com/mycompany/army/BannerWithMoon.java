
package com.mycompany.army;

public class BannerWithMoon implements Banner {
    private final String name;

    public BannerWithMoon(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}