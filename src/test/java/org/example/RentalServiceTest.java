package org.example;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class RentalServiceTest {
    private RentalService rentalService;

    @Before
    public void setUp() {
        rentalService = new RentalService();
    }

    private Date parseDate(String date) {
        try {
            return new SimpleDateFormat("MM/dd/yy").parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testCheckoutWithValidInput() {
        Date checkoutDate = parseDate("09/03/15");
        RentalAgreement agreement = rentalService.checkout("JAKR", 5, 0, checkoutDate);

        assertNotNull(agreement);
        assertEquals("JAKR", agreement.getTool().getCode());
        assertEquals("Jackhammer", agreement.getTool().getType().getName());
        assertEquals("Ridgid", agreement.getTool().getBrand());
        assertEquals(5, agreement.getRentalDays());
        assertEquals(checkoutDate, agreement.getCheckoutDate());
        assertEquals(parseDate("09/08/15"), agreement.getDueDate());
        assertEquals(2.99, agreement.getDailyCharge(), 0.01);
        assertEquals(4, agreement.getChargeDays());
        assertEquals(11.96, agreement.getPreDiscountCharge(), 0.01);
        assertEquals(0, agreement.getDiscountPercent());
        assertEquals(0.00, agreement.getDiscountAmount(), 0.01);
        assertEquals(11.96, agreement.getFinalCharge(), 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckoutWithInvalidRentalDays() {
        rentalService.checkout("JAKR", 0, 0, parseDate("09/03/15"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckoutWithInvalidDiscountPercent() {
        rentalService.checkout("JAKR", 5, 101, parseDate("09/03/15"));
    }

    @Test
    public void testCheckoutWithDiscount() {
        Date checkoutDate = parseDate("07/02/20");
        RentalAgreement agreement = rentalService.checkout("LADW", 3, 10, checkoutDate);

        assertNotNull(agreement);
        assertEquals("LADW", agreement.getTool().getCode());
        assertEquals("Ladder", agreement.getTool().getType().getName());
        assertEquals("Werner", agreement.getTool().getBrand());
        assertEquals(3, agreement.getRentalDays());
        assertEquals(checkoutDate, agreement.getCheckoutDate());
        assertEquals(parseDate("07/05/20"), agreement.getDueDate());
        assertEquals(1.99, agreement.getDailyCharge(), 0.01);
        assertEquals(4, agreement.getChargeDays());
        assertEquals(7.96, agreement.getPreDiscountCharge(), 0.01);
        assertEquals(10, agreement.getDiscountPercent());
        assertEquals(0.80, agreement.getDiscountAmount(), 0.01);
        assertEquals(7.16, agreement.getFinalCharge(), 0.01);
    }

    // Additional tests for other scenarios

    @Test
    public void testCheckoutDuringHolidayWithCharge() {
        Date checkoutDate = parseDate("07/02/15"); // Independence Day on July 4th, 2015 is a Saturday
        RentalAgreement agreement = rentalService.checkout("CHNS", 5, 25, checkoutDate);

        assertNotNull(agreement);
        assertEquals("CHNS", agreement.getTool().getCode());
        assertEquals("Chainsaw", agreement.getTool().getType().getName());
        assertEquals("Stihl", agreement.getTool().getBrand());
        assertEquals(5, agreement.getRentalDays());
        assertEquals(checkoutDate, agreement.getCheckoutDate());
        assertEquals(parseDate("07/07/15"), agreement.getDueDate());
        assertEquals(1.49, agreement.getDailyCharge(), 0.01);
        assertEquals(5, agreement.getChargeDays());
        assertEquals(7.45, agreement.getPreDiscountCharge(), 0.01);
        assertEquals(25, agreement.getDiscountPercent());
        assertEquals(1.86, agreement.getDiscountAmount(), 0.01);
        assertEquals(5.59, agreement.getFinalCharge(), 0.01);
    }

    @Test
    public void testCheckoutDuringHolidayWithoutCharge() {
        Date checkoutDate = parseDate("09/03/15"); // Labor Day on September 7th, 2015
        RentalAgreement agreement = rentalService.checkout("JAKD", 6, 0, checkoutDate);

        assertNotNull(agreement);
        assertEquals("JAKD", agreement.getTool().getCode());
        assertEquals("Jackhammer", agreement.getTool().getType().getName());
        assertEquals("DeWalt", agreement.getTool().getBrand());
        assertEquals(6, agreement.getRentalDays());
        assertEquals(checkoutDate, agreement.getCheckoutDate());
        assertEquals(parseDate("09/09/15"), agreement.getDueDate());
        assertEquals(2.99, agreement.getDailyCharge(), 0.01);
        assertEquals(5, agreement.getChargeDays());
        assertEquals(14.95, agreement.getPreDiscountCharge(), 0.01);
        assertEquals(0, agreement.getDiscountPercent());
        assertEquals(0.00, agreement.getDiscountAmount(), 0.01);
        assertEquals(14.95, agreement.getFinalCharge(), 0.01);
    }

    @Test
    public void testCheckoutWithHighDiscount() {
        Date checkoutDate = parseDate("07/02/20");
        RentalAgreement agreement = rentalService.checkout("JAKR", 4, 50, checkoutDate);

        assertNotNull(agreement);
        assertEquals("JAKR", agreement.getTool().getCode());
        assertEquals("Jackhammer", agreement.getTool().getType().getName());
        assertEquals("Ridgid", agreement.getTool().getBrand());
        assertEquals(4, agreement.getRentalDays());
        assertEquals(checkoutDate, agreement.getCheckoutDate());
        assertEquals(parseDate("07/06/20"), agreement.getDueDate());
        assertEquals(2.99, agreement.getDailyCharge(), 0.01);
        assertEquals(3, agreement.getChargeDays());
        assertEquals(8.97, agreement.getPreDiscountCharge(), 0.01);
        assertEquals(50, agreement.getDiscountPercent());
        assertEquals(4.49, agreement.getDiscountAmount(), 0.01);
        assertEquals(4.48, agreement.getFinalCharge(), 0.01);
    }

    @Test
    public void testCheckoutWithZeroDiscount() {
        Date checkoutDate = parseDate("09/03/15");
        RentalAgreement agreement = rentalService.checkout("LADW", 3, 0, checkoutDate);

        assertNotNull(agreement);
        assertEquals("LADW", agreement.getTool().getCode());
        assertEquals("Ladder", agreement.getTool().getType().getName());
        assertEquals("Werner", agreement.getTool().getBrand());
        assertEquals(3, agreement.getRentalDays());
        assertEquals(checkoutDate, agreement.getCheckoutDate());
        assertEquals(parseDate("09/06/15"), agreement.getDueDate());
        assertEquals(1.99, agreement.getDailyCharge(), 0.01);
        assertEquals(4, agreement.getChargeDays());
        assertEquals(7.96, agreement.getPreDiscountCharge(), 0.01);
        assertEquals(0, agreement.getDiscountPercent());
        assertEquals(0.00, agreement.getDiscountAmount(), 0.01);
        assertEquals(7.96, agreement.getFinalCharge(), 0.01);
    }
}
