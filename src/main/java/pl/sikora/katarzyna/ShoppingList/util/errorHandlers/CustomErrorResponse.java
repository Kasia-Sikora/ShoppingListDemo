package pl.sikora.katarzyna.ShoppingList.util.errorHandlers;

import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.validation.ObjectError;

class CustomErrorResponse {

    String errorCode;
    String errorMsg;
    int status;
    List<ObjectError> errorBindingMsg;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    LocalDateTime timestamp;

    public CustomErrorResponse(String errorCode, String errorMsg) {
        super();
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public CustomErrorResponse(String errorCode, List<ObjectError> errorBindingMsg) {
        super();
        this.errorCode = errorCode;
        this.errorBindingMsg = errorBindingMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public List<ObjectError> getErrorBindingMsg() {
        return errorBindingMsg;
    }

    public void setErrorBindingMsg(List<ObjectError> errorBindingMsg) {
        this.errorBindingMsg = errorBindingMsg;
    }
}
