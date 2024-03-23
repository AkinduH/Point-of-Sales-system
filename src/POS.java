import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

class POS implements Serializable{
    private String cashierName;
    private String branchName;
    private String customerName;
    private HashMap<String,GroceryItem> itemList;
    private double totalDiscount;
    private double totalPrice;
    LocalDateTime billDateTime;

    // Hardcoded database for simplicity
    private static Map<String, GroceryItem> database = new HashMap<>();

    // Initialize the database with some sample data
    static {
        LocalDateTime now = LocalDateTime.now();
        database.put("001", new GroceryItem("001", "Apple", 2.99, 0.5, "Fruit Co.", now.minusMonths(2), now.plusMonths(2), 0.1));
        database.put("002", new GroceryItem("002", "Bread", 3.49, 1.0, "Bakery Inc.", now.minusDays(3), now.plusDays(5), 0.0));
        database.put("003", new GroceryItem("003", "Milk", 2.29, 1.0, "Dairy Farm", now.minusDays(7), now.plusDays(10), 0.2));
    }

    // Constructor to initialize the POS object
    POS(String cashierName, String branchName, String customerName) {
        this.cashierName = cashierName;
        this.branchName = branchName;
        this.customerName = customerName;
        this.itemList = new HashMap<>();
        this.totalDiscount = 0;
        this.totalPrice = 0;
    }

    // Method to add an item to the cart
    public void addItem(String itemCode) {
        try {
            GroceryItem item = getItemDetails(itemCode);
            if (item != null) {
                if (itemList.containsKey(item.getItemName())) {
                    itemList.get(item.getItemName()).setQuantity();
                }
                else{
                    itemList.put(item.getItemName(),item);
                }
                totalPrice += item.getPrice() * (1 - item.getDiscount());
                totalDiscount += item.getPrice() * item.getDiscount();

            }
            System.out.println("Item added successfully.");
        } catch (ItemCodeNotFound e) {
            System.out.println(e.getMessage());
        }

    }


    // Method to get the details of an item from the database
    public GroceryItem getItemDetails(String itemCode) throws ItemCodeNotFound {
        GroceryItem item = database.get(itemCode);
        if (item == null) {
            throw new ItemCodeNotFound("Item code " + itemCode + " not found. Please re-enter the item code.");
        }
        return item;
    }

    // Method to print the bill
    public void printBill() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        billDateTime = LocalDateTime.now();

        System.out.println("+--------------------------------------+");
        System.out.println("|     Super-Saving Supermarket         |");
        System.out.println("+--------------------------------------+");
        System.out.println("| Cashier: " + cashierName + "               |");
        System.out.println("| Branch: " + branchName + "                   |");
        System.out.println("| Customer: " + (customerName.isEmpty() ? "Walk-in" : customerName) + "     |");
        System.out.println("| Date: " + formatter.format(billDateTime) + "      |");
        System.out.println("+--------------------------------------+");
        System.out.println("| Item         | Qty | Price   | Total |");
        System.out.println("+--------------------------------------+");

        for (Map.Entry<String, GroceryItem> entry : itemList.entrySet()) {
            GroceryItem item = entry.getValue();
            double unitPrice = item.getPrice() * (1 - item.getDiscount());
            double totalPrice = item.getQuantity() * unitPrice;
            System.out.printf("| %-13s | %.2f | $%.2f | $%.2f |\n", item.getItemName(), item.getQuantity(),unitPrice,totalPrice);
        }

        System.out.println("+--------------------------------------+");
        System.out.printf("| Total Discount: $%.2f                |\n", totalDiscount);
        System.out.printf("| Total Price: $%.2f                   |\n", totalPrice);
        System.out.println("+--------------------------------------+");
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCashierName() {
        return cashierName;
    }

    public String getBranchName() {
        return branchName;
    }
}
