import java.io.*;

public class PendingBillManager {

    // Method to save a pending bill to a file
    public static void savePendingBill(BufferedReader reader, POS pos) {
        try {
            String fileName = reader.readLine();
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(pos);
            out.close();
            fileOut.close();
            System.out.println("Pending bill saved to " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving pending bill.");
            System.out.println("Invalid File name,Enter again");
            savePendingBill(reader,pos);
        }
    }

    // Method to load a pending bill from a file
    public static POS loadPendingBill(BufferedReader reader) {
        try {
            System.out.print("Enter filename to load pending bill: ");
            String fileName = reader.readLine();
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            POS pendingBill = (POS) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Pending bill loaded successfully.");
            return pendingBill;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading pending bill.");
            return null;
        }
    }
}

