package de.hertzi.cli;

import java.util.HashMap;
import java.util.Map;

public enum Option {
    ADD_CREDIT("Add Credit", Menu.ADD_CREDIT),
    RETURN_CREDIT("Return Credit", Menu.MAIN),
    PURCHASE_PRODUCT("Purchase Product", Menu.PURCHASE_PRODUCT),
    MAIN_MENU("Back To Main Menu", Menu.MAIN),
    EMPTY_COIN_RETURN("Empty Coin Return", Menu.MAIN),

    ADD_COIN_ONE("1 ECU", Menu.MAIN),
    ADD_COIN_TWO("2 ECU", Menu.MAIN),
    ADD_COIN_FIVE("5 ECU", Menu.MAIN),
    ADD_COIN_TEN("10 ECU", Menu.MAIN),

    PURCHASE_WATER("Water", Menu.MAIN),
    PURCHASE_ALCOHOL("Alcohol", Menu.MAIN),
    PURCHASE_JUICE("Juice", Menu.MAIN),

    ;
    static{
        reversMap = new HashMap<>();

    }
    private static Map<String, Option> reversMap;
    private String menuName;
    private Menu nextMenu;

    Option(String menuName, Menu nextMenu){
        this.menuName = menuName;
        this.nextMenu = nextMenu;
    }

    public String getMenuName() {
        return menuName;
    }

    public Menu getNextMenu() {
        return nextMenu;
    }

    public static Option getOptionForMenuName(String menuName){
        if(reversMap.isEmpty()){
            for(Option option : Option.values()){
                reversMap.put(option.menuName, option);
            }
        }
        return reversMap.get(menuName);
    }
}
