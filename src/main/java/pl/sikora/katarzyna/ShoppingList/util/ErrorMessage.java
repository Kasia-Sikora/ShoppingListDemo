package pl.sikora.katarzyna.ShoppingList.util;


import lombok.Setter;

@Setter
public class ErrorMessage {

    private String status;
    private String message;

    public ErrorMessage(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
