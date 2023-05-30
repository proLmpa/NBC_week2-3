package Kiosk.entity;

public class Product extends Menu{
    private String name;
    private String description;
    private float price;
    private float option;

    public Product(String name, String description, float price) {
        this.name = name;
        this.description = description;
        this.price = price;
        option = 0f;
    }

    public Product(String name, String description, float price, float option){
        this.name = name;
        this.description = description;
        this.price = price;
        this.option = option;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDesc() {return description; }

    public float getPrice() { return price; }

    public float getOption() { return option; }
}
