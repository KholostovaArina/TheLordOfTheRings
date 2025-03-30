
package com.mycompany.army;

public class BannerWithASpider implements Banner {
    private final String name;

    public BannerWithASpider(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}