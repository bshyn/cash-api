package ar.com.frupp.cashapi.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class NotFoundException extends CustomHTTPException {
    private static final HttpStatus status = HttpStatus.NOT_FOUND;

    public NotFoundException(String errorCode, Map<String, String> validations) {
        super(status, errorCode, validations);
    }
}
