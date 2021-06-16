package ar.com.frupp.cashapi.services;

import ar.com.frupp.cashapi.entities.Loan;
import ar.com.frupp.cashapi.entities.User;
import ar.com.frupp.cashapi.exceptions.NotFoundException;
import ar.com.frupp.cashapi.models.UserModel;
import ar.com.frupp.cashapi.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class UserServiceImplTest {

    private UserRepository repository;
    private UserService service;
    private User user;
    private int userId;

    @BeforeEach
    void setUp() {
        this.repository = Mockito.mock(UserRepository.class);
        this.service = new UserServiceImpl(this.repository);

        this.userId = 1;

        Loan loan = new Loan();
        this.user = new User();

        loan.setUser(this.user);
        this.user.setLoans(Collections.singleton(loan));

        loan.setTotal(2500.00);
        loan.setId(1);
        this.user.setId(this.userId);
        this.user.setEmail("user@test.com");
        this.user.setFirstName("Pepe");
        this.user.setLastName("Argento");
    }

    @Test
    void shouldFindUser() {
        int userId = this.user.getId();

        Mockito.when(this.repository.findById(userId)).thenReturn(Optional.ofNullable(this.user));

        User found = this.service.findById(userId);

        assertThat(found)
                .usingRecursiveComparison()
                .withStrictTypeChecking()
                .isEqualTo(found);
    }

    @Test
    void shouldThrowExceptionWhenNotFound() {
        Mockito.when(repository.findById(this.userId)).thenReturn(Optional.empty());

        assertThrows(
                NotFoundException.class,
                () -> this.service.findById(this.user.getId())
        );
    }

    @Test
    void shouldCreateUser() {
        UserModel model = new UserModel(
                null, "email@email.com", "nombre",
                "apellido", null
        );
        this.service.createUser(model);

        verify(this.repository, times(1)).save(Mockito.any(User.class));
    }

    @Test
    void shouldDeleteUser() {
        int id = 5;
        this.service.deleteUserById(id);

        verify(this.repository, times(1)).deleteById(id);
    }

    @Test
    void shouldThrowNotFoundWhenUserNotExists() {
        int id = 99;
        Mockito.when(this.repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(
                NotFoundException.class,
                () -> this.service.deleteUserById(id)
        );
    }

}