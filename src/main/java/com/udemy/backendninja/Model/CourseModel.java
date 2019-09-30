package com.udemy.backendninja.Model;

public class CourseModel {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Course{" +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", hours=" + hours +
                '}';
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    private String description;

    private int price;

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    private int hours;

    public CourseModel(){}

    public CourseModel(String name, String description, int price, int hours){
        super();
        this.name = name;
        this.description = description;
        this.price = price;
        this.hours = hours;
    }
}
