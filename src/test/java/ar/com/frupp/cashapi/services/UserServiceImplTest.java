package ar.com.frupp.cashapi.services;

import ar.com.frupp.cashapi.entities.Loan;
import ar.com.frupp.cashapi.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserServiceImplTest {

    private UserService service;
    private User user;

    @BeforeEach
    void setUp() {
        this.service = new UserServiceImpl();

        int userId = 1;

        Loan loan = new Loan();
        this.user = new User();

        loan.setUser(this.user);
        this.user.setLoans(Collections.singleton(loan));

        loan.setTotal(2500.00);
        loan.setId(1);
        this.user.setId(userId);
        this.user.setEmail("user@test.com");
        this.user.setFirstName("Pepe");
        this.user.setLastName("Argento");
    }

    @Test
    void shouldFindUser() {
        User found = this.service.findById(this.user.getId());

        assertThat(found)
                .usingRecursiveComparison()
                .withStrictTypeChecking()
                .isEqualTo(found);
    }
}