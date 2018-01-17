package de.hertzi.enums;

public enum Product {
    ALOHOL("Alcohol", 5),
    JUICE("Juice", 4),
    WATER("Water", 3);

    private int price;
    private String menuName;

    Product(String menuName, int price){
        this.menuName = menuName;
        this.price = price;
    }

    public int getPrice(){
        return price;
    }

    public String getMenuName(){
        return menuName;
    }
}
