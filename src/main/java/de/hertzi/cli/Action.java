package de.hertzi.cli;

public class Action {
    private Option option;
    private String menuName;
    private Menu nextMenu;
    private Object arg;

    Action(Option option) {
        this.option = option;
        this.menuName = option.getMenuName();
        this.nextMenu = option.getNextMenu();
    }

    Action(Option option, String menuName, Object arg) {
        this.option = option;
        this.menuName = menuName;
        this.nextMenu = option.getNextMenu();
        this.arg = arg;
    }

    public Option getOption() {
        return option;
    }

    public String getMenuName() {
        return menuName;
    }

    public Menu getNextMenu() {
        return nextMenu;
    }

    public Object getArg() {
        return arg;
    }
}
