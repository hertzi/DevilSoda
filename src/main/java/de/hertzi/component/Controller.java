package de.hertzi.component;

import de.hertzi.enums.Coin;
import de.hertzi.enums.Product;

import java.util.List;

public interface Controller {
    int addCredit(Coin coin);
    void emptyCoinReturn();

    List<Coin> listCoinReturn();

    void emptyCredit();
    int getCredit();


    List<String> listProductsWithPrices();
    List<String> listProductsForPrice(int price);

    void purchaseProduct(Product product);
}
