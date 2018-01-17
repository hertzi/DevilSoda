package de.hertzi;

import de.hertzi.component.CashBox;
import de.hertzi.component.Controller;
import de.hertzi.component.StockImpl;
import de.hertzi.enums.Coin;
import de.hertzi.enums.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SodaMachine implements Controller {
    private CashBox cashBox = new CashBox(true);
    private CashBox purchaseBuffer = new CashBox();
    private StockImpl stock = new StockImpl();
    private List<Coin> coinReturn = new ArrayList<>();

    @Override
    public int addCredit(Coin coin) {
        return purchaseBuffer.add(coin);
    }

    @Override
    public int getCredit() {
        return purchaseBuffer.sum();
    }

    @Override
    public List<Coin> listCoinReturn(){
        return coinReturn;
    }

    @Override
    public void emptyCredit(){
        coinReturn.addAll(purchaseBuffer.returnAllCoins());
    }

    @Override
    public void emptyCoinReturn(){
        coinReturn.clear();
    }

    @Override
    public List<String> listProductsWithPrices() {
        return stock.listProducts().stream().map(x -> x.getMenuName() + " for " + x.getPrice() + " ECU")
                                            .collect(Collectors.toList());
    }

    @Override
    public List<String> listProductsForPrice(int price) {
        return stock.listProductsForPrice(price).stream().map(Product::getMenuName).collect(Collectors.toList());
    }


    @Override
    public void purchaseProduct(Product product) {
        stock.releaseProduct(product);
        //fixme has product been rleased? what to do in error case?
        int credit = purchaseBuffer.sum();
        cashBox.add(purchaseBuffer.returnAllCoins());
        purchaseBuffer.returnAllCoins();
        coinReturn.addAll(cashBox.returnChange(credit - product.getPrice()));
    }

}
