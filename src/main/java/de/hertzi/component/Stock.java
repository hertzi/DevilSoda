package de.hertzi.component;

import de.hertzi.enums.Product;

import java.util.List;
import java.util.Optional;

public interface Stock {
    Optional<Product> releaseProduct(Product product);
    List<Product> listProducts();
    List<Product> listProductsForPrice(int price);


    List<String> listInventory();
}
