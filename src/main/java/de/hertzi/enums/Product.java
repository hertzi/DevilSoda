package de.hertzi.enums;

import java.util.HashMap;
import java.util.Map;

public enum Product {
    ALOHOL("Alcohol", 5),
    JUICE("Juice", 4),
    WATER("Water", 3),

    RENE("Rene", 1),
    ;

    private int price;
    private String menuName;

    private static Map<String, Product> reversMap;

    static{
        reversMap = new HashMap<>();
    }

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

    public static Product getProductForMenuName(String menuName){
        if(reversMap.isEmpty()){
            for(Product product : Product.values()){
                reversMap.put(product.menuName, product);
            }
        }
        return reversMap.get(menuName);
    }
}
