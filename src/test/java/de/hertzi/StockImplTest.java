package de.hertzi;


import de.hertzi.component.Stock;
import de.hertzi.component.StockImpl;
import de.hertzi.enums.Product;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class StockImplTest {

    @Test
    public void releaseProductAlcoholTwice_noAlcoholInProductList(){
        Stock stock = new StockImpl();

        Assert.assertTrue(stock.listProducts().contains(Product.ALOHOL));

        stock.releaseProduct(Product.ALOHOL);
        stock.releaseProduct(Product.ALOHOL);

        Assert.assertFalse(stock.listProducts().contains(Product.ALOHOL));
    }

    @Test
    public void initializeStock_stockContainsRightProducts(){
        Stock stock = new StockImpl();

        Product[] products = new Product[]{Product.ALOHOL, Product.ALOHOL, Product.JUICE, Product.JUICE, Product.WATER,
                                            Product.WATER, Product.WATER};
        List<Product> expectedProducts = Arrays.asList(products);

        List<Product> actualProducts = stock.listProducts();

        Assert.assertTrue(expectedProducts.containsAll(actualProducts) && actualProducts.containsAll(expectedProducts));
    }
}