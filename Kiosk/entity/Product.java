package Kiosk.entity;

public class Product extends Menu{
    private float price;
    private float option;

    public Product(String name, String description, float price) {
        super(name, description);
        this.price = price;
        option = 0f;
    }

    public Product(String name, String description, float price, float option){
        super(name, description);
        this.price = price;
        this.option = option;
    }

    public float getPrice() { return price; }

    public float getOption() { return option; }
}
