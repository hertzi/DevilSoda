package de.hertzi.cli;

import de.hertzi.enums.Coin;
import de.hertzi.enums.Product;
import de.hertzi.SodaMachine;

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
            List<Option> inputOptions = new ArrayList<>();
            inputOptions.add(Option.ADD_CREDIT);
            if (sodaMachine.getCredit() > 0) {
                inputOptions.add(Option.RETURN_CREDIT);
            }
            if (!sodaMachine.listCoinReturn().isEmpty()) {
                inputOptions.add(Option.EMPTY_COIN_RETURN);
            }
            if (!sodaMachine.listProductsForPrice(sodaMachine.getCredit()).isEmpty()) {
                inputOptions.add(Option.PURCHASE_PRODUCT);
            }
            userInput(inputOptions);

        }
    }

    private void printAddCoinOptions() {
        printWelcomeScreen(false);
        System.out.println();
        List<Option> inputOptions = new ArrayList<>();
        for (Coin coin : Coin.values()) {
            inputOptions.add(Option.getOptionForMenuName(coin.getNumValue() + " ECU"));
        }
        inputOptions.add(Option.MAIN_MENU);
        userInput(inputOptions);
    }

    private void printPurchaseProductOption() {
        printWelcomeScreen(false);
        System.out.println();
        List<Option> inputOptions = new ArrayList<>();
        for (String productString : sodaMachine.listProductsForPrice(sodaMachine.getCredit())) {
            inputOptions.add(Option.getOptionForMenuName(productString));
        }
        inputOptions.add(Option.MAIN_MENU);
        userInput(inputOptions);
    }

    private void userInput(List<Option> options) {
        System.out.println("Your Options:");
        System.out.println("-------------");
        System.out.println();
        for (int i = 0; i < options.size(); i++) {
            System.out.println("" + (i + 1) + ". " + options.get(i).getMenuName());
        }

        int choice = 0;
        boolean isWrongInput = true;
        while (isWrongInput) {
            try {
                Scanner scanner = new Scanner(System.in);
                choice = scanner.nextInt();
                if (choice > 0 && choice <= options.size()) {
                    isWrongInput = false;
                }
            } catch (InputMismatchException ime) {
                // really nothing to do here
                //TODO add log here
            }
            if (isWrongInput) {
                System.out.println("Please make your choice from one of the options 1 - " + options.size());
            }
        }
        executeOption(options.get(choice - 1));
    }

    private void executeOption(final Option option) {
        switch (option) {
            case EMPTY_COIN_RETURN:
                sodaMachine.emptyCoinReturn();
                break;
            case RETURN_CREDIT:
                sodaMachine.emptyCredit();
                break;
            case ADD_COIN_ONE:
                sodaMachine.addCredit(Coin.ONE);
                break;
            case ADD_COIN_TWO:
                sodaMachine.addCredit(Coin.TWO);
                break;
            case ADD_COIN_FIVE:
                sodaMachine.addCredit(Coin.FIVE);
                break;
            case ADD_COIN_TEN:
                sodaMachine.addCredit(Coin.TEN);
                break;
            case PURCHASE_WATER:
                sodaMachine.purchaseProduct(Product.WATER);
                break;
            case PURCHASE_JUICE:
                sodaMachine.purchaseProduct(Product.JUICE);
                break;
            case PURCHASE_ALCOHOL:
                sodaMachine.purchaseProduct(Product.ALOHOL);
                break;
        }
        handleNavigation(option.getNextMenu());
    }

    private void handleNavigation(Menu menu) {
        switch (menu) {
            case MAIN:
                printWelcomeScreen(true);
                break;
            case ADD_CREDIT:
                printAddCoinOptions();
                break;
            case PURCHASE_PRODUCT:
                printPurchaseProductOption();
                break;
        }
    }

    private void clearConsole() {
        final String os = System.getProperty("os.name");
        try {
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (IOException e) {
            System.err.println("Clear screen command could not be executed");
        } catch (InterruptedException e) {
            System.err.println("Waiting for clear screen has been interrupted");
        }
    }
}