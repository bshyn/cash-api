package ar.com.frupp.cashapi.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class BadRequestException extends CustomHTTPException {
    private final static HttpStatus status = HttpStatus.BAD_REQUEST;

    public BadRequestException(String errorCode, Map<String, String> validations) {
        super(status, errorCode, validations);
    }


}
