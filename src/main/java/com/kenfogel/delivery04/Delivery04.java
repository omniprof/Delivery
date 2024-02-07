package com.kenfogel.delivery04;

import java.text.NumberFormat;
import java.util.Scanner;

// Ken
public class Delivery04 {

    final private double ZONE1 = 2.0;
    final private double ZONE2 = 3.0;
    final private double ZONE3 = 5.0;
    final private double ZONE4 = 6.0;
    final private double GST = 0.05;
    final private double PST = 0.09975;

    private int requestZone() {
        int zone;
        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter a Zone from 1 - 4: ");
        if (sc.hasNextInt()) {
            zone = sc.nextInt();
        } else {
            zone = -1;
        }
        sc.nextLine();

        while (zone < 1 || zone > 4) {
            System.out.println("I'm sorry, your input was invalid.");
            System.out.println("Please enter a Zone from 1 - 4: ");
            if (sc.hasNextInt()) {
                zone = sc.nextInt();
            } else {
                zone = -1;
            }
            sc.nextLine();
        }
        return zone;
    }

    private double determineBaseCharge(int zone) {
        double baseCharge;

        baseCharge = switch (zone) {
            case 1 ->
                ZONE1;
            case 2 ->
                ZONE2;
            case 3 ->
                ZONE3;
            case 4 ->
                ZONE4;
            default ->
                0.0;
        };
        return baseCharge;
    }

    private double calculateCharge(double baseCharge) {
        double gstCharge;
        double pstCharge;
        double totalCharge = 0.0;

        if (baseCharge > 0) {
            gstCharge = baseCharge * GST;
            pstCharge = baseCharge * PST;
            totalCharge = baseCharge + gstCharge + pstCharge;
        }
        return totalCharge;
    }

    private void displayResults(double baseCharge, double totalCharge) {
        NumberFormat money = NumberFormat.getCurrencyInstance();
        System.out.println("Base Charge: " + money.format(baseCharge));
        System.out.println("      Total: " + money.format(totalCharge));
    }

    public void perform() {
        int zone = requestZone();
        double baseCharge = determineBaseCharge(zone);
        double totalCharge = calculateCharge(baseCharge);
        displayResults(baseCharge, totalCharge);
    }

    public static void main(String[] args) {
        var delivery = new Delivery04();
        delivery.perform();
        System.exit(0);
    }
}
