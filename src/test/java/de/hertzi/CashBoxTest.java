package de.hertzi;

import de.hertzi.component.CashBox;
import de.hertzi.enums.Coin;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CashBoxTest {

    @Test
    public void addCoinToEmptyCashBox_cashBoxSumEqualsCoinsValue() {
        CashBox cashBox = new CashBox(false);

        Assert.assertEquals(0, cashBox.sum());

        Coin coin = Coin.FIVE;
        cashBox.add(coin);

        Assert.assertEquals(coin.getNumValue(), cashBox.sum());
    }

    @Test
    public void initializeCashBox_cashBoxSumEquals37(){
        CashBox cashBox = new CashBox(true);

        Assert.assertEquals(37, cashBox.sum());
    }

    @Test
    public void initializeCashBox_cashBoxContainsNineCoins(){
        CashBox cashBox = new CashBox(true);

        Assert.assertEquals(9, cashBox.returnAllCoins().size());
    }

    @Test
    public void initializeCashBox_cashBoxContainsRightCoins(){
        CashBox cashBox = new CashBox(true);
        Coin[] coins = new Coin[]{Coin.TEN, Coin.TEN, Coin.FIVE, Coin.FIVE, Coin.TWO, Coin.TWO, Coin.ONE, Coin.ONE, Coin.ONE};
        List<Coin> expetedCoins = Arrays.asList(coins);
        List<Coin> actualCoins = cashBox.returnAllCoins();

        Assert.assertTrue(expetedCoins.containsAll(actualCoins) && actualCoins.containsAll(expetedCoins));
    }

    @Test
    public void returnChangeFromEmptyCashBox_changeIsZero() {
        CashBox cashBox = new CashBox(false);

        List<Coin> expectedCoins = new ArrayList<>();

        Assert.assertEquals(expectedCoins, cashBox.returnChange(23));
    }

    @Test
    public void returnChangeFromInitializedCashBox_changeIsMax37() {
        CashBox cashBox = new CashBox(true);

        int expectedSum = 37;
        int actualSum = cashBox.returnChange(50).stream().mapToInt(Coin::getNumValue).sum();

        Assert.assertEquals(expectedSum, actualSum);
    }

    @Test
    public void returnAllCoinsFromInitializedCashBox_cashBoxSumIsZero() {
        CashBox cashBox = new CashBox(true);
        cashBox.returnAllCoins();

        Assert.assertEquals(0, cashBox.sum());
    }

}