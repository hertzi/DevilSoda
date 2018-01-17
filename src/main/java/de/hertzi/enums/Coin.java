package de.hertzi.enums;

public enum Coin {
    ONE(1),
    TWO(2),
    FIVE(5),
    TEN(10);

    private int numValue;

    Coin(int numValue){
        this.numValue = numValue;
    }

    public int getNumValue(){
        return numValue;
    }
}
