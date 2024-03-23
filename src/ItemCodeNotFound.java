// This class represents an exception that is thrown when an item code is not found in the database
class ItemCodeNotFound extends Exception {
    public ItemCodeNotFound(String message) {
        super(message);
    }
}