package ar.com.frupp.cashapi.services;

import ar.com.frupp.cashapi.entities.Loan;
import ar.com.frupp.cashapi.entities.User;
import ar.com.frupp.cashapi.exceptions.NotFoundException;
import ar.com.frupp.cashapi.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class UserServiceImplTest {

    @MockBean
    private UserRepository repository;
    private UserService service;
    private User user;

    @BeforeEach
    void setUp() {
        this.service = new UserServiceImpl(this.repository);

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
        Mockito.when(repository.findById(this.user.getId())).thenReturn(Optional.of(this.user));

        User found = this.service.findById(this.user.getId());

        assertThat(found)
                .usingRecursiveComparison()
                .withStrictTypeChecking()
                .isEqualTo(found);
    }

    @Test
    void shouldThrowExceptionWhenNotFound() {
        Mockito.when(repository.findById(this.user.getId())).thenReturn(Optional.empty());

        assertThrows(
                NotFoundException.class,
                () -> this.service.findById(this.user.getId())
        );
    }
}