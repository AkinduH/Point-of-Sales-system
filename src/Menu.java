import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    // Method to display the main menu
    public static void displayMainMenu(){
        System.out.println();
        System.out.println("1. Login as a Cashier");
        System.out.println("2. Exit");
        try {
            int choice = Integer.parseInt(reader.readLine());

            switch (choice) {
                case 1:
                    System.out.print("Enter Cashier Name: ");
                    String cashierName = reader.readLine();
                    System.out.println("Enter your Branch: ");
                    String branchName = reader.readLine();
                    POS pos = new POS(cashierName, branchName, "");
                    CashierMenu(pos);
                    break;
                case 2:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("\u001B[31m" + "Invalid choice. Please try again." + "\u001B[0m");
                    displayMainMenu();
                    break;
            }
        }catch (IOException | NumberFormatException e) {
            System.out.println("\u001B[31m" + "Please enter the option number." + "\u001B[0m");
            displayMainMenu();
        }
    }

    // Method to display the cashier menu
    public static void CashierMenu(POS pos) {
        System.out.println("Welcome " + pos.getCashierName());
        System.out.println("1. Add customer name(Optional)");
        System.out.println("2. Add Items");
        System.out.println("3. Load Pending Bill");
        System.out.println("4. Print Bill");
        System.out.println("5. Back");
        System.out.print("Enter your choice: ");
        try {
            int choice = Integer.parseInt(reader.readLine());

            switch (choice) {
                case 1:
                    String CustomerName = reader.readLine();
                    pos.setCustomerName(CustomerName);
                    CashierMenu(pos);
                    break;
                case 2:
                    addItem(reader,pos);
                    break;
                case 3:
                    POS temp = PendingBillManager.loadPendingBill(reader);
                    if (temp != null) {
                        pos = temp;
                        PendingBillUpdate(pos);
                    }else{
                        CashierMenu(pos);
                    }
                    break;
                case 4:
                    pos.printBill();
                    System.out.println("Do you want to Bill a new customer?");
                    System.out.println("1. Yes");
                    System.out.println("2. No and exit");
                    int choice2 = Integer.parseInt(reader.readLine());
                    switch (choice2) {
                        case 1:
                            POS pos_new = new POS(pos.getCashierName(), pos.getBranchName(), "");
                            CashierMenu(pos_new);
                            break;
                        case 2:
                            System.out.println("Exiting...");
                            System.exit(0);
                    }
                    break;
                case 5:
                    displayMainMenu();
                    break;
                default:
                    System.out.println("\u001B[31m" + "Invalid choice. Please try again." + "\u001B[0m");
                    CashierMenu(pos);
                    break;
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("\u001B[31m" + "Please enter the option number." + "\u001B[0m");
            CashierMenu(pos);
        }
    }

    // Method to add items to the cart
    private static void addItem(BufferedReader reader, POS pos) {
        System.out.println("Add your items continuously");
        System.out.println();
        System.out.println("If you want to end adding please enter 'e' ");
        System.out.println("If you want to add the current bill to pending please enter 'p' ");
        System.out.println();
        while (true){
            try {
                System.out.print("Enter Item Code: ");
                String itemCode = reader.readLine();
                if (itemCode.equals("p")){
                    System.out.print("Enter filename to save pending bill: ");
                    PendingBillManager.savePendingBill(reader, pos);
                    POS pos_new = new POS(pos.getCashierName(), pos.getBranchName(), "");
                    CashierMenu(pos_new);
                    break;
                }else if(itemCode.equals("e")){
                    CashierMenu(pos);
                    break;
                }
                pos.addItem(itemCode);
            } catch (IOException e) {
                System.out.println("Error reading input.");
            }
        }
    }

    // Method to update or add items to a pending bill
    public static void PendingBillUpdate(POS pos) {
        System.out.println("1. Add Items");
        System.out.println("2. Back");
        System.out.print("Enter your choice: ");
        try {
            int choice = Integer.parseInt(reader.readLine());

            switch (choice) {
                case 1:
                    addItem(reader,pos);
                    break;
                case 2:
                    CashierMenu(pos);
                    break;
                default:
                    System.out.println("\u001B[31m" + "Invalid choice. Please try again." + "\u001B[0m");
                    PendingBillUpdate(pos);
                    break;
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("\u001B[31m" + "Please enter the option number." + "\u001B[0m");
            PendingBillUpdate(pos);
        }
    }

}
