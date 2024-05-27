# Tool Rental Application

## Overview
This project is a simple tool rental application that demonstrates a point-of-sale tool for a store, similar to Home Depot, that rents out big tools. The application includes the ability to rent a tool for a specified number of days, generate a rental agreement, and apply discounts. The application is implemented in Java and includes a comprehensive set of unit tests using JUnit.

## Tools Available for Rental
The following tools are available for rental:
- **Chainsaw (CHNS)** by Stihl
- **Ladder (LADW)** by Werner
- **Jackhammer (JAKD)** by DeWalt
- **Jackhammer (JAKR)** by Ridgid

## Tool Rental Charges
- **Ladder**: $1.99/day (Weekdays and Weekends)
- **Chainsaw**: $1.49/day (Weekdays and Holidays)
- **Jackhammer**: $2.99/day (Weekdays only)

## Holidays
- **Independence Day (July 4th)**: If it falls on a weekend, it is observed on the closest weekday (Friday if it falls on Saturday, Monday if it falls on Sunday).
- **Labor Day**: First Monday in September.

## Checkout Process
When a customer checks out a tool, the following information is required:
- **Tool code** (e.g., CHNS, LADW)
- **Rental day count**: The number of days for which the customer wants to rent the tool.
- **Discount percent**: A discount percentage to apply to the total charges.
- **Checkout date**: The date the tool is rented.

The application will then generate a rental agreement that includes:
- Tool details (code, type, brand)
- Rental period
- Charges (daily charge, discount, final charge)

## How to Run
1. Clone the repository.
2. Navigate to the project directory.
3. Compile and run the application using Maven:
    ```bash
    mvn clean install
    mvn exec:java -Dexec.mainClass="org.example.Main"
    ```

## Project Structure
