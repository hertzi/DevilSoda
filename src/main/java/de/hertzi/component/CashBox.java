package de.hertzi.component;


import de.hertzi.enums.Coin;

import java.util.*;
import java.util.stream.Collectors;

public class CashBox {
    private Map<Coin, Integer> wallet = new HashMap<>();

    public CashBox(){
    }

    public CashBox(boolean initialize){
        if(initialize){
            init();
        }
    }

    private void init(){
        wallet.put(Coin.TEN, 2);
        wallet.put(Coin.FIVE, 2);
        wallet.put(Coin.TWO, 2);
        wallet.put(Coin.ONE, 3);
    }

    public int sum(){
        return wallet.entrySet().stream().mapToInt(x -> x.getKey().getNumValue() * x.getValue()).sum();
    }

    public int add(Coin coin){
        int amount = 1;
        if(wallet.containsKey(coin)){
            amount += wallet.get(coin);
        }
        wallet.put(coin, amount);
        return this.sum();
    }

    public List<Coin> returnChange(Integer amount){
        List<Coin> change = new ArrayList<>();
        List<Coin> coinsInWalletHighestFirst =  wallet.keySet().stream()
                                                            .sorted(Comparator.comparingInt(Coin::getNumValue)
                                                                              .reversed())
                                                            .collect(Collectors.toList());
        for(Coin coin : coinsInWalletHighestFirst){
            while((coin.getNumValue() <= amount) && remove(coin)){
                change.add(coin);
                amount -= coin.getNumValue();
            }
        }
        return change;
    }

    private boolean remove(Coin coin){
        if(!wallet.containsKey(coin)){
            return false;
        }
        if(isLast(coin)){
            wallet.remove(coin);
        } else {
            int newAmount = wallet.get(coin) - 1;
            wallet.put(coin, newAmount);
        }
        return true;
    }

    private boolean isLast(Coin coin){
        return wallet.containsKey(coin) && wallet.get(coin) == 1;
    }

    public List<Coin> returnAllCoins(){
        List<Coin> coins = new ArrayList<>();
        for(Map.Entry<Coin, Integer> entry : wallet.entrySet()){
            for(int i = 0; i < entry.getValue(); i++){
                coins.add(entry.getKey());
            }
        }
        wallet.clear();
        return coins;
    }

    public int add(List<Coin> coins){
        for (Coin coin : coins) {
            add(coin);
        }
        return sum();
    }

}