package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        RentalService rentalService = new RentalService();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");

        try {
            Date checkoutDate = dateFormat.parse("09/03/15");
            RentalAgreement agreement = rentalService.checkout("JAKR", 5, 10, checkoutDate);
            agreement.printAgreement();
        } catch (ParseException e) {
            System.err.println("Error parsing date: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error in rental agreement: " + e.getMessage());
        }
    }
}
