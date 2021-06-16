package ar.com.frupp.cashapi.services;

import ar.com.frupp.cashapi.entities.Loan;
import ar.com.frupp.cashapi.entities.User;
import org.springframework.stereotype.Service;


import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User findById(int userId) {
        Loan loan = new Loan();
        User user = new User();

        loan.setUser(user);
        user.setLoans(Collections.singleton(loan));

        loan.setTotal(2500.00);
        loan.setId(1);
        user.setId(userId);
        user.setEmail("user@test.com");
        user.setFirstName("Pepe");
        user.setLastName("Argento");

        return user;
    }
}
