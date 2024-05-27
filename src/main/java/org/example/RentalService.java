package org.example;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RentalService {
    private static final List<Tool> TOOLS = Arrays.asList(
            new Tool("CHNS", ToolType.CHAINSAW, "Stihl"),
            new Tool("LADW", ToolType.LADDER, "Werner"),
            new Tool("JAKD", ToolType.JACKHAMMER, "DeWalt"),
            new Tool("JAKR", ToolType.JACKHAMMER, "Ridgid")
    );

    public RentalAgreement checkout(String toolCode, int rentalDays, int discountPercent, Date checkoutDate) throws IllegalArgumentException {
        if (rentalDays < 1) {
            throw new IllegalArgumentException("Rental day count must be 1 or greater");
        }

        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Discount percent must be between 0 and 100");
        }

        Tool tool = getToolByCode(toolCode);
        if (tool == null) {
            throw new IllegalArgumentException("Invalid tool code");
        }

        Date dueDate = calculateDueDate(checkoutDate, rentalDays);
        int chargeDays = calculateChargeDays(tool, checkoutDate, dueDate);
        double dailyCharge = tool.getType().getDailyCharge();
        double preDiscountCharge = roundToCents(chargeDays * dailyCharge);
        double discountAmount = roundToCents((discountPercent / 100.0) * preDiscountCharge);
        double finalCharge = roundToCents(preDiscountCharge - discountAmount);

        return new RentalAgreement(tool, rentalDays, checkoutDate, dueDate, dailyCharge, chargeDays,
                preDiscountCharge, discountPercent, discountAmount, finalCharge);
    }

    private Tool getToolByCode(String toolCode) {
        for (Tool tool : TOOLS) {
            if (tool.getCode().equals(toolCode)) {
                return tool;
            }
        }
        return null; // Tool not found
    }

    private Date calculateDueDate(Date checkoutDate, int rentalDays) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(checkoutDate);
        cal.add(Calendar.DAY_OF_YEAR, rentalDays);
        return cal.getTime();
    }

    private int calculateChargeDays(Tool tool, Date checkoutDate, Date dueDate) {
        int chargeDays = 0;
        Calendar cal = Calendar.getInstance();
        cal.setTime(checkoutDate);

        while (cal.getTime().before(dueDate) || cal.getTime().equals(dueDate)) {
            cal.add(Calendar.DAY_OF_YEAR, 1);
            Date currentDate = cal.getTime();

            boolean isChargeableDay = (tool.getType().isWeekdayCharge() && !DateUtil.isWeekend(currentDate))
                    || (tool.getType().isWeekendCharge() && DateUtil.isWeekend(currentDate))
                    || (tool.getType().isHolidayCharge() && (DateUtil.isIndependenceDay(currentDate) || DateUtil.isLaborDay(currentDate)));

            if (isChargeableDay) {
                chargeDays++;
            }
        }

        return chargeDays;
    }

    private double roundToCents(double amount) {
        return Math.round(amount * 100.0) / 100.0;
    }
}
