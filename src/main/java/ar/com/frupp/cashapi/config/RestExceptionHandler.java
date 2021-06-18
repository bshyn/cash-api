package ar.com.frupp.cashapi.config;

import ar.com.frupp.cashapi.exceptions.CustomHTTPException;
import ar.com.frupp.cashapi.exceptions.ErrorCode;
import ar.com.frupp.cashapi.models.ApiValidation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomHTTPException.class)
    protected ResponseEntity<ApiValidation> handleCustomValidation(CustomHTTPException exception, HttpServletRequest request) {
        ApiValidation response = new ApiValidation(
                exception.getStatus(), request.getRequestURI(),
                exception.getErrorCode(), exception.getValidations()
        );

        return buildResponse(response);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ApiValidation> handleGenericError(HttpServletRequest request) {
        ApiValidation response = new ApiValidation(
                HttpStatus.INTERNAL_SERVER_ERROR, request.getRequestURI(),
                ErrorCode.SERVER_ERROR, null
        );

        return buildResponse(response);
    }

    private ResponseEntity<ApiValidation> buildResponse(ApiValidation response) {
        return new ResponseEntity<>(response, response.getStatus());
    }
}
