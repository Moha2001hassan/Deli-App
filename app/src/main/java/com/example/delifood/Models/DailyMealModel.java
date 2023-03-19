package com.example.delifood.Models;

public class DailyMealModel {

    int image;
    String name;
    String description;
    String type;
    String discount;

    public DailyMealModel(int image, String name, String discount, String description, String type) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.discount = discount;
        this.type = type;
    }


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}
