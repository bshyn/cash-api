package ar.com.frupp.cashapi.validators;

import ar.com.frupp.cashapi.exceptions.BadRequestException;

public interface RequestValidator {
    void validate() throws BadRequestException;
}
