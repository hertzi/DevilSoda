package de.hertzi.component;

import de.hertzi.enums.Coin;
import de.hertzi.enums.Product;

import java.util.List;

public interface Controller {
    void emptyCoinReturn();

    List<Coin> listCoinReturn();

    void emptyCredit();

    int addCredit(String coinString);

    int getCredit();


    List<String> listProductsWithPrices();
    List<String> listProductsForPrice(int price);

    void purchaseProduct(String productString);
}
