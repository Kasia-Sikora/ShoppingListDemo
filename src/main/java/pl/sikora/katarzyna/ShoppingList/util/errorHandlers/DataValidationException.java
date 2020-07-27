package pl.sikora.katarzyna.ShoppingList.util.errorHandlers;


public class DataValidationException extends RuntimeException {

    public DataValidationException() {
        super("Invalid Data");
    }

    public DataValidationException(String message) {
        super(message);
    }
}
