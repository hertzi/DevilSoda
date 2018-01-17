package de.hertzi;

import de.hertzi.cli.SodaCli;

public class Main {

    public static void main(String[] args) {
        SodaMachine sodaMachine = new SodaMachine();
        SodaCli sodaCli = new SodaCli(sodaMachine);
        sodaCli.start();
    }
}
