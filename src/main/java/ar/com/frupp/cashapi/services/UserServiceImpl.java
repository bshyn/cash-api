package ar.com.frupp.cashapi.services;

import ar.com.frupp.cashapi.models.LoanModel;
import ar.com.frupp.cashapi.models.UserModel;
import org.springframework.stereotype.Service;


import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserModel findById(int userId) {
        LoanModel loanModel = new LoanModel("1", 2500, userId);
        return new UserModel(
                userId, "test@app.com.ar", "Pepe",
                "Argento", Collections.singletonList(loanModel)
        );
    }
}
