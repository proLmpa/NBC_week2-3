package HW;

public class Product extends Menu{
    String name;
    String description;
    String price;

    Product() {
        super();
        price = "W 6.9";
    }
    Product(String name, String description, String price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getMenu() {
        return name + " \t\t| " + price + " | "+ description ;
    }

    public float getPrice() {
        return Float.parseFloat(price.split(" ")[1]);
    }
}
