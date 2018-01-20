package de.hertzi.cli;

import de.hertzi.SodaMachine;
import de.hertzi.enums.Coin;

import java.io.IOException;
import java.util.*;

public class SodaCli {
    SodaMachine sodaMachine;

    public SodaCli(SodaMachine sodaMachine) {
        this.sodaMachine = sodaMachine;
    }

    public void start() {
        printWelcomeScreen(true);
    }

    private void printWelcomeScreen(boolean isMainMenu) {
        boolean hasOptions = true;
        clearConsole();
        System.out.println("Welcome to DevilSoda");
        System.out.println("--------------------");
        System.out.println();
        System.out.println("This machine sells:");
        System.out.println();
        List<String> productsWithPrices = sodaMachine.listProductsWithPrices();
        if (productsWithPrices.isEmpty()) {
            System.out.println("Nothing at all - wait for restock");
            hasOptions = false;
        } else {
            for (String productString : productsWithPrices) {
                System.out.println(productString);
            }
        }
        System.out.println();
        System.out.println("Credit: " + sodaMachine.getCredit());
        System.out.println("Coin Return: " + Arrays.toString(sodaMachine.listCoinReturn().toArray()));
        System.out.println();
        if (hasOptions && isMainMenu) {
            List<Action> intputActions = new ArrayList<>();
            intputActions.add(new Action(Option.ADD_CREDIT));
            if (sodaMachine.getCredit() > 0) {
                intputActions.add(new Action(Option.RETURN_CREDIT));
            }
            if (!sodaMachine.listCoinReturn().isEmpty()) {
                intputActions.add(new Action(Option.EMPTY_COIN_RETURN));
            }
            if (!sodaMachine.listProductsForPrice(sodaMachine.getCredit()).isEmpty()) {
                intputActions.add(new Action(Option.PURCHASE_PRODUCT));
            }
            userInput(intputActions);

        }
    }

    private void printAddCoinActions() {
        printWelcomeScreen(false);
        System.out.println();
        List<Action> inputActions = new ArrayList<>();
        for (Coin coin : Coin.values()) {
            inputActions.add(new Action(Option.ADD_COIN, coin.getMenuName() + " ECU", coin.getMenuName()));
        }
        inputActions.add(new Action(Option.MAIN_MENU));
        userInput(inputActions);
    }

    private void printPurchaseProductActions() {
        printWelcomeScreen(false);
        System.out.println();
        List<Action> inputActions = new ArrayList<>();
        for (String productString : sodaMachine.listProductsForPrice(sodaMachine.getCredit())) {
            inputActions.add(new Action(Option.PURCHASE_ITEM, productString, productString));
        }
        inputActions.add(new Action(Option.MAIN_MENU));
        userInput(inputActions);
    }


    private void userInput(List<Action> actions) {
        System.out.println("Your Options:");
        System.out.println("-------------");
        System.out.println();
        for (int i = 0; i < actions.size(); i++) {
            System.out.println("" + (i + 1) + ". " + actions.get(i).getMenuName());
        }

        int choice = 0;
        boolean isWrongInput = true;
        while (isWrongInput) {
            try {
                Scanner scanner = new Scanner(System.in);
                choice = scanner.nextInt();
                if (choice > 0 && choice <= actions.size()) {
                    isWrongInput = false;
                }
            } catch (InputMismatchException ime) {
                // really nothing to do here
                //TODO add log here
            }
            if (isWrongInput) {
                System.out.println("Please make your choice from one of the options 1 - " + actions.size());
            }
        }
        executeAction(actions.get(choice - 1));
    }

    private void executeAction(final Action action) {
        switch (action.getOption()) {
            case EMPTY_COIN_RETURN:
                sodaMachine.emptyCoinReturn();
                break;
            case RETURN_CREDIT:
                sodaMachine.emptyCredit();
                break;
            case ADD_COIN:
                sodaMachine.addCredit((String) action.getArg());
                break;
            case PURCHASE_ITEM:
                sodaMachine.purchaseProduct((String) action.getArg());
                break;
        }
        handleNavigation(action.getNextMenu());
    }

    private void handleNavigation(Menu menu) {
        switch (menu) {
            case MAIN:
                printWelcomeScreen(true);
                break;
            case ADD_CREDIT:
                printAddCoinActions();
                break;
            case PURCHASE_PRODUCT:
                printPurchaseProductActions();
                break;
        }
    }

    private void clearConsole() {
        final String os = System.getProperty("os.name");
        try {
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException e) {
            System.err.println("Clear screen command could not be executed");
        } catch (InterruptedException e) {
            System.err.println("Waiting for clear screen has been interrupted");
        }
    }
}