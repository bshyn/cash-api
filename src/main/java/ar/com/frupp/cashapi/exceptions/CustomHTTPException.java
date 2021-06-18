package ar.com.frupp.cashapi.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Map;

public abstract class CustomHTTPException extends RuntimeException {
    private final HttpStatus status;
    private final String errorCode;
    private final Map<String, String> validations;

    public CustomHTTPException(HttpStatus status, String errorCode, Map<String, String> validations) {
        this.status = status;
        this.errorCode = errorCode;
        this.validations = validations;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Map<String, String> getValidations() {
        return validations;
    }
}
