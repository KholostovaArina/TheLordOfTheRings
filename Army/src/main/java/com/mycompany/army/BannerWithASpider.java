package com.mycompany.army;

public class BannerWithASpider implements Banner {
    private final String name = "Знамя с пауком";

    @Override
    public String getName() {
        return name;
    }
}