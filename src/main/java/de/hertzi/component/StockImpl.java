package de.hertzi.component;

import de.hertzi.enums.Product;

import java.util.*;
import java.util.stream.Collectors;

public class StockImpl implements Stock {
    private Map<Product, Integer> products = new HashMap<>();

    public StockImpl() {
        init();
    }

    private void init() {
        products.put(Product.ALOHOL, 2);
        products.put(Product.JUICE, 2);
        products.put(Product.WATER, 3);
    }

    @Override
    public Optional<Product> releaseProduct(Product product) {
        if (!products.containsKey(product)) {
            return Optional.empty();
        }
        if (isLastProduct(product)) {
            products.remove(product);
        } else {
            int newAmount = products.get(product) - 1;
            products.put(product, newAmount);
        }
        return Optional.of(product);
    }

    @Override
    public List<String> listInventory() {
        return products.entrySet().stream()
                       .map(x -> x.getValue().toString() + "\\t" + x.getKey().getMenuName())
                       .collect(Collectors.toList());
    }

    @Override
    public List<Product> listProducts() {
        return new ArrayList<>(products.keySet());
    }

    @Override
    public List<Product> listProductsForPrice(int price) {
        return products.keySet().stream().filter(x -> x.getPrice() <= price).collect(Collectors.toList());
    }


    private boolean isLastProduct(Product product) {
        return products.containsKey(product) && products.get(product) == 1;
    }


}
