package Kiosk.entity;

public class Menu {
    private String name;
    private String description;

    Menu() {}

    public Menu(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName(){
        return name;
    }

    public String getDesc() {return description; }
}
