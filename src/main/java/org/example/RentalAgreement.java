package org.example;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RentalAgreement {
    private final Tool tool;
    private final int rentalDays;
    private final Date checkoutDate;
    private final Date dueDate;
    private final double dailyCharge;
    private final int chargeDays;
    private final double preDiscountCharge;
    private final int discountPercent;
    private final double discountAmount;
    private final double finalCharge;

    public RentalAgreement(Tool tool, int rentalDays, Date checkoutDate, Date dueDate, double dailyCharge, int chargeDays,
                           double preDiscountCharge, int discountPercent, double discountAmount, double finalCharge) {
        this.tool = tool;
        this.rentalDays = rentalDays;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.dailyCharge = dailyCharge;
        this.chargeDays = chargeDays;
        this.preDiscountCharge = preDiscountCharge;
        this.discountPercent = discountPercent;
        this.discountAmount = discountAmount;
        this.finalCharge = finalCharge;
    }

    public void printAgreement() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        DecimalFormat currencyFormat = new DecimalFormat("$#,##0.00");
        DecimalFormat percentFormat = new DecimalFormat("0%");

        System.out.println("Tool code: " + tool.getCode());
        System.out.println("Tool type: " + tool.getType().getName());
        System.out.println("Tool brand: " + tool.getBrand());
        System.out.println("Rental days: " + rentalDays);
        System.out.println("Check out date: " + dateFormat.format(checkoutDate));
        System.out.println("Due date: " + dateFormat.format(dueDate));
        System.out.println("Daily rental charge: " + currencyFormat.format(dailyCharge));
        System.out.println("Charge days: " + chargeDays);
        System.out.println("Pre-discount charge: " + currencyFormat.format(preDiscountCharge));
        System.out.println("Discount percent: " + percentFormat.format(discountPercent / 100.0));
        System.out.println("Discount amount: " + currencyFormat.format(discountAmount));
        System.out.println("Final charge: " + currencyFormat.format(finalCharge));
    }

    public Tool getTool() {
        return tool;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public double getDailyCharge() {
        return dailyCharge;
    }

    public int getChargeDays() {
        return chargeDays;
    }

    public double getPreDiscountCharge() {
        return preDiscountCharge;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public double getFinalCharge() {
        return finalCharge;
    }
}
