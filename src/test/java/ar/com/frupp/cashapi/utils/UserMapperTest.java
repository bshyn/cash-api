package ar.com.frupp.cashapi.utils;

import ar.com.frupp.cashapi.entities.Loan;
import ar.com.frupp.cashapi.entities.User;
import ar.com.frupp.cashapi.models.LoanModel;
import ar.com.frupp.cashapi.models.UserModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserMapperTest {

    private User entity;
    private UserModel model;

    @BeforeEach
    void setUp() {
        Loan loan = new Loan();
        User user = new User();

        loan.setUser(user);
        user.setLoans(Collections.singleton(loan));

        UserModel userModel = new UserModel();
        LoanModel loanModel = new LoanModel();

        userModel.setLoans(Collections.singleton(loanModel));

        double total = 2500.00;
        loan.setTotal(total);
        loanModel.setTotal(total);

        int id = 1;
        loan.setId(id);
        user.setId(id);
        userModel.setId(id);
        loanModel.setUserId(id);

        String email = "user@test.com";
        user.setEmail(email);
        userModel.setEmail(email);

        String firstName = "Pepe";
        user.setFirstName(firstName);
        userModel.setFirstName(firstName);

        String lastName = "Argento";
        user.setLastName(lastName);
        userModel.setLastName(lastName);

        this.entity = user;
        this.model = userModel;
    }

    @Test
    void shouldConvertEntityToModel() {
        UserModel converted = UserMapper.toModel(this.entity);

        assertThat(converted)
                .usingRecursiveComparison()
                .ignoringFields("loans")
                .withStrictTypeChecking()
                .isEqualTo(this.model);

        assertThat(converted.getLoans().size()).isEqualTo(this.model.getLoans().size());
    }

    @Test
    void shouldConvertModelToEntity() {
        User converted = UserMapper.toEntity(this.model);

        assertThat(converted)
                .usingRecursiveComparison()
                .ignoringFields("loans")
                .withStrictTypeChecking()
                .isEqualTo(this.entity);

        assertThat(converted.getLoans().size()).isEqualTo(this.entity.getLoans().size());
    }

    @Test
    void shouldConvertModelWithNoIdToEntity() {
        this.model.setId(null);

        User converted = UserMapper.toEntity(this.model);

        assertThat(converted.getId()).isNull();
    }

    @Test
    void shouldConvertModelWithNoLoansToEntity() {
        this.model.setLoans(null);

        User converted = UserMapper.toEntity(this.model);

        assertThat(converted.getLoans()).isNull();
    }
}
