package org.example;

import java.util.ArrayList;
import java.util.List;

public class Bike {
    protected static List<Bike> bikeList = new ArrayList<>();
    protected int deposit;
    protected int rate;
    protected int bikeNumber;

    static {
        for (int i = 1; i <= 150; i++) {
            Bike b = new Bike(i * 10, i * 5, i);
            bikeList.add(b);
        }
    }

    public Bike(int dep, int rat, int num) {
        deposit = dep;
        rate = rat;
        bikeNumber = num;
    }

    public int getDeposit() {
        return deposit;
    }

    public int getRate() {
        return rate;
    }

    public int getBikeNumber() {
        return bikeNumber;
    }

    public static Bike findBikeByNumber(int bikeNum) {
        for (Bike bike : bikeList) {
            if (bike.getBikeNumber() == bikeNum) {
                System.out.println("Bike with number '" + bikeNum + "' found" + "\n");
                return bike;
            }
        }
        System.out.println("Bike with number '" + bikeNum + "' not found" + "\n");
        return null;
    }

    public void showDetails() {
        System.out.println("Details for bike number '" + bikeNumber + "'");
        System.out.println("DEPOSIT: " + deposit);
        System.out.println("RATE: " + rate + "\n");
    }

    public void calculateCost(int numberOfDays) {
        int cost = deposit + (rate * numberOfDays);
        System.out.println("COST would be $" + cost + "\n");
    }
}
