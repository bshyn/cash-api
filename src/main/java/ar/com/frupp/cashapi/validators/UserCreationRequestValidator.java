package ar.com.frupp.cashapi.validators;

import ar.com.frupp.cashapi.exceptions.BadRequestException;
import ar.com.frupp.cashapi.exceptions.ErrorCode;
import ar.com.frupp.cashapi.models.UserModel;

import java.util.HashMap;
import java.util.Map;

public class UserCreationRequestValidator implements RequestValidator{
    private final Map<String, String> errors = new HashMap<>();
    private final String errorMsg = "Can't create User without %s";

    private final UserModel model;

    public UserCreationRequestValidator(UserModel model) {
        this.model = model;
    }

    public void validate() throws BadRequestException {
        validateLoans();

        validateId();

        validateEmail();

        validateFirstName();

        validateLastName();

        if(requestIsInvalid()) {
            throw new BadRequestException(ErrorCode.INVALID_USER, errors);
        }
    }

    private boolean requestIsInvalid() {
        return !this.errors.isEmpty();
    }

    private void validateLastName() {
        if (stringIsInvalid(this.model.getLastName())){
            this.errors.put("lastName", String.format(this.errorMsg, "lastName"));
        }
    }

    private void validateFirstName() {
        if (stringIsInvalid(this.model.getFirstName())) {
            this.errors.put("firstName", String.format(this.errorMsg, "firstName"));
        }
    }

    private void validateEmail() {
        if (stringIsInvalid(this.model.getEmail())) {
            this.errors.put("email", String.format(this.errorMsg, "email"));
        }
    }

    private void validateId() {
        if (this.model.getId() != null) {
            this.errors.put("id", String.format(this.errorMsg, "null id"));
        }
    }

    private void validateLoans() {
        if (this.model.getLoans() != null) {
            this.errors.put("loans", String.format(this.errorMsg, "null loans"));
        }
    }

    private static boolean stringIsInvalid(String str) {
        return str == null || str.length() == 0;
    }
}
