package HW;

public class Menu {
    String name;
    String description;

    Menu() {}

    Menu(String name, String description){
        this.name = name;
        this.description = description;
    }

    String getName(){
        return name;
    }

    String getMenu(){
        return name + " \t\t| " + description;
    }
}
