import java.io.Serializable;
import java.time.LocalDateTime;

public class GroceryItem implements Serializable {

    private String itemName;
    private String itemCode;
    private double price;
    private double weight;
    private LocalDateTime manufacturingDate;
    private LocalDateTime expiryDate;
    private String manufacturerName;
    public int quantity = 1;

    private double discount;

    // Constructor to initialize a GroceryItem object
    GroceryItem(String itemCode, String name, double price, double weight, String manufacturerName, LocalDateTime manufactureDate, LocalDateTime expiryDate, double discount) {
        this.itemCode = itemCode;
        this.itemName = name;
        this.price = price;
        this.weight = weight;
        this.manufacturerName = manufacturerName;
        this.manufacturingDate = manufactureDate;
        this.expiryDate = expiryDate;
        this.discount = discount;
    }

    public String getItemName() {
        return itemName;
    }
    public double getPrice() {
        return price;
    }

    public double getQuantity() {
        return quantity;
    }
    public double getWeight() {
        return weight;
    }

    public double getDiscount() {
        return discount;
    }

    public void setQuantity() {
        quantity++;
    }

}


