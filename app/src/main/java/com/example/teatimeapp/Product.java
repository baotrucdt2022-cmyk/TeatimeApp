package com.example.teatimeapp;

public class Product {
    private String name;
    private String price;
    private int imageResource;
    private String description;
    private String category;

    public Product(String name, String price, int imageResource, String description, String category) {
        this.name = name;
        this.price = price;
        this.imageResource = imageResource;
        this.description = description;
        this.category = category;
    }

    public String getName() { return name; }
    public String getPrice() { return price; }
    public int getImageResource() { return imageResource; }
    public String getDescription() { return description; }
    public String getCategory() { return category; }
}
