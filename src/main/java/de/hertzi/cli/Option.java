package de.hertzi.cli;

public enum Option {
    ADD_CREDIT("Add Credit", Menu.ADD_CREDIT),
    RETURN_CREDIT("Return Credit", Menu.MAIN),
    PURCHASE_PRODUCT("Purchase Product", Menu.PURCHASE_PRODUCT),
    MAIN_MENU("Back To Main Menu", Menu.MAIN),
    EMPTY_COIN_RETURN("Empty Coin Return", Menu.MAIN),

    ADD_COIN("", Menu.MAIN),
    PURCHASE_ITEM("", Menu.MAIN),;

    private String menuName;
    private Menu nextMenu;

    Option(String menuName, Menu nextMenu) {
        this.menuName = menuName;
        this.nextMenu = nextMenu;
    }

    public String getMenuName() {
        return menuName;
    }

    public Menu getNextMenu() {
        return nextMenu;
    }
}
