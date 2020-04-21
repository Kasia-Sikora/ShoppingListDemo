package pl.sikora.katarzyna.ShoppingList.errorHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class DataValidationException extends RuntimeException {

    public DataValidationException() {
        super("Invalid Data");
    }

    public DataValidationException(String message) {
        super(message);
    }
}
