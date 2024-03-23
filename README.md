# Point of Sales (POS) System

This is a Java implementation of a Point of Sales (POS) system for the Super-Saving supermarket chain. The POS system allows cashiers to enter item codes, fetch product information from a database, apply discounts, and generate bills. It also includes functionality to handle pending bills and handle exceptions related to invalid item codes.

## Features

- Enter item codes and fetch product details (price, weight/size, manufacturing date, expiry date, manufacturer name)
- Apply discounts ranging from 0-75% on items
- Generate bills with cashier's name, branch, customer name (if registered), item list (unit price, quantity, discount, net price), total discount, total price, date, and time
- Handle pending bills when customers need to weigh vegetables/fruits
- Custom exception `ItemCodeNotFound` to handle invalid item codes

## Usage

1. Compile and run the Java code.
2. Follow the prompts to enter the required details (cashier's name, branch, customer name, etc.).
3. Enter item codes to add items to the bill.
4. If an invalid item code is entered, the program will handle the `ItemCodeNotFound` exception and prompt the cashier to re-enter the item code.
5. After adding all items, the program will generate the bill with the calculated totals and discounts.
6. If a customer needs to weigh items, the cashier can mark the bill as pending and continue with other customers.

## Implementation Details

- The `POS` class contains the main logic for the Point of Sales system.
- Product details are hardcoded for this exercise instead of using a database connection.
- The `getItemDetails()` method in the `POS` class handles the `ItemCodeNotFound` exception by allowing the cashier to re-enter the item code.
- Serialization is used to handle pending bills.
- The program follows a readable and well-commented structure for classes, variables, and methods.

## Requirements

- Java Development Kit (JDK)
- Java IDE or text editor for compiling and running Java code

## Contributing

Contributions to this project are welcome. If you find any issues or have suggestions for improvements, please open an issue or submit a pull request.

