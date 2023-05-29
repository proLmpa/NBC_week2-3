package Kiosk.entity;

public class Product extends Menu{
    private String name;
    private String description;
    private String price;

    Product() {
        super();
        price = "W 6.9";
    }
    public Product(String name, String description, String price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDesc() {return description; }

    public String getPrice() { return price; }
}
