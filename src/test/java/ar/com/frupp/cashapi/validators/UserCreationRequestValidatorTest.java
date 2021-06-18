package ar.com.frupp.cashapi.validators;

import ar.com.frupp.cashapi.exceptions.BadRequestException;
import ar.com.frupp.cashapi.models.LoanModel;
import ar.com.frupp.cashapi.models.UserModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class UserCreationRequestValidatorTest {

    private UserModel model;

    @BeforeEach
    void setUp() {
        this.model = new UserModel(
                null, "email@email.com",
                "fName", "lName", null
        );
    }

    @Test
    void shouldNotThrowExceptionIfRequestIsValid() {
        UserCreationRequestValidator validator = new UserCreationRequestValidator(model);
        validator.validate();
    }

    @Test
    void shouldThrowExceptionIfIdNotNull() {
        this.model.setId(1);

        UserCreationRequestValidator validator = new UserCreationRequestValidator(model);

        BadRequestException thrown = assertThrows(
                BadRequestException.class,
                validator::validate
        );

        assertTrue(thrown.getValidations().containsKey("id"));
        assertEquals(thrown.getValidations().size(), 1);
    }

    @Test
    void shouldThrowExceptionIfLoanNotNull() {
        Collection<LoanModel> loans = Collections.singletonList(
                new LoanModel(1, 1, 1)
        );
        this.model.setLoans(loans);

        UserCreationRequestValidator validator = new UserCreationRequestValidator(model);

        BadRequestException thrown = assertThrows(
                BadRequestException.class,
                validator::validate
        );

        assertTrue(thrown.getValidations().containsKey("loans"));
        assertEquals(thrown.getValidations().size(), 1);
    }

    @Test
    void shouldThrowExceptionIfFirstNameNull() {
        this.model.setFirstName(null);
        UserCreationRequestValidator validator = new UserCreationRequestValidator(model);

        BadRequestException thrown = assertThrows(
                BadRequestException.class,
                validator::validate
        );

        assertTrue(thrown.getValidations().containsKey("firstName"));
        assertEquals(thrown.getValidations().size(), 1);
    }

    @Test
    void shouldThrowExceptionIfLastNameNull() {
        this.model.setLastName(null);
        UserCreationRequestValidator validator = new UserCreationRequestValidator(model);

        BadRequestException thrown = assertThrows(
                BadRequestException.class,
                validator::validate
        );

        assertTrue(thrown.getValidations().containsKey("lastName"));
        assertEquals(thrown.getValidations().size(), 1);
    }

    @Test
    void shouldThrowExceptionIfEmailNull() {
        this.model.setEmail(null);
        UserCreationRequestValidator validator = new UserCreationRequestValidator(model);

        BadRequestException thrown = assertThrows(
                BadRequestException.class,
                validator::validate
        );

        assertTrue(thrown.getValidations().containsKey("email"));
        assertEquals(thrown.getValidations().size(), 1);
    }

}