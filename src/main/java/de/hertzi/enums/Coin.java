package de.hertzi.enums;

import java.util.HashMap;
import java.util.Map;

public enum Coin {
    ONE(1),
    TWO(2),
    FIVE(5),
    TEN(10);

    private int numValue;
    private String menuNmae;
    private static Map<String, Coin> reverseMap;

    Coin(int numValue){
        this.numValue = numValue;
        this.menuNmae = ""+numValue;
    }

    public int getNumValue(){
        return numValue;
    }

    public String getMenuName(){
        return menuNmae;
    }

    public static Coin getCoinForMenuName(String menuName){
        if(reverseMap == null){
            reverseMap = new HashMap<>();
            for(Coin coin : Coin.values()){
                reverseMap.put(coin.menuNmae, coin);
            }
        }
        return reverseMap.get(menuName);
    }
}
