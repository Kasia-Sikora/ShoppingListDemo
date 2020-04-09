package pl.sikora.katarzyna.ShoppingList.util;

import lombok.Setter;

@Setter
public class FieldErrorMessage {

    private String field;
    private String message;

    public FieldErrorMessage(String field, String message) {
        this.field = field;
        this.message = message;
    }


}
